package com.cryprocodes.mediator.Models;

import java.util.List;

/**
 * Created by yonifra on 13/10/17.
 */

public class RssFeedModel {

    public String title;
    public String link;
    public String description;
    public List<String> resolutions;
    public String pubDate;

    public RssFeedModel(String title, String link, String description, List<String> resolutions, String pubDate) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.pubDate = pubDate;
        this.resolutions = resolutions;
    }
}
