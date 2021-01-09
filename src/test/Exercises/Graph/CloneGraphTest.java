package Exercises.Graph;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

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
    public void cloneGraphWithOneNeighbor(){
        GraphNode neighbor = new GraphNode(2);
        GraphNode graphNode = new GraphNode(1);
        graphNode.neighbords.add(neighbor);

        GraphNode graphClone = clone (graphNode);
        assertNotSame(graphNode, graphClone);
        assertEquals(graphNode.value, graphClone.value);
        assertNotSame(graphNode.neighbords.get(0), graphClone.neighbords.get(0));
        assertEquals(graphNode.neighbords.get(0).value, graphClone.neighbords.get(0).value);
    }

    @Test
    public void cloneGraphMultipleNeighborsTest(){
        GraphNode graph = createDefaultGraph();
        GraphNode clone = clone(graph);
        assertNotSame(graph, clone);

        Queue<GraphNode> graphQueue = new LinkedList<>();
        Queue<GraphNode> cloneQueue = new LinkedList<>();

        graphQueue.add(graph);
        cloneQueue.add(clone);

        Queue<GraphNode> discovered = new LinkedList<>();
        discovered.add(graph);
        discovered.add(clone);

        while(!graphQueue.isEmpty() && !cloneQueue.isEmpty()){
            GraphNode source = graphQueue.remove();
            GraphNode target = cloneQueue.remove();
            assertEquals(source.value, target.value);
            for(int i = 0; i < source.neighbords.size(); ++i){
                GraphNode sourceNeighbor = source.neighbords.get(i);
                if (!discovered.contains(sourceNeighbor)){
                    discovered.add(sourceNeighbor);
                    graphQueue.add(sourceNeighbor);
                }
                GraphNode targetNeighbor = target.neighbords.get(i);
                if (!discovered.contains(targetNeighbor)){
                    discovered.add(targetNeighbor);
                    cloneQueue.add(targetNeighbor);
                }
            }
        }


    }

    private GraphNode createDefaultGraph(){
        GraphNode graph = new GraphNode(1);
        GraphNode node2 = new GraphNode(2);
        GraphNode node3 = new GraphNode(3);
        GraphNode node4 = new GraphNode(4);

        graph.neighbords.add(node2);
        graph.neighbords.add(node4);

        node2.neighbords.add(node3);
        node2.neighbords.add(graph);

        node3.neighbords.add(node2);
        node3.neighbords.add(node4);

        node4.neighbords.add(node3);
        node4.neighbords.add(graph);

        return graph;
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


    static private GraphNode clone(GraphNode graphNode, Map<Integer, GraphNode> map){
        // We don't want to create a copy if we already have it
        if (map.containsKey(graphNode.value)){
            return map.get(graphNode.value);
        }

        GraphNode copy = new GraphNode(graphNode.value);
        map.put(graphNode.value, copy);

        // DFS
        for(GraphNode neighbor : graphNode.neighbords){
            copy.neighbords.add(clone(neighbor, map));
        }
        return copy;
    }

}