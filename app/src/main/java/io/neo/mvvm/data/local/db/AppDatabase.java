package io.neo.mvvm.data.local.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import io.neo.mvvm.data.local.db.dao.UserDao;
import io.neo.mvvm.data.model.db.User;


@Database(entities = {User.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}
