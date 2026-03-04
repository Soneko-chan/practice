package ci.nsu.mobile.main

import ci.nsu.mobile.main.ui.main.MainFragment
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import ci.nsu.mobile.main.ui.colorsearch.ColorSearchFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                replace(R.id.fragment_container, ColorSearchFragment())
            }
        }
    }
}