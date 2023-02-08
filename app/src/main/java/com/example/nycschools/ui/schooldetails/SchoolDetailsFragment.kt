package com.example.nycschools.ui.schooldetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.nycschools.databinding.FragmentSchoolDetailsBinding
import com.example.nycschools.domain.data.SchoolInfo
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SchoolDetailsFragment : Fragment() {

    private val viewModel: SchoolDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var schoolInfo: SchoolInfo? = arguments?.getParcelable("schoolInfo")
        if (schoolInfo != null) {
            viewModel.getSchoolDetails(schoolInfo.schoolId)
        }

        var binding = FragmentSchoolDetailsBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.schoolInfo = schoolInfo
        return binding.root
    }
}
