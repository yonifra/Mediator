package com.cryprocodes.mediator;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MediaItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_item);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            InitializeFields(bundle);
        }
    }

    private void InitializeFields(Bundle bundle) {
        TextView titleTv = findViewById(R.id.titleTextView);
        TextView plotTv = findViewById(R.id.descriptionTextView);
        ImageView posterImage = findViewById(R.id.posterImageView);

        titleTv.setText(bundle.getString("title"));
        plotTv.setText(bundle.getString("plot"));
        posterImage.setImageURI(Uri.parse(bundle.getString("posterUrl")));
    }
}
