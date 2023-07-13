/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.MarketModel;

import java.util.ArrayList;



import model.ProductManagement.Product;
import model.ProductManagement.SolutionOffer;
import model.ProductManagement.SolutionOfferCatalog;

/**
 *
 * @author kal bugrara
 */
public class Market {

  SolutionOfferCatalog so; //?? maybe get arraylist fromm solutionOffer catalog
  

  //ArrayList<MarketChannelAssignment> 
  MarketChannelComboCatalog channels;
  ArrayList<String> characteristics;
  ArrayList<Market> submarkets;
  String mName;
  //int size;
  
  public Market(String name) {
    mName = name;
    characteristics = new ArrayList<String>();
    channels = new MarketChannelComboCatalog();
    submarkets = new ArrayList<Market>();
    so = new SolutionOfferCatalog(); 
  }
  

  public void addCharacteristics(String ch){
    characteristics.add(ch);
  }

   //maybe some logic problems
  public void addSubMarket(String name){
    Market sub = new Market(name);
    submarkets.add(sub);
  }
 

  public String getMName(){
    return mName;
  }

   public MarketChannelComboCatalog getChannels() {
    return channels;
  }
  
   //get Mca by index
   public MarketChannelAssignment getMCAByIndex(int i){
     return channels.getMCAList().get(i);
   }
   
   public MarketChannelAssignment getMCAByChannel(Channel c){
     return channels.findMarketChannelAssignmentByC(c);
  }
  
   public SolutionOffer findSolutionOfferByc (Channel web){
    for (SolutionOffer sos : so.getSolutionOffers()) 
    if (getMCAByChannel(web)== sos.getMarketchannelcomb() ){
      return sos;
    }
    return null;
   }

  
   public SolutionOfferCatalog getSo() {
    return so;
   }
   
   //get solution offer  by index
   public SolutionOffer getSOByIndex(int i){
    return so.getSolutionOffers().get(i);
   }
   

   public Market matchMarket(String country){
    if (country == getMName() ){
      return this;
    }

    return null;
   }

   public String feedAD(Channel c){
   
    return findSolutionOfferByc(c).feedRandomADs();
    
  }

/*   public void sortByRulesMCA() {
    MCARevenueComparator comparator = new MCARevenueComparator();
    Collections.sort(channels, comparator);
  } */
  

  public void feedSOProduct(Channel c){
    SolutionOffer so = findSolutionOfferByc(c);
    System.out.println("-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-");
    System.out.println();
    for (Product p : so.getProducts()){
      System.out.print(" + ");
      System.out.print("{ "+p.getName()+" } ");
      
    }
    System.out.println("Package Deal Price:"+so.countPrice()+" || "+(int)((1-(so.getDeal()))*100)+" % off!!!");
    System.out.println("");
  }


}
