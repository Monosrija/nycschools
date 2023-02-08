package com.example.nycschools.ui.schooldetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nycschools.domain.data.SatDetails
import com.example.nycschools.domain.SchoolUsecase
import com.example.nycschools.ui.schoollist.NycSchoolsApiStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SchoolDetailsViewModel @Inject constructor(
    private val schoolUsecase: SchoolUsecase
) : ViewModel() {
    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<NycSchoolsApiStatus>()

    // The external immutable LiveData for the request status
    val status: LiveData<NycSchoolsApiStatus> = _status

    // Internally, we use a MutableLiveData, because we will be updating the List
    // with new values
    private val _details = MutableLiveData<SatDetails>()

    // The external LiveData interface to the property is immutable, so only this class can modify
    val details: LiveData<SatDetails> = _details

    fun getSchoolDetails(schoolId: String) {
        viewModelScope.launch {
            _status.value = NycSchoolsApiStatus.LOADING
            try {
                val response = schoolUsecase.getSatDetails(schoolId)
                if (response.isNotEmpty()) {
                    _details.value = response[0]
                }
                _status.value = NycSchoolsApiStatus.DONE
            } catch (e: Exception) {
                _status.value = NycSchoolsApiStatus.ERROR
            }
        }
    }
}
