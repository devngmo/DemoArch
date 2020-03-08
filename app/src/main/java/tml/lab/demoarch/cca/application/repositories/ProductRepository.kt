package tml.lab.demoarch.cca.application.repositories

import tml.lab.demoarch.cca.core.datasources.IProductDataProvider
import tml.lab.demoarch.cca.core.entities.PLT_ALL
import tml.lab.demoarch.cca.core.repositories.IProductRepository
import tml.lab.demoarch.cca.core.entities.ProductList
import tml.lab.demoarch.common.dataquery.IDataQueryListener
import tml.lab.demoarch.cca.core.viper.SLG

/**
 * Product Repository
 * Get data from DataSource, convert & format content
 */
class ProductRepository(val dataProvider: IProductDataProvider) :
    IProductRepository {
    override fun filter(listType: String, listener: IDataQueryListener<ProductList>) {
//        val ls = arrayListOf<Product>()
//        ls.add(Product("1", "$listType Prod 1", 100.0f))
//        ls.add(Product("2", "$listType Prod 2", 200.0f))
        var validListType = listType
        when (listType) {
            "" -> validListType = PLT_ALL
            "null" -> validListType = PLT_ALL
            //MutableLiveData(dataProvider.filter(listType))
        }
        SLG.d("ProductRepository", "filter valid list type = $validListType")
        dataProvider.filter(validListType, listener) //MutableLiveData(dataProvider.filter(listType))
    }

}