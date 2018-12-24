package satheesh.mobilitytask.model


import com.google.gson.annotations.SerializedName

data class VenuesItem(@SerializedName("venuePage")
                      val venuePage: VenuePage,
                      @SerializedName("name")
                      val name: String = "",
                      @SerializedName("location")
                      val location: Location,
                      @SerializedName("id")
                      val id: String = "",
                      @SerializedName("categories")
                      val categories: List<CategoriesItem>?)


data class Response(@SerializedName("venues")
                    val venues: List<VenuesItem>?)


data class Meta(@SerializedName("code")
                val code: Int = 0,
                @SerializedName("requestId")
                val requestId: String = "")


data class VenueResponse(@SerializedName("meta")
                         val meta: Meta,
                         @SerializedName("response")
                         val response: Response)


data class VenuePage(@SerializedName("id")
                     val id: String = "")


data class CategoriesItem(@SerializedName("pluralName")
                          val pluralName: String = "",
                          @SerializedName("name")
                          val name: String = "",
                          @SerializedName("icon")
                          val icon: Icon,
                          @SerializedName("id")
                          val id: String = "",
                          @SerializedName("shortName")
                          val shortName: String = "",
                          @SerializedName("primary")
                          val primary: Boolean = false)


data class LabeledLatLngsItem(@SerializedName("lng")
                              val lng: Double = 0.0,
                              @SerializedName("label")
                              val label: String = "",
                              @SerializedName("lat")
                              val lat: Double = 0.0)


data class Icon(@SerializedName("prefix")
                val prefix: String = "",
                @SerializedName("suffix")
                val suffix: String = "")


data class Location(@SerializedName("cc")
                    val cc: String = "",
                    @SerializedName("country")
                    val country: String = "",
                    @SerializedName("address")
                    val address: String = "",
                    @SerializedName("labeledLatLngs")
                    val labeledLatLngs: List<LabeledLatLngsItem>?,
                    @SerializedName("lng")
                    val lng: Double = 0.0,
                    @SerializedName("distance")
                    val distance: Int = 0,
                    @SerializedName("formattedAddress")
                    val formattedAddress: List<String>?,
                    @SerializedName("city")
                    val city: String = "",
                    @SerializedName("postalCode")
                    val postalCode: String = "",
                    @SerializedName("state")
                    val state: String = "",
                    @SerializedName("crossStreet")
                    val crossStreet: String = "",
                    @SerializedName("lat")
                    val lat: Double = 0.0)


