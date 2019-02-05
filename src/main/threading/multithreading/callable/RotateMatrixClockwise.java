package threading.multithreading.callable;

import java.util.List;
import java.util.concurrent.Callable;

public class RotateMatrixClockwise implements Callable<Integer[][]>
{

	Integer[][] matrix;
	public RotateMatrixClockwise(Integer[][] matrix){
		this.matrix = matrix;
	}

	@Override
	public Integer[][] call() throws Exception
	{
		return rotateClockwise(matrix);
	}

	public Integer[][] rotateClockwise(Integer[][] matrix){
		Integer[][] result = new Integer[matrix[0].length][matrix.length];
		for(int i = 0; i < matrix[0].length; ++i){
			for(int j = 0; j < matrix.length; ++j){
				result[i][j] = matrix[matrix.length - j - 1][i];
			}
		}
		return result;
	}

}
