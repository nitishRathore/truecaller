package com.task.mercari.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Nitish Singh on 2019-06-02.
 */
public class ProductData {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("data")
    @Expose
    private String data;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
