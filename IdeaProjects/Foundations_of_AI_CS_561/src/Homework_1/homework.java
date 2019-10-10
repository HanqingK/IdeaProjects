package Homework_1;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

// find a solution to the initial board (using the BFS or UCS or A* algorithm)
public class homework {

    private static Field terrain;
    private int[][] matrix;
    private int x;
    private int y;
    private int max;
    private List<int[]> target;
    private Set<String> targetSet;
    private HashMap<String, List<Integer>> hashMap;
    private int[][] direction;
    private int row;
    private int col;

    public homework() throws IOException {
        terrain = new Field();
        x = terrain.getY();
        y = terrain.getX();
        matrix = terrain.getMap();
        target = terrain.getTarget();
        targetSet = new HashSet<>();
        hashMap = new HashMap<>();
        //ArrayList<Integer> indices = new ArrayList<>();
        int i = 0;
        for (int[] cur : target) {
            String tar = cur[0] + "," + cur[1];
            targetSet.add(tar);
            if (!hashMap.containsKey(tar)) {
                ArrayList<Integer> indices = new ArrayList<>();
                indices.add(i);
                hashMap.put(tar, indices);
                i++;
            } else {
                List<Integer> newIndices = hashMap.get(tar);
                newIndices.add(i);
                hashMap.put(tar, newIndices);
                i++;
            }
        }
        max = terrain.getMaxGap();
        row = terrain.getH();
        col = terrain.getW();
        direction = new int[][]{{1, 0, 10}, {1, 1, 14}, {1, -1, 14}, {0, 1, 10}, {0, -1, 10}, {-1, 1, 14}, {-1, 0, 10}, {-1, -1, 14}};

    }

    private List<String> BreadthFirstSearch() {
        List<String> res = new ArrayList<>();
        int count = target.size();
        boolean[][] visited = new boolean[row][col];
        Queue<Path> queue = new LinkedList<>(); // store the nodes
        visited[x][y] = true;
        int[] landing = {x, y};
        StringBuilder sb = new StringBuilder();
        String start = y + "," + x;
        sb.append(start);
        for (int[] tar : target) {
            String end = tar[0] + "," + tar[1];
            if (end.equals(start)) {
                res.add(end);
            } else res.add(null);
        }
        Path path = new Path(landing, sb);
        queue.offer(path);
        while (!queue.isEmpty()) {
            Path cur = queue.poll();
            int[] curPosition = cur.point;
            StringBuilder curSb = cur.path;
            int curX = curPosition[0];
            int curY = curPosition[1];
            for (int[] dir : direction) {
                int newX = curX + dir[0];
                int newY = curY + dir[1];
                if (newX >= 0 && newX < row && newY >= 0 && newY < col && !visited[newX][newY]
                        && Math.abs(matrix[newX][newY] - matrix[curX][curY]) <= max) {
                    StringBuilder newSb = new StringBuilder(curSb.toString());
                    String s = newY + "," + newX;
                    visited[newX][newY] = true;
                    int[] newPosition = {newX, newY};
                    newSb.append(" " + s);
                    Path newPath = new Path(newPosition, newSb);
                    queue.offer(newPath);
                    if (targetSet.contains(s)) {
                        //count --;
                        List<Integer> j = hashMap.get(s);
                        if (j.size() == 1) {
                            int index = j.get(0);
                            res.set(index, newSb.toString());
                        } else {
                            for (int index : j) {
                                res.set(index, newSb.toString());
                            }
                        }
                        //return res;
                    }
                }
            }
        }
        return res;
    }

    private List<String> UniformCostSearch() {

        List<String> res = new ArrayList<>();
        int count = target.size();
        boolean[][] visited = new boolean[row][col];

        PriorityQueue<Path> queue = new PriorityQueue<Path>(new Comparator<Path>() {
            @Override
            public int compare(Path o1, Path o2) {
                return o1.cost - o2.cost;
            }
        });
        visited[x][y] = true;
        int[] landing = {x, y};
        StringBuilder sb = new StringBuilder();
        String start = y + "," + x;
        sb.append(start);
        for (int[] tar : target) {
            String end = tar[0] + "," + tar[1];
            if (end.equals(start)) {
                res.add(end);
            } else res.add(null);
        }
        Path path = new Path(landing, sb, 0); // cost for landing site is 0
        queue.offer(path);
        while (!queue.isEmpty()) {
            Path cur = queue.poll();
            int[] curPosition = cur.point;
            StringBuilder curSb = cur.path;
            int curCost = cur.cost;
            int curX = curPosition[0];
            int curY = curPosition[1];
            for (int[] dir : direction) {
                int newX = curX + dir[0];
                int newY = curY + dir[1];
                int newCost = curCost + dir[2];
                if (newX >= 0 && newX < row && newY >= 0 && newY < col && !visited[newX][newY]
                        && Math.abs(matrix[newX][newY] - matrix[curX][curY]) <= max) {
                    StringBuilder newSb = new StringBuilder(curSb.toString());
                    String s = newY + "," + newX;
                    visited[newX][newY] = true;
                    int[] newPosition = {newX, newY};
                    newSb.append(" " + s);
                    Path newPath = new Path(newPosition, newSb, newCost);
                    queue.offer(newPath);
                    if (targetSet.contains(s)) {
                        //count --;
                        List<Integer> j = hashMap.get(s);
                        if (j.size() == 1) {
                            int index = j.get(0);
                            res.set(index, newSb.toString());
                        } else {
                            for (int index : j) {
                                res.set(index, newSb.toString());
                            }
                        }
                    }
                }
            }
        }
        return res;
    }

    private List<String> AStarSearch() {
        List<String> res = new ArrayList<>(); // list contains the output result

        for (int[] i : target) {
            res.add(AStarHelper(i));
        }
        return res;
    }

    // return the path of specific target point
    private String AStarHelper(int[] oneEnd) {
        String outcome = null;
        boolean[][] visited = new boolean[row][col];
        PriorityQueue<Path> queue = new PriorityQueue<Path>(new Comparator<Path>() {
            @Override
            public int compare(Path o1, Path o2) {
                return o1.cost - o2.cost;
            }
        });
        visited[x][y] = true;
        int[] landing = {x, y};
        String start = y + "," + x;
        StringBuilder sb = new StringBuilder();
        sb.append(start);
        int Hcost = Manhattan(x, y, oneEnd);
        String point = oneEnd[0] + "," + oneEnd[1];
        if (point.equals(start)) {
            return point;
        }
        Path path = new Path(landing, sb, Hcost); // cost for landing site is 0
        queue.offer(path);
        while (!queue.isEmpty()) {
            Path cur = queue.poll();
            int[] curPosition = cur.point;
            StringBuilder curSb = cur.path;
            int curCost = cur.cost;
            int curX = curPosition[0];
            int curY = curPosition[1];
            for (int[] dir : direction) {
                int newX = curX + dir[0];
                int newY = curY + dir[1];
                int newCost = curCost + dir[2] + Manhattan(newX, newY, oneEnd);
                if (newX >= 0 && newX < row && newY >= 0 && newY < col && !visited[newX][newY]
                        && Math.abs(matrix[newX][newY] - matrix[curX][curY]) <= max) {
                    StringBuilder newSb = new StringBuilder(curSb.toString());
                    String s = newY + "," + newX;
                    visited[newX][newY] = true;
                    int[] newPosition = {newX, newY};
                    newSb.append(" " + s);
                    Path newPath = new Path(newPosition, newSb, newCost);
                    queue.offer(newPath);
                    if (s.equals(point)) {
                        outcome = newSb.toString();
                    }
                }
            }
        }
        return outcome;
    }

    // calculate the Manhattan distance, which is the steps need to go target in vertical
    // or horizontal direction
    // @parameter x y are the index of row and column
    private int Manhattan(int x, int y, int[] end) {
        int MCost = 0;
        if (target.contains(end)) {
            MCost = Math.abs(end[1] - x) + Math.abs(end[0] - y);
        }
        return MCost * 10;
    }


    // use specific algorithm to print out solution
    public static void main(String[] args) throws IOException {
        homework hw = new homework();
        String alg = terrain.getAlg();
        List<String> result = null;
        PrintWriter writer = new PrintWriter("output.txt");
        if (alg.equals("BFS")) {
            result = hw.BreadthFirstSearch();
        } else if (alg.equals("UCS")) {
            result = hw.UniformCostSearch();
        } else if (alg.equals("A*")) {
            result = hw.AStarSearch();
        }
        if (result == null) {
            writer.println("FAIL");
        } else {
            for (String s : result) {
                writer.println(Objects.requireNonNullElse(s, "FAIL"));
            }
        }
        writer.close();
    }
}


