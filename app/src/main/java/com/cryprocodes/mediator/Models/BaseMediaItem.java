package com.cryprocodes.mediator.Models;

public abstract class BaseMediaItem {

    public String rawTitle;
    public String imdbId;
    public String tmdbId;
    public String plot;
    public String originalReleaseDate;
    public String torrentReleaseDate;
    public String resolution;

    public String extractTitle() {
        return TitleParser.parseTitle(rawTitle);
    }

    public abstract String getPlot();

}
