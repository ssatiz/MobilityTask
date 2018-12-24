package satheesh.mobilitytask.repository.venue

import io.reactivex.Observable
import satheesh.mobilitytask.model.VenueResponse
import satheesh.mobilitytask.repository.network.BaseRetrofit

class VenueApiHelper: BaseRetrofit() {

    fun getAPI(): VenueApi {
        return buildRetrofit().create(VenueApi::class.java)
    }

    fun getVenues(ll: String, client_id: String, client_secret: String, date: String): Observable<VenueResponse>{
        return getAPI().getVenues(ll, client_id, client_secret, date).doOnNext {  }
    }
}