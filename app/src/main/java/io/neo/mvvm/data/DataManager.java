package io.neo.mvvm.data;

import java.util.ArrayList;

import io.neo.mvvm.data.local.db.DbHelper;
import io.neo.mvvm.data.local.prefs.PreferencesHelper;
import io.neo.mvvm.data.model.db.Article;
import io.neo.mvvm.data.model.db.ArticleCategory;
import io.neo.mvvm.data.remote.ApiHelper;
import io.reactivex.Observable;


public interface DataManager extends DbHelper, PreferencesHelper, ApiHelper {

    void updateApiHeader(String token);

    void setUserAsLoggedOut();

    void updateUserInfo(
            String accessToken,
            LoggedInMode loggedInMode,
            String userName,
            String phoneNumber,
            String email,
            String profilePicPath,
            String token);

    enum LoggedInMode {
        LOGGED_OUT(0),
        LOGGED_IN(1);

        private final int mType;

        LoggedInMode(int type) {
            mType = type;
        }

        public int getType() {
            return mType;
        }
    }

    Observable<ArrayList<ArticleCategory>> seedDatabaseCategories();

    Observable<ArrayList<Article>> seedDatabaseArticle();

    ArrayList<Article> getArrayArticles();

    void setArrayArticles(ArrayList<Article> mArrayArticles);

    ArrayList<ArticleCategory> getArrayCategories();

    void setArrayCategories(ArrayList<ArticleCategory> mArrayCategories);

    Article getArticleByName(String name);
}
