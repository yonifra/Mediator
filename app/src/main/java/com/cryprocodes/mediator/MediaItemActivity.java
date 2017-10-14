package com.cryprocodes.mediator;

import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cryprocodes.mediator.Adapters.RssFeedListAdapter;
import com.cryprocodes.mediator.Models.RssFeedModel;
import com.squareup.picasso.Picasso;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MediaItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_item);
    }

    private void InitializeFields(Bundle bundle) {
        TextView titleTv = findViewById(R.id.titleTextView);
        TextView plotTv = findViewById(R.id.descriptionTextView);
        ImageView posterImage = findViewById(R.id.posterImageView);

        titleTv.setText(bundle.getString("title"));
        plotTv.setText(bundle.getString("plot"));
        //posterImage.setImageURI(Uri.parse(bundle.getString("posterUrl")));
        Picasso.with(this).load(bundle.getString("posterUrl")).into(posterImage);

    }
}
