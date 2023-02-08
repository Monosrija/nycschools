package com.example.nycschools.ui.schoollist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nycschools.domain.data.SchoolInfo
import com.example.nycschools.domain.SchoolUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class NycSchoolsApiStatus { LOADING, ERROR, DONE }

@HiltViewModel
class SchoolListViewModel @Inject constructor(
    private val schoolUsecase : SchoolUsecase
) : ViewModel() {

    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<NycSchoolsApiStatus>()

    // The external immutable LiveData for the request status
    val status: LiveData<NycSchoolsApiStatus> = _status

    // Internally, we use a MutableLiveData, because we will be updating the List
    // with new values
    private val _list = MutableLiveData<List<SchoolInfo>>()

    // The external LiveData interface to the property is immutable, so only this class can modify
    val list: LiveData<List<SchoolInfo>> = _list

    init {
        getSchoolList()
    }

    private fun getSchoolList() {

        viewModelScope.launch {
            _status.value = NycSchoolsApiStatus.LOADING
            try {
                _list.value = schoolUsecase.getSchoolList()
                _status.value = NycSchoolsApiStatus.DONE
            } catch (e: Exception) {
                _status.value = NycSchoolsApiStatus.ERROR
                _list.value = listOf()
            }
        }
    }
}
