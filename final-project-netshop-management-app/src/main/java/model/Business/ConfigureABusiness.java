/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Business;

import java.util.HashMap;

/**
 *
 * @author kal bugrara
 */

import java.util.Random;

import com.github.javafaker.Faker;

import model.Business.Business;
import model.CustomerManagement.ChannelCatalog;
import model.CustomerManagement.CustomerDirectory;
import model.CustomerManagement.CustomerProfile;
import model.CustomerManagement.CustomerSummary;
import model.CustomerManagement.CustomersReport;
import model.MarketModel.Channel;
import model.MarketModel.Market;
import model.MarketModel.MarketChannelAssignment;
import model.MarketModel.MarketChannelComboCatalog;
import model.MarketingManagement.MarketingPersonDirectory;
import model.MarketingManagement.MarketingPersonProfile;
import model.OrderManagement.MasterOrderList;
import model.OrderManagement.Order;
import model.OrderManagement.OrderItem;
import model.Personnel.EmployeeDirectory;
import model.Personnel.EmployeeProfile;
import model.Personnel.Person;
import model.Personnel.PersonDirectory;
import model.ProductManagement.Product;
import model.ProductManagement.ProductCatalog;
import model.ProductManagement.ProductsReport;
import model.ProductManagement.SolutionOffer;
import model.ProductManagement.SolutionOfferCatalog;
import model.SalesManagement.SalesPersonDirectory;
import model.SalesManagement.SalesPersonProfile;
import model.Supplier.Supplier;
import model.Supplier.SupplierDirectory;
import model.UserAccountManagement.UserAccount;
import model.UserAccountManagement.UserAccountDirectory;

/**
 *
 * @author kal bugrara
 */
public class ConfigureABusiness {

  public static Business initialize(String name, int suppliersNum, int customersNum, int productsNum,
      int totalCustomerOrders,
      int perItemQMin, int perItemQMax) {

    // name = Business name
    // suppliersNum = amount of suppliers that want to generate
    // customersNum = amount of customers that want to generate
    // productsNum = amount of products that want to generate
    // totalCustomerOrders = how many customers to have order
    // perItemQMin = orderItem amount min
    // perItemQMax = orderItem amount max

    Business business = new Business(name);
    SupplierDirectory sd = business.getSupplierDirectory();
    CustomerDirectory cd = business.getCustomerDirectory();
    MasterOrderList mol = business.getMasterOrderList();
    Faker faker = new Faker();

    /*
     * for (CustomerProfile cp :cd.getCustomerlist()){
     * System.out.println(cp.getCustomerId()+cp.getCountry());
     */

    // populate suppliers
    for (int counter = 0; counter < suppliersNum; counter++){
      Supplier s = sd.newSupplier(faker.company().name());
    // populate products for every suppliers
      ProductCatalog pc = s.getProductCatalog();
      for (int i = 0; i < productsNum; i++) {
        int floorPrice = randomInNum(20, 50);
        int ceilingPrice = randomInNum(70, 150);
        int targetPrice = randomInNum(50, 70);
        pc.newProduct(faker.beer().name(), floorPrice, ceilingPrice, targetPrice);
      }
    }
  
    // populate Channel
    ChannelCatalog chCatalog = new ChannelCatalog();
    Channel web = chCatalog.newChannel("Web", "low");
    Channel tv = chCatalog.newChannel("TV", "high");
    Channel store = chCatalog.newChannel("Store", "medium");

    // populate Market
    String[] countryName = { "USA", "China", "Japan", "UK", "Australia" };
    ;
    Market[] markets = new Market[countryName.length];

    for (int i = 0; i < countryName.length; i++) {
      markets[i] = new Market(countryName[i]);
      MarketChannelComboCatalog MCMarkets = markets[i].getChannels();
      // add MAC to market
      MCMarkets.addMarketChannelAssignment(markets[i], web);
      MCMarkets.addMarketChannelAssignment(markets[i], tv);
      MCMarkets.addMarketChannelAssignment(markets[i], store);

      // add market-channel to market
      SolutionOfferCatalog sOMarket = markets[i].getSo();
      sOMarket.addSolutionOffer(markets[i].getMCAByIndex(0), randomInDouble(0.5, 0.7));
      sOMarket.addSolutionOffer(markets[i].getMCAByIndex(1), randomInDouble(0.9, 1));
      sOMarket.addSolutionOffer(markets[i].getMCAByIndex(2), randomInDouble(0.6, 0.8));

      // add product to solution offer
      for (int in = 0; in < markets[i].getChannels().getMCAList().size(); in++) {
        Supplier pickOneSupplier = sd.getRandomSupplier();
        ProductCatalog pickProductCatalog = pickOneSupplier.getProductCatalog();
        SolutionOffer so = markets[i].getSOByIndex(in);
        so.addProduct(pickProductCatalog.pickRandomP());
        so.addProduct(pickProductCatalog.pickRandomP());
        so.addAD("!!!! Deal in " + markets[i].getMName() + "!! ");
        so.addAD(">>>> " + markets[i].getMName() + " Holiday deal !!!");
      }
    }
    Random r = new Random();
    // populate customers

    for (int counter = 0; counter < customersNum; counter++) {
      int i = r.nextInt(countryName.length);
      cd.newCustomerProfile(new Person("Customer " + counter), markets[i]);
    }

    Order[] orders = new Order[totalCustomerOrders];
    System.out.println("------------Feed Random AD to Random Customers-------------");
    // pick random customers
    for (int counter = 0; counter < totalCustomerOrders; counter++) {
      CustomerProfile somebody = cd.pickRandomC();
      // pick customers to feed AD
      // pick random channel for customer
      Channel ch = chCatalog.pickRandomChannel();
      Order order = orders[counter];
      System.out.println();
      somebody.feedADToC(ch);

      // add solution order to somebody
      order = mol.newOrder(somebody);
      SolutionOffer so = somebody.getMarket().findSolutionOfferByc(ch);
      order.newSolutionOrderItem(so, perItemQMax);

      // add 1-5 items to non-deal item
      int randomIndex = (1 + r.nextInt(5));
      for (int i = 0; i < randomIndex; i++) {
        Supplier pickOneSupplier = sd.getRandomSupplier();
        ProductCatalog pickProductCatalog = pickOneSupplier.getProductCatalog();
        OrderItem[] orderItems = new OrderItem[randomIndex];
        int actualPrice = randomInNum(perItemQMin, perItemQMax);
        orderItems[i] = order.newOrderItem(pickProductCatalog.pickRandomP(), actualPrice,
            randomInNum(perItemQMin, perItemQMax));
      }
    }
    System.out.println("");
    System.out.println("------------Product Report-------------");
    //generate product report and print 
    for (Supplier s : sd.getSuplierList()) {
      ProductsReport productsReport = s.getProductCatalog().generatProductPerformanceReport();
      System.out.println("");
      System.out.printf("%-8s:%-10s %n", "Supplier", s.getName());
      productsReport.printReport();
      }

    System.out.println("");
    System.out.println("------------Revenue by MarketChannelAssignment-------------");
  
    for (int i = 0; i < countryName.length; i++) {
      for (MarketChannelAssignment mca : markets[i].getChannels().getMCAList()) {
        System.out.printf("%-15s|%-10s+%-6s | %-8s  %-15s %n", "MarketChannel ", mca.getMarket().getMName(),
            mca.getChannel().getChannelType(), " Revenue: ", mol.getSaleRevenueByMarketChannelCombo(mca));
      }
    }
    System.out.println("");
    System.out.println("------------Revenue by Market-------------");

    for (int i = 0; i < countryName.length; i++) {
      Market m = markets[i];
      System.out.printf("%-8s|%-10s| %-8s  %-15s %n", "Market ", m.getMName(),
           " Revenue: ", mol.getSaleRevenueByMarket(m));
    }
    System.out.println("");
    System.out.println("------------Revenue by Channel-------------");
    for (Channel c :chCatalog.getChannelList()) {
     
      System.out.printf("%-8s|%-10s| %-8s  %-15s %n", "Channel ", c.getChannelType(),
           " Revenue: ", mol.getSaleRevenueByChannel(c));
    }


     return business;
  }

 

  

  public static int randomInNum(int min, int max) {
    Random random = new Random();
    return min + random.nextInt(max - min);
  }

  public static Double randomInDouble(double min, double max) {
    Random random = new Random();
    return min + Math.round(random.nextDouble(max - min) * 100) * 0.01;
  }

}
