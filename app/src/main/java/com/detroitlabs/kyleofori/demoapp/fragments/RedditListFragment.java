package com.detroitlabs.kyleofori.demoapp.fragments;

/**
 * Created by BFineRocks on 11/17/14.
 */

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.detroitlabs.kyleofori.demoapp.R;
import com.detroitlabs.kyleofori.demoapp.activities.DemoAppMainActivity;
import com.detroitlabs.kyleofori.demoapp.adapters.RedditListAdapter;
import com.detroitlabs.kyleofori.demoapp.backgroundthreadscheduler.RepeatingPostFetchExecutor;
import com.detroitlabs.kyleofori.demoapp.models.RedditTextPost;
import java.util.ArrayList;

public class RedditListFragment extends ListFragment {
    private ArrayList<RedditTextPost> redditPostArrayList;
    private RedditListAdapter redditListAdapter;
    private RepeatingPostFetchExecutor repeater;
    public static Handler postRefreshHandler;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        redditPostArrayList = new ArrayList<RedditTextPost>();
        redditListAdapter = new RedditListAdapter(getActivity(), R.layout.list_item_reddit_post, redditPostArrayList);
        repeater = new RepeatingPostFetchExecutor(getActivity().getIntent().getStringExtra(DemoAppMainActivity.SUBREDDIT_NAME));
        updatePosts(redditPostArrayList);
        postRefreshHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                redditListAdapter.clear();
                redditPostArrayList = msg.getData().getParcelableArrayList("array");
                redditListAdapter.addAll(redditPostArrayList);
                redditListAdapter.notifyDataSetChanged();
            }
        };
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_reddit_list, container, false);
        setListAdapter(redditListAdapter);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("onResume", "On Resume Called");
        repeater.startRepeatingPostFetchExecutor();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Bundle bundle = new Bundle();
        RedditTextPost redditTextPost = redditPostArrayList.get(position);
        bundle.putParcelable("clickedObject", redditTextPost);
        RedditPostFragment redditPostFragment = new RedditPostFragment();
        redditPostFragment.setArguments(bundle);
        getFragmentManager().beginTransaction()
        .replace(R.id.container, redditPostFragment, "post_fragment")
        .addToBackStack("post_fragment")
        .commit();
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("onPause", "On Pause Called");
        repeater.stopRepeatingPostFetchExecutor();
    }

    private void updatePosts(ArrayList<RedditTextPost> updatedPostsList) {
        redditPostArrayList = updatedPostsList;
        redditListAdapter.notifyDataSetChanged();
    }
}