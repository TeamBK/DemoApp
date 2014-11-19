package com.detroitlabs.kyleofori.demoapp.arraylists;

import com.detroitlabs.kyleofori.demoapp.models.RedditTextPost;

import java.util.ArrayList;

/**
 * Created by BFineRocks on 11/18/14.
 */
public class RedditPostArrayList {

    ArrayList<RedditTextPost> redditTextPostArrayList;

    public RedditPostArrayList(){
        redditTextPostArrayList = new ArrayList<RedditTextPost>();
    }

    public void addNewRedditPostToArrayList(RedditTextPost redditTextPost){
        redditTextPostArrayList.add(redditTextPost);
    }

    public RedditTextPost getRedditTextPost(int itemNumber){
        return redditTextPostArrayList.get(itemNumber);
    }
}
