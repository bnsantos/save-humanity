package com.bnsantos;

import java.util.Scanner;

public class Solution1 {
    private static void solve(String pattern, String text){
        if(text.length() > pattern.length()){
            search(pattern, text);
        }else if(text.length() == pattern.length() && text.equals(pattern)){
            System.out.println("0");
        }else {
            System.out.println("No Match!");
        }
    }

    private static void search(String pattern, String text) {
        boolean mismatch;
        boolean matched;
        boolean found = false;
        for (int i = 0; i < text.length() && text.length() - i >= pattern.length(); i++) {
            matched = true;
            mismatch = false;
            for (int j = 0; j < pattern.length() && i+j<text.length(); j++) {
                if(pattern.charAt(j) != text.charAt(i+j)){
                    if (mismatch) {
                        matched = false;
                        break;
                    }
                    mismatch = true;
                }
            }
            if (matched) {
                found = true;
                System.out.print(i + " ");
            }
        }
        if (found) {
            System.out.println();
        }else {
            System.out.println("No Match!");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int lines = scanner.nextInt();
        scanner.nextLine();
        for (int i=0; i<lines; i++) {
            String[] line = scanner.nextLine().split(" ");
            solve(line[1], line[0]);
        }
        scanner.close();
    }
}
