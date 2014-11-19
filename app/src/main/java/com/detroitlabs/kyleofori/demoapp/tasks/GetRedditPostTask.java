package com.detroitlabs.kyleofori.demoapp.tasks;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

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
public class GetRedditPostTask extends AsyncTask<String, Void, ArrayList<String>> {
    private static final String LOG_TAG = GetRedditPostTask.class.getSimpleName();
    String jsonText = null;

    @Override
    protected ArrayList<String> doInBackground(String... strings) {
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

        return new ArrayList<String>();
    }

    @Override
    protected void onPostExecute(ArrayList<String> strings) {
        Log.d(LOG_TAG, strings.toString());
    }
}
