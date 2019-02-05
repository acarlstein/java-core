package threading.multithreading.callable;

public class MatrixUtils
{
	public static Integer[][] rotateClockwise(Integer[][] matrix){
		Integer[][] result = new Integer[matrix[0].length][matrix.length];
		for(int i = 0; i < matrix[0].length; ++i){
			for(int j = 0; j < matrix.length; ++j){
				result[i][j] = matrix[matrix.length - j - 1][i];
			}
		}
		return result;
	}

	public static Integer[][] rotateCounterClockwise(Integer[][] matrix){
		Integer[][] result = new Integer[matrix[0].length][matrix.length];
		for(int i = 0; i < matrix[0].length; ++i){
			for(int j = 0; j < matrix.length; ++j){
				result[i][j] = matrix[j][matrix[0].length - i - 1];
			}
		}
		return result;
	}

	public static boolean equal(Integer[][] left, Integer[][] right){
		int n = 3;
		for(int i = 0; i < n; ++i){
			for(int j = 0; j < n; ++j){
				if (left[i][j] != right[i][j]) return false;
			}
		}
		return true;
	}
}
