package com.detroitlabs.kyleofori.demoapp.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.detroitlabs.kyleofori.demoapp.R;
import com.detroitlabs.kyleofori.demoapp.models.RedditTextPost;

/**
 * Created by BFineRocks on 11/17/14.
 */
public class RedditPostFragment extends Fragment{
    private TextView txt_author;
    private TextView txt_title;
    private TextView txt_description;
    private TextView txt_url;
    private RedditTextPost redditTextPost;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            redditTextPost = getArguments().getParcelable("clickedObject");
            View rootView = inflater.inflate(R.layout.fragment_single_item, container, false);
            txt_author = (TextView) rootView.findViewById(R.id.fragment_single_item_author);
            txt_title = (TextView) rootView.findViewById(R.id.fragment_single_item_title);
            txt_description = (TextView) rootView.findViewById(R.id.fragment_single_item_description);
            txt_url = (TextView) rootView.findViewById(R.id.fragment_single_item_url);
            txt_author.setText(redditTextPost.getAuthor());
            txt_title.setText(redditTextPost.getTitle());
            if(redditTextPost.getText().equals("")){
                txt_url.setText(redditTextPost.getUrl());
            }
            else {
                txt_description.setText(redditTextPost.getText());
            }
            return rootView;
        }
}
