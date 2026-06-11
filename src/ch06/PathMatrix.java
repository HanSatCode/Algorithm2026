package ch06;

public class PathMatrix {
    public static int maxMatrix(int[][] matrix) {
        int N = matrix.length;
        int[][] result = new int[N][N]; result[0][0] = matrix[0][0];

        for(int i = 1; i < N; i++) {
            result[0][i] = result[0][i - 1] + matrix[0][i];
            result[i][0] = result[i - 1][0] + matrix[i][0];
        }

        for(int i = 1; i < N; i++) {
            for(int j = 1; j < N; j++) {
                result[i][j] = Math.max(result[i- 1][j], result[i][j - 1]) + matrix[i][j];
            }
        }

        return result[N - 1][N - 1];
    }

    public static int minMatrix(int[][] matrix) {
        int N = matrix.length;
        int[][] result = new int[N][N]; result[0][0] = matrix[0][0];

        for(int i = 1; i < N; i++) {
            result[0][i] = result[0][i - 1] + matrix[0][i];
            result[i][0] = result[i - 1][0] + matrix[i][0];
        }

        for(int i = 1; i < N; i++) {
            for(int j = 1; j < N; j++) {
                result[i][j] = Math.min(result[i- 1][j], result[i][j - 1]) + matrix[i][j];
            }
        }

        return result[N - 1][N - 1];
    }

    public static void main(String[] args) {
        int[][] input = { {6, 7, 12, 5},    // 강의자료 예시
                          {5, 3, 11, 18},
                          {7, 17, 3, 3},
                          {8, 10, 14, 9} };
        
        System.out.println("최대 경로의 합 : " + maxMatrix(input));
        System.out.println("최소 경로의 합 : " + minMatrix(input));
    }
}