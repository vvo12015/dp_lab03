package net.vrakin;

import java.math.BigInteger;
import java.util.Random;

public class Task02 {

    // Обчислення кількості шляхів (біноміальний коефіцієнт)
    public static BigInteger binomialCoefficient(int n, int k) {
        BigInteger result = BigInteger.ONE;
        for (int i = 0; i < k; i++) {
            result = result.multiply(BigInteger.valueOf(n - i))
                    .divide(BigInteger.valueOf(i + 1));
        }
        return result;
    }

    public static BigInteger countPaths(int n, int m) {
        return binomialCoefficient(n + m, m);
    }

    // Мінімальна вага маршруту (динамічне програмування)
    public static int findMinPath(int[][] grid, int n, int m) {
        int[][] dp = new int[n][m];
        dp[0][0] = grid[0][0];

        // Заповнення першого рядка
        for (int j = 1; j < m; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }

        // Заповнення першого стовпця
        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }

        // Заповнення решти таблиці
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1])) + grid[i][j];
            }
        }

        return dp[n - 1][m - 1]; // Мінімальна вага маршруту
    }

    public static void main(String[] args) {
        int[][] testCases = {
                {3, 3}, {5, 5}, {10, 10}, {10, 20},
                {20, 10}, {20, 20}, {50, 50}, {50, 100},
                {100, 50}, {100, 100}
        };

        Random rand = new Random();

        System.out.println("🔹 Тестування для задачі 2 (Кількість маршрутів)");
        for (int[] test : testCases) {
            int n = test[0], m = test[1];
            long start = System.nanoTime();
            BigInteger result = countPaths(n, m);
            long end = System.nanoTime();
            double timeMs = (end - start) / 1_000_000.0;

            System.out.println("Розмір поля: " + n + "x" + m);
            System.out.println("Кількість шляхів: " + result);
            System.out.println("Час виконання: " + timeMs + " мс\n");
        }

        System.out.println("\n🔹 Тестування для задачі 3 (Мінімальна вага маршруту)");
        for (int[] test : testCases) {
            int n = test[0], m = test[1];
            int[][] grid = new int[n][m];

            // Генерація випадкових чисел (1-100)
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    grid[i][j] = rand.nextInt(100) + 1;
                }
            }

            long start = System.nanoTime();
            int result = findMinPath(grid, n, m);
            long end = System.nanoTime();
            double timeMs = (end - start) / 1_000_000.0;

            System.out.println("Розмір поля: " + n + "x" + m);
            System.out.println("Мінімальна вага маршруту: " + result);
            System.out.println("Час виконання: " + timeMs + " мс\n");
        }
    }
}
