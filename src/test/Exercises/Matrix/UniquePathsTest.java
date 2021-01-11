package Exercises.Matrix;

import junit.framework.Assert;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class UniquePathsTest {

    @Test
    public void cannotFindUniquePathDueEntranceBlockedTest(){
        int[][] grid = {
                {1, 0},
                {0, 0}
        };
        assertEquals(getNumberOfUniquePaths(grid), -1);
    }

    @Test
    public void cannotFindUniquePathDueExitBlockedTest(){
        int[][] grid = {
                {0, 0},
                {0, 1}
        };
        assertEquals(getNumberOfUniquePaths(grid), -1);
    }

    @Test
    public void findUniquePath2x2(){
       int[][] grid = {
               {0, 0},
               {0, 0}
       };
       assertEquals(getNumberOfUniquePaths(grid), 2);
    }

    @Test
    public void findUniquePath3x3(){
        int[][] grid = {
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
        };
        assertEquals(getNumberOfUniquePaths(grid), 6);
    }

    @Test
    public void findUniquePath3x3WithCenterBlocked(){
        int[][] grid = {
                {0, 0, 0},
                {0, 1, 0},
                {0, 0, 0}
        };
        assertEquals(getNumberOfUniquePaths(grid), 2);
    }

    public int getNumberOfUniquePaths(int[][] grid){
        // Blocking the entrance or exit give us no possible paths
        if (grid[0][0] == 1
            || grid[grid.length - 1 ][grid[0].length - 1] == 1) return -1;

        // row, column
        int[][] pathGrid = new int[grid.length][grid[0].length];

        pathGrid[0][0] = 0;
        for (int row = 1; row < grid.length; ++row){
            pathGrid[row][0] = 1;
        }
        for (int col = 1; col < grid[0].length; ++col){
            pathGrid[0][col] = 1;
        }

        for(int row = 1; row < grid.length; ++row){
            for(int col = 1; col < grid[0].length; ++col){
                if (grid[row][col] == 0){
                    pathGrid[row][col] = pathGrid[row - 1][col]
                                        + pathGrid[row][col - 1];
                }else{
                    pathGrid[row][col] = 0;
                }
            }
        }
        return pathGrid[grid.length - 1][grid[0].length - 1];
    }

}
