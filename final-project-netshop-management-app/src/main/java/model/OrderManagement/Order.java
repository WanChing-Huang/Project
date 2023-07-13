/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.OrderManagement;


import java.util.ArrayList;

import model.CustomerManagement.CustomerProfile;
import model.MarketModel.Channel;
import model.MarketModel.Market;
import model.MarketModel.MarketChannelAssignment;
import model.ProductManagement.Product;
import model.ProductManagement.SolutionOffer;
import model.SalesManagement.SalesPersonProfile;

/**
 *
 * @author kal bugrara
 */
public class Order {
    ArrayList<SolutionOffer> solutionOrder;
    ArrayList<OrderItem> orderitems;
    ArrayList<OrderItem> solutionOrderItems;
    public ArrayList<OrderItem> getSolutionOrderItems() {
        return solutionOrderItems;
    }


    CustomerProfile customer;
    SalesPersonProfile salesperson;
    MarketChannelAssignment mca;
    String status;

    public Order(CustomerProfile cp) {
        solutionOrder = new ArrayList<SolutionOffer>();
        orderitems = new ArrayList<OrderItem>(); 
        solutionOrderItems = new ArrayList<OrderItem>(); 
        customer = cp;
        customer.addCustomerOrder(this); //we link the order to the customer
        salesperson = null;
        status = "in process";
    }
    

 /*    public Order(CustomerProfile cp) {
        orderitems = new ArrayList<OrderItem>(); 
        customer = cp;
        customer.addCustomerOrder(this); //we link the order to the customer
        salesperson = null;
        status = "in process";
    } */
    public Order(CustomerProfile cp, SalesPersonProfile ep) {
        orderitems = new ArrayList<OrderItem>();
        customer = cp;
        salesperson = ep;
        customer.addCustomerOrder(this); //we link the order to the customer
        salesperson.addSalesOrder(this);  
    }
    public OrderItem newOrderItem(Product p, int actualprice, int q) {
        OrderItem oi = new OrderItem(p, actualprice, q);
        orderitems.add(oi);
        return oi;
    }
    
    //add solution order to arraylist solution order items 
    public void newSolutionOrderItem(SolutionOffer so,int q) {
        solutionOrder.add(so);
        for(Product p : so.getProducts()){
          OrderItem oi = new OrderItem(p, q,so);
          solutionOrderItems.add(oi);
        } 
    }

  
    
    

    //order total is the sumer of the order item totals(orderItem + SolutionOrderItem)
    public int getOrderTotal() {
        int sum = 0;
        int sOSum = 0; //solution order items sum
        for (OrderItem oi : orderitems) {
            sum = sum + oi.getOrderItemTotal();
        }
        for (OrderItem oi : solutionOrderItems){
            sOSum = sOSum + oi.getOrderItemTotal();
        }
        return sum+sOSum;
    }
    
    
    
    public int getOrderPricePerformance() {
        int sum = 0;
        int sOSum = 0; //solution order items sum
        for (OrderItem oi : orderitems) {
            sum = sum + oi.calculatePricePerformance();     //positive and negative values       
        }
        for (OrderItem oi : solutionOrderItems){
            sOSum = sOSum + oi.calculatePricePerformance();
        }
        return sum+sOSum;
    }

    public int getNumberOfOrderItemsAboveTarget() {
        int sum = 0;
        int sOSum = 0; //solution order items sum
        for (OrderItem oi : orderitems) {
            if (oi.isActualAboveTarget() == true) {
                sum = sum + 1;
            }
        }
        for (OrderItem oi : solutionOrderItems) {
            if (oi.isActualAboveTarget() == true) {
                sOSum = sOSum + 1;
            }
        }
        return sum + sOSum;
    }


    
    //sum all the item targets and compare to the total of the order 
    public boolean isOrderAboveTotalTarget(){
        int sum = 0;
        for (OrderItem oi: orderitems){
            sum = sum + oi.getOrderItemTargetTotal(); //product targets are added
        }
        if(getOrderTotal()>sum) {return true;}
        else {return false;}
        
    }
public void CancelOrder(){
    status = "Cancelled";
}
public void Submit(){
    status = "Submitted";}

    public SolutionOffer getSO (MarketChannelAssignment MCA){
        for (SolutionOffer so : solutionOrder){
            if (so.getMarketchannelcomb() == MCA)
            return so;
        }
        return null;
    }
    
    public SolutionOffer getSOByMarket (Market market){
        for (SolutionOffer so : solutionOrder){
            if (so.getMarketchannelcomb().getMarket() == market)
            return so;
        }
        return null;
    }
    

    public SolutionOffer getSOByChannel (Channel channel){
        for (SolutionOffer so : solutionOrder){
            if (so.getMarketchannelcomb().getChannel() == channel)
            return so;
        }
        return null;
    }

    //sum of solution sale quantities
  public int soOrderItemTotalQuantity() {
    int sSum = 0;
    for (OrderItem oi : solutionOrderItems) {
      sSum = sSum + oi.getQuantity();
    }
    return sSum;
  }
  
 
}
