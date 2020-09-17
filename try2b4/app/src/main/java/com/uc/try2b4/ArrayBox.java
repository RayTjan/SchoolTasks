package com.uc.try2b4;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class ArrayBox implements Parcelable {
    private static ArrayList<items> itemlist = new ArrayList<>();

    public ArrayBox() {

    }

    protected ArrayBox(Parcel in) {
        itemlist = in.createTypedArrayList(items.CREATOR);
    }

    public static final Creator<ArrayBox> CREATOR = new Creator<ArrayBox>() {
        @Override
        public ArrayBox createFromParcel(Parcel in) {
            return new ArrayBox(in);
        }

        @Override
        public ArrayBox[] newArray(int size) {
            return new ArrayBox[size];
        }
    };

    public ArrayList<items> getItemlist() {
        return itemlist;
    }

    public void setItemlist(ArrayList<items> itemlist) {
        this.itemlist = itemlist;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(itemlist);
    }
}
