package tml.lab.demoarch.ma.features.idealist

import android.os.Bundle
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
import kotlinx.android.synthetic.main.fragment_ma_idea_list.*
import tml.lab.demoarch.BR
import tml.lab.demoarch.R
import tml.lab.demoarch.cca.core.viper.SLG
import tml.lab.demoarch.ma.entities.*
import tml.lab.demoarch.ma.extensions.getViewModelFactory
import tml.lab.demoarch.ma.extensions.hide
import tml.lab.demoarch.ma.extensions.show

class MAIdeaListFragment : Fragment() {
    val viewModel by viewModels<IdeaListViewModel> { getViewModelFactory(activity!!) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity!!.title = "Idea List"
        SLG.d(this, "onCreate")
    }

    val selectedTabListType: String
    get() {
        when (ideaListBotNav.selectedItemId) {
            R.id.navlist_recent -> return ILT_RECENT
            R.id.navlist_favorite -> return ILT_FAVORITE
            R.id.navlist_all -> return ILT_ALL
        }
        return ILT_UNDEF
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        SLG.d(this, "onViewCreated")

        ideaListBotNav.setOnNavigationItemSelectedListener { item ->
            ideaListLoadingProgressBar.show()

            when (item.itemId) {
                R.id.navlist_recent -> {
                    SLG.d(this@MAIdeaListFragment, "switch tab $ILT_RECENT")
                    viewModel.getIdeas( ILT_RECENT )
                }
                R.id.navlist_favorite -> {
                    SLG.d(this@MAIdeaListFragment, "switch tab $ILT_FAVORITE")
                    viewModel.getIdeas( ILT_FAVORITE )
                }
                R.id.navlist_all -> {
                    SLG.d(this@MAIdeaListFragment, "switch tab $ILT_ALL")
                    viewModel.getIdeas( ILT_ALL)
                }
            }
            true
        }

        val args : MAIdeaListFragmentArgs by navArgs()
        viewModel.collection.observe(viewLifecycleOwner, Observer { updateListView() })

        if (viewModel.collection.value!!.type != args.listType) {
            when(args.listType) {
                ILT_RECENT -> ideaListBotNav.selectedItemId = R.id.navlist_recent
                ILT_FAVORITE -> ideaListBotNav.selectedItemId = R.id.navlist_favorite
                ILT_ALL -> ideaListBotNav.selectedItemId = R.id.navlist_all
            }
        }
        else {
            if (viewModel.isLoadingData) ideaListLoadingProgressBar.show()
            else ideaListLoadingProgressBar.hide()
        }
    }

    private fun updateListView() {
        SLG.d(this, "notify list adapter " +
                "data has changed: ${viewModel.collection.value!!.ideas.size} " +
                "isLoading ${viewModel.isLoadingData}")
        listAdapter.notifyDataSetChanged()
        if (viewModel.isLoadingData)
            ideaListLoadingProgressBar.show()
        else
            ideaListLoadingProgressBar.hide()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        SLG.d(this, "onCreateView")
        val root = inflater.inflate(R.layout.fragment_ma_idea_list, container, false)
        val lvIdeas = root.findViewById<RecyclerView>(R.id.lvIdeas)
        lvIdeas.adapter = createAdapter()
        lvIdeas.layoutManager = LinearLayoutManager(context)


        return root
    }

    class IdeaViewHolder(
        val binding: ViewDataBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int, idea: Idea) {
//            val gson = Gson()
            //SLG.d("IdeaViewHolder", "bind at $position IDEA: ${gson.toJson(idea)}")
            binding.setVariable(BR.idea, idea)
            binding.executePendingBindings()
        }
    }
    lateinit var listAdapter : RecyclerView.Adapter<IdeaViewHolder>
    private fun createAdapter(): RecyclerView.Adapter<IdeaViewHolder> {
        listAdapter = object: RecyclerView.Adapter<IdeaViewHolder>() {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IdeaViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = DataBindingUtil.inflate<ViewDataBinding>(
                    layoutInflater, R.layout.idea_list_item, parent, false
                )
                return IdeaViewHolder(binding)
            }

            override fun getItemCount(): Int {
                return viewModel.collection.value!!.ideas.size
            }

            override fun onBindViewHolder(holder: IdeaViewHolder, position: Int) {
                val idea: Idea = viewModel.collection.value!!.ideas[position]
                holder.bind(position,idea)
            }
        }
        return listAdapter
    }
}
