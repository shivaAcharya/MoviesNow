package com.example.moviesnow

import com.google.gson.annotations.SerializedName

/**
 * The Model for storing a single movie from the Movie Database API
 *
 * SerializedName tags MUST match the JSON response for the
 * object to correctly parse with the gson library.
 */

class RecentMovie {
//    @SerializedName("rank")
//    var rank = 0

    @JvmField
    @SerializedName("title")
    var title: String? = null


    //TODO bookImageUrl
    @JvmField
    @SerializedName("backdrop_path")
    var movieImageUrl : String? = null

    //TODO description
    @JvmField
    @SerializedName("overview")
    var description : String? = null


}
