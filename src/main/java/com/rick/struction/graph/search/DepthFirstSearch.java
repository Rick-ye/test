package com.rick.struction.graph.search;

import com.rick.struction.graph.Graph;

/**
 * 深度优先搜索
 */
public class DepthFirstSearch {
    private boolean[] marked;   //标记已走过的顶点
    private int count;          //走过的顶点的个数


    public DepthFirstSearch(Graph g, int s) {
        marked = new boolean[g.V()];
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
        count++;
        for (int w : g.adj(v)) {
            if (!marked[w]) {
                dfs(g, w);
            }
        }
    }

    public boolean marked(int w) {
        return marked[w];
    }

    public int count() {
        return count;
    }

}
