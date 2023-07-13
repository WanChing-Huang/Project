/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.ProductManagement;

import java.util.ArrayList;

/**
 *
 * @author kal bugrara
 */
public class ProductsReport {

  ArrayList<ProductSummary> productsummarylist;

  public ProductsReport() {
    productsummarylist = new ArrayList<ProductSummary>();
  }
 

  public void addProductSummary(ProductSummary ps){
    productsummarylist.add(ps);
  }
 /*  public void addProductSummary(Product p) {
    ProductSummary ps = new ProductSummary(p);
    productsummarylist.add(ps);
  } */

  /* public ProductSummary getTopProductAboveTarget() {
    ProductSummary currenttopproduct = null;

    for (ProductSummary ps : productsummarylist) {
      if (currenttopproduct == null) {
        currenttopproduct = ps; // initial step
      } else if (
        ps.getNumberAboveTarget() > currenttopproduct.getNumberAboveTarget()
      ) {
        currenttopproduct = ps; //we have a new higher total
      }
    }
    return currenttopproduct;
  } */

  public ArrayList<ProductSummary> getProductsAlwaysAboveTarget() {
    ArrayList<ProductSummary> productsalwaysabovetarget = new ArrayList<ProductSummary>(); //temp array list

    for (ProductSummary ps : productsummarylist) {
      if (ps.isProductAlwaysAboveTarget() == true) {
        productsalwaysabovetarget.add(ps);
      }
    }

    return productsalwaysabovetarget;
  }

  public void printReport(){
   
    for (ProductSummary ps : productsummarylist){
      Product product = ps.getSubjectproduct();
      System.out.println("");
      System.out.printf("%-8s:%-10s %n", "Product", product.getName());
      System.out.println("----------------------------");
      System.out.println("Sale Revenue and Quantity: ");
      System.out.println("");
      System.out.printf("%-30s%-8s%-3s |%-15s:%-8s|%-15s:%-8s|%-15s:%-8s %n", "Common Sale: | Total Revenue:",ps.getAcutalsalesvolume(),"USD","Price Performance",ps.getProductpriceperformance(),"Sale Above Target",ps.getNumberofsalesabovetarget(),"Sale Below Target",ps.getNumberofsalesbelowtarget());
      System.out.printf("%-30s%-8s%-3s |%-15s:%-8s|%-15s:%-8s|%-15s:%-8s %n", "Deal Sale:   | Total Revenue:",ps.getAcutalSOsalesvolume(),"USD","Price Performance",ps.getSoPoductpriceperformance(),"Sale Above Target", ps.getNumberofSOsalesabovetarget(),"Sale Below Target",ps.getNumberofSOsalesbelowtarget());
      System.out.println("");
      /* System.out.println("Sale price and Target Price: ");
      System.out.println("");
      System.out.printf("%-30s%-10s%-3s |%-15s:%-10s%-3s %n", "Common Sale: | Above Target:",ps.getNumberofsalesabovetarget(),"pcs","Below Target", ps.getNumberofsalesbelowtarget(),"pcs");
      System.out.printf("%-30s%-10s%-3s |%-15s:%-10s%-3s %n", "Deal Sale:   | Above Target:",ps.getNumberofSOsalesabovetarget(),"pcs","Below Target", ps.getNumberofSOsalesbelowtarget(),"pcs"); */
    }

  }
}
