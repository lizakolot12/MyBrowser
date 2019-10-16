package ua.kolot.filebrowser.ui.list

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import ua.kolot.filebrowser.R
import ua.kolot.filebrowser.databinding.ResourceItemBinding
import ua.kolot.filebrowser.data.model.Resource
import ua.kolot.filebrowser.ui.editresource.EditResourceActivity


class ResourceItemAdapter(private var items: List<Resource>) :
    RecyclerView.Adapter<ResourceItemAdapter.ResourceViewHolder>() {
    init {
    }

    override fun onBindViewHolder(holder: ResourceViewHolder, position: Int) {
        val res = items[position]
        holder.resourceItemBinding.resource = res
        holder.resourceItemBinding.executePendingBindings()
        holder.itemView.setOnClickListener(View.OnClickListener {
            val intent = Intent(holder.itemView.context, EditResourceActivity::class.java)
            intent.putExtra("id", items[position].id)
            holder.itemView.context.startActivity(intent)
        })

    }

    fun setItems(items: List<Resource>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResourceViewHolder {
        val resourceItemBinding: ResourceItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.getContext()),
            R.layout.resource_item, parent, false
        )
        return ResourceViewHolder(
            resourceItemBinding
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ResourceViewHolder(var resourceItemBinding: ResourceItemBinding) :
        RecyclerView.ViewHolder(resourceItemBinding.root) {
        init {
            this.resourceItemBinding = resourceItemBinding
        }
    }

}