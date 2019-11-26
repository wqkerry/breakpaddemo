package com.swangq.com.suanfa;

/**
 * @author Administrator
 * @package com.swangq.com.suanfa
 * @date 2019/4/10
 * @describe TODO
 */
public class Test {

    public static void selectS(int[] arr) {
        int len = arr.length;
        for (int i = 0, k = 0; i < len; i++, k = i) {
            for (int j = i + 1; j < len; j++) {
                if (arr[k] > arr[j]) {
                    k = j;
                }
            }
            if (i != k) {
                int temp = arr[i];
                arr[i] = arr[k];
                arr[k] = temp;
            }
        }
    }


    public static void chaS(int[] arr, int start, int end) {
        if (end > start) {
            int mid = 0;
            mid = div(arr, start, end);
            chaS(arr, start, mid);
            chaS(arr, mid + 1, end);
        }
    }

    private static int div(int[] arr, int start, int end) {
        int pr = arr[start];
        while (start<end){

        }
        return 0;
    }


}
