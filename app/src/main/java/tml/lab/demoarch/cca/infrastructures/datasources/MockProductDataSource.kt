package tml.lab.demoarch.cca.infrastructures.datasources

import kotlinx.coroutines.*
import tml.lab.demoarch.cca.core.datasources.IProductDataProvider
import tml.lab.demoarch.cca.core.entities.Product
import tml.lab.demoarch.cca.core.entities.ProductList
import tml.lab.demoarch.common.dataquery.EDataQueryResult
import tml.lab.demoarch.common.dataquery.IDataQueryListener
import tml.lab.demoarch.common.dataquery.DataQueryResult
import tml.lab.demoarch.cca.core.viper.SLG
import tml.lab.demoarch.cca.core.viper.ViperContext
import kotlin.random.Random

class MockProductDataSource(val context : ViperContext) :
    IProductDataProvider {
    override fun filter(listType: String, listener: IDataQueryListener<ProductList>) {
        SLG.d("MockProductDataSource", "filter valid list type = $listType")



            GlobalScope.launch(context = Dispatchers.Main) {
                val ls = spawnSomeProduct(listType)
                val result = DataQueryResult(EDataQueryResult.Success, "mock data source return OK", ls, listType)
                listener.onFinished(result)
            }
    }

    private suspend fun spawnSomeProduct(listType: String): ProductList {
        val sleepSeconds : Long = 2
        SLG.d("MockProductDataSource", "sleep $sleepSeconds seconds then spawn some products...")
        delay(2 * 1000)
        val ls = arrayListOf<Product>()
        val rnd = Random(123)
        for(i in 0 until rnd.nextInt(20))
            ls.add(Product("$i", "$listType Product $i", 100.0f + i))
        return ls
    }

}