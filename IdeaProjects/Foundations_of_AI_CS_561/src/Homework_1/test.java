package Homework_1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class test {

    private int reverse(int x) {
        String s = Integer.toString(x);
        StringBuilder reverse = null;
        char[] chars = s.toCharArray();
        if (x >= 0) {
            for (int i = chars.length - 1; i > -1; i--) {
                reverse.append(chars[i]);
            }
        } else {
            reverse = new StringBuilder(String.valueOf(chars[0]));
            for (int i = chars.length - 1; i > 0; i--) {
                reverse = new StringBuilder(reverse.toString() +) char[i];
            }
        }
        return reverse.toString();
    }

    public boolean isPalindrome(int x) {
        String s = Integer.toString(x);
        char[] chars = s.toCharArray();
        int i = 0;
        int j = s.length();
        while (i < j) {
            if (chars[i] == chars[j]) {
                i++;
                j--;
            } else {
                return false;
            }

            Stack<String> stackOfCards = new Stack<>();
        }
        return true;


    }


}

    public static void main(String[] args) throws IOException {
        Field test = new Field();
        int[][] myMap = test.getMap();
        ArrayList<int[]> list = test.getTarget();
        System.out.println(test.getW() + " " + test.getH());
        System.out.println(test.getX() + " " + test.getY());
        System.out.println(test.getMaxGap());
        System.out.println(test.getNumOfTarget());
        for (int[] array : list) {
            System.out.println(Arrays.toString(array));
        }
        for (int i = 0; i < myMap.length; i++) {
            for (int j = 0; j < myMap[0].length; j++) {
                System.out.print(myMap[i][j] + " ");
            }
            System.out.println();
        }
    }
}
