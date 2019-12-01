package com.rick.graph.search;

import com.rick.collection.stack.linkedlist.Stack;
import com.rick.graph.Graph;

/**
 * 深度优先搜索
 */
public class DepthFirstPaths {
    private boolean[] marked;   //标记已走过的顶点
    private int[] edgeTo;       //从起点到一个顶点的已知路径上的最后一个顶点
    private final int s;        //起点

    public DepthFirstPaths(Graph g, int s) {
        marked = new boolean[g.V()];
        edgeTo = new int[g.V()];
        this.s = s;
        dfs(g, s);
    }

    /**
     * 递归搜索
     * 1.标记所有第一次通过的路口和通道
     * 2.当来到一个标记过得路口时回退到上个路口
     * 3.当退回到的路口已没有可走的路时继续回退
     * @param g
     * @param v
     */
    public void dfs(Graph g, int v) {
        marked[v] = true;
        for (int w : g.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(g, w);
            }
        }
    }

    public boolean hasPathTo(int w) {
        return marked[w];
    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<>();
        for (int x = v; x != s; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(s);
        return path;
    }

    public static void main(String[] args) {
        try {
            Graph graph = new Graph();

            int s = 0;
            DepthFirstPaths paths = new DepthFirstPaths(graph, s);
            for (int v = 0; v < graph.V(); v++) {
                System.out.print(s + " to " + v + ": ");
                if (paths.hasPathTo(v)) {
                    for (int x : paths.pathTo(v))
                        if (x == s) System.out.print(x);
                        else System.out.print("-" + x);
                }
                System.out.println();
            }
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
