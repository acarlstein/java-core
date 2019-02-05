package threading.multithreading.callable;

import java.util.concurrent.Callable;

public class RotateMatrixCounterClockwise implements Callable<Integer[][]>
{

	Integer[][] matrix;
	public RotateMatrixCounterClockwise(Integer[][] matrix){
		this.matrix = matrix;
	}

	@Override
	public Integer[][] call() throws Exception
	{
		return rotateCounterClockwise(matrix);
	}

	public Integer[][] rotateCounterClockwise(Integer[][] matrix){
		Integer[][] result = new Integer[matrix[0].length][matrix.length];
		for(int i = 0; i < matrix[0].length; ++i){
			for(int j = 0; j < matrix.length; ++j){
				result[i][j] = matrix[j][matrix[0].length - i - 1];
			}
		}
		return result;
	}

}
