import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Iterator;
/*
import static java.lang.System.out;

public class Lab4Question3
{
    public class Bag<Item> implements Iterable<Item>
    {
        private Node first; // first node in list
        private class Node  // uses node to store items in bag
        {
            Item item;  // stores the item
            Node next;  // what the next item is so it can iterate
        }
        public void add(Item item)
        { // same as push() in Stack
            Node oldfirst = first;
            first = new Node();
            first.item = item;
            first.next = oldfirst;
        }
        public Iterator<Item> iterator()  //basic iterator
        { return new ListIterator(); }
        private class ListIterator implements Iterator<Item>  //specific iterator for this type
        {
            private Node current = first;
            public boolean hasNext()
            { return current != null; }
            public void remove() { }
            public Item next()
            {
                Item item = current.item;
                current = current.next;
                return item;
            }
        }
    }

    public class EdgeWeight
    {
        private int v;  //node v
        private int w;  //node w
        private int weight;  //weight of the connection

        public EdgeWeight(int v, int w, int weight)  //constructor
        {
            this.v = v;
            this.w = w;
            this.weight = weight;
        }

        public Integer v()
        {
            return (this.v);
        }

        public Integer w()
        {
            return (this.w);
        }

        public int weight()
        {
            return (weight);
        }
    }

    public class Graph
    {
        private final int V; // number of vertices
        private int E; // number of edges
        private Bag<Integer>[] adj; // adjacency lists
        private Bag<EdgeWeight>[] wei;
        public Graph(int V)
        {
            this.V = V; this.E = 0;
            adj = (Bag<Integer>[]) new Bag[V]; // Create array of lists.
            wei = (Bag<EdgeWeight>[]) new Bag[V];
            for (int v = 0; v < V; v++) // Initialize all lists
            {
                adj[v] = new Bag<Integer>(); // to empty.
                wei[v] = new Bag<EdgeWeight>();
            }
        }

        public int V() { return V; }
        public int E() { return E; }

        public void addEdge(int v, int w, int weight)
        {
            EdgeWeight ew = new EdgeWeight(v, w, weight);
            adj[v].add(w); // Add w to v’s list.
            adj[w].add(v); // Add v to w’s list.
            wei[v].add(ew);
            wei[w].add(ew);
            E++;
        }

        public Iterable<EdgeWeight> weight(int v)
        {
            return (wei[v]);
        }

        public Iterable<Integer> adj(int v)
        { return adj[v]; }
    }

    public class Queue<Item> implements Iterable<Item>
    {  // oldest(first)-->next-->next-->newest(last)-->null
        private Node first; // link to least recently added node
        private Node last; // link to most recently added node
        private int N; // number of items on the queue
        private class Node
        { // nested class to define nodes
            Item item;
            Node next;
        }
        public boolean isEmpty() { return first == null; } // Or: N == 0.
        public int size() { return N; }
        public void enqueue(Item item)
        { // Add item to the end of the list.
            Node oldlast = last;
            last = new Node();
            last.item = item;
            last.next = null;
            if (isEmpty()) first = last;
            else oldlast.next = last;
            N++;
        }
        public Item dequeue()
        { // Remove item from the beginning of the list.
            Item item = first.item;
            first = first.next;
            if (isEmpty()) last = null;
            N--;
            return item;
        }

        public Iterator<Item> iterator()
        { return new ListIterator(); }
        private class ListIterator implements Iterator<Item>
        {
            private Node current = first;
            public boolean hasNext()
            { return current != null; }
            public void remove() { }
            public Item next()
            {
                Item item = current.item;
                current = current.next;
                return item;
            }
        }
// See page 155 for iterator() implementation.
// See page 150 for test client main().
    }

    public class Stack<Item> implements Iterable<Item>
    {  //newest(first)-->next-->next-->null
        private Node first; // top of stack (most recently added node)
        private int N; // number of items
        private class Node
        { // nested class to define nodes
            Item item;
            Node next;
        }
        public boolean isEmpty() { return first == null; } // Or: N == 0.
        public int size() { return N; }
        public void push(Item item)
        { // Add item to top of stack.
            Node oldfirst = first;
            first = new Node();
            first.item = item;
            first.next = oldfirst;
            N++;
        }
        public Item pop()
        { // Remove item from top of stack.
            Item item = first.item;
            first = first.next;
            N--;
            return item;
        }

        public Iterator<Item> iterator()  //basic iterator
        { return new ListIterator(); }
        private class ListIterator implements Iterator<Item>  //specific iterator for this type
        {
            private Node current = first;
            public boolean hasNext()
            { return current != null; }
            public void remove() { }
            public Item next()
            {
                Item item = current.item;
                current = current.next;
                return item;
            }
        }
// See page 155 for iterator() implementation.
// See page 147 for test client main().
    }

    public class BreadthFirstPaths
    {
        private boolean[] marked; // Is a shortest path to this vertex known?
        private int[] edgeTo; // last vertex on known path to this vertex
        private final int s; // source
        public BreadthFirstPaths(Graph G, int s)
        {
            marked = new boolean[G.V()];
            edgeTo = new int[G.V()];
            this.s = s;
            bfs(G, s);
        }
        private void bfs(Graph G, int s)
        {
            Queue<Integer> queue = new Queue<Integer>();
            marked[s] = true; // Mark the source
            queue.enqueue(s); // and put it on the queue.
            while (!queue.isEmpty())
            {
                int v = queue.dequeue(); // Remove next vertex from the queue.
                for (Integer w : G.adj(v))
                    if (!marked[w]) // For every unmarked adjacent vertex,
                    {
                        edgeTo[w] = v; // save last edge on a shortest path,
                        marked[w] = true; // mark it because path is known,
                        queue.enqueue(w); // and add it to the queue.
                    }
            }
        }
        public boolean hasPathTo(int v)
        { return marked[v]; }

        public Iterable<Integer> pathTo(int v)
        {
            if (!hasPathTo(v)) return null;
            Queue<Integer> path = new Queue<Integer>();
            for (int x = v; x != s; x = edgeTo[x])
                path.enqueue(x);
            path.enqueue(s);
            return path;
        }
// Same as for DFS (see page 536).
    }

    public class DijkstraSP
    {
        private EdgeWeight[] edgeTo;
        private double[] distTo;
        private IndexMinPQ<Double> pq;
        public DijkstraSP(Graph G, int s)
        {
            edgeTo = new EdgeWeight[G.V()];
            distTo = new double[G.V()];
            pq = new IndexMinPQ<Double>(G.V());
            for (int v = 0; v < G.V(); v++)
                distTo[v] = Double.POSITIVE_INFINITY;
            distTo[s] = 0.0;
            pq.insert(s, 0.0);
            while (!pq.isEmpty())
                relax(G, pq.delMin())
        }
        private void relax(Graph G, int v)
        {
            for(EdgeWeight e : G.weight(v))
            {
                int w = e.to();
                if (distTo[w] > distTo[v] + e.weight())
                {
                    distTo[w] = distTo[v] + e.weight();
                    edgeTo[w] = e;
                    if (pq.contains(w)) pq.change(w, distTo[w]);
                    else pq.insert(w, distTo[w]);
                }
            }
        }
        public double distTo(int v) // standard client query methods
        public boolean hasPathTo(int v) // for SPT implementatations
        public Iterable<Edge> pathTo(int v) // (See page 649.)
    }

    /*
    public class DijkstraSP
    {
        final int source;
        private boolean[] mark;
        private int[] shortest;

        public DijkstraSP(Graph g, int s)
        {
            this.source = s;
            shortestPath(g, s, 0);
            mark = new boolean[g.V()];
            shortest = new int[g.V()];
        }

        public void shortestPath(Graph g, int v, int total)
        {
            Queue<Integer> queue1 = new Queue<Integer>();
            Stack<Integer> stack1 = new Stack<Integer>();
            Queue<Integer> queue2 = new Queue<Integer>();
            Iterable adj = g.adj(v);
            Iterable wei = g.weight(v);
            int w;

            for (Object o : adj)
            {
                w = (Integer) o;
                queue1.enqueue(w);
                queue2.enqueue(w);
            }


        }
    }*/
/*
    public static void main(String[] args) throws IOException
    {
        Scanner in = new Scanner(new File("Text.txt"));  //input file
        Lab4Question3 lab = new Lab4Question3();  //instantiate class
        int limit = 49;  //limit of input for array
        String[] indexName = new String[limit];  //array of states with index as input (symbol table)
        Lab4Question3.Graph graph = lab.new Graph(limit);  //instantiate inner class
        int count = 0;  //left input marker
        int count2 = 0;  //right input marker
        int counter = 0;  //array counter
        int state1 = 0;  //int for the left input state
        int state2 = 0;  //int for the right input state
        int weight = 1;

        while (in.hasNext())
        {
            String[] inputs = in.nextLine().split(" ");
            for (int i = 0; i < indexName.length; i++)
            {
                if (inputs[0].equals(indexName[i]))
                {
                    count++;
                }
                if (inputs[1].equals(indexName[i]))
                {
                    count2++;
                }
            }
            if (count == 0)
            {
                indexName[counter] = inputs[0];
                //state1 = counter;
                counter++;
            }
            if (count2 == 0)
            {
                indexName[counter] = inputs[1];
                //state2 = counter;
                counter++;
            }
            count2 = 0;
            count = 0;

            for (int i = 0; i < counter; i++)
            {
                if (inputs[0].equals(indexName[i]))
                {
                    state1 = i;
                }
                if (inputs[1].equals(indexName[i]))
                {
                    state2 = i;
                }
            }

            graph.addEdge(state1, state2, weight);
            weight++;
        }

        Iterable[] iter = new Iterable[limit];
        int edgeCounter = 0;
        int stateCounter = 0;

        for (int i = 0; i < limit; i++)
        {
            iter[i] = graph.adj(i);
        }

        for (Iterable i : iter)
        {
            out.println("Borders of: " + indexName[stateCounter]);
            for (Object j : i)
            {
                //out.println(j.toString());
                //out.println(indexName[Integer.getInteger(j.toString())]);
                out.println(indexName[(Integer) (j)]);
                edgeCounter++;
            }
            stateCounter++;
            out.println();
        }
        out.println("vertices " + graph.V());
        out.println("edges " + graph.E());

        //out.println(edgeCounter);

        Scanner sc = new Scanner(System.in);
        out.println("Shortest state state search");
        out.println("Input state x state y with a space in the middle");
        String input = sc.nextLine();
        String[] inputSplit = input.split(" ");

        for (int i = 0; i < limit; i++)
        {
            if (inputSplit[0].equals(indexName[i]))
            {
                state1 = i;
            }
            if (inputSplit[1].equals(indexName[i]))
            {
                state2 = i;
            }
        }

        Lab4Question3.BreadthFirstPaths bfp = lab.new BreadthFirstPaths(graph, state1);

        Iterable path = bfp.pathTo(state2);

        for (Object i : path)
        {
            out.println(indexName[(Integer) (i)]);
        }

    }
}*/



