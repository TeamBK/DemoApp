package com.detroitlabs.kyleofori.demoapp.fragments;

/**
 * Created by BFineRocks on 11/17/14.
 */

import android.app.Activity;
import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.detroitlabs.kyleofori.demoapp.R;
import com.detroitlabs.kyleofori.demoapp.adapters.RedditListAdapter;
import com.detroitlabs.kyleofori.demoapp.models.RedditTextPost;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class RedditListFragment extends ListFragment {
    ArrayList<RedditTextPost> redditTextPosts;
    RedditListAdapter redditListAdapter;

    public RedditListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_reddit_list, container, false);
      //  ListView redditPostList = getListView();
        setListAdapter(redditListAdapter);
     //   redditListAdapter.notifyDataSetChanged();
        return rootView;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        redditTextPosts= new ArrayList<RedditTextPost>();
        RedditTextPost dummyPost1 = new RedditTextPost("fake title", "fake author", "no text", "url");
        RedditTextPost dummyPost2 = new RedditTextPost("fake title", "fake author", "no text", "url");
        redditTextPosts.add(dummyPost1);
        redditTextPosts.add(dummyPost2);
        redditListAdapter = new RedditListAdapter(getActivity(), R.layout.list_item_reddit_post, redditTextPosts);

    }
}