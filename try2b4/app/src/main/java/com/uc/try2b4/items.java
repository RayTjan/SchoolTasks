package com.uc.try2b4;

import android.os.Parcel;
import android.os.Parcelable;

public class items implements Parcelable {
    private String mtext1, mtext2, mtext3;
    private int img1, img2;

    public items(String mtext1, String mtext2, String mtext3, int img1, int img2) {
        this.mtext1 = mtext1;
        this.mtext2 = mtext2;
        this.mtext3 = mtext3;
        this.img1 = img1;
        this.img2 = img2;
    }

    protected items(Parcel in) {
        mtext1 = in.readString();
        mtext2 = in.readString();
        mtext3 = in.readString();
        img1 = in.readInt();
        img2 = in.readInt();
    }

    public static final Creator<items> CREATOR = new Creator<items>() {
        @Override
        public items createFromParcel(Parcel in) {
            return new items(in);
        }

        @Override
        public items[] newArray(int size) {
            return new items[size];
        }
    };

    public void setMtext1(String mtext1) {
        this.mtext1 = mtext1;
    }

    public void setMtext2(String mtext2) {
        this.mtext2 = mtext2;
    }

    public void setMtext3(String mtext3) {
        this.mtext3 = mtext3;
    }

    public void setImg1(int img1) {
        this.img1 = img1;
    }

    public void setImg2(int img2) {
        this.img2 = img2;
    }

    public String getMtext1() {
        return mtext1;
    }

    public String getMtext2() {
        return mtext2;
    }

    public String getMtext3() {
        return mtext3;
    }

    public int getImg1() {
        return img1;
    }

    public int getImg2() {
        return img2;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mtext1);
        dest.writeString(mtext2);
        dest.writeString(mtext3);
        dest.writeInt(img1);
        dest.writeInt(img2);
    }
}
