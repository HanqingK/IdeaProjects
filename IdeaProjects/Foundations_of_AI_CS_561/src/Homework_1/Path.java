package Homework_1;

// path contains the information of the current node and the path to it
public class Path {
    int cost;
    int[] point;
    StringBuilder path;

    public Path(int[] point, StringBuilder path) {
        this.point = point;
        this.path = path;
    }

    public Path(int[] point, StringBuilder path, int cost) {
        this.point = point;
        this.path = path;
        this.cost += cost;
    }
}
