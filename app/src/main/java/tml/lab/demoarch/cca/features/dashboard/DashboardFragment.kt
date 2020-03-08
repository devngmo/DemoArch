@file:Suppress("unused")

package tml.lab.demoarch.cca.features.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import tml.lab.demoarch.R
import tml.lab.demoarch.cca.ui.EventObserver
import tml.lab.demoarch.cca.ui.extensions.getViewModelFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DashboardFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DashboardFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        root.findViewById<Button>(R.id.btnHotList).setOnClickListener { onClickBtnHotList() }
        root.findViewById<Button>(R.id.btnColdList).setOnClickListener { onClickBtnColdList() }
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupNavigation()
    }

    private val viewModel by viewModels<DashboardViewModel> { getViewModelFactory(activity!!) }
    private fun setupNavigation() {
        viewModel.openProductListEvent.observe(viewLifecycleOwner,
            EventObserver {
                openProductList(it)
            })
    }

    private fun openProductList(listType: String) {
        val action =
            DashboardFragmentDirections.actionDashboardFragmentToProductListFragment(
                listType
            )
        findNavController().navigate(action)
    }

    fun onClickBtnHotList() {
        viewModel.openProductList("hot")
    }

    fun onClickBtnColdList() {
        viewModel.openProductList("cold")
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DashboardFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DashboardFragment().apply {
                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
