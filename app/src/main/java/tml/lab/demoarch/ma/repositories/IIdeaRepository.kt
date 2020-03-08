package tml.lab.demoarch.ma.repositories

import tml.lab.demoarch.common.dataquery.IDataQueryListener
import tml.lab.demoarch.ma.entities.IdeaList

interface IIdeaRepository {
    fun filter(listType: String, listener: IDataQueryListener<IdeaList>)
    suspend fun syncFilter(listType: String): IdeaList
}