package com.napier.sem;

import java.util.Scanner;

public class Testing {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter first num");
        int num1 = sc.nextInt();
        System.out.println("Enter second num");
        int num2 = sc.nextInt();
        System.out.println(sum(num1,num2));
    }
    public static int sum(int a, int b) {
        return a+b;
    }

}
