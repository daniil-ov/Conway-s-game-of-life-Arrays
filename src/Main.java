import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;
import java.util.Arrays;
import java.util.Random;

public class Main {

    public static final Integer SIZE_X = 10;
    public static final Integer SIZE_Y = 10;

    static int[][] currentLife = new int[SIZE_Y + 2][SIZE_X + 2];
    static int[][] nextGeneration = new int[SIZE_Y + 2][SIZE_X + 2];

    public static void main(String[] args) {

        StdDraw.setCanvasSize(600, 600);
        StdDraw.setXscale(0, SIZE_X + 2);
        StdDraw.setYscale(0, SIZE_Y + 2);
        StdDraw.setPenColor(Color.BLACK);

        createTable();
        run();
    }

    private static void createTable() {

        Random rnd = new Random();

        for (int y = 1; y < SIZE_Y + 1; y++) {

            for (int x = 1; x < SIZE_X + 1; x++) {
                currentLife[y][x] = rnd.nextInt(2);
            }
        }
    }

    public static void run() {

        //вывод в консоль начального массива
        for (int i = 0; i < currentLife.length; i++) {
            System.out.println(Arrays.toString(currentLife[i]));
        }

        while (true) {
            drawTable(currentLife);
            change(currentLife, nextGeneration);
        }
    }


    public static void drawTable(int[][] array) {

        StdDraw.clear(Color.WHITE);

        for (int y = 1; y < SIZE_Y + 1; y++) {

            for (int x = 1; x < SIZE_X + 1; x++) {

                if (array[y][x] == 1) {
                    StdDraw.filledSquare(x + 1, SIZE_Y - y + 2, 0.5);
                }
            }
        }
    }

    public static int neighbours(int y, int x, int[][] array) {
        int neighbours = 0;

        if ((y > 0) && (y < SIZE_Y + 1) && (x > 0) && (x < SIZE_X + 1)) {

            neighbours = array[y][x + 1] + array[y][x - 1] + array[y + 1][x] + array[y - 1][x]
                    + array[y + 1][x - 1] + array[y - 1][x - 1] + array[y - 1][x + 1] + array[y + 1][x + 1];
        }

        return neighbours;
    }

    public static void change(int[][] array1, int[][] array2) {

        int[][] tmp;

        for (int y = 1; y < SIZE_Y + 1; y++) {

            for (int x = 1; x < SIZE_X + 1; x++) {
                int nb = neighbours(y, x, array1);

                if (((nb == 2) || (nb == 3)) && (array1[y][x] == 1)) {
                    array2[y][x] = 1;
                } else if (nb == 3) {
                    array2[y][x] = 1;
                } else {
                    array2[y][x] = 0;
                }
            }
        }

        tmp = currentLife;
        currentLife = nextGeneration;
        nextGeneration = tmp;
    }
}