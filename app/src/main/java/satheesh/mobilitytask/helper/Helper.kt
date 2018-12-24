package satheesh.mobilitytask.helper

import android.content.Context
import android.net.ConnectivityManager

fun Context.isInternetConnected(): Boolean {
    val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo = cm.activeNetworkInfo
    return networkInfo != null && networkInfo.isConnected
}