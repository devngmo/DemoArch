package tml.lab.demoarch.cca.features.productlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import tml.lab.demoarch.cca.application.repositories.ProductRepository
import tml.lab.demoarch.cca.core.entities.ProductList
import tml.lab.demoarch.cca.ui.CCAMainActivity
import tml.lab.demoarch.cca.core.viper.ProductListViewer
import tml.lab.demoarch.cca.core.viper.SLG
import tml.lab.demoarch.cca.core.viper.ViperContext

public class ProductListViewModel (val context: ViperContext, val productRepository: ProductRepository) : ViewModel(), ProductListViewer {
    private val _listType = MutableLiveData<String>()
    //var _items = productRepository.observeProducts("")
    var items : MutableLiveData<ProductList> = MutableLiveData(ProductList()) // : LiveData<List<Product>> = _items
//
//    fun filter(listType:String) {
//        Log.d("DBGAPP", "filter $listType")
//        _listType.value = listType
//        //items = productRepository.observeProducts(listType)
//    }

    override fun onDataChanged(listType: String, data: ProductList) {

        (context._context as CCAMainActivity).runOnUiThread {
            SLG.d("ProductListViewModel", "on data changed: $listType ${data.size} Products")
            _listType.value = listType
            items.value = data
        }
    }
}