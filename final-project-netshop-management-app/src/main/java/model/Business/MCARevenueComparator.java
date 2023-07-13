package model.Business;


import java.util.Comparator;

import model.MarketModel.MarketChannelAssignment;
import model.OrderManagement.MasterOrderList;


public class MCARevenueComparator implements Comparator<MarketChannelAssignment> {
    MasterOrderList ml;
    @Override

    public int compare(MarketChannelAssignment o1, MarketChannelAssignment o2) {
        //int ordering = -1; 
        // if (sortingOrder == "desc") ordering = -1;
        //return (ordering) * Integer.compare(o1.getTotalVolume(),o2.getTotalVolume());
        return -1 * Integer.compare(ml.getSaleRevenueByMarketChannelCombo(o1),ml.getSaleRevenueByMarketChannelCombo(o2));
    }

}