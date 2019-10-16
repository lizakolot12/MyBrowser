package ua.kolot.filebrowser

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import ua.kolot.filebrowser.data.model.Resource
import ua.kolot.filebrowser.ui.list.ResourceItemAdapter

@BindingAdapter("data")
fun <T> setRecyclerViewProperties(recyclerView: RecyclerView, items: List<Resource>) {
    if (recyclerView.adapter is ResourceItemAdapter) {
        (recyclerView.adapter as ResourceItemAdapter).setItems(items)
    }
}