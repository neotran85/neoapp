package io.neo.mvvm.utils.helper;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;

import io.neo.mvvm.R;

public final class ViewUtils {

    private ViewUtils() {
        // This class is not publicly instantiable
    }

    public static float pxToDp(float px) {
        float densityDpi = Resources.getSystem().getDisplayMetrics().densityDpi;
        return px / (densityDpi / 160f);
    }

    public static int dpToPx(float dp) {
        float density = Resources.getSystem().getDisplayMetrics().density;
        return Math.round(dp * density);
    }


    public static String getStringByTag(View view) {
        if (view != null && view.getTag() != null) {
            return view.getTag().toString();
        }
        return "";
    }

    public static void setOnClickListener(View mainView, int[] idResourceButtons, View.OnClickListener listener) {
        if (mainView != null) {
            for (int i = 0; i < idResourceButtons.length; i++) {
                View view = mainView.findViewById(idResourceButtons[i]);
                if (view != null)
                    view.setOnClickListener(listener);
            }
        }
    }

    public static void setOnClickListener(View mainView, View.OnClickListener listener, int... idResourceButtons) {
        if (mainView != null) {
            for (int i = 0; i < idResourceButtons.length; i++) {
                View view = mainView.findViewById(idResourceButtons[i]);
                if (view != null)
                    view.setOnClickListener(listener);
            }
        }
    }

    public static void setOnClickListener(View.OnClickListener listener, View... views) {
        for (int i = 0; i < views.length; i++) {
            if (views[i] != null)
                views[i].setOnClickListener(listener);
        }
    }

    public static void setOnClickListenersOfParentView(ViewGroup view) {

    }
}
