package com.rick.graph.search;

import com.rick.collection.queue.Queue;
import com.rick.collection.stack.linkedlist.Stack;
import com.rick.graph.Graph;

public class BreadthFirstPaths {

    private boolean[] marked;
    private int[] edgeTo;
    private final int s;

    public BreadthFirstPaths(Graph g, int s) {
        this.s = s;
        marked = new boolean[g.V()];
        edgeTo = new int[g.V()];
        bfs(g, s);
    }

    private void bfs(Graph g, int s) {
        Queue<Integer> queue = new Queue<>();
        marked[s] = true;       //标记起点
        queue.enqueue(s);       //将起点入列
        while (!queue.isEmpty()) {
            Integer v = queue.dequeue();//从队列中删去下一个顶点
            for (int w : g.adj(v)) {
                if (!marked[w]) {       //对于每个未被标记的相邻顶点
                    edgeTo[w] = v;      //保存最短路径的最后一条边
                    marked[w] = true;   //标记它，因为最短路径已知
                    queue.enqueue(w);   //并添加到队列
                }
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
}
