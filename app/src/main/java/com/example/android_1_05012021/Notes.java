package com.example.android_1_05012021;

import android.os.Parcel;
import android.os.Parcelable;

public class Notes implements Parcelable {
    private String name;
    private String description;
    private String comment;
    private String status;
    private String dateOfCreation;

    public Notes(String name, String description, String comment, String status, String dateOfCreation) {
        this.name = name;
        this.description = description;
        this.comment = comment;
        this.status = status;
        this.dateOfCreation = dateOfCreation;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getComment() {
        return comment;
    }

    public String getStatus() {
        return status;
    }

    public String getDateOfCreation() {
        return dateOfCreation;
    }

    protected Notes(Parcel in) {
        name = in.readString();
        description = in.readString();
        comment = in.readString();
        status = in.readString();
        dateOfCreation = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getName());
        dest.writeString(getDescription());
        dest.writeString(getComment());
        dest.writeString(getStatus());
        dest.writeString(getDateOfCreation());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Notes> CREATOR = new Creator<Notes>() {
        @Override
        public Notes createFromParcel(Parcel in) {
            return new Notes(in);
        }

        @Override
        public Notes[] newArray(int size) {
            return new Notes[size];
        }
    };
}
