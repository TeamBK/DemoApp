package com.detroitlabs.kyleofori.demoapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.detroitlabs.kyleofori.demoapp.R;
import com.detroitlabs.kyleofori.demoapp.models.RedditTextPost;

import java.util.ArrayList;

public class RedditListAdapter extends ArrayAdapter<RedditTextPost> {

    public RedditListAdapter(Context context, int resource, ArrayList<RedditTextPost> posts) {
        super(context, resource, posts);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View redditPostListItem = layoutInflater.inflate(R.layout.list_item_reddit_post, parent, false);
        RedditTextPost redditTextPost = getItem(position);

        TextView authorText = (TextView) redditPostListItem.findViewById(R.id.list_item_author);
        authorText.setText(redditTextPost.getAuthor());
        TextView titleText = (TextView) redditPostListItem.findViewById(R.id.list_item_title);
        titleText.setText(redditTextPost.getTitle());

        return redditPostListItem;
    }

}
