package io.neo.mvvm.data.local.db;

import io.neo.mvvm.data.model.db.User;

import java.util.List;

import io.reactivex.Observable;


public interface DbHelper {

    Observable<Boolean> insertUser(final User user);
    Observable<List<User>> getAllUsers();
}
