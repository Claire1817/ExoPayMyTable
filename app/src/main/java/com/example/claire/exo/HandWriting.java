package com.example.claire.exo;

/**
 * Created by claire on 23/09/2016.
 */

public class HandWriting {
    public String id;
    public String title;
    public String date_created;
    public String date_modified;
    public int rating_neatness;
    public int rating_cursivity;
    public int rating_embellishment;

    public void setRating_character_width(int rating_character_width) {
        this.rating_character_width = rating_character_width;
    }

    public void setRating_embellishment(int rating_embellishment) {
        this.rating_embellishment = rating_embellishment;
    }

    public void setRating_cursivity(int rating_cursivity) {
        this.rating_cursivity = rating_cursivity;
    }

    public void setRating_neatness(int rating_neatness) {
        this.rating_neatness = rating_neatness;
    }

    public void setDate_modified(String date_modified) {
        this.date_modified = date_modified;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int rating_character_width;

    public int getRating_character_width() {
        return rating_character_width;
    }

    public int getRating_embellishment() {
        return rating_embellishment;
    }

    public int getRating_cursivity() {
        return rating_cursivity;
    }

    public int getRating_neatness() {
        return rating_neatness;
    }

    public String getDate_modified() {
        return date_modified;
    }

    public String getDate_created() {
        return date_created;
    }

    public String getTitle() {
        return title;
    }

    public String getId() {
        return id;
    }
}
