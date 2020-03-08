package tml.lab.demoarch.cca.core.entities
//PRODUCT_LIST_TYPE
val PLT_ALL = "all"
val PLT_HOT = "hot"
val PLT_COLD = "cold"

class Product(var id:String, var name:String, var price: Float) {
}

typealias ProductList = ArrayList<Product>