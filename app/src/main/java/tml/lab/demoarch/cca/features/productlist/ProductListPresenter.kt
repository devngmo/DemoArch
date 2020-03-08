package tml.lab.demoarch.cca.features.productlist

import tml.lab.demoarch.cca.application.ServiceLocator
import tml.lab.demoarch.cca.core.entities.Product
import tml.lab.demoarch.common.dataquery.IDataQueryListener
import tml.lab.demoarch.common.dataquery.DataQueryResult
import tml.lab.demoarch.cca.features.productlist.usecases.ProductListing
import tml.lab.demoarch.cca.core.viper.ProductListViewer
import tml.lab.demoarch.cca.core.viper.SLG
import tml.lab.demoarch.cca.core.viper.ViperContext

interface IProductListPresenter {
    fun showProductList(listType: String)
}

class ProductListPresenter(val context: ViperContext, val viewer: ProductListViewer): IProductListPresenter {
    val uc = ProductListing(ServiceLocator.provideProductRepository(context))

    override fun showProductList(listType: String) {
        uc.filter(listType, object : IDataQueryListener<ArrayList<Product>> {
            override fun onFinished(result: DataQueryResult<ArrayList<Product>>) {
                SLG.d("ProductListPresenter", "on filter finished: found ${result.data.size} Products")
                viewer.onDataChanged(result.extra as String, result.data)
            }
        })
    }

}