package tml.lab.demoarch.cca.core.viper

import android.util.Log

class SLGOutputLogcat : SLGOutput {
    override fun d(tag:Any, msg: String) {
        if (tag is String)
            Log.d("SLGOutput", "$tag: $msg")
        else
        Log.d("SLGOutput", "${tag.javaClass.name}: $msg")
    }
}