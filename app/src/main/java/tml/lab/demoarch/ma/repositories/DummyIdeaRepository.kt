package tml.lab.demoarch.ma.repositories

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import tml.lab.demoarch.cca.core.viper.SLG
import tml.lab.demoarch.common.dataquery.IDataQueryListener
import tml.lab.demoarch.common.dataquery.DataQueryResult
import tml.lab.demoarch.common.dataquery.EDataQueryResult
import tml.lab.demoarch.ma.entities.Idea
import tml.lab.demoarch.ma.entities.IdeaList
import java.util.*

class DummyIdeaRepository : IIdeaRepository {
    override fun filter(listType: String, listener: IDataQueryListener<IdeaList>) {
        GlobalScope.launch {
            val ls = spawnSampleData(listType)
            listener.onFinished(DataQueryResult(EDataQueryResult.Success, "", ls))
        }

    }

    override suspend fun syncFilter(listType: String) : IdeaList {
        SLG.d(this, "syncFilter $listType...")
        return spawnSampleData(listType)
    }

    private suspend fun spawnSampleData(listType: String): IdeaList {
        delay(3000)
        val rnd = Random(123)

        val authors = arrayListOf<String>()
        for(k in 1 until rnd.nextInt(100))
            authors.add("author $k")

        val ls = IdeaList()
        for (i in 0 until rnd.nextInt(100))
        {
            val ideaAuthors = arrayListOf<String>()
            for(k in 0 until rnd.nextInt(5)) {
                val aid = authors[rnd.nextInt(authors.size)]
                if (!(aid in ideaAuthors))
                    ideaAuthors.add(aid)
            }
            ls.add(
                Idea("idea_$i", "$listType Idea $i", "Content of idea $i", ideaAuthors)
            )
        }
        return ls
    }
}