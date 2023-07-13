/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.CustomerManagement;


import model.MarketModel.Channel;
import java.util.ArrayList;


import model.MarketModel.Market;

import model.OrderManagement.Order;
import model.Personnel.Person;


/**
 *
 * @author kal bugrara
 */
public class CustomerProfile {
    ArrayList<Order> orders;
    Market market;
    Person person;

    public CustomerProfile(Person p,Market m) {
        market = m; 
        person = p;
        orders = new ArrayList<Order>();
    

    }

    public int getTotalPricePerformance() {

        // for each order in the customer orderlist
        // calculate order price performance and add it to the sum

        return 0;
    }

    public int getNumberOfOrdersAboveTotalTarget() {
        // for each order in the customer order list
        // calculate if order is positive (actual order total is greater than sum of
        // item targets
        // if yes then add 1 to total
        int sum = 0;
        for (Order o : orders) {
            if (o.isOrderAboveTotalTarget() == true)
                sum = sum + 1;
        }

        return sum;
    }

    public int getNumberOfOrdersBelowTotalTarget() {
        return 0;
    }
    // for each order in the customer order list
    // calculate if order is negative
    // if yes then add 1 to total

    public boolean isMatch(String id) {
        if (person.getPersonId().equals(id)) {
            return true;
        }
        return false;
    }

    public void addCustomerOrder(Order o) {
        orders.add(o);
    }

    @Override
    public String toString() {
        return person.getPersonId();
    }

    public String getCustomerId() {
        return person.getPersonId();
    }

    public Person getPerson() {
        return person;
    }


    
   public Market getMarket() {
        return market;
    }

/*     public AD feedADToC(model.MarketModel.Channel web){
      return market.getMCAByChannel(null).getSolutionOffer().feedRandomADs();
    } */
    
     //feed ad to Costumers with products
    public void feedADToC(Channel c){
        System.out.println("--------------------------------------------");
        System.out.println("| Customer: "+getCustomerId()+" | Market: "+market.getMName()+" | Channel: "+c.getChannelType());
        System.out.println();
        System.out.println("-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-");
       
        System.out.println(market.feedAD(c));
       
        market.feedSOProduct(c);
      }
} 
       