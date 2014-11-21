package com.detroitlabs.kyleofori.demoapp.parsers;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.detroitlabs.kyleofori.demoapp.fragments.RedditListFragment;
import com.detroitlabs.kyleofori.demoapp.models.RedditTextPost;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by kyleofori on 11/18/14.
 */
public class RedditJSONParser {

    public void convertJSONStringToRedditObjects(String JSONString) throws JSONException {
        ArrayList<RedditTextPost> redditPostArrayList = getRedditPosts(JSONString);

        Handler fragmentHandler = RedditListFragment.postRefreshHandler;

        Message arrayListMessage = fragmentHandler.obtainMessage();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("array", redditPostArrayList);
        arrayListMessage.setData(bundle);
        fragmentHandler.sendMessage(arrayListMessage);
    }

    private ArrayList<RedditTextPost> getRedditPosts(String rawJSONString) throws JSONException {
        ArrayList<RedditTextPost> redditPostArrayList = new ArrayList<RedditTextPost>();
        final String redditDataObject = "data";
        final String redditChildrenArray = "children";
        final String redditPostAuthor = "author";
        final String redditPostTitle = "title";
        final String redditPostText = "selftext";
        final String redditPostUrl = "url";

        JSONObject jsonObject = new JSONObject(rawJSONString);
        JSONObject jsonDataObject = jsonObject.getJSONObject(redditDataObject);
        JSONArray jsonChildrenArray = jsonDataObject.getJSONArray(redditChildrenArray);

        RedditTextPost currentRedditTextPost;

        for(int i = 0; i < jsonChildrenArray.length(); i++){
            JSONObject currentRedditObject = jsonChildrenArray.getJSONObject(i);
            JSONObject currentRedditObjectData = currentRedditObject.getJSONObject(redditDataObject);
            String currentRedditPostAuthor = currentRedditObjectData.getString(redditPostAuthor);
            String currentRedditPostTitle = currentRedditObjectData.getString(redditPostTitle);
            String currentRedditPostText = currentRedditObjectData.getString(redditPostText);
            String currentRedditPostUrl = currentRedditObjectData.getString(redditPostUrl);

            currentRedditTextPost= new RedditTextPost(currentRedditPostTitle, currentRedditPostAuthor,
                    currentRedditPostText, currentRedditPostUrl);

            redditPostArrayList.add(currentRedditTextPost);
        }
        return redditPostArrayList;
    }
}
