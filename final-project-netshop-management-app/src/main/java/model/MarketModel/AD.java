package model.MarketModel;

import java.util.ArrayList;

import model.ProductManagement.Product;
import model.ProductManagement.SolutionOffer;

public class AD {
    String ad;
    MarketChannelAssignment mAssignment;
    SolutionOffer so;

    public AD(String ad, SolutionOffer so) {
        this.ad = ad;
        this.so = so;
    }

    public String getAd() {
        return ad;
    }

    public SolutionOffer getSo() {
        return so;
    }
    
    //get product list form solution offer
    public ArrayList<Product> getProductsList(){
       return so.getProducts();
    }
}
