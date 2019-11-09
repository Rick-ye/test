package com.rick.sort.heap;

public class PQ {

//    protected static char randomChar() {
//        Character[] c = {'A','B','C','D','E','F','G','H','I','J','K','V','L','M','N',
//                'O','P','Q','R','S','T','U','V','W','X','Y','Z'};
//        Random random = new Random();
//        int i = random.nextInt(26);
//        return c[i];
//    }

    static int i = 0;

    protected static char sortChar() {
        Character[] c = {'A','B','C','D','E','F','G','H','I','J','K','V','L','M','N',
                'O','P','Q','R','S','T','U','V','W','X','Y','Z'};
        return c[i++];
    }


}
