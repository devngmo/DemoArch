package tml.lab.demoarch.cca.core.viper

import tml.lab.demoarch.cca.core.entities.ProductList

interface ProductListViewer {
    fun onDataChanged(listType: String, data: ProductList)
}