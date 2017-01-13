package com.vlad_romanchuk.o_movies.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ListItem implements Parcelable {

    private String id;
    private String name;
    private String backdropPath;
    private String postPath;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getPostPath() {
        return postPath;
    }

    public void setPostPath(String postPath) {
        this.postPath = postPath;
    }

    @Override
    public String toString() {
        return "ListItem{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", backdropPath='" + backdropPath + '\'' +
                ", postPath='" + postPath + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(backdropPath);
        dest.writeString(name);
        dest.writeString(postPath);
    }

    protected ListItem(Parcel in) {
        id = in.readString();
        name = in.readString();
        backdropPath = in.readString();
        postPath = in.readString();
    }

    public static final Creator<ListItem> CREATOR = new Creator<ListItem>() {
        @Override
        public ListItem createFromParcel(Parcel in) {
            return new ListItem(in);
        }

        @Override
        public ListItem[] newArray(int size) {
            return new ListItem[size];
        }
    };

}

