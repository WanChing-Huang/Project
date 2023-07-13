/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.ProductManagement;

import java.util.ArrayList;
import model.OrderManagement.OrderItem;

/**
 *
 * @author kal bugrara
 */
public class Product {

  private String name;
  private int floorPrice;
  private int ceilingPrice;
  private int targetPrice;
  ArrayList<OrderItem> orderitems;
  ArrayList<OrderItem> solutionOrderItems;
  // create 2 <orderItem> arrayList to identity 2 different kind of order

  public Product(int fp, int cp, int tp) {
    floorPrice = fp;
    ceilingPrice = cp;
    targetPrice = tp;
    orderitems = new ArrayList<OrderItem>();
    solutionOrderItems = new ArrayList<OrderItem>();
  }

  public Product(String n, int fp, int cp, int tp) {
    name = n;
    floorPrice = fp;
    ceilingPrice = cp;
    targetPrice = tp;
    orderitems = new ArrayList<OrderItem>();
    solutionOrderItems = new ArrayList<OrderItem>();
    
  }

  public Product updateProduct(int fp, int cp, int tp) {
    floorPrice = fp;
    ceilingPrice = cp;
    targetPrice = tp;
    return this; // returns itself
  }

  public int getTargetPrice() {
    return targetPrice;
  }

  public void addOrderItem(OrderItem oi) {
    orderitems.add(oi);
  }

  public void addSolutionOrderItem(OrderItem oi) {
    solutionOrderItems.add(oi);
  }

  // Number of item sales above target
  public int getNumberOfAllProductSalesAboveTarget() {
    int sum = 0;
    int sSum = 0; // soltuon order sum
    for (OrderItem oi : orderitems) {
      if (oi.isActualAboveTarget() == true)
        sum = sum + oi.getQuantity();
    }
    for (OrderItem oi : solutionOrderItems) {
      if (oi.isActualAboveTarget() == true)
        sSum = sSum + oi.getQuantity();
    }
    return sum + sSum;
  }
 
  public int getNumberOfSOProductSalesAboveTarget() {
    int sSum = 0; // soltuon order sum
    for (OrderItem oi : solutionOrderItems) {
      if (oi.isActualAboveTarget() == true)
        sSum = sSum + oi.getQuantity();
    }
    return  + sSum;
  }

  public int getNumberOfProductSalesAboveTarget() {
    int sum = 0;
    for (OrderItem oi : orderitems) {
      if (oi.isActualAboveTarget() == true)
        sum = sum + oi.getQuantity();
    }
    return sum ;
  }

  public int getNumberOfAllProductSalesBelowTarget() {
    int sum = 0;
    int sSum = 0; // soltuon order sum
    for (OrderItem oi : orderitems) {
      if (oi.isActualBelowTarget() == true)
        sum = sum + oi.getQuantity();
    }
    for (OrderItem oi : solutionOrderItems) {
      if (oi.isActualAboveTarget() == true)
        sSum = sSum + oi.getQuantity();
    }
    return sum + sSum;
  }

  public int getNumberOfSOProductSalesBelowTarget() {
 
    int sSum = 0; // soltuon order sum
  
    for (OrderItem oi : solutionOrderItems) {
      if (oi.isActualAboveTarget() == true)
        sSum = sSum + oi.getQuantity();
    }
    return  sSum;
  }
  
  public int getNumberOfProductSalesBelowTarget() {
    int sum = 0;
    for (OrderItem oi : orderitems) {
      if (oi.isActualBelowTarget() == true)
        sum = sum + oi.getQuantity();
    }
    return sum;
  }

  public boolean isProductAlwaysAboveTarget() {
    for (OrderItem oi : orderitems) {
      if (oi.isActualAboveTarget() == false)
        return false; //
    }
    for (OrderItem oi : solutionOrderItems) {
      if (oi.isActualAboveTarget() == false)
        return false; //
    }
    return true;
  }

  // calculates the revenues gained or lost (in relation to the target)
  // For example, if target is at $2000 and actual is $2500 then revenue gained
  // is $500 above the expected target. If the actual is $1800 then the lose will
  // be $200
  // Add all these difference to get the total including wins and loses

  public int getOrderPricePerformance() {
    int sum = 0;
    for (OrderItem oi : orderitems) {
      sum = sum + oi.calculatePricePerformance(); // positive and negative values
    }
    return sum;
  }
  public int getSOOrderPricePerformance() {
    int sum = 0;
    for (OrderItem oi : solutionOrderItems) {
      sum = sum + oi.calculatePricePerformance(); // positive and negative values
    }
    return sum;
  }  
  
  // sum sale total
  public int getSalesVolume() {
    int sum = 0;
    for (OrderItem oi : orderitems) {
      sum = sum + oi.getOrderItemTotal(); // positive and negative values
    }
    return sum;
  }

  // sum solution sale total
  public int getSolutionSalesVolume() {
    int sum = 0;
    for (OrderItem oi : solutionOrderItems) {
      sum = sum + oi.getOrderItemTotal(); // positive and negative values
    }
    return sum;
  }

  public String getName() {
    return name;
  }

  public void setName(String n) {
    name = n;
  }

  @Override
  public String toString() {
    return name;
  }

  public int getFloorPrice() {
    return floorPrice;
  }

  public int getCeilingPrice() {
    return ceilingPrice;
  }
   //sum of normal sale quantities
  public int productTotalQuantity() {
    int sum = 0;
    for (OrderItem oi : orderitems) {
      sum = sum + oi.getQuantity();
    }
    return sum;
  }
  //sum of solution sale quantities
  public int soProductTotalQuantity() {
    int sSum = 0;
    for (OrderItem oi : solutionOrderItems) {
      sSum = sSum + oi.getQuantity();
    }
    return sSum;
  }
  
  

  // print product sold quantities and total sales
  public void printPInfo() {
    System.out.println("Normal Sale");
    System.out.printf("%-10s %-28s  %-10s  %-5s  %-15s %-10s %-5s %n",
        " * Product : ", getName(), " * Sold: ", productTotalQuantity(), " items * Total Sales : ", getSalesVolume(),
        " USD");
    System.out.println("Solution Sale");
    System.out.printf("%-10s %-28s  %-10s  %-5s  %-15s %-10s %-5s %n",
            " * Product : ", getName(), " * Sold: ", soProductTotalQuantity(), " items * Total Sales : ", getSolutionSalesVolume(),
            " USD");

  }
}
