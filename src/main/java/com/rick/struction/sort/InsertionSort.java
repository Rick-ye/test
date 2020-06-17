package com.rick.struction.sort;

/**
 * 插入排序算法
 * 通常人们整理桥牌的方法是一张一张得来，将一张桥牌插入到其他已经有序的牌中的适当位置
 * 在计算机中，为了给要插入的元素腾出空间，我们需要将其余所有元素向右移动一位。这就是插入排序
 *
 * 总的来说，插入排序对于部分有序的数组十分高效，也很适合小规模数组
 */
public class InsertionSort extends Sort {
    /**
     * 旧版
     * @param a
     */
    /*@Override
    protected void sort(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            //将a[i]插入到a[i-1],a[i-2],a[i-3]...中
            for (int j = i; j > 0 && compare(a[j], a[j-1]); j--) {
                exch(j, j-1, a);
            }
        }
    }*/

    /**
     * 改进版，不需要交换元素
     * 在数组较大时，效率几乎提高了一倍
     * @param a
     */
    @Override
    public void sort(Comparable[] a){
        int N = a.length;
        for(int i=1;i<N;i++){
            Comparable temp = a[i];
            int j;
            for(j=i;j>0 && compare(temp, a[j-1]); j--){
                    a[j] = a[j-1];
            }
            a[j] = temp;

        }
    }

    /**
     * 数组局部插入排序
     * @param a
     * @param lo
     * @param hi
     */
    public void sort(Comparable[] a, int lo, int hi) {
        for (int i = lo; i <= hi; i++) {
            Comparable temp = a[i];
            int j;
            for (j = i; j > lo && compare(temp, a[j-1]); j--) {
                a[j] = a[j-1];
            }
            a[j] = temp;
        }
    }

    public static void main(String[] args) {
        //Comparable[] arr = {"r","d","t","s","w","a","c"};
        Comparable[] arr = { 49, 38, 65, 97, 76, 13, 27, 50 };
        new InsertionSort().sort(arr, 2, 5);
        show(arr);
    }



}
