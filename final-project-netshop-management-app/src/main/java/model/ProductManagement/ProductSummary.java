/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.ProductManagement;

/**
 *
 * @author kal bugrara
 */

//this class will extract summary data from the product
public class ProductSummary {
    Product subjectproduct;
    int allNumberofsalesabovetarget;
    int numberofsalesabovetarget;
    int numberofSOsalesabovetarget;

    int numberofsalesbelowtarget;
    int numberofSOsalesbelowtarget;
    
    int productpriceperformance;
    int soPoductpriceperformance; //total profit above target --could be negative too
    
    int acutalsalesvolume;
    int acutalSOsalesvolume;

    int productSaleQuantity;
    int productSOSaleQuantity;

    public ProductSummary(Product p){
        subjectproduct = p; //keeps track of the product itself not as well;
        //sale above target
        allNumberofsalesabovetarget = p.getNumberOfAllProductSalesAboveTarget();
        numberofsalesabovetarget = p.getNumberOfProductSalesAboveTarget();
        numberofSOsalesabovetarget = p.getNumberOfSOProductSalesAboveTarget();
        //sale below target
        numberofsalesbelowtarget = p.getNumberOfProductSalesBelowTarget();
        numberofSOsalesbelowtarget = p.getNumberOfSOProductSalesBelowTarget();
        // product performance
        productpriceperformance = p.getOrderPricePerformance();
        soPoductpriceperformance = p.getSOOrderPricePerformance();
        //sale volume
        acutalsalesvolume = p.getSalesVolume();
        acutalSOsalesvolume = p.getSolutionSalesVolume();
        //sale quantity
        productSaleQuantity = p.productTotalQuantity();
        productSOSaleQuantity = p.soProductTotalQuantity();
    }

    public Product getSubjectproduct() {
        return subjectproduct;
    }

    public int getAllNumberofsalesabovetarget() {
        return allNumberofsalesabovetarget;
    }

    public int getNumberofsalesabovetarget() {
        return numberofsalesabovetarget;
    }

    public int getNumberofSOsalesabovetarget() {
        return numberofSOsalesabovetarget;
    }

    public int getNumberofsalesbelowtarget() {
        return numberofsalesbelowtarget;
    }

    public int getNumberofSOsalesbelowtarget() {
        return numberofSOsalesbelowtarget;
    }

    public int getProductpriceperformance() {
        return productpriceperformance;
    }

    public int getSoPoductpriceperformance() {
        return soPoductpriceperformance;
    }

    public int getAcutalsalesvolume() {
        return acutalsalesvolume;
    }

    public int getAcutalSOsalesvolume() {
        return acutalSOsalesvolume;
    }

    public int getProductSaleQuantity() {
        return productSaleQuantity;
    }

    public int getProductSOSaleQuantity() {
        return productSOSaleQuantity;
    }

    int rank; // will be done later
    
   
          
    public boolean isProductAlwaysAboveTarget(){
        return false; // to be implemented
    }


}
