package com.example.moviesnow

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.ContentLoadingProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Headers
import org.json.JSONObject
// My API => 1fc81b63212fc7b37711764f6b913e4a
private const val API_KEY = "a07e22bc18f5cb106bfe4cc1f83ad8ed"

class RecentMoviesFragment : Fragment(), OnListFragmentInteractionListener {
    /*
   * Constructing the view
   */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_recent_movies_list, container, false)
        val progressBar = view.findViewById<View>(R.id.progress) as ContentLoadingProgressBar
        val recyclerView = view.findViewById<View>(R.id.list) as RecyclerView
        val context = view.context

        // spanCount 1 makes the layout with single col instead of 2 cols
        recyclerView.layoutManager = GridLayoutManager(context, 1)
        updateAdapter(progressBar, recyclerView)
        return view
    }

    /*
     * Updates the RecyclerView adapter with new data.  This is where the
     * networking magic happens!
     */
    private fun updateAdapter(progressBar: ContentLoadingProgressBar, recyclerView: RecyclerView) {
        progressBar.show()

        // Create and set up an AsyncHTTPClient() here
        //  NEWLY ADDED
        val client = AsyncHttpClient()
        val params = RequestParams()
        params["api_key"] = API_KEY

        // Using the client, perform the HTTP request
        //  NEWLY ADDED
        client[
                "https://api.themoviedb.org/3/movie/now_playing",
                params,
                object : JsonHttpResponseHandler()

                // Uncomment me once you complete the above sections!
                {
                    /*
                     * The onSuccess function gets called when
                     * HTTP response status is "200 OK"
                     */
                    override fun onSuccess(
                        statusCode: Int,
                        headers: Headers,
                        json: JsonHttpResponseHandler.JSON
                    ) {
                        // The wait for a response is over
                        progressBar.hide()


                        //TODO - Parse JSON into Models
                        //Log.i("JSON DATA", json.toString())
                        //val resultsJSON : JSONObject = json.jsonObject.get("results") as JSONObject
                        val resultsJSON : String = json.jsonObject.get("results").toString()
                        //Log.i("JSON DATA STR", moviesRawJSON)

                        val gson = Gson()
                        val arrayMovieType = object : TypeToken<List<RecentMovie>>() {}.type
                        val models : List<RecentMovie> = gson.fromJson(resultsJSON, arrayMovieType)
                        recyclerView.adapter = RecentMoviesRecyclerViewAdapter(models, this@RecentMoviesFragment)

                        // Look for this in Logcat:
                        Log.d("RecentMoviesFragment", "response successful")
                    }

                    /*
                     * The onFailure function gets called when
                     * HTTP response status is "4XX" (eg. 401, 403, 404)
                     */
                    override fun onFailure(
                        statusCode: Int,
                        headers: Headers?,
                        errorResponse: String,
                        t: Throwable?
                    ) {
                        // The wait for a response is over
                        progressBar.hide()

                        // If the error is not null, log it!
                        t?.message?.let {
                            Log.e("BestSellerBooksFragment", errorResponse)
                        }
                    }
                }]
        //  */

    }

    /*
     * What happens when a particular book is clicked.
     */
    override fun onItemClick(item: RecentMovie) {
        Toast.makeText(context, "test: " + item.title, Toast.LENGTH_LONG).show()
    }

}