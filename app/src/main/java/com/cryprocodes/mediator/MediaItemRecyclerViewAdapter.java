package com.cryprocodes.mediator;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cryprocodes.mediator.MediaItemFragment.OnListFragmentInteractionListener;
import com.cryprocodes.mediator.Models.BaseMediaItem;
import com.cryprocodes.mediator.dummy.DummyContent.DummyItem;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MediaItemRecyclerViewAdapter extends RecyclerView.Adapter<MediaItemRecyclerViewAdapter.ViewHolder> {

    private final List<BaseMediaItem> mValues;
    private final OnListFragmentInteractionListener mListener;
    private Context context;

    public MediaItemRecyclerViewAdapter(List<BaseMediaItem> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context)
                .inflate(R.layout.fragment_mediaitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        BaseMediaItem item = mValues.get(position);
        holder.mItem = item;
        holder.itemRatingTextView.setText(item.rating);
        holder.itemTitleTextView.setText(item.rawTitle);
        holder.itemReleaseDateTextView.setText(item.torrentReleaseDate);
     //   holder.itemDescriptionTextView.setText(item.plot);

//        Picasso.with(context)
//                .load(mValues.get(position).posterUrl)
//                .resize(80, 120)
//                .centerCrop()
//                .into(holder.itemPosterImageView);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView itemTitleTextView;
        public final TextView itemReleaseDateTextView;
       // public final ImageView itemPosterImageView;
        public final TextView itemRatingTextView;
     //   public final TextView itemDescriptionTextView;
        public BaseMediaItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            itemTitleTextView = (TextView) view.findViewById(R.id.itemNameTextView);
            itemReleaseDateTextView = (TextView) view.findViewById(R.id.itemReleaseDateTextView);
        //    itemPosterImageView = (ImageView) view.findViewById(R.id.itemPosterImageView);
            itemRatingTextView = (TextView) view.findViewById(R.id.itemRatingTextView);
      //      itemDescriptionTextView = (TextView) view.findViewById(R.id.text_description);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + itemTitleTextView.getText() + "'";
        }
    }
}
