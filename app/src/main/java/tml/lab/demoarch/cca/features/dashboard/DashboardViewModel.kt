package tml.lab.demoarch.cca.features.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import tml.lab.demoarch.cca.ui.Event

class DashboardViewModel(val handle: SavedStateHandle) : ViewModel() {
    private val _openProductListEvent = MutableLiveData<Event<String>>()
    val openProductListEvent: LiveData<Event<String>> = _openProductListEvent

    fun openProductList(listType: String) {
        _openProductListEvent.value = Event(listType)
    }
}