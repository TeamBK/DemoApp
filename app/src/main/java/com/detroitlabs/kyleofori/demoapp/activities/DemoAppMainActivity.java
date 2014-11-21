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
    private static final String LOG_TAG = DemoAppMainActivity.class.getSimpleName();
    public static final String SUBREDDIT_NAME = "subredditname";
    private EditText edtSubredditName;
    private Button btnSubmit;
    RepeatingPostFetchExecutor repeatingTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_app_main);
        edtSubredditName = (EditText) findViewById(R.id.edt_subreddit_name);
        btnSubmit = (Button) findViewById(R.id.btn_submit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(LOG_TAG, edtSubredditName.getText().toString());
                btnSubmit.setEnabled(false);
                String subredditName = edtSubredditName.getText().toString();
                Intent intent = new Intent(DemoAppMainActivity.this, SubRedditActivity.class);
                intent.putExtra(SUBREDDIT_NAME, subredditName);
                startActivity(intent);


            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.demo_app, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        btnSubmit.setEnabled(true);
        edtSubredditName.setText("");
    }
}
