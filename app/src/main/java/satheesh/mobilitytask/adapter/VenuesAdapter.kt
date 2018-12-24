package satheesh.mobilitytask.adapter

import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import satheesh.mobilitytask.R
import satheesh.mobilitytask.model.VenuesItem

class VenuesAdapter: RecyclerView.Adapter<VenueViewHolder>(), Filterable{

    private var venuesList: MutableList<VenuesItem>  = mutableListOf()
    private var venuesListFiltered: MutableList<VenuesItem>  = mutableListOf()

    fun refreshItem(venuesList: MutableList<VenuesItem>){
        this.venuesList = venuesList
        this.venuesListFiltered = venuesList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VenueViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_venue,
                parent, false)
        return VenueViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return venuesListFiltered.size
    }

    override fun onBindViewHolder(holder: VenueViewHolder, position: Int) {
        holder.onBind(venuesListFiltered[position])
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence?): FilterResults {
                val searchQuery = charSequence.toString()
                venuesListFiltered = if (TextUtils.isEmpty(searchQuery)){
                    venuesList
                }else {
                    val filteredList = mutableListOf<VenuesItem>()
                    for (i in 0 until venuesList.size){
                        val venue = venuesList[i]
                        if (venue.name.toLowerCase().contains(searchQuery)){
                            filteredList.add(venue)
                        }
                    }
                    filteredList
                }

                val results = FilterResults()
                results.values = venuesListFiltered
                return results
            }

            override fun publishResults(charSequence: CharSequence?, filterResults: FilterResults?) {
                venuesListFiltered = filterResults?.values as MutableList<VenuesItem>
                notifyDataSetChanged()
            }

        }
    }

}