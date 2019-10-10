package Homework_1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Field {
    private String alg;
    private final int[][] map;
    private final int W; // number of columns of the map
    private final int H; // number of rows of the map
    private final int X; // X coordinate of landing site
    private final int Y; // Y coordinate of landing site
    private final int maxGap; // maximum difference in elevation between two adjacent cells
    private final int numOfTarget;
    private final ArrayList<int[]> target = new ArrayList<>();


    // read the input and construct the map
    public Field() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(
                "C:\\Users\\Hanson\\IdeaProjects\\Foundations_of_AI_CS_561\\src\\Homework_1\\input1.txt"));
        alg = br.readLine(); // read the first line that represents algorithm
        String line = br.readLine(); // read the second line, size of the map
        W = helpReadLine(line)[0];
        H = helpReadLine(line)[1];
        map = new int[H][W];
        line = br.readLine(); // read the third line, landing site
        X = helpReadLine(line)[0];
        Y = helpReadLine(line)[1];
        if (X < 0 || X > W - 1 || Y < 0 || Y > H - 1) {
            throw new IllegalArgumentException();
        }
        line = br.readLine(); // read the fourth line, max difference evaluation
        maxGap = helpReadLine(line)[0];
        line = br.readLine(); // read the fifth line, number of target sites
        numOfTarget = helpReadLine(line)[0];
        // read the next few lines of the coordinate of target sites
        for (int i = 0; i < numOfTarget; i++) {
            line = br.readLine();
            int[] element = helpReadLine(line);
            if (element[0] < 0 || element[0] > W - 1 || element[1] < 0 || element[1] > H - 1) {
                throw new IllegalArgumentException();
            }
            target.add(element);
        }
        int lineCount = 0;
        while ((line = br.readLine()) != null) {
            String[] str = line.trim().split("\\s+");
            for (int i = 0; i < W; i++) {
                map[lineCount][i] = Integer.parseInt(str[i]);
            }
            lineCount++;
            if (lineCount >= H) {
                break;
            }
        }

    }

    String getAlg() {
        return alg;
    }

    public int getW() {
        return W;
    }

    public int getH() {
        return H;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    public int getNumOfTarget() {
        return numOfTarget;
    }

    public int[][] getMap() {
        return map;
    }

    public int getMaxGap() {
        return maxGap;
    }

    public ArrayList<int[]> getTarget() {
        return target;
    }

    // helper function that transfer each line into an integer array
    private static int[] helpReadLine(String temp) {
        String[] currentLine = temp.trim().split("\\s+");
        int[] arr = new int[currentLine.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(currentLine[i]);
        }
        return arr;
    }


}
