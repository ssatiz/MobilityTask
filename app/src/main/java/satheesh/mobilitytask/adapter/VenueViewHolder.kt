package satheesh.mobilitytask.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.view_holder_venue.view.*
import satheesh.mobilitytask.R
import satheesh.mobilitytask.model.VenuesItem

class VenueViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun onBind(venue: VenuesItem){
        itemView.lblName.text = venue.name
        itemView.lblAddress.text = venue.location.address
        itemView.lblDistance.text = String.format(itemView.context.getString(R.string.distance), venue
                .location
                .distance.toString())
    }
}