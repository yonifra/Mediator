package com.cryprocodes.mediator.Models;

public class MovieItem extends BaseMediaItem {
    public MovieItem() {
        super();
        this.category = Category.MOVIE;
    }

    public MovieItem(BaseMediaItem baseItem) {
        super(baseItem);
        this.category = Category.MOVIE;
    }

    @Override
    public String getPlot() {
        // TODO: Implement this!
        return "Test plot for Movie item";
    }
}
