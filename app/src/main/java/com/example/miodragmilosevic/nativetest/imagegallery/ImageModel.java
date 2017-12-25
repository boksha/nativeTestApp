package com.example.miodragmilosevic.nativetest.imagegallery;

/**
 * Created by miodrag.milosevic on 12/6/2017.
 */

public class ImageModel {
    private String mUrl;
    private String mName;

    public ImageModel(String url, String name) {
        mUrl = url;
        mName = name;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    @Override
    public String toString() {
        return "ImageModel{" +
                "mUrl='" + mUrl + '\'' +
                ", mName='" + mName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ImageModel that = (ImageModel) o;

        if (!mUrl.equals(that.mUrl)) return false;
        return mName.equals(that.mName);
    }

    @Override
    public int hashCode() {
        int result = mUrl.hashCode();
        result = 31 * result + mName.hashCode();
        return result;
    }
}
