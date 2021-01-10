package Exercises.Graph;

import org.junit.Test;

public class APathFindingTest {

    @Test
    public void pathFindingTest() {
        // openNodes: Set of nodes to be evaluated
        // closedNodes: Set of nodes already evaluated
        // Add the start node to openNodes
        // Loop
        //   currentNode = node in openNodes with lowest f_cost
        //   remove currentNode from openNodes
        //   add currentNode to closedNodes
        //   if currentNode is the targetNode
        //     return
        //   foreach neighbor of the currentNode
        //     if (neighbor is not traversable
        //        or neighbour is not in openNodes) then
        //       set f_cost of neighbour
        //       set parentNode of neighbour to currentNode
        //       if neighbour is not in openNodes
        //         add neighbour to openNodes

    }
}
