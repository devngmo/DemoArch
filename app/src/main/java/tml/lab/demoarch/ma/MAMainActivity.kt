package tml.lab.demoarch.ma

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import tml.lab.demoarch.R

class MAMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_minimalist_main)
        title = "Minimalist Architecture"
    }
}
