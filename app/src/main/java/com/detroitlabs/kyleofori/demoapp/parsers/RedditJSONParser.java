package com.detroitlabs.kyleofori.demoapp.parsers;

import com.detroitlabs.kyleofori.demoapp.models.RedditTextPost;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by kyleofori on 11/18/14.
 */
public class RedditJSONParser {
    RedditTextPost redditTextPost;

    public String convertJSONStringToRedditObject(String JSONString) throws JSONException{
        final String redditDataObject = "data";
        final String redditChildrenArray = "children";
        final String redditPostAuthor = "author";
        final String redditPostTitle = "title";
        final String redditPostText = "selftext";
        final String redditPostUrl = "url";

        JSONObject jsonObject = new JSONObject(JSONString);
        JSONObject jsonDataObject = jsonObject.getJSONObject(redditDataObject);
        JSONArray jsonChildrenArray = jsonDataObject.getJSONArray(redditChildrenArray);

    }

}
