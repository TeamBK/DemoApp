package com.detroitlabs.kyleofori.demoapp.parsers;

import com.detroitlabs.kyleofori.demoapp.arraylists.RedditPostArrayList;
import com.detroitlabs.kyleofori.demoapp.models.RedditTextPost;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by kyleofori on 11/18/14.
 */
public class RedditJSONParser {
    RedditTextPost redditTextPost;
    RedditPostArrayList redditPostArrayList;


    public void convertJSONStringToRedditObject(String JSONString) throws JSONException{
        redditPostArrayList = new RedditPostArrayList();
        final String redditDataObject = "data";
        final String redditChildrenArray = "children";
        final String redditPostAuthor = "author";
        final String redditPostTitle = "title";
        final String redditPostText = "selftext";
        final String redditPostUrl = "url";

        JSONObject jsonObject = new JSONObject(JSONString);
        JSONObject jsonDataObject = jsonObject.getJSONObject(redditDataObject);
        JSONArray jsonChildrenArray = jsonDataObject.getJSONArray(redditChildrenArray);

        for(int i = 0; i < jsonChildrenArray.length(); i++){
            JSONObject currentRedditObject = jsonChildrenArray.getJSONObject(i);
            JSONObject currentRedditObjectData = currentRedditObject.getJSONObject(redditDataObject);
            String currentRedditPostAuthor = currentRedditObjectData.getString(redditPostAuthor);
            String currentRedditPostTitle = currentRedditObjectData.getString(redditPostTitle);
            String currentRedditPostText = currentRedditObjectData.getString(redditPostText);
            String currentRedditPostUrl = currentRedditObjectData.getString(redditPostUrl);

            redditTextPost = new RedditTextPost(currentRedditPostTitle, currentRedditPostAuthor,
                    currentRedditPostText, currentRedditPostUrl);

            addNewRedditPostsToArrayList(redditTextPost);
        }

    }

    public void addNewRedditPostsToArrayList(RedditTextPost redditTextPost){
        redditPostArrayList.addNewRedditPostToArrayList(redditTextPost);
    }

    public RedditPostArrayList getRedditTextPostArrayList(){
        return redditPostArrayList;
    }


}
