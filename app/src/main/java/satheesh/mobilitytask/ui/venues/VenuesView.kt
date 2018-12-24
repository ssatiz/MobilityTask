package satheesh.mobilitytask.ui.venues

import satheesh.mobilitytask.base.BaseView
import satheesh.mobilitytask.model.VenuesItem

interface VenuesView: BaseView {

    fun refreshVenue(venueList: MutableList<VenuesItem>?)

}