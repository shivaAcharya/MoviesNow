package com.example.moviesnow

/**
* This interface is used by the [RecentMoviesRecyclerViewAdapter] to ensure
* it has an appropriate Listener.
*
* In this app, it's implemented by [RecentMoviesFragment]
*/
interface OnListFragmentInteractionListener {
    fun onItemClick(item: RecentMovie)
}
