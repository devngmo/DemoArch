package tml.lab.demoarch.ma.extensions

import android.app.Activity
import androidx.fragment.app.Fragment
import tml.lab.demoarch.App
import tml.lab.demoarch.ma.factories.ViewModelFactory

fun Fragment.getViewModelFactory(activity:Activity): ViewModelFactory {
    val repository = (requireContext().applicationContext as App).ideaRepository
    return ViewModelFactory(this, repository, this)
}