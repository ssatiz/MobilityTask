package satheesh.mobilitytask.ui.venues

import android.annotation.SuppressLint
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import satheesh.mobilitytask.BuildConfig
import satheesh.mobilitytask.base.BasePresenter
import satheesh.mobilitytask.model.VenuesItem
import satheesh.mobilitytask.repository.DataManager

class VenuesPresenter(val venuesView: VenuesView): BasePresenter() {

    private val dataManager = DataManager()

    @SuppressLint("CheckResult")
    fun getVenues(latitude: Double, longitude: Double
                  , formattedDate: String){
        dataManager.getVenues("$latitude,$longitude", BuildConfig.username, BuildConfig.password, formattedDate)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe{
                    venuesView.refreshVenue(it.response.venues as MutableList<VenuesItem>)
        }
    }
}