package com.detroitlabs.kyleofori.demoapp.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.detroitlabs.kyleofori.demoapp.R;

/**
 * Created by BFineRocks on 11/17/14.
 */
public class RedditPostFragment extends Fragment{

        public RedditPostFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_reddit_post, container, false);
            return rootView;
        }

}
