package com.bnsantos;

import java.util.*;

public class Solution2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int lines = scanner.nextInt();
        scanner.nextLine();
        for (int i=0; i<lines; i++) {
            String[] line = scanner.nextLine().split(" ");
            Set<Integer> pos = matchPattern(line[0], line[1]);
            if(pos.size() == 0){
                System.out.println("No Match!");
            }else {
                for (Integer integer : pos) {
                    System.out.print(integer + " ");
                }
                System.out.println();
            }
        }
        scanner.close();
    }

    private static int[] calculateZ(String input) {
        int Z[] = new int[input.length()];
        int left = 0;
        int right = 0;
        for(int i = 1; i < input.length(); i++) {
            if(i > right) {
                left = right = i;
                while(right < input.length() && input.charAt(right) == input.charAt(right - left)) {
                    right++;
                }
                Z[i] = right - left;
                right--;
            } else {
                int k = i - left;
                if(Z[k] < right - i + 1) {
                    Z[i] = Z[k];
                } else {
                    left = i;
                    while(right < input.length() && input.charAt(right) == input.charAt(right - left)) {
                        right++;
                    }
                    Z[i] = right - left;
                    right--;
                }
            }
        }
        return Z;
    }

    /**
     * Returns list of all indices where pattern is found in text.
     */
    public static Set<Integer> matchPattern(String text, String pattern) {
        int Z[] = calculateZ(pattern + "$" + text);
        int ZReverse[] = calculateZ(new StringBuilder(pattern).reverse() + "$" + new StringBuilder(text).reverse());

        Set<Integer> result = new LinkedHashSet<>();

        int patternLength = pattern.length();
        for(int i = patternLength + 1; i < Z.length - patternLength + 1; i++) {
            int idx = i - patternLength - 1;
            if (Z[i] == patternLength || Z[i] == patternLength - 1) {
                if(idx >= 0 && idx < text.length()) {
                    result.add(idx);
                }
            } else {
                int zIdx = i + patternLength - 1;
//                zIdx -> zReverseIdx
                if (ZReverse[Z.length - zIdx + patternLength] >= patternLength - Z[i] - 1) {
                    if(idx >= 0 && idx < text.length()) {
                        result.add(idx);
                    }
                }
            }
        }
        return result;
    }
}
