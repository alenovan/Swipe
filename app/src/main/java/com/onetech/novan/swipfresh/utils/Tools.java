package com.onetech.novan.swipfresh.utils;

import android.content.Context;

import com.okedev.layan.utils.library.ConnectionDetector;

public class Tools {

    public static boolean checkConnection(Context context) {
        ConnectionDetector conn = new ConnectionDetector(context);
        if (conn.isConnectedToInternet()) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isUnderJellyBean() {
        int sdk = android.os.Build.VERSION.SDK_INT;
        if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            return true;
        }
        return false;
    }
}
