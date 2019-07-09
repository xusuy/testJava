package com.basic.arithmetic;

/**
 * @author xsy
 * @create 2019-05-13 18:11
 * @desc 位图法：对16以下的数进行排序以及统计不同数字个数
 **/
public class BitGraph {
    private final int BITS_PRE_WORD = 32;
    private final static int max = 16;

    //16位bit从最低位到高位依次表示数字0,1,2,3...15。bit位，1代表存在该数字，0代表不存在
    //返回16以下的数组的bit表示形式
    void setBit(int[] arr, int n) {
        arr[n / BITS_PRE_WORD] |= (1 << (n % BITS_PRE_WORD));
    }

    void clearBit(int[] arr, int n) {
    }

    int getBit(int[] arr, int n) {
        return (arr[n / BITS_PRE_WORD] & (1 << (n % BITS_PRE_WORD))) != 0 ? 1 : 0;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        BitGraph bg = new BitGraph();
        int[] datas = new int[]{1, 13, 14, 15, 7, 8, 9, 13, 1, 13, 14, 15, 7, 8, 9, 13, 2};
        int[] arr = new int[max / 32 + 1];
        for (int data : datas) {
            bg.setBit(arr, data);
        }
        int count = 0;
        for (int i = 0; i < max; i++) {
            if (bg.getBit(arr, i) == 1) {
                System.out.println(i);
                ++count;
            }
        }
        System.out.println("count=" + count);
    }
}
