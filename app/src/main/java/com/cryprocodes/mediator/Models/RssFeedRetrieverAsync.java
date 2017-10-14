package com.cryprocodes.mediator.Models;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;

import com.cryprocodes.mediator.MediaItemRecyclerViewAdapter;
import com.cryprocodes.mediator.R;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.cryprocodes.mediator.MainActivity.getAndroidPitRssFeed;

public class RssFeedRetrieverAsync extends AsyncTask<Void, Void, List<BaseMediaItem>> {

    private Activity mCallingActivity;
    private static final String TV_SHOWS_FEED_URL = "http://www.scnsrc.me/category/tv/feed/";
    private static final String MOVIES_FEED_URL = "http://www.scnsrc.me/category/films/feed/";
    private static final String ALL_FEED_URL = "http://feeds.feedburner.com/scnsrc/rss?format=xml";
    private static String CURRENT_RSS_FEED = null;
    private List<BaseMediaItem> itemsToShow;

    public RssFeedRetrieverAsync(Activity callingActivity) {
        mCallingActivity = callingActivity;
        itemsToShow = new ArrayList<>();
        PopulateWithDummyContent();
    }

    private void PopulateWithDummyContent() {
        MovieItem movie = new MovieItem();
        movie.rawTitle = "The Wall 2017 1080p 5.1CH DOLBY-HD";
        movie.tmdbId = "405775";
        movie.imdbId = "tt4218696";
        movie.rating = "6.2";
        movie.posterUrl = "https://images-na.ssl-images-amazon.com/images/M/MV5BMTc5ODMyNzE4OF5BMl5BanBnXkFtZTgwNTM0Mzc4MTI@._V1_UX182_CR0,0,182,268_AL_.jpg";
        movie.plot = "Two American Soldiers are trapped by a lethal sniper, with only an unsteady wall between them.";
        itemsToShow.add(movie);
    }

    public List<BaseMediaItem> getMovies(int count) {
        return itemsToShow;
    }

    public List<BaseMediaItem> getShows(int count) {
        return itemsToShow;
    }

    @Override
    protected List<BaseMediaItem> doInBackground(Void... voids) {
        List<BaseMediaItem> result = null;
        try {
            String feed = getAndroidPitRssFeed();

            if (feed == null) {
                return new ArrayList<>();
            } else {
                result = parse(feed);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private List<BaseMediaItem> parse(String rssFeed) throws Exception {
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        XmlPullParser xpp = factory.newPullParser();
        xpp.setInput(new StringReader(rssFeed));
        xpp.nextTag();
        return readRss(xpp);
    }

    private List<BaseMediaItem> readRss(XmlPullParser parser)
            throws Exception {
        List<BaseMediaItem> items = new ArrayList<>();
        parser.require(XmlPullParser.START_TAG, null, "rss");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("channel")) {
                items.addAll(readChannel(parser));
            } else {
                skip(parser);
            }
        }
        return items;
    }

    private List<BaseMediaItem> readChannel(XmlPullParser parser)
            throws IOException, XmlPullParserException {
        List<BaseMediaItem> items = new ArrayList<>();
        parser.require(XmlPullParser.START_TAG, null, "channel");

        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }

            String name = parser.getName();

            if (name.equals("item")) {
                BaseMediaItem newItem = readItem(parser);
                boolean added = false;

                if (newItem.getClass() == MovieItem.class) {
                    for (BaseMediaItem item : items) {
                        if (item.extractTitle().equals(newItem.extractTitle())) {
                            item.resolutions.add(newItem.extractResolution());
                            added = true;
                            break;
                        }
                    }

                    if (!added) {
                        items.add(newItem);
                    }
                } else {
                    items.add(newItem);
                }
            } else {
                skip(parser);
            }
        }
        return items;
    }

    private BaseMediaItem readItem(XmlPullParser parser) throws XmlPullParserException, IOException {

        BaseMediaItem result;
        Category category = readCategory(parser);

        if (category == Category.MOVIE) {
            result = new MovieItem();
        }
        else {
            result = new TvItem();
        }

        parser.require(XmlPullParser.START_TAG, null, "item");

        while (parser.next() != XmlPullParser.END_TAG) {

            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }

            String name = parser.getName();

            switch (name) {
                case "title":
                    result.rawTitle = readTitle(parser);
                    break;
                case "pubDate":
                    result.torrentReleaseDate = readPubDate(parser);
                    break;
                case "category":
                    result.category = readCategory(parser);

                    if (result.category == Category.MOVIE) {
                        result = new MovieItem(result);
                    } else if (result.category == Category.TV) {
                        result = new TvItem(result);
                    }
                    break;
                default:
                    skip(parser);
                    break;
            }
        }

        return result;
    }

    // Processes title tags in the feed.
    private String readTitle(XmlPullParser parser)
            throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, null, "title");
        String title = readText(parser);
        parser.require(XmlPullParser.END_TAG, null, "title");

        return title;
    }

    private String readCreator(XmlPullParser parser)
            throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, null, "dc:creator");
        String creator = readText(parser);
        parser.require(XmlPullParser.END_TAG, null, "dc:creator");

        return creator.replace("&#124;", "&");
    }

    private String readPubDate(XmlPullParser parser)
            throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, null, "pubDate");
        String dateStr = readText(parser);
        parser.require(XmlPullParser.END_TAG, null, "pubDate");

        Date date;
        try {
            date = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH).parse(dateStr);
            return date.toString();
        } catch (ParseException e) {
            return dateStr.substring(0, dateStr.length() - 6);
        }
    }

    private Category readCategory(XmlPullParser parser)//, Category currentCategory)
            throws IOException, XmlPullParserException {

        parser.require(XmlPullParser.START_TAG, null, "category");
        String category = readText(parser);
        parser.require(XmlPullParser.END_TAG, null, "category");

        if (category == null) {
            return Category.UNDEFINED;
        }

        if (category.contains("TV")) {
            return Category.TV;
        } else if (category.contains("Movies")) {
            return Category.MOVIE;
        }

        // for the extreme case we couldn't parse the category
        return Category.UNDEFINED;
    }

    private String readText(XmlPullParser parser)
            throws IOException, XmlPullParserException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        return result;
    }

    private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }
        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
    }

    @Override
    protected void onPostExecute(List<BaseMediaItem> rssFeed) {
        Context context = mCallingActivity.getApplicationContext();
        if (rssFeed != null && mCallingActivity != null) {
            if (rssFeed.size() == 0) {
//                Snackbar.make(mCallingActivity.findViewById(R.id.container),
//                        context.getString(R.string.CheckInternetConn),
//                        Snackbar.LENGTH_LONG).show();
                return;
            }

            RecyclerView recyclerView = mCallingActivity.findViewById(R.id.recyclerView);

            if (recyclerView != null) {
                recyclerView.setAdapter(new MediaItemRecyclerViewAdapter(itemsToShow, null));
            }
        }
    }
}
