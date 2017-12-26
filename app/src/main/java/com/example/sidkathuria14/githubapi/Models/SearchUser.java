package com.example.sidkathuria14.githubapi.Models;

/**
 * Created by sidkathuria14 on 25/12/17.
 */

public class SearchUser {
    int total_count;
    String incomplete_results;
    Items[] items;

    public int getTotal_count() {
        return total_count;
    }

    public String getIncomplete_results() {
        return incomplete_results;
    }

    public Items[] getItems() {
        return items;
    }
}
