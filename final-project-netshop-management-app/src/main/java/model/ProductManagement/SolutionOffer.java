/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.ProductManagement;
import java.util.ArrayList;
import java.util.Random;

import model.MarketModel.AD;
import model.MarketModel.MarketChannelAssignment;

/**
 *
 * @author kal bugrara
 */
public class SolutionOffer {

  ArrayList<Product> products;
  double deal; // ex 20%off
  int price; 
  MarketChannelAssignment marketchannelcomb;
  ArrayList<AD> adList;

 
  //total bundle price(a solution offer can be thinks of one bundle)
  public SolutionOffer(MarketChannelAssignment m,double deal) {
    this.deal = deal;
    marketchannelcomb = m;
    products = new ArrayList<Product>();
    adList = new ArrayList<AD>();
  }

  public void addProduct(Product p) {
    products.add(p);
  }
 

  public void addAD(String slogan){
    AD ad1 = new AD(slogan, this);
    adList.add(ad1);
    
  }

  //total bundle price
  public int countPrice() {
    int price =0 ;
    for (Product p : products){
      price =  (int) (price + Math.round(p.getCeilingPrice()*deal));}
    return price;
  } 

  public int getPrice() {
    return price;
  }

  public ArrayList<Product> getProducts() {
    return products;
  }

  public double getDeal() {
    return deal;
  }
  
  public MarketChannelAssignment getMarketchannelcomb() {
    return marketchannelcomb;
  }
 /*  public Market getMarket(){
    return marketchannelcomb.getMarket();
  } */
  public SolutionOffer findSolutionOffer(
    MarketChannelAssignment mca){
    if (mca == marketchannelcomb){
      return this;
    }
    return null;
  }

  // feed ad base on market-channel assignment   
  public String feedRandomADs(){
    Random r = new Random();
      return adList.get(r.nextInt(adList.size())).getAd();
    }
    
  
  
  
}
