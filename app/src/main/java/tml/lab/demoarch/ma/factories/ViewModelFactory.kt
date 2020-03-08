package tml.lab.demoarch.ma.factories

import android.content.Context
import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import tml.lab.demoarch.ma.features.idealist.IdeaListViewModel
import tml.lab.demoarch.ma.features.idealist.MAIdeaListFragment
import tml.lab.demoarch.ma.repositories.IIdeaRepository


@Suppress("UNCHECKED_CAST")
class ViewModelFactory constructor(
    val anyContext: Any,
    private val ideaRepository: IIdeaRepository,
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {

    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ) = with(modelClass) {
        when {
            isAssignableFrom(IdeaListViewModel::class.java) ->
                IdeaListViewModel(anyContext as MAIdeaListFragment, ideaRepository)
            else ->
                throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    } as T
}
