package MultiThreading;

public class MatrixMultiplication {
    static int MAX_SIZE = 2;
    static int[][] first = new int[MAX_SIZE][MAX_SIZE];
    static int[][] second = new int[MAX_SIZE][MAX_SIZE];
    static final int[][] solution = new int[MAX_SIZE][MAX_SIZE];
    static int step = 0;

    public static void main(String[] args) {
        first = new int[][]{
                {1, 2},
                {3, 4}
        };

        second = new int[][]{
                {1, 2},
                {3, 4}
        };

        Thread[] thread = new Thread[MAX_SIZE * MAX_SIZE];
        int threadCount = 0;

        for (int i = 0; i < MAX_SIZE; i++) {
            for (int j = 0; j < MAX_SIZE; j++) {
                thread[threadCount] = new Thread(new Calculate(i, j));
                thread[threadCount].start();
                threadCount++;
            }
        }

        for (int i = 0; i < MAX_SIZE; i++) {
            try {
                thread[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        for (int i = 0; i < MAX_SIZE; i++) {
            for (int j = 0; j < MAX_SIZE; j++) {
                System.out.print(solution[i][j] + " ");
            }
            System.out.println();
        }


    }

    static class Calculate implements Runnable {
        int row;
        int col;

        public Calculate(int row, int col) {
            this.row = row;
            this.col = col;
        }


        @Override
        public void run() {
            for (int k = 0; k < MAX_SIZE; k++) {
                synchronized (solution) {
                    solution[row][col] += first[row][k] * first[k][col];
                }
            }

        }
    }
}

