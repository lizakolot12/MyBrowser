package ua.kolot.filebrowser.ui.editresource

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import ua.kolot.filebrowser.MyBrowserApp
import ua.kolot.filebrowser.R
import ua.kolot.filebrowser.data.model.Resource
import ua.kolot.filebrowser.databinding.EditResourceFragmentBinding

class EditResourceFragment : Fragment() {

    companion object {
        fun newInstance(id: Int): EditResourceFragment {
            val fragment = EditResourceFragment()
            val args = Bundle()
            args.putSerializable("id", id)
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var viewModel: EditResourceViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.edit_resource_fragment, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        val factory = EditViewModelFactory(
            (activity?.application as MyBrowserApp)
        )
        viewModel = ViewModelProviders.of(this, factory).get(EditResourceViewModel::class.java)
        val binding: EditResourceFragmentBinding =
            setContentView(this.requireActivity(), R.layout.edit_resource_fragment);
        binding.viewModel = viewModel;
        binding.resource = Resource(1, "", "", "")
        val passId = arguments?.getInt(("id"), 0) ?: 0
        if (passId != 0) {
            viewModel.getById(passId)?.observe(this, Observer {
                binding.resource = it
            })

        }

        viewModel.saveSuccessEvent().observe(this, Observer { successEvent ->
            if (successEvent) activity?.finish()
        })

    }

    class EditViewModelFactory(var myApp: MyBrowserApp) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            val editResourceViewModel: EditResourceViewModel =
                myApp.applicationComponent.editResourceViewModel()
            return editResourceViewModel as T
        }

    }
}
