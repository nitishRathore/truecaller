package com.task.mercari.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Nitish Singh on 2019-06-01.
 */
public class Product {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("num_likes")
    @Expose
    private Integer numLikes;
    @SerializedName("num_comments")
    @Expose
    private Integer numComments;
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("photo")
    @Expose
    private String photo;


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public Integer getNumLikes() {
        return numLikes;
    }

    public Integer getNumComments() {
        return numComments;
    }

    public Integer getPrice() {
        return price;
    }

    public String getPhoto() {
        return photo;
    }
}
