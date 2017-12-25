package com.example.miodragmilosevic.nativetest.filterimage;

import com.example.miodragmilosevic.nativetest.filters.ImageProcessor;

/**
 * Created by miodrag.milosevic on 12/13/2017.
 */

public class FilterItem {

    private String mName;
    private int mType;

    public FilterItem(String name,@ImageProcessor.Type int type) {
        mName = name;
        mType = type;
    }

    public String getName() {
        return mName;
    }

   @ImageProcessor.Type
    public int getType() {
        return mType;
    }
}
