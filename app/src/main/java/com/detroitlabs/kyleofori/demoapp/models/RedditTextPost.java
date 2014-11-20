package com.detroitlabs.kyleofori.demoapp.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by BFineRocks on 11/17/14.
 */
public class RedditTextPost implements Parcelable {
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.author);
        dest.writeString(this.text);
        dest.writeString(this.url);
    }

    private RedditTextPost(Parcel in) {
        this.title = in.readString();
        this.author = in.readString();
        this.text = in.readString();
        this.url = in.readString();
    }

    public static final Parcelable.Creator<RedditTextPost> CREATOR = new Parcelable.Creator<RedditTextPost>() {
        public RedditTextPost createFromParcel(Parcel source) {
            return new RedditTextPost(source);
        }

        public RedditTextPost[] newArray(int size) {
            return new RedditTextPost[size];
        }
    };
}
