package com.example.nycschools.domain

import com.example.nycschools.domain.data.SatDetails
import com.example.nycschools.domain.data.SchoolInfo
import javax.inject.Inject

class SchoolUsecase @Inject constructor(
    private val apiService: NycSchoolsApiService
) : NycSchoolsApiService {

    override suspend fun getSchoolList(): List<SchoolInfo> {
        return apiService.getSchoolList()
            .sortedWith(compareBy(String.CASE_INSENSITIVE_ORDER) { it.schoolName })
    }

    override suspend fun getSatDetails(schoolId: String): List<SatDetails> {
        return apiService.getSatDetails(schoolId)
    }
}
