package com.tarrotsystem.codepreneur.reachable;

import android.content.Context;

/**
 * Created by codepreneur on 9/4/17.
 */

public class AppUtils {
    public static String getStringFromResources(Context context, int id){
        return context.getResources().getString(id);
    }
}
