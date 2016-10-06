package com.example.claire.exo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by claire on 23/09/2016.
 * Model handWriting
 */

public class HandWriting {

    @SerializedName("id")
    public String id;

    @SerializedName("title")
    public String title;

    @SerializedName("date_created")
    public String dateCreated;

    @SerializedName("date_modified")
    public String dateModified;

    @SerializedName("rating_neatness")
    public int ratingNeatness;

    @SerializedName("rating_cursivity")
    public int ratingCursivity;

    @SerializedName("rating_embellishment")
    public int ratingEmbellishment;

    @SerializedName("rating_character_width")
    public int ratingCharacterWidth;

    public void setRatingEmbellishment(int ratingEmbellishment) {
        this.ratingEmbellishment = ratingEmbellishment;
    }

    public void setRatingCharacterWidth(int ratingCharacterWidth) {
        this.ratingCharacterWidth = ratingCharacterWidth;
    }

    public void setRatingCursivity(int ratingCursivity) {
        this.ratingCursivity = ratingCursivity;
    }

    public void setRatingNeatness(int ratingNeatness) {
        this.ratingNeatness = ratingNeatness;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void setDateModified(String dateModified) {
        this.dateModified = dateModified;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getRatingCharacterWidth() {
        return ratingCharacterWidth;
    }

    public int getRatingCursivity() {
        return ratingCursivity;
    }

    public int getRatingEmbellishment() {
        return ratingEmbellishment;
    }

    public int getRatingNeatness() {
        return ratingNeatness;
    }

    public String getDateModified() {
        return dateModified;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public String getTitle() {
        return title;
    }

    public String getId() {
        return id;
    }
}
