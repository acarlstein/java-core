package Exercises.Tree;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.assertEquals;

public class DiameterBinaryTreeTest {

    @Test
    public void DiameterOfEmptyTree() {
        assertEquals(getDiameter(null), 0);
    }

    @Test
    public void DiameterOfTreeWithOneLeftNode(){
        Node root = new Node(1);
        assertEquals(getDiameter(root), 1);
    }

    @Test
    public void DiameterOfTreeRootAndLeftNodes(){
        Node root = new Node(1);
        root.left = new Node(2);
        assertEquals(getDiameter(root), 2);
    }

    @Test
    public void DiameterOfTreeRootAndRightNodes(){
        Node root = new Node(1);
        root.right = new Node(2);
        assertEquals(getDiameter(root), 2);
    }

    @Test
    public void DiameterOfTreeRootLeftRightNodes(){
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        assertEquals(getDiameter(root), 3);
    }

    @Test
    public void DiameterOfTreeLongerLeft(){
        Node root = new Node(1);
        root.left = new Node (2);
        root.left.left = new Node (3);
        assertEquals(getDiameter(root), 3);
    }

    @Test
    public void DiameterOfTreeLongerRight(){
        Node root = new Node(1);
        root.right = new Node (2);
        root.right.right = new Node (3);
        assertEquals(getDiameter(root), 3);
    }

    @Test
    public void DiameterOfTreeFirstCase(){
        Node root = new Node(1);
        root.left = new Node(2);
        root.left.left = new Node(4);
        root.right = new Node(3);
        assertEquals(getDiameter(root), 4);
    }

    @Test
    public void DiameterOfTreeSecondCase(){
        Node root = new Node(1);
        root.right = new Node(3);
        root.right.left = new Node(4);
        root.right.left.right = new Node(6);
        root.right.left.right.right = new Node(10);
        root.right.right = new Node(5);
        root.right.right.left  = new Node(7);
        assertEquals(getDiameter(root), 6);
    }

    @Test
    public void DiameterOfTreeThirdCase(){

    }

    public int getDiameter(Node root){
        AtomicInteger countNodes = new AtomicInteger(0);
        getDiameter(root, countNodes);
        return countNodes.get();
    }

    private synchronized int getDiameter(Node root, AtomicInteger countNodes){
        if (root == null) return 0;
        int leftNodes = getDiameter(root.left, countNodes);
        int rightNodes = getDiameter(root.right, countNodes);
        int numberOfNodes = leftNodes + rightNodes + 1;
        countNodes.set(Math.max(countNodes.get(), numberOfNodes));
        return Math.max(leftNodes, rightNodes) + 1;
    }

    class Node {
        int value;
        Node left, right;
        Node(int value){
            this.value = value;
            left = null;
            right = null;
        }
    }

}
