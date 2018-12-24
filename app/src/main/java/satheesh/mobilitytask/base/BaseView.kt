package satheesh.mobilitytask.base

import android.location.Location

interface BaseView {

    fun onLocationUpdate(location: Location)
}