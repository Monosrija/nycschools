package com.example.nycschools.ui.schoollist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import com.example.nycschools.R
import com.example.nycschools.databinding.FragmentSchoolListBinding
import com.example.nycschools.domain.data.SchoolInfo
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SchoolListFragment : Fragment() {

    private val viewModel: SchoolListViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSchoolListBinding.inflate(inflater)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        // Giving the binding access to the ViewModel
        binding.viewModel = viewModel

        // Sets the adapter to the RecyclerView
        binding.schoolList.adapter = SchoolListAdapter(SchoolListAdapter.OnClickListener {
            navigateToDetails(it)
        })
        return binding.root
    }

    private fun navigateToDetails(schoolInfo: SchoolInfo) {
        val bundle = bundleOf("schoolInfo" to schoolInfo)
        findNavController(this).navigate(R.id.action_schoolList_to_schoolDetails, bundle)
    }
}
