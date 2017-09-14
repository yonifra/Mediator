package com.cryprocodes.mediator.Models;

import java.util.ArrayList;

public abstract class BaseMediaItem {

    public String rawTitle;
    public String imdbId;
    public String tmdbId;
    public String plot;
    public String originalReleaseDate;
    public String torrentReleaseDate;
    public String posterUrl;
    public String rating;
    public ArrayList<String> resolutions;
    public Category category;

    public BaseMediaItem(){

    }

    public BaseMediaItem(BaseMediaItem copyItem) {
        this.rawTitle = copyItem.rawTitle;
        this.resolutions = copyItem.resolutions;
        this.plot = copyItem.plot;
        this.imdbId = copyItem.imdbId;
        this.tmdbId = copyItem.tmdbId;
        this.originalReleaseDate = copyItem.originalReleaseDate;
        this.torrentReleaseDate = copyItem.torrentReleaseDate;
    }

    public String extractTitle() {
        return TitleParser.parseTitle(rawTitle);
    }

    public abstract String getPlot();

    public String extractResolution() { return TitleParser.parseQuality(rawTitle);}
}
