package com.detroitlabs.kyleofori.demoapp.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.detroitlabs.kyleofori.demoapp.R;
import com.detroitlabs.kyleofori.demoapp.fragments.RedditListFragment;

public class SubRedditActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_reddit);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new RedditListFragment(), "list_fragment")
                    .commit();
        }
    }
}
