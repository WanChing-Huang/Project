/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.MarketModel;



import model.ProductManagement.SolutionOffer;

/**
 *
 * @author kal bugrara
 */
public class MarketChannelAssignment {
    
    Market market;
    Channel channel;

    SolutionOffer solutionOffer;
   
    
    public SolutionOffer getSolutionOffer() {
        if (this == solutionOffer.getMarketchannelcomb()){
            return solutionOffer;
        }
        return null;
    }

    public MarketChannelAssignment(Market m, Channel c){
        
        market = m;
        channel = c;   
    }

    public  MarketChannelAssignment matchs(Market m, Channel c){
        if (market==m && channel==c) return this ;
        else return null;
    }
    public  boolean matchBoolean(Market m, Channel c){
        if (market==m && channel==c) return true ;
        else return false;
    }
    
    public boolean matchsC(Channel c){
        if ( channel==c) return true;
        else return false;
    }
    public Market getMarket() {
        return market;
    }

    public Channel getChannel() {
        return channel;
    }

   
 
}
