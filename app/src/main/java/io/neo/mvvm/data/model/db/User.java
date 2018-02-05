package io.neo.mvvm.data.model.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "users")
public class User {

    @PrimaryKey
    @NonNull
    public String username;

    @ColumnInfo(name = "first_name")
    public String firstName;

    @ColumnInfo(name = "last_name")
    public String lastName;

    @ColumnInfo(name = "phone_number")
    public String phoneNumber;

    @ColumnInfo(name = "birthday")
    public String birthday;

    @ColumnInfo(name = "email")
    public String email;

    @ColumnInfo(name = "address")
    public String address;

    @ColumnInfo(name = "shipping_address")
    public String shippingAddress;

    @ColumnInfo(name = "avatar_path")
    public String avatarPath;

    @ColumnInfo(name = "others")
    public String others;
}
