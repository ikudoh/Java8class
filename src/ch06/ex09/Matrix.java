package ch06.ex09;

import java.util.Arrays;

public class Matrix {
	private double[][] matrix;
	private int size = 2;
	public Matrix() {
		matrix = new double[size][size];
		// |1 1|
		// |1 0|の行列
		matrix[0][0] = 1;
		matrix[0][1] = 1;
		matrix[1][0] = 1;
		matrix[1][1] = 0;
	}

	public double getFibonacciNumber() {
		return matrix[0][0];
	}

	// 行列の累乗を求める
	public Matrix multiply(Matrix target) {
        Matrix ret = new Matrix();
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
            	// 値格納場所
            	ret.matrix[x][y] = 0;
                for (int i = 0; i < size; i++) {
                    ret.matrix[x][y]
                    		= ret.matrix[x][y]	// ex. i=1->a*eの結果
                    				// |a b||e f|　　　|ae+bg af+bh|
                    				// |c d||g h|　→　|ce+dg cf+dh|
                    		+ matrix[x][i] * target.matrix[i][y];	// ex. i=0->a*e, i=1->b*g
                }
            }
        }
        return ret;
	}

	public static void main(String[] args) {
		Matrix matrix = new Matrix();
		Matrix[] array = new Matrix[10];
		// 行列の行列を作成(0-10までmatrixを入れる)
		Arrays.parallelSetAll(array, i -> matrix);
		// 行列の乗算
		Arrays.parallelPrefix(array, Matrix::multiply);
		for(Matrix m : array) {
			System.out.println(m.getFibonacciNumber());
		}
	}
}
