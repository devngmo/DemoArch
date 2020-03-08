package tml.lab.demoarch.cca.ui

import android.content.Context
import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import tml.lab.demoarch.cca.application.repositories.ProductRepository
import tml.lab.demoarch.cca.features.productlist.ProductListViewModel
import tml.lab.demoarch.cca.core.viper.ViperContext
import tml.lab.demoarch.cca.features.dashboard.DashboardViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory constructor(
    val context: Context,
    private val productRepository: ProductRepository,
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {

    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ) = with(modelClass) {
        when {
//            isAssignableFrom(StatisticsViewModel::class.java) ->
//                StatisticsViewModel(tasksRepository)
//            isAssignableFrom(TaskDetailViewModel::class.java) ->
//                TaskDetailViewModel(tasksRepository)
            isAssignableFrom(ProductListViewModel::class.java) ->
                ProductListViewModel(ViperContext(context), productRepository)
            isAssignableFrom(DashboardViewModel::class.java) ->
                DashboardViewModel(handle)
            else ->
                throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    } as T
}
