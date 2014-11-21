package com.detroitlabs.kyleofori.demoapp.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.detroitlabs.kyleofori.demoapp.R;
import com.detroitlabs.kyleofori.demoapp.backgroundthreadscheduler.RepeatingPostFetchExecutor;


public class DemoAppMainActivity extends Activity {
    public static final String SUBREDDIT_NAME = "subredditname";
    private EditText edtSubredditName;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_app_main);
        edtSubredditName = (EditText) findViewById(R.id.edt_subreddit_name);
        btnSubmit = (Button) findViewById(R.id.btn_submit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnSubmit.setEnabled(false);
                String subredditName = edtSubredditName.getText().toString();
                Intent intent = new Intent(DemoAppMainActivity.this, SubRedditActivity.class);
                intent.putExtra(SUBREDDIT_NAME, subredditName);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        btnSubmit.setEnabled(true);
        edtSubredditName.setText("");
    }
}
