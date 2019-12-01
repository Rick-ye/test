package com.rick.graph;

import com.rick.collection.bag.Bag;

/**
 * 基于深度优先搜索解决图连通性问题
 */
public class CC {

    private boolean[] marked;   //标记已走过的顶点

    private int[] id;           //将顶点关联连通分量标识符

    private int count;          //连通分量标识符

    public CC(Graph g) {
        marked = new boolean[g.V()];
        id = new int[g.V()];
        for (int s = 0; s < g.V(); s++)
            if (!marked[s]) {
                dfs(g, s);
                count++;
            }
    }

    private void dfs(Graph g, int s) {
        marked[s] = true;
        id[s] = count;
        for (int w : g.adj(s))
            if (!marked[w])
                dfs(g, w);
    }

    public boolean connected(int w, int v) {
        return id[w] == id[v];
    }

    public int id(int w) {
        return id[w];
    }

    public int getCount() {
        return count;
    }

    public static void main(String[] args) throws Exception {
        Graph g = new Graph();
        CC cc = new CC(g);
        int m = cc.getCount();
        Bag<Integer>[] bags = new Bag[m];
        for (int j = 0; j < m; j++) {
            bags[j] = new Bag<>();
        }
        for (int i = 0; i < g.V(); i++) {
            bags[cc.id[i]].add(i);
        }
        for (int i = 0; i < m; i++) {
            for (int w : bags[i]) {
                System.out.print(w+" ");
            }
            System.out.println();
        }
    }

}

