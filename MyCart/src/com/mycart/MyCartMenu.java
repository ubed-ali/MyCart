package com.mycart;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class MyCartMenu {

  private static String CustomerName;
  private static String address;
  private static int totalItemInCart;
  private static int maxItemLimit = 5;
  private static ArrayList<String> itemInCart = new ArrayList<>();
  private static ArrayList<Integer> priceAfterPurchase = new ArrayList<>();
  private static ProductList list= new ProductList();
  private static int[] purchasedList = new int[maxItemLimit];

  public String getName() {
    return CustomerName;
  }

  public String getIdNum() {
    return address;
  }

  public void setName(String newName) {
    CustomerName = newName;
  }

  public void setIdNum(String newAddress) {
    address = newAddress;
  }

  public static void Customer() {

    System.out.println("___Flat 500 OFF on Purchase of 10,000 and above___");
    System.out.println("==========Welcome to My Cart=========\n");
    System.out.println("\t\tChoose Option:\n\t\t1: Products\n\t\t2: Go to Cart\n\t\t3: Bill\n\t\t99: Exit MyCart\n");
    System.out.println("===============My Cart===============\n");
    System.out.print("Enter:: ");
    Scanner customerInput = new Scanner(System.in);
    int customerIn = customerInput.nextInt();

    if (customerIn == 1) {
      System.out.println("\t***__ Shop now__***");
      System.out.println("____________Available Products____________");
      System.out.println("__________________________________________");
      System.out.println("Pr. id\t\tItems\t\t\t\tPrice\n");
      for (int i=0; i< list.product.size(); i++) {
        if(list.product.get(i).length() <= 6){
          System.out.println(i + "\t\t\t" + list.product.get(i) + "\t\t\t\t" + list.productPrices.get(i));
        }
        else if (list.product.get(i).length() >= 12){
          System.out.println(i + "\t\t\t" + list.product.get(i) + "\t\t" + list.productPrices.get(i));
        }
        else{
          System.out.println(i + "\t\t\t" + list.product.get(i) + "\t\t\t" + list.productPrices.get(i));
        }
      }
      System.out.println("__________________________________________");

      // purchase start
      Scanner itemPurchase = new Scanner(System.in);

      System.out.print("No. of items you want:: ");
      int index_no_item = itemPurchase.nextInt();



      if (index_no_item <= maxItemLimit) {
        try {
          System.out.println("Enter Product id:: ");
          for (int i=0; i<index_no_item; i++) {
            purchasedList[i] = itemPurchase.nextInt();
          }
          totalItemInCart = index_no_item;
          System.out.println("_______________________________________");
          System.out.println("     Below items added in the cart     ");
          System.out.println("Items in Cart: " + index_no_item);
          System.out.println("_______________________________________");

          System.out.println("_______________________________________");
          for (int i=0; i<index_no_item; i++){
            System.out.println(purchasedList[i] + ":\t" + list.product.get(purchasedList[i]) + "\t\t " + list.productPrices.get(purchasedList[i]) );
            itemInCart.add(i, String.valueOf(list.productPrices.get(purchasedList[i])));
            priceAfterPurchase.add(list.productPrices.get(purchasedList[i]));
          }

        } catch (Exception e) {

        }
        finally {
          System.out.println("_______________________________________");
        }
      }
      else {
        System.out.println("You can't purchase more than 5 items! ");
        Customer();
      }

      System.out.println("Choose:\n\t1: Main Menu\n\t99: Exit MyCart");
      System.out.println("Enter::");
      Scanner ch = new Scanner(System.in);
      int num = ch.nextInt();
      if (num == 2) {
        Customer();
      }
      else if (num == 99) {
        return;
      }
      else {
        System.out.println("Please enter a valid input!");
        Customer();
      }
    }
    else if(customerIn == 2) {
      System.out.println("**********__Your Cart__**********");
      System.out.println("_________________________________");
      Cart();
      System.out.println("_________________________________");
      System.out.println("Total Items: \t\t\t\t" + itemInCart.size());
      double sum = 0;
      for (int d : priceAfterPurchase) {
        sum += d;
      }

      // Applying discount
      if (sum > 10000) {
        int discount = 500;
        System.out.println("Total Amount: \t\t\t" + sum);
        System.out.println("_________________________________");
        System.out.println("Discount: \t\t\t\t" + 500);
        System.out.println("_________________________________");
        System.out.println("Payable Amount:\t\t\t" + (sum - discount));
        // discount end

      }
      else {
        System.out.println("Payable Amount:\t\t\t" + sum);
      }

      System.out.println("_________________________________");
      System.out.println("Choose:\n\t1: Manage Cart\n\t2: Continue\n\t99: Exit MyCart");
      System.out.println("Enter::");
      Scanner cr = new Scanner(System.in);
      int num = cr.nextInt();
      if (num == 1) {
        System.out.println("Do you want to? \n1: Add more item\n2: Remove item\n3: Main Menu");
        Scanner cartManageInput = new Scanner(System.in);
        int cm = cartManageInput.nextInt();
        if (cm == 1) {
          System.out.print("Enter Product id: ");
          Scanner add = new Scanner(System.in);
          int cmm = add.nextInt();
          for (int i = 0; i < maxItemLimit - totalItemInCart; i++) {
            itemInCart.set(i, list.product.get(cmm));
            priceAfterPurchase.add(list.productPrices.get(cmm));
          }
          Cart();

        } else if (cm == 2) {
          itemInCart.remove(list.product.remove(cm));
        } else if (cm == 3) {
          Customer();
        } else {
          Cart();
        }

      }
      else if (num == 99) {
        return;
      }
    }
      else if (customerIn == 3) {
        bill();
        System.out.println("Choose:\n\t1: Main Menu\n\t2: Exit MyCart");
        System.out.println("Enter:: ");
        Scanner sc = new Scanner(System.in);
        int num1 = sc.nextInt();
        if (num1 == 1) {
          Customer();
        } else if (num1 == 2) {
          return;
        } else {
          bill();
        }
      }
      else if (customerIn == 99) {
        return;
      }
      else {
        System.out.println("Please enter correct choice!!");
        Customer();
      }
  }

  public static void Admin() {
    System.out.println("===================== My Cart ====================\n");
    System.out.println("View as an Admin\n");
    System.out.println("\t\tChoose Option:\n\t\t1: Add Item(s)\n\t\t2: Update Existing Item\n\t\t3: Remove Item\n\t\t4: Stock\n\t\t5: Recent Orders\n\t\t99: Exit MyCart");
    System.out.println("==================================================\n");

    Scanner inputAdmin = new Scanner(System.in);
    System.out.println("Enter::");
    int adminInput = inputAdmin.nextInt();
    if (adminInput == 1) {
      System.out.println("Product name & price");

      //adding item starts
      Scanner addProduct = new Scanner(System.in);
      System.out.println("Product Name: ");
      String newProductName = addProduct.nextLine();
      list.product.add(list.product.size(), newProductName);
      System.out.println("Product Price: ");
      Integer newProductPrice = addProduct.nextInt();
      list.productPrices.add(newProductPrice);
      //adding items end

      System.out.print("Product added successfully");
      Admin();
      // added end
    }
    else if (adminInput == 2) {
      Scanner update = new Scanner(System.in);
      System.out.println("Product id: ");
      int index = update.nextInt();
      System.out.println("Product name: ");
      String updatedProductName = update.next();
      System.out.println("Price: ");
      int updatedPrice = update.nextInt();
      list.product.set(index, updatedProductName);
      list.productPrices.set(index, updatedPrice);

      System.out.println("***Product details updated successfully!!***");
      System.out.println("Choose:\n\t1: Continue.\n\t2: Exit");
      System.out.print("Enter::");
      Scanner cr = new Scanner(System.in);
      int num = cr.nextInt();
      if (num == 1) {
        Admin();
      } else if (num == 2) {
        return;
      } else {
        return;
      }
      // update end
    }

    else if (adminInput == 3) {
      //remove start
      Scanner remove = new Scanner(System.in);
      System.out.println("Product id: ");
      int removeItem = remove.nextInt();
      list.product.remove(removeItem);
      list.productPrices.remove(removeItem);
      System.out.println("Item removed successfully");

      Admin();
      //  remove end
    }

    else if (adminInput == 4) {
      Stock();
      System.out.println("Choose:\n\t1: Continue.\n\t2: Exit MyCart.");
      System.out.println("Enter::");
      Scanner cr = new Scanner(System.in);
      int num = cr.nextInt();
      if (num == 1) {
        Admin();
      } else if (num == 2) {
        return;
      } else {
        return;
      }

    }

    else if (adminInput == 5) {
      CustomerRecord();
    }
    else if (adminInput==99) {
      return;
    }
    else {
      System.out.println("Please enter correct choice!!");
      Admin();
    }
  }

  public static int Stock() {
    System.out.println("*************************Full Stock Details*************************");

    System.out.println("Total Prodcuts: " + list.product.size());

    int sum = 0;
    for (int d : list.productPrices) {
      sum += d;
    }
    System.out.print("Total Mart Cost: " + sum);

    System.out.println("\n________________________________________________");
    System.out.println("Item no\t\tItem\t\t\tPrice\n");
    for (int i = 0; i < list.product.size(); i++) {
      System.out.println(i + "\t\t"+list.product.get(i) + "\t\t\t" + list.productPrices.get(i));
    }
    System.out.println("________________________________________________");
    return sum;
  }

  public static void Cart() {
    MyCart object = new MyCart();
    MyCart oi = new MyCart();
    System.out.println("Item No. \tItems\t\tPrice");
    System.out.println("_______________________________________\n");
    for (int i=0; i<totalItemInCart; i++){
      System.out.println(i + ":\t\t " + list.product.get(purchasedList[i]) + "\t\t " + list.productPrices.get(purchasedList[i]) );
    }
  }

  public static void bill() {

    Scanner csName = new Scanner(System.in);
    System.out.print("Name: ");
    CustomerName = csName.nextLine();
    System.out.print("Address: ");
    address = csName.nextLine();

    System.out.println("***************YOUR BILL***************");
    System.out.println("_______________________________________");
    System.out.println("CustomerName: " + CustomerName);
    System.out.println("Address: " + address);
    LocalDate date = LocalDate.now();
    System.out.println("Date: "+ date);
    LocalTime time = LocalTime.now();
    System.out.println("Time: " + time);
    System.out.println("_______________________________________");
    Cart();
    System.out.println("\n---------------------------------------");
    System.out.println("Total items: " + itemInCart.size());
    double sum = 0;
    for (int d : priceAfterPurchase) {
      sum += d;
    }
    if (sum > 10000) {
      int discount = 500;
      System.out.println("Total Amount: \t\t\t" + sum);
      System.out.println("_________________________________");
      System.out.println("Discount: \t\t\t\t" + 500);
      System.out.println("_________________________________");
      System.out.println("Payable Amount:\t\t\t" + (sum - discount));
      // discount end

    } else {
      System.out.println("Payable Amount:\t\t\t" + sum);
    }
    System.out.println("_______________________________________");
    System.out.println("**Thank You for Shopping with MY CART**\n");

    //  keeping record start

    File file = new File("D:\\Java projects\\MyCart\\src\\customerRecord.txt");
    String name = CustomerName;
    String customerAddress = address;
    try {
      // Creating a writer using FileWriter
      FileWriter output = new FileWriter("customerRecord.txt");
      // Writes string to the file

      output.write("_______________________________________");
      output.write("\nCustomer Name: " + name);
      output.write("\nAddress: " + customerAddress);
      output.write("\nDate & time: " + date + time);
      output.write("\nQuantity: " + itemInCart.size());
      output.write("\nList of purchased items: \n");
      for (int i=0; i<totalItemInCart; i++){
        output.write(i + ":\t\t " + list.product.get(purchasedList[i]) + "\t\t " + list.productPrices.get(purchasedList[i]) +"\n");
      }
      output.write("\nTotal purchase amount: " + sum);
      output.write("\n_______________________________________");
      output.close();

      // Closes the writer
    } catch (Exception e) {
      e.getStackTrace();
    }
    // Keeping record end

    System.out.println("Choose:\n\t1: Main Menu\n\t99: Exit MyCart");
    System.out.println("Enter::");
    int num = csName.nextInt();
    if (num == 1) {
      Customer();
    } else if (num == 99) {
      return;
    } else {
      System.out.println("Entered incorrect");
    }
  }

  public static void CustomerRecord() {
    char[] array = new char[350];
    try {
      FileReader input  = new FileReader("customerRecord.txt");

      // Reads characters
      input.read(array);
      System.out.println("Customer Details:");
      if (input != null) {
        System.out.println(array);
      }
    } catch (Exception e) {
      e.getStackTrace();
    }
  }




}
