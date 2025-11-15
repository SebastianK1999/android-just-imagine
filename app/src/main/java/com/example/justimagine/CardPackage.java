package com.example.justimagine;

import java.util.ArrayList;
import java.util.List;

public class CardPackage {
    public String mName;
    public Integer mNameId;
    public boolean isActive;
    public Integer mCoverId;
    public Integer mColorId;
    public List<Integer> mImages;

    CardPackage(String name,Integer nameId, boolean active, Integer coverId, Integer colorId, List<Integer> images){
        mName = name;
        mNameId = nameId;
        isActive = active;
        mCoverId = coverId;
        mColorId = colorId;
        mImages = images;
    }
}
