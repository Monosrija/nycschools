package com.example.nycschools.ui.schoollist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.nycschools.databinding.SchoolListItemBinding
import com.example.nycschools.domain.data.SchoolInfo

class SchoolListAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<SchoolInfo, SchoolListAdapter.SchoolListViewHolder>(DiffCallback) {

    class SchoolListViewHolder(
        private var binding: SchoolListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(schoolInfo: SchoolInfo) {
            binding.schoolInfo = schoolInfo
            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }
    }

    /**
     * Allows the RecyclerView to determine which items have changed when the [List] of
     * [SchoolInfo] has been updated.
     */
    companion object DiffCallback : DiffUtil.ItemCallback<SchoolInfo>() {
        override fun areItemsTheSame(oldItem: SchoolInfo, newItem: SchoolInfo): Boolean {
            return oldItem.schoolId == newItem.schoolId
        }
        override fun areContentsTheSame(oldItem: SchoolInfo, newItem: SchoolInfo): Boolean {
            return oldItem.schoolName == newItem.schoolName
        }
    }

    /**
     * Create new [RecyclerView] item views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SchoolListViewHolder {
        return SchoolListViewHolder(
            SchoolListItemBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    /**
     * Replaces the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: SchoolListViewHolder, position: Int) {
        val schoolInfo = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(schoolInfo)
        }
        holder.bind(schoolInfo)
    }

    class OnClickListener(val clickListener: (schoolInfo: SchoolInfo) -> Unit) {
        fun onClick(schoolInfo: SchoolInfo) = clickListener(schoolInfo)
    }
}
