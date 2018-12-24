package satheesh.mobilitytask.ui.venues

import android.Manifest
import android.app.Activity
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_venue.*
import satheesh.mobilitytask.R
import satheesh.mobilitytask.adapter.VenuesAdapter
import satheesh.mobilitytask.base.BaseActivity
import satheesh.mobilitytask.helper.isInternetConnected
import satheesh.mobilitytask.location.LocationHelper
import satheesh.mobilitytask.model.VenuesItem


class VenuesActivity: BaseActivity<VenuesPresenter>(), VenuesView {

    var adapter: VenuesAdapter ?= null
    private var searchView: SearchView? = null

    private val REQUEST_LOCATION_PERMISSION = 123

    override fun createPresenter(): VenuesPresenter = VenuesPresenter(this)

    override fun getContentView(): Int = R.layout.activity_venue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setLocationInterface()
        if (Build.VERSION.SDK_INT >= 23){
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
               if (locationHelper.isNetworkEnabled(this)){
                   locationHelper.startRequestLocation()
               }
            }else{
                requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest
                        .permission.ACCESS_COARSE_LOCATION), REQUEST_LOCATION_PERMISSION)
            }
        }

    }

    private fun getLocation(latitude: Double, longitude: Double){
        if (isInternetConnected()){
            adapter = VenuesAdapter()
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = adapter
            presenter?.getVenues(latitude, longitude)
        }else{
            Snackbar.make(lnrRoot, getString(R.string.internet_not_connected), Snackbar.LENGTH_INDEFINITE).show()
        }

    }

    override fun refreshVenue(venueList: MutableList<VenuesItem>?) {
        venueList?.let {
            adapter?.refreshItem(it)
        }
    }

    override fun onLocationUpdate(location: Location) {
        getLocation(location.latitude, location.longitude)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView = menu?.findItem(R.id.action_search)?.actionView as SearchView
        searchView?.setSearchableInfo(searchManager
                .getSearchableInfo(componentName))
        searchView?.maxWidth = Integer.MAX_VALUE

        // listening to search query text change
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                // filter recycler view when query submitted
                adapter?.filter?.filter(query)
                return false
            }

            override fun onQueryTextChange(query: String): Boolean {
                // filter recycler view when text is changed
                adapter?.filter?.filter(query)
                return false
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId
        return if (id == R.id.action_search) {
            true
        } else super.onOptionsItemSelected(item)

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            REQUEST_LOCATION_PERMISSION -> {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    locationHelper.startRequestLocation()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            LocationHelper.REQUEST_CHECK_SETTINGS -> {
                if (resultCode == Activity.RESULT_OK) locationHelper.startRequestLocation()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        locationHelper.stopRequestLocation()
    }
}