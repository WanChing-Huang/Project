package model.CustomerManagement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.Random;

import model.MarketModel.Channel;

/**
 *
 * @author kal bugrara
 */
public class ChannelCatalog {
    ArrayList<Channel> channelList;

    public ChannelCatalog() {
        channelList = new ArrayList<>();

    }

    public Channel newChannel(String type, String priceType) {
        Channel c = new Channel(type, priceType);
        channelList.add(c);
        return c;
    }

    public Channel findChannel(String type) {
        for (Channel c : channelList) {
            if (c.getChannelType().equalsIgnoreCase(type))
                return c;
        }
        return null;
    }

    public ArrayList<Channel> getChannelList() {
        return channelList;
    }

    public Channel pickRandomChannel() {
        Random r = new Random();
        int randomIndex = (r.nextInt(channelList.size()));
        return channelList.get(randomIndex);
    }
}
