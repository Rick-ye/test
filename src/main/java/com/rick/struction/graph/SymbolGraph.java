package com.rick.struction.graph;

import com.rick.struction.search.BST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 二分图的性质自动完成了反向索引
 */
public class SymbolGraph {

    //符号表
    private BST<String, Integer> st;
    //反向索引
    private String[] keys;
    //无向图
    private Graph g;

    static BufferedReader reader;
    static BufferedReader gReader;
    public static String paths = "graph/routes.txt";

    static {
        InputStream stream = Graph.class.getClassLoader().getResourceAsStream(paths);
        reader = new BufferedReader(new InputStreamReader(stream));
        gReader = new BufferedReader(new InputStreamReader(Graph.class.getClassLoader().getResourceAsStream(paths)));;
    }
    public SymbolGraph(String sp) throws IOException {
        st = new BST<>();
        String line;
        //构造索引
        while ((line = reader.readLine()) != null) {
            String[] s = line.split(sp);
            //为每个不同的字符串关联一个索引
            for (int i = 0; i < s.length; i++) {
                if (st.get(s[i]) == null)
                    st.put(s[i], st.size());
            }
        }
        //用来获得顶点名的反向索引是一个索引
        keys = new String[st.size()];
        for (String name : st.keys()) {
            keys[st.get(name)] = name;
        }

        //构造图
        g = new Graph(st.size());
        while ((line = gReader.readLine()) != null) {
            String[] a = line.split(sp);
            Integer v = st.get(a[0]);
            for (int i = 0; i < a.length; i++) {
                g.addEdge(v, st.get(a[i]));
            }

        }
    }

    /**
     * key是一个顶点吗
     * @param s
     * @return
     */
    public boolean contains(String s) {
        return st.get(s) != null;
    }

    /**
     * key的索引
     * @param s
     * @return
     */
    public int index(String s) {
        return st.get(s);
    }

    /**
     * 索引v的顶点名
     * @param v
     * @return
     */
    public String name(int v) {
        return keys[v];
    }

    public Graph g() {
        return g;
    }

    public static void main(String[] args) throws IOException {
        SymbolGraph graph = new SymbolGraph(" ");
        Graph g = graph.g();
        for (int w : g.adj(graph.index("ATL"))) {
            System.out.println(graph.name(w) + ": " + w);
        }
    }

}
