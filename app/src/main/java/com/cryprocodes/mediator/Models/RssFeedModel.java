package com.cryprocodes.mediator.Models;

/**
 * Created by yonifra on 13/10/17.
 */

public class RssFeedModel {

    public String title;
    public String link;
    public String description;

    public RssFeedModel(String title, String link, String description) {
        this.title = title;
        this.link = link;
        this.description = description;
    }
}
