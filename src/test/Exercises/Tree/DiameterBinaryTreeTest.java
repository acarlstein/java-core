package Exercises.Tree;

import org.junit.Assert;
import org.junit.Test;

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
        assertEquals(getDiameter(root), 2);
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

    public int getDiameter(Node root){
        if (root == null) return 0;
        int leftHeight = getDiameter(root.left);
        int rightHeight = getDiameter(root.right);
        return Math.max(leftHeight, rightHeight) + 1;
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
