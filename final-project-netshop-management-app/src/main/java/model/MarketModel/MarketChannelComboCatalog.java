package model.MarketModel;

import java.util.ArrayList;

public class MarketChannelComboCatalog {

    ArrayList<MarketChannelAssignment> MCAList;

    public MarketChannelComboCatalog() {
        MCAList = new ArrayList<>();
    }

    public MarketChannelAssignment addMarketChannelAssignment(Market m, Channel c) {
        MarketChannelAssignment MCA = new MarketChannelAssignment(m, c);
        MCAList.add(MCA);
        return MCA;
    }

    public MarketChannelAssignment findMarketChannelAssignment(Market m, Channel c) {
        for (MarketChannelAssignment MCA : MCAList) {
            if (MCA.matchBoolean(m, c))
                return MCA;

        }
        return null;
    }

    public MarketChannelAssignment findMarketChannelAssignmentByC(Channel c) {
        for (MarketChannelAssignment MCA : MCAList) {
            if (MCA.matchsC(c))
                return MCA;

        }
        return null;
    }

    public ArrayList<MarketChannelAssignment> getMCAList() {
        return MCAList;
    }

}
