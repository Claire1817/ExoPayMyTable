package com.example.claire.exo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by claire on 23/09/2016.
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


    public void setRatingCharacterWidth(int rating_character_width) {
        this.ratingCharacterWidth = rating_character_width;
    }

    public void setRatingEmbellishment(int rating_embellishment) {
        this.ratingEmbellishment = rating_embellishment;
    }

    public void setRating_cursivity(int rating_cursivity) {
        this.ratingCursivity = rating_cursivity;
    }

    public void setRatingNeatness(int rating_neatness) {
        this.ratingNeatness = rating_neatness;
    }

    public void setDate_modified(String date_modified) {
        this.dateModified = date_modified;
    }

    public void setDate_created(String date_created) {
        this.dateCreated = date_created;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getRating_character_width() {
        return ratingCharacterWidth;
    }

    public int getRating_embellishment() {
        return ratingEmbellishment;
    }

    public int getRating_cursivity() {
        return ratingCursivity;
    }

    public int getRating_neatness() {
        return ratingNeatness;
    }

    public String getDate_modified() {
        return dateModified;
    }

    public String getDate_created() {
        return dateCreated;
    }

    public String getTitle() {
        return title;
    }

    public String getId() {
        return id;
    }
}
