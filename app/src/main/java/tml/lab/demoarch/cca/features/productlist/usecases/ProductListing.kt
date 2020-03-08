package tml.lab.demoarch.cca.features.productlist.usecases

import tml.lab.demoarch.cca.application.repositories.ProductRepository
import tml.lab.demoarch.cca.core.entities.ProductList
import tml.lab.demoarch.common.dataquery.IDataQueryListener
import tml.lab.demoarch.cca.core.viper.SLG

/**
 * Usecase: Product Listing
 * 1. get data from Repository
 * 2. apply business logic processing
 */
class ProductListing(val repo: ProductRepository) {
    fun filter(listType: String, listener: IDataQueryListener<ProductList>) {
        SLG.d("ProductListing", "filter $listType")
        repo.filter(listType, listener)
    }
}