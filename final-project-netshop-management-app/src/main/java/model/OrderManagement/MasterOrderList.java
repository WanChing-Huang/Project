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
import model.SalesManagement.SalesPersonProfile;

/**
 *
 * @author kal bugrara
 */
public class MasterOrderList {

  ArrayList<Order> orders;
  MasterOrderReport masterorderreport;

  public MasterOrderList() {
    orders = new ArrayList<Order>();
  }

  public Order newOrder(CustomerProfile cp) {
    Order o = new Order(cp);
    orders.add(o);
    return o;
  }

  public Order newOrder(CustomerProfile cp, SalesPersonProfile spp) {
    Order o = new Order(cp, spp);
    orders.add(o);
    return o;
  }

  public MasterOrderReport generateMasterOrderReport() {
    masterorderreport = new MasterOrderReport();

    return masterorderreport;
  }

  public int getSalesVolume() {
    int sum = 0;
    for (Order order : orders) {
      sum = sum + order.getOrderTotal();
    }
    return sum;
  }

  public int getSaleRevenueByMarketChannelCombo(MarketChannelAssignment mca) {
    int sum = 0;
    for (Order order : orders) {
      for (OrderItem oi : order.getSolutionOrderItems()) {
        if (order.getSO(mca) == oi.getSo()) {
          sum = sum + oi.getOrderItemTotal();
        }
      }
    }
    return sum;
  }

    public int getSaleRevenueByMarket(Market market) {
      int sum = 0;
      for (Order order : orders) {
        for (OrderItem oi : order.getSolutionOrderItems()) {
          if (order.getSOByMarket(market) == oi.getSo() ) {
            sum = sum + oi.getOrderItemTotal();
          }
        }
      }

    return sum;
  }
    
  public int getSaleRevenueByChannel(Channel channel) {
    int sum = 0;
    for (Order order : orders) {
      for (OrderItem oi : order.getSolutionOrderItems()) {
        if (order.getSOByChannel(channel) == oi.getSo() ) {
          sum = sum + oi.getOrderItemTotal();
        }
      }
    }
  
  return sum;
}


}
