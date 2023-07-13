/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.CustomerManagement;

import java.util.ArrayList;
import java.util.Random;

import model.Business.Business;
import model.MarketModel.Market;
import model.Personnel.Person;

/**
 *
 * @author kal bugrara
 */
public class CustomerDirectory {

    Business business;
    ArrayList<CustomerProfile> customerlist;

    public CustomerDirectory(Business d) {

        business = d;
        customerlist = new ArrayList<CustomerProfile>();

    }

    public CustomerProfile newCustomerProfile(Person p, Market m) {

        CustomerProfile sp = new CustomerProfile(p,m);
        customerlist.add(sp);
        return sp;
    }

    public CustomerProfile findCustomer(String id) {

        for (CustomerProfile sp : customerlist) {

            if (sp.isMatch(id)) {
                return sp;
            }
        }
        return null; // not found after going through the whole list
    }

    public CustomersReport generatCustomerPerformanceReport() {
        CustomersReport customersreport = new CustomersReport();

        for (CustomerProfile cp : customerlist) {

            CustomerSummary cs = new CustomerSummary(cp);
            customersreport.addCustomerSummary(cs);
        }
        return customersreport;
    }

    public ArrayList<CustomerProfile> getCustomerlist() {
        return customerlist;
    }
    
    public CustomerProfile pickRandomC() {
        Random r = new Random();
        int randomIndex = (r.nextInt(customerlist.size()/2)+r.nextInt(customerlist.size()/2));
        return customerlist.get(randomIndex);
    }


}
