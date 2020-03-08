package tml.lab.demoarch.ma.features.idealist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import tml.lab.demoarch.cca.core.viper.SLG
import tml.lab.demoarch.ma.entities.*
import tml.lab.demoarch.ma.repositories.IIdeaRepository

class IdeaCollection(var type:String, var ideas: IdeaList = arrayListOf())

class IdeaListViewModel(val fragment: MAIdeaListFragment, val ideaRepository : IIdeaRepository) : ViewModel() {
    val collection = MutableLiveData<IdeaCollection>(IdeaCollection(ILT_UNDEF))
    var isLoadingData = false

    val cachedCollections = HashMap<String, IdeaCollection>()

    init {
        SLG.d(this, "init...")
        cachedCollections.put(ILT_ALL, IdeaCollection(ILT_ALL))
        cachedCollections.put(ILT_FAVORITE, IdeaCollection(ILT_FAVORITE))
        cachedCollections.put(ILT_RECENT, IdeaCollection(ILT_RECENT))
//        listType.observe(fragment, Observer { value ->
//            SLG.d("IdeaListViewModel", "Observer listType has changed $value")
//            getIdeas(value)
//        })
    }

    fun getIdeas(listType: String) {
        SLG.d(this, "get $listType Ideas...")
        isLoadingData = true
        collection.value = cachedCollections[listType]
        GlobalScope.launch(Dispatchers.Main) {
            val filteredItems = ideaRepository.syncFilter(listType)
            isLoadingData = false
            cachedCollections[listType] = IdeaCollection(listType, filteredItems)
            if (listType == fragment.selectedTabListType)
                collection.value = cachedCollections[listType]
        }

    }
}