package com.detroitlabs.kyleofori.demoapp.tasks;

import android.net.Uri;
import android.util.Log;

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

/**
 * Created by kyleofori on 11/18/14.
 */
public class GetRedditPostTask implements Runnable /*extends AsyncTask<String, Void, ArrayList<RedditTextPost>> */{
    private static final String LOG_TAG = GetRedditPostTask.class.getSimpleName();
    RedditTextPost redditTextPost;
    RedditJSONParser redditJSONParser = new RedditJSONParser();
    String jsonText = null;
    String searchTerm;

    public GetRedditPostTask(String searchTerm){
     this.searchTerm = searchTerm;
    }

    @Override
    public void run() {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("http")
                .authority("api.reddit.com")
                .appendPath("r")
                .appendPath(searchTerm);
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
                return;
            }

            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            while ((line = bufferedReader.readLine())!=null) {
                stringBuffer.append(line+"\n");
            }

            if(stringBuffer.length()==0) {
                return;
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
            redditTextPost = redditJSONParser.convertJSONStringToRedditObjects(jsonText);

        }catch(JSONException e){
            e.printStackTrace();
        }

    }

}
