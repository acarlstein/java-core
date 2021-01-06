package Exercises.Matrix;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;


public class RotateTest extends TestCase {

    public static final int[][] DEFAULT_MATRIX_2D = {
        {1, 2},
        {3, 4}
    };

    public static final int[][] DEFAULT_MATRIX_3D = {
        {1, 2, 3},
        {4, 5, 6},
        {7, 8, 9}
    };

    int[][] matrix2D;
    int[][] matrix3D;

    @Override
    protected void setUp()
            throws Exception {
        super.setUp();
        matrix2D = cloneMatrix(DEFAULT_MATRIX_2D);
        matrix3D = cloneMatrix(DEFAULT_MATRIX_3D);
    }

    protected int[][] cloneMatrix(int[][] matrix){
        return Arrays.stream(matrix)
                .map((int[] row) -> row.clone()) // Map each row of the matrix
                .toArray((int length) -> new int[length][]);
    }

    @Override
    protected void tearDown()
            throws Exception {
        super.tearDown();
        matrix2D = null;
    }

    @Test
    public void test2DMatrix(){
        int row = 0, col = 0;
        assertEquals(matrix2D[row][col], 1);
        assertEquals(matrix2D[row][col + 1], 2);
        assertEquals(matrix2D[row + 1][col], 3);
        assertEquals(matrix2D[row + 1][col + 1], 4);
    }

    @Test
    public void test3DMatrix(){
        int row = 0, col = 0;
        assertEquals(matrix3D[row][col], 1);
        assertEquals(matrix3D[row][col + 1], 2);
        assertEquals(matrix3D[row][col + 2], 3);
        assertEquals(matrix3D[row + 1][col], 4);
        assertEquals(matrix3D[row + 1][col + 1], 5);
        assertEquals(matrix3D[row + 1][col + 2], 6);
        assertEquals(matrix3D[row + 2][col], 7);
        assertEquals(matrix3D[row + 2][col + 1], 8);
        assertEquals(matrix3D[row + 2][col + 2], 9);
    }

    @Test
    public void testRotate90Clockwise(){
        int[][] result2D = rotate90Clockwise(matrix2D);
        int [][] compareWithMatrix2D = {
            {3, 1},
            {4, 2}
        };
        assertArrayEquals("Failed to rotate 2D array", result2D, compareWithMatrix2D );

        int[][] result3D = rotate90Clockwise(matrix3D);
        int[][] compareWithMatrix3D = {
            {7, 4, 1},
            {8, 5, 2},
            {9, 6, 3}
        };
        assertArrayEquals("Failed to rotate 3D array", result3D, compareWithMatrix3D );
    }

    protected int[][] rotate90Clockwise(int[][] matrix){
        int last = matrix.length - 1;
        int numberOfLevels = matrix.length / 2;
        int level = 0;
        while (level < numberOfLevels){
            for (int i = level; i < last; ++i){
                swap(matrix, level, i, i, last);
                swap(matrix, level, i, last, last - i + level);
                swap(matrix, level, i, last - i + level, level);
            }
            ++level;
            ++last;
        }
        return matrix;
    }

    @Test
    public void testSwap(){
        int row = 0, col = 0;
        swap(matrix2D, row, col, row, col + 1);
        swap(matrix2D, row + 1, col, row + 1, col + 1);

        int[][] compareWithMatrix2D = {
            {2, 1},
            {4, 3}
        };
        assertArrayEquals(matrix2D, compareWithMatrix2D);
    }

    protected void swap(int[][] matrix, int row, int col, int row2, int col2){
        int temp = matrix[row][col];
        matrix[row][col] = matrix[row2][col2];
        matrix[row2][col2] = temp;
    }

}
