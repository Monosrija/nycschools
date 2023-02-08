package com.example.nycschools.ui

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.nycschools.R
import com.example.nycschools.domain.data.SchoolInfo
import com.example.nycschools.ui.schoollist.NycSchoolsApiStatus
import com.example.nycschools.ui.schoollist.SchoolListAdapter

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<SchoolInfo>?) {
    val adapter = recyclerView.adapter as SchoolListAdapter
    adapter.submitList(data)
}

@BindingAdapter("nycSchoolsApiStatus")
fun bindStatus(statusImageView: ImageView, status: NycSchoolsApiStatus) {
    when (status) {
        NycSchoolsApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        NycSchoolsApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        NycSchoolsApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}

@BindingAdapter("nycSchoolsApiStatus")
fun bindStatus(linearLayout: LinearLayout, status: NycSchoolsApiStatus) {
    when (status) {
        NycSchoolsApiStatus.LOADING -> {
            linearLayout.visibility = View.GONE
        }
        NycSchoolsApiStatus.ERROR -> {
            linearLayout.visibility = View.GONE
        }
        NycSchoolsApiStatus.DONE -> {
            linearLayout.visibility = View.VISIBLE
        }
    }
}