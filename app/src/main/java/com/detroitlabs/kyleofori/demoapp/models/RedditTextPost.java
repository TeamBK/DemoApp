package com.detroitlabs.kyleofori.demoapp.models;

/**
 * Created by BFineRocks on 11/17/14.
 */
public class RedditTextPost {
    private String title;
    private String author;
    private String text;
    private String url;

    public RedditTextPost(String title, String author, String text, String url){
        this.title = title;
        this.author = author;
        this.text = text;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getText() {
        return text;
    }

    public String getUrl() {
        return url;
    }
}
