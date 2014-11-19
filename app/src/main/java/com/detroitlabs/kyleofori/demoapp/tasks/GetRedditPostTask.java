package com.detroitlabs.kyleofori.demoapp.tasks;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.detroitlabs.kyleofori.demoapp.arraylists.RedditPostArrayList;
import com.detroitlabs.kyleofori.demoapp.models.RedditTextPost;
import com.detroitlabs.kyleofori.demoapp.parsers.RedditJSONParser;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by kyleofori on 11/18/14.
 */
public class GetRedditPostTask extends AsyncTask<String, Void, ArrayList<RedditTextPost>> {
    private static final String LOG_TAG = GetRedditPostTask.class.getSimpleName();
    RedditJSONParser redditJSONParser = new RedditJSONParser();
    String jsonText = null;

    @Override
    protected ArrayList<RedditTextPost> doInBackground(String... strings) {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("http")
                .authority("api.reddit.com")
                .appendPath("r")
                .appendPath(strings[0]);
        String urlString = builder.build().toString();

        HttpURLConnection connection = null;
        URL url;
        String line;
        BufferedReader bufferedReader = null;
        try {
            url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            InputStream inputStream = connection.getInputStream();
            StringBuffer stringBuffer = new StringBuffer();
            if(inputStream == null) {
                return null;
            }

            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            while ((line = bufferedReader.readLine())!=null) {
                stringBuffer.append(line+"\n");
            }

            if(stringBuffer.length()==0) {
                return null;
            }

            jsonText = stringBuffer.toString();
            Log.d(LOG_TAG, jsonText);
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        finally {
            if(connection!=null) {
                connection.disconnect();
            }
            if(bufferedReader!=null) {
                try{
                    bufferedReader.close();
                } catch (final IOException e) {
                    e.printStackTrace();
                }
            }
        }
        try{
            redditJSONParser.convertJSONStringToRedditObject(jsonText);
            return redditJSONParser.getRedditTextPostArrayList();

        }catch(JSONException e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<RedditTextPost> redditPostArrayList) {
        super.onPostExecute(redditPostArrayList);
    }
}
