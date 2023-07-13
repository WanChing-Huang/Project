/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.ProductManagement;

import java.util.ArrayList;

import model.MarketModel.MarketChannelAssignment;

/**
 *
 * @author kal bugrara
 */
public class SolutionOfferCatalog {
    ArrayList<SolutionOffer> solutionOffers;

    public SolutionOfferCatalog() {
        solutionOffers = new ArrayList<SolutionOffer>();
    }

    public SolutionOffer addSolutionOffer(MarketChannelAssignment m, Double double1) {
        SolutionOffer so = new SolutionOffer(m, double1);
        solutionOffers.add(so);
        return so;

    }

    public SolutionOffer findSolutionOffer(MarketChannelAssignment m) {
        for (SolutionOffer so : solutionOffers) {
            if (so.getMarketchannelcomb() == m)
                return so;

        }
        return null;
    }

    public ArrayList<SolutionOffer> getSolutionOffers() {
        return solutionOffers;
    }

   

    
}
