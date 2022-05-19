package com.mycart;

import java.util.Scanner;

public class MyCart {

  public static void main(String[] args) {
    try {
      Scanner sc = new Scanner(System.in);
      System.out.println("Please choose your profile: " + "\n1. Customer" + "\n2. Admin" + "\n3. Exit MyCart.");
      System.out.print("Enter:: ");
      byte profileNum = sc.nextByte();

      if (profileNum == 1) {
        MyCartMenu.Customer();
      } else if (profileNum == 2) {
        MyCartMenu.Admin();

      } else if (profileNum == 3) {

        System.exit(0);
      } else if (profileNum <= 0 || profileNum > 3) {
        System.out.println("Invalid input!");
        main(null);
      }
    }
    catch (Exception e) {
      System.out.println("Invalid input!");
      main(null);
    }
  }
}









