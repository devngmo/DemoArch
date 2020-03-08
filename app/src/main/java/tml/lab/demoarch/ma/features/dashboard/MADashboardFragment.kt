package tml.lab.demoarch.ma.features.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import tml.lab.demoarch.R
import tml.lab.demoarch.cca.core.entities.PLT_COLD
import tml.lab.demoarch.cca.core.entities.PLT_HOT
import tml.lab.demoarch.ma.entities.ILT_FAVORITE
import tml.lab.demoarch.ma.entities.ILT_RECENT

class MADashboardFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_ma_dashboard, container, false)
        root.findViewById<Button>(R.id.btnHotList).setOnClickListener { onClickBtnHotList() }
        root.findViewById<Button>(R.id.btnColdList).setOnClickListener { onClickBtnColdList() }
        return root
    }
    fun onClickBtnHotList() {
        showIdeaList(ILT_RECENT)
    }

    private fun showIdeaList(listType:String) {
        val action = MADashboardFragmentDirections
            .actionMADashboardToIdeaList(listType)
        findNavController().navigate(action)
    }

    fun onClickBtnColdList() {
        showIdeaList(ILT_FAVORITE)
    }
}
