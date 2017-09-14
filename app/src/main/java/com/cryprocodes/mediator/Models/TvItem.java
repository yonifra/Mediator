package com.cryprocodes.mediator.Models;

public class TvItem extends BaseMediaItem {

    public TvItem() {
        super();
        this.category = Category.TV;
    }

    public TvItem(BaseMediaItem item) {
        super(item);
        this.category = Category.TV;
    }

    @Override
    public String getPlot() {
        // TODO: Change this!
        return "Test plot for a TV item";
    }
}
