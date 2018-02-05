package io.neo.mvvm.utils.helper;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public final class NetworkUtils {

    private NetworkUtils() {
        // This class is not publicly instantiable
    }

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        boolean value = netInfo != null && netInfo.isConnectedOrConnecting();
        if (!value)
            Toast.makeText(context, "Network error.", Toast.LENGTH_LONG).show();
        return value;
    }
}
