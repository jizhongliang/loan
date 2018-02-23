package com.hwc.framework;

/**
 * Created by lxk on 2017/12/25.
 */

import cn.freesoft.utils.FsUtils;

import java.util.Scanner;
import java.util.Stack;


public class Hawei23 {

    private static char[] array = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
            .toCharArray();
    private static String numStr = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    //10进制转为其他进制，除留取余，逆序排列
    public static String _10_to_N(long number, int N) {
        Long rest = number;
        Stack<Character> stack = new Stack<Character>();
        StringBuilder result = new StringBuilder(0);
        while (rest != 0) {
            stack.add(array[new Long((rest % N)).intValue()]);
            rest = rest / N;
        }
        for (; !stack.isEmpty(); ) {
            result.append(stack.pop());
        }
        return result.length() == 0 ? "0" : result.toString();

    }

    // 其他进制转为10进制，按权展开
    public static long N_to_10(String number, int N) {
        char ch[] = number.toCharArray();
        int len = ch.length;
        long result = 0;
        if (N == 10) {
            return Long.parseLong(number);
        }
        long base = 1;
        for (int i = len - 1; i >= 0; i--) {
            int index = numStr.indexOf(ch[i]);
            result += index * base;
            base *= N;
        }

        return result;
    }


    public static void main(String[] args) {
        // TODO Auto-generated method stub

//        Scanner in = new Scanner(System.in);

//        while(in.hasNext()){
//        int src = 10;  //in.nextInt();
//        // int aim = in.nextInt();
//        String intStr = "12345678903213213";
//
//
//        Long tmp = N_to_10(intStr, src);
//        String tmp2 = _10_to_N(tmp, 36);
//
//        String newStr = tmp2.replaceFirst("^0*", "");
//        System.out.println(newStr);
        String str = "182 3872 7755,182 3872 7755";
        //System.out.println(rest.replace(" ",""));
        int len = str.length();
        StringBuffer buffer = new StringBuffer();

        for(int i = 0; i < len; ++i) {
            char codePoint = str.charAt(i);
            if(FsUtils.isInteger(codePoint+"")) {
                buffer.append(codePoint);
            }
        }

        System.out.println(buffer.toString());

        //}
    }
    public static boolean isEmojiCharacter(char codePoint) {
        return codePoint == 0 || codePoint == 9 || codePoint == 10 || codePoint == 13 || codePoint >= 32 && codePoint <= '\ud7ff' || codePoint >= '\ue000' && codePoint <= '�' || codePoint >= 65536 && codePoint <= 1114111;
    }
}