package com.a.eye.by.sort;

public class Sort {

    /**
     * 1、冒泡排序 核心思想:比较两个元素，如果前一个比后一个大，则进行交换。经过对每个元素的比较， 最后将最大的元素设置到最后一个元素，重复改操作，最后形成一个由大到小的排序。
     * 
     * @param arr
     */
    public void sort(long[] arr) {
        long temp;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = arr.length - 1; j > i; j--) {
                if (arr[j] < arr[j - 1]) {
                    temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                }
            }
        }
    }

    /**
     * 2、选择排序 核心思想:扫描所有元素，得到最小的元素，并将最小的元素与左边第一个元素进行交换。再次扫描除了第一个位置的所有元素， 得到最小的元素，与左边的第二个元素进行交换，以此类推。
     * 
     * @param arr
     */
    public void selectorSort(long[] arr) {
        long temp;
        int min;
        for (int i = 0; i < arr.length - 1; i++) {
            min = i;
            for (int j = i; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            temp = arr[min];
            arr[min] = arr[i];
            arr[i] = temp;
        }
    }

    /**
     * 3、插入排序 核心思想:抽出一个元素，从右往左进行比较，找到合适的位置进行插入。插入排序算法是一个对少量元素进行排序的有效算法。插入排序的工作原理与打牌时整理手中的牌的做法类似，开始摸牌时，
     * 我们的左手是空的，接着一次从桌上摸起一张牌 ，并将它插入到左手的正确位置。 为了找到这张牌的正确位置，要将它与手中已有的牌从右到左进行比较，无论什么时候手中的牌都是排序好的。
     * 
     * @param arr
     */
    public void insertSort(long[] arr) {
        long select;
        for (int i = 1; i < arr.length - 1; i++) {
            select = arr[i];
            int j;
            for (j = i; j > 0 & arr[j - 1] >= select; j--) {
                arr[j] = arr[j - 1];
            }
            arr[j] = select;
        }
    }
}
