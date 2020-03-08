package tml.lab.demoarch.cca.core.repositories

import tml.lab.demoarch.cca.core.entities.ProductList
import tml.lab.demoarch.common.dataquery.IDataQueryListener

interface IProductRepository {
    fun filter(listType:String, listener: IDataQueryListener<ProductList>)
}