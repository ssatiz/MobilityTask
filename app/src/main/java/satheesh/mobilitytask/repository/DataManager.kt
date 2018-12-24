package satheesh.mobilitytask.repository

import io.reactivex.Observable
import satheesh.mobilitytask.model.VenueResponse
import satheesh.mobilitytask.repository.venue.VenueApi
import satheesh.mobilitytask.repository.venue.VenueApiHelper

class DataManager(private val apiHelper: VenueApiHelper = VenueApiHelper()): VenueApi{
    override fun getVenues(ll: String, client_id: String, client_secret: String, date: String): Observable<VenueResponse> {
        return apiHelper.getVenues(ll, client_id, client_secret, date)
    }

}