package satheesh.mobilitytask.location

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.google.android.gms.location.LocationResult
import satheesh.mobilitytask.base.BaseActivity

class LocationUpdatesBroadcastReceiver : BroadcastReceiver() {

    companion object {
        const val ACTION_PROCESS_UPDATES: String = "satheesh.mobilitytask.location.action.PROCESS_UPDATES"
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        if (ACTION_PROCESS_UPDATES == intent?.action) {
            val result = LocationResult.extractResult(intent)
            if (result != null) {
                val location = result.lastLocation
                Log.e("LocationUpdates ---> ", "Lat: " + result.lastLocation.latitude + "  Lng:" + result.lastLocation.longitude)
                BaseActivity.locationUpdate(location)
            }
        }
    }
}