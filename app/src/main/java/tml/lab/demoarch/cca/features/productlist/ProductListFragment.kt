package tml.lab.demoarch.cca.features.productlist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import tml.lab.demoarch.R
import tml.lab.demoarch.databinding.FragmentProductListBinding
import tml.lab.demoarch.cca.core.entities.Product
import tml.lab.demoarch.cca.ui.extensions.getViewModelFactory
import tml.lab.demoarch.cca.core.viper.SLG
import tml.lab.demoarch.cca.core.viper.ViperContext


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProductListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProductListFragment : Fragment() {
    // TODO: Rename and change types of parameters
//    private var param1: String? = null
//    private var param2: String? = null

    private lateinit var viewDataBinding: FragmentProductListBinding
    private val viewModel by viewModels<ProductListViewModel> { getViewModelFactory(activity!!) }

    lateinit var presenter : ProductListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }
        viewModel.items.observe(this, Observer { list -> listAdapter.notifyDataSetChanged() })
        presenter =  ProductListPresenter(ViperContext(context!!), viewModel)
        //presenter.showProductList(args.listType)
    }

    //val args : ProductListFragmentArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("DBGAPP", "onCreateView ProductList")
        // Inflate the layout for this fragment
        val root =  inflater.inflate(R.layout.fragment_product_list, container, false)
        viewDataBinding = FragmentProductListBinding.bind(root).apply {
            //viewmodel = viewModel
        }

        val lvProducts = root.findViewById<RecyclerView>(R.id.lvProducts)
        lvProducts.adapter = createAdapter()
        lvProducts.layoutManager = LinearLayoutManager(context)
        return root
    }

    class ProductViewHolder(
        //view:View
        val binding: ViewDataBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int, product: Product) {
            val gson = Gson()
            SLG.d("ProductViewHolder", "bind at $position product: ${gson.toJson(product)}")
            //(itemView as TextView).text = product.name
            //binding.setVariable(tml.lab.demoarch.BR.product, product)
            binding.executePendingBindings()
        }
    }
    lateinit var listAdapter : RecyclerView.Adapter<ProductViewHolder>
    private fun createAdapter(): RecyclerView.Adapter<ProductViewHolder> {
        listAdapter = object: RecyclerView.Adapter<ProductViewHolder>() {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                //val card = layoutInflater.inflate(android.R.layout.simple_list_item_1, parent, false)
                val binding = DataBindingUtil.inflate<ViewDataBinding>(
                    layoutInflater, R.layout.product_list_item, parent, false
                )
                return ProductViewHolder(binding)
            }

            override fun getItemCount(): Int {
                if (viewModel.items == null) return 0
                if (viewModel.items.value == null) return 0
                SLG.d("ProductListFragment.Adapter", "items ${viewModel.items.value!!.size}")
                return viewModel.items.value!!.size
            }

            override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
                val product: Product = viewModel.items.value!![position]
                holder.bind(position, product)
            }
        }
        return listAdapter
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProductListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProductListFragment().apply {
                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
