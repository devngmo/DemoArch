package tml.lab.demoarch

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import tml.lab.demoarch.cca.ui.CCAMainActivity
import tml.lab.demoarch.ma.MAMainActivity

class ArchListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_arch_list)
    }

    fun goToCCA(v:View) {
        val i = Intent(this, CCAMainActivity::class.java)
        startActivity(i)
    }

    fun goToMA(v:View) {
        val i = Intent(this, MAMainActivity::class.java)
        startActivity(i)
    }
}
