package com.rick.struction.graph;

import com.rick.struction.collection.bag.Bag;
import com.rick.struction.graph.search.DepthFirstSearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;

/**
 * 无向图 基于邻接表的实现
 */
public class Graph {

    private final int V;        //顶点数目
    private int E;              //边的数目
    private static Bag<Integer>[] adj; //邻接表
    static BufferedReader reader;
    static Integer i;
    public static String paths = "graph/tinyG.txt";
    static {
        InputStream stream = Graph.class.getClassLoader().getResourceAsStream(paths);
        reader = new BufferedReader(new InputStreamReader(stream));
        try {
            i = Integer.valueOf(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Graph() throws Exception {
        this(i);
        String line;
        this.E = Integer.valueOf(reader.readLine());
        while ((line = reader.readLine()) != null) {
            String[] split = line.split(" ");
            addEdge(Integer.valueOf(split[0]), Integer.valueOf(split[1]));
        }
    }

    public Graph(int V) {
        this.V = V;
        this.E = 0;
        adj = new Bag[V];   //创建邻接表
        for (int i = 0; i < adj.length; i++) {  //将所有链表初始化为空
            adj[i] = new Bag<>();
        }
    }

    public static void main(String[] args) throws Exception {
        Graph graph = new Graph();
        for (int j = 0; j < adj.length; j++) {
            Iterable<Integer> adj = graph.adj(j);
            Iterator<Integer> it = adj.iterator();
            System.out.print(j+": ");
            while (it.hasNext()) {
                System.out.print(it.next() + " ");
            }
            System.out.println();
        }

        DepthFirstSearch search = new DepthFirstSearch(graph, 0);

    }

    public int E() {
        return E;
    }

    /**
     * 顶点数
     * @return
     */
    public int V() {
        return V;
    }

    /**
     * 添加连接
     * @param v
     * @param w
     */
    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
        E++;
    }

    /**
     * 和v相邻的所有顶点
     * @param v
     * @return
     */
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    /**
     * 计算v的度数
     * 度数：某个顶点的度数即为依附于它的边的总数
     * @param g
     * @param v
     * @return
     */
    public int degree(Graph g, int v) {
        int degree = 0;
        for (int w: g.adj(v)) {
            degree++;
        }
        return degree;
    }

    /**
     * 计算所有顶点的最大度数
     * @param g
     * @return
     */
    public int maxDegree(Graph g) {
        int max = 0;
        for (int i = 0; i < g.V(); i++) {
            if (degree(g, i) > max)
                max = degree(g, i);
        }
        return max;
    }

}
