package Exercises.Graph;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class CloneGraphTest {

    @Test
    public void createGraphNode(){
        GraphNode gn = new GraphNode();
        assertEquals(gn.value, 0);
        assertThat(gn.neighbords, instanceOf(List.class));
        assertEquals(gn.neighbords.size(), 0);
    }

    @Test
    public void createGraphNodeWithValue(){
        GraphNode gn = new GraphNode(1);
        assertEquals(gn.value, 1);
        assertThat(gn.neighbords, instanceOf(List.class));
        assertEquals(gn.neighbords.size(), 0);
    }

    @Test
    public void createGraphNodeWithValueAndNeibords(){
        List<GraphNode> list = new ArrayList<>();
        GraphNode newNode = new GraphNode(2);
        list.add(newNode);

        GraphNode gn = new GraphNode(1, list);
        assertEquals(gn.value, 1);
        assertThat(gn.neighbords, instanceOf(List.class));
        assertEquals(gn.neighbords.size(), 1);
        assertEquals(gn.neighbords.get(0), newNode);
    }

    @Test
    public void cannotCloneIfNull(){
        assertNull(clone(null));
    }

    @Test
    public void cloneGraphNode(){
        GraphNode graphNode = new GraphNode(1);
        GraphNode graphClone = clone (graphNode);
        assertNotSame(graphNode, graphClone);
    }

    @Test
    public void cloneGraphNodeWithOneNeighbor(){
        GraphNode neighbor = new GraphNode(2);
        GraphNode graphNode = new GraphNode(1);
        graphNode.neighbords.add(neighbor);

        GraphNode graphClone = clone (graphNode);
        assertNotSame(graphNode, graphClone);
        assertEquals(graphNode.value, graphClone.value);
        assertNotSame(graphNode.neighbords.get(0), graphClone.neighbords.get(0));
        assertEquals(graphNode.neighbords.get(0).value, graphClone.neighbords.get(0).value);
    }

    static class GraphNode  {
        public int value;
        public List<GraphNode> neighbords;

        public GraphNode(){
            this(0);
        }

        public GraphNode(int value){
            this(value, new ArrayList<GraphNode>());
        }

        public GraphNode(int value, List<GraphNode> neighbords){
            this.value = value;
            this.neighbords = neighbords;
        }
    }

    static public GraphNode clone(GraphNode graphNode){
        if (graphNode == null) return null;
        Map<Integer, GraphNode> map = new HashMap<>();
        return clone(graphNode, map);
    }

    // We don't want to copy a node if we already have it
    static private GraphNode clone(GraphNode graphNode, Map<Integer, GraphNode> map){
        if (map.containsKey(graphNode.value)){
            return map.get(graphNode.value);
        }
        GraphNode copy = new GraphNode(graphNode.value);
        map.put(graphNode.value, copy);
        for(GraphNode neighbor : graphNode.neighbords){
            copy.neighbords.add(clone(neighbor));
        }
        return copy;
    }

}