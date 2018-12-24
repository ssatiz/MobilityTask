package satheesh.mobilitytask.base

import android.location.Location
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.gson.Gson
import satheesh.mobilitytask.location.LocationHelper

abstract class BaseActivity<P: BasePresenter>: AppCompatActivity(), BaseView {

    /**
     * Will return presenter instance
     *
     * @return Presenter object for the particular activity
     */
    protected abstract fun createPresenter(): P

    /**
     * Set layout file name
     *
     * @return layout id for the activity
     */
    protected abstract fun getContentView(): Int

    /**
     * Presenter object
     */
    protected var presenter: P ? =null

    protected var gson: Gson ?= null

    lateinit var locationHelper: LocationHelper

    companion object {
        var baseView: BaseView ?= null
        fun locationUpdate(location: Location) {
            baseView?.onLocationUpdate(location)
        }
    }

    fun setLocationInterface(){
        baseView = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getContentView())
        locationHelper = LocationHelper(this)
        presenter = createPresenter()
        gson = Gson()
    }

}