package mkruglikov.fitnesskittest.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ConnectionUtils {

    private static ConnectivityManager connectivityManager;

    private static void initConnectivityManger(Context applicationContext) {
        if (connectivityManager == null)
            connectivityManager = (ConnectivityManager) applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    public static boolean isNetworkConnected(Context applicationContext) {
        initConnectivityManger(applicationContext);
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }
}
