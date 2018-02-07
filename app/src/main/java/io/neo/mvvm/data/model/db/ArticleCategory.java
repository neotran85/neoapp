package io.neo.mvvm.data.model.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ArticleCategory {
    @Expose
    @PrimaryKey
    public Long id;

    @Expose
    @SerializedName("name")
    @ColumnInfo(name = "name")
    public String name;

    @Expose
    @SerializedName("image")
    @ColumnInfo(name = "image")
    public String image;

    @Expose
    @SerializedName("description")
    @ColumnInfo(name = "description")
    public String description;
}
