package ua.kolot.filebrowser.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import ua.kolot.filebrowser.MyBrowserApp
import ua.kolot.filebrowser.R
import ua.kolot.filebrowser.databinding.ListResourceFragmentBinding
import ua.kolot.filebrowser.db.ResourceDatabase

class ListResourceFragment : Fragment() {
    private val listAdapter: ResourceItemAdapter =
        ResourceItemAdapter(emptyList())

    companion object {
        fun newInstance() = ListResourceFragment()
    }

    private lateinit var viewModel: ListResourceViewModel
    private lateinit var listResourceFragmentBinding: ListResourceFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        listResourceFragmentBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.list_resource_fragment,
            container,
            false
        )
        val resourceDao =
            this.context?.let { ResourceDatabase.getDatabase(it).resourceDao() }
                ?: return listResourceFragmentBinding.root
        val factory =
            ListViewModelFactory(
                activity?.application as MyBrowserApp
            )
        viewModel = ViewModelProviders.of(this, factory).get(ListResourceViewModel::class.java)
        listResourceFragmentBinding.viewModel = viewModel;
        return listResourceFragmentBinding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        listResourceFragmentBinding.lifecycleOwner = this.viewLifecycleOwner
        val viewModel = listResourceFragmentBinding.viewModel
        if (viewModel != null) {
            listResourceFragmentBinding.list.layoutManager = LinearLayoutManager(activity)
            listResourceFragmentBinding.list.adapter = listAdapter
        }

        viewModel?.getAll()?.observe(this, Observer { t ->
            run {
                listAdapter.setItems(t)

            }
        })
    }

    class ListViewModelFactory(var myApp: MyBrowserApp) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            val editResourceViewModel: ListResourceViewModel =
                myApp.applicationComponent.listResourceViewModel()
            return editResourceViewModel as T
        }

    }
}
