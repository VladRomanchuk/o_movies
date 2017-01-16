package com.vlad_romanchuk.o_movies.model;


import android.os.Parcel;
import android.os.Parcelable;

public class Language implements Parcelable {

    private String name;
    private String iso_639_1;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getIso_639_1() {
        return iso_639_1;
    }
    public void setIso_639_1(String iso_639_1) {
        this.iso_639_1 = iso_639_1;
    }

    protected Language(Parcel in) {
        name = in.readString();
        iso_639_1 = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(iso_639_1);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Language> CREATOR = new Parcelable.Creator<Language>() {
        @Override
        public Language createFromParcel(Parcel in) {
            return new Language(in);
        }

        @Override
        public Language[] newArray(int size) {
            return new Language[size];
        }
    };
}