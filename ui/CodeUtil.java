package com.XYu1230.ui;

import java.util.Random;
import java.util.StringJoiner;

public class CodeUtil {
    public static String getCode(){
        StringBuilder sb = new StringBuilder();
        StringBuilder code = new StringBuilder();
        for(int i = 0; i < 26; i++){
            sb.append((char)('a' + i));
            sb.append((char)('A' + i));
        }
        /**for (int i = 0; i < sb.length(); i++) {
            System.out.println(sb.charAt(i) + ", ");
        }*/
        Random r = new Random();
        for (int i = 0; i < 4; i++) {
            int randomIndex = r.nextInt(sb.length());
            code.append(sb.charAt(randomIndex));
        }
        code.append(r.nextInt(10));
        char[] arr = code.toString().toCharArray();
        char temp = arr[r.nextInt(5)];
        arr[r.nextInt(5)] = arr[4];
        arr[4] = temp;
        return new String(arr);
    }
}
