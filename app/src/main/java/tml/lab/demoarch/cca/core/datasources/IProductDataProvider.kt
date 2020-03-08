package tml.lab.demoarch.cca.core.datasources

import tml.lab.demoarch.cca.core.entities.ProductList
import tml.lab.demoarch.common.dataquery.IDataQueryListener


interface IProductDataProvider {
    fun filter(listType: String, listener: IDataQueryListener<ProductList>)
}