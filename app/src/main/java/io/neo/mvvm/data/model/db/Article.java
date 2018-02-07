package io.neo.mvvm.data.model.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Article {
    @Expose
    @PrimaryKey
    public Long id;

    @Expose
    @SerializedName("name")
    @ColumnInfo(name = "name")
    public String name;

    @Expose
    @SerializedName("category")
    @ColumnInfo(name = "category")
    public String category;

    @Expose
    @SerializedName("references")
    @ColumnInfo(name = "references")
    public ArrayList<String> references;

    @Expose
    @SerializedName("description")
    @ColumnInfo(name = "description")
    public String description;

    @Expose
    @SerializedName("detail")
    @ColumnInfo(name = "detail")
    public String detail;

    @Expose
    @SerializedName("image")
    @ColumnInfo(name = "image")
    public String image;
}
