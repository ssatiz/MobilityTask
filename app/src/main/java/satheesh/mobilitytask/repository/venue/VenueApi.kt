package satheesh.mobilitytask.repository.venue

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import satheesh.mobilitytask.model.VenueResponse

interface VenueApi {

    @GET("venues/search")
    fun getVenues(@Query("ll") ll: String, @Query("client_id") client_id: String, @Query
    ("client_secret") client_secret: String, @Query("v") date: String): Observable<VenueResponse>

}