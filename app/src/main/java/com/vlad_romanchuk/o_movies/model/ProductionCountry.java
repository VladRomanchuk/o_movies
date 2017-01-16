package com.vlad_romanchuk.o_movies.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ProductionCountry implements Parcelable {

    private String name;
    private String iso_3166_1;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getIso_3166_1() {
        return iso_3166_1;
    }
    public void setIso_3166_1(String iso_3166_1) {
        this.iso_3166_1 = iso_3166_1;
    }

    protected ProductionCountry(Parcel in) {
        name = in.readString();
        iso_3166_1 = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(iso_3166_1);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<ProductionCountry> CREATOR = new Parcelable.Creator<ProductionCountry>() {
        @Override
        public ProductionCountry createFromParcel(Parcel in) {
            return new ProductionCountry(in);
        }

        @Override
        public ProductionCountry[] newArray(int size) {
            return new ProductionCountry[size];
        }
    };
}