package com.example.moviesnow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


/**
 * The MainActivity for the RecentMovies app.
 * Launches a [RecentMoviesFragment].
 */

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val supportFragmentManager = supportFragmentManager
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.content, RecentMoviesFragment(), null).commit()
    }
}