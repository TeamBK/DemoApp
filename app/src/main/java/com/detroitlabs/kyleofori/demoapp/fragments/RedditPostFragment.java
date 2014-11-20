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
    TextView txt_author;
    TextView txt_title;
    TextView txt_description;
    TextView txt_url;
    RedditTextPost redditTextPost;


        public RedditPostFragment() {

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

   //         Bundle bundle = getArguments().getBundle("clickedObject");
   //         redditTextPost = bundle.getParcelable("clickedObject");

            redditTextPost = getArguments().getParcelable("clickedObject");

            View rootView = inflater.inflate(R.layout.fragment_single_item, container, false);
            txt_author = (TextView) rootView.findViewById(R.id.fragment_single_item_author);
            txt_title = (TextView) rootView.findViewById(R.id.fragment_single_item_title);
            txt_description = (TextView) rootView.findViewById(R.id.fragment_single_item_description);
            txt_url = (TextView) rootView.findViewById(R.id.fragment_single_item_url);

            txt_author.setText(redditTextPost.getAuthor());
            txt_title.setText(redditTextPost.getTitle());
            txt_description.setText(redditTextPost.getText());
            txt_url.setText(redditTextPost.getUrl());

            return rootView;
        }
}
