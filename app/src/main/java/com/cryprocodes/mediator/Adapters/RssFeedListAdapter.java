package com.cryprocodes.mediator.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cryprocodes.mediator.Models.RssFeedModel;
import com.cryprocodes.mediator.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by yonifra on 13/10/17.
 */

public class RssFeedListAdapter
        extends RecyclerView.Adapter<RssFeedListAdapter.FeedModelViewHolder> {

    private List<RssFeedModel> mRssFeedModels;

    public static class FeedModelViewHolder extends RecyclerView.ViewHolder {
        private View rssFeedView;

        public FeedModelViewHolder(View v) {
            super(v);
            rssFeedView = v;
        }
    }

    public RssFeedListAdapter(List<RssFeedModel> rssFeedModels) {
        mRssFeedModels = rssFeedModels;
    }

    @Override
    public FeedModelViewHolder onCreateViewHolder(ViewGroup parent, int type) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_mediaitem, parent, false);
        return new FeedModelViewHolder(v);
    }

    @Override
    public void onBindViewHolder(FeedModelViewHolder holder, int position) {
        final RssFeedModel rssFeedModel = mRssFeedModels.get(position);
        ((TextView)holder.rssFeedView.findViewById(R.id.itemNameTextView)).setText(rssFeedModel.title);
        ((ImageView)holder.rssFeedView.findViewById(R.id.categoryIcon)).setImageDrawable(holder.rssFeedView.getResources().getDrawable(R.drawable.ic_movie_black_24dp));
//        WebView webview = ((WebView)holder.rssFeedView.findViewById(R.id.text_description));
//        webview.getSettings().setJavaScriptEnabled(true);
//        webview.loadDataWithBaseURL("", rssFeedModel.description, "text/html", "UTF-8", "");

        Date date = null;
        try {
            date = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss").parse(rssFeedModel.pubDate);
        } catch (ParseException e) {
            date = null;
            e.printStackTrace();
        }

        if (date == null) {
            ((TextView) holder.rssFeedView.findViewById(R.id.itemReleaseDateTextView)).setText(rssFeedModel.pubDate);
        }
        else {
            SimpleDateFormat day = new SimpleDateFormat("dd-MMM-yyyy", Locale.US);
            SimpleDateFormat time = new SimpleDateFormat("HH:mm", Locale.US);
            ((TextView) holder.rssFeedView.findViewById(R.id.itemReleaseDateTextView)).setText(String.format("%s %s", day.format(date), time.format(date)));
        }
    }

    @Override
    public int getItemCount() {
        return mRssFeedModels.size();
    }
}
