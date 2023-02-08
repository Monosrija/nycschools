package com.example.nycschools.domain

import com.example.nycschools.domain.data.SatDetails
import com.example.nycschools.domain.data.SchoolInfo
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * A public interface that exposes the methods getSchoolList() and getSchoolDetails()
 */
interface NycSchoolsApiService {
    @GET("s3k6-pzi2.json")
    suspend fun getSchoolList(): List<SchoolInfo>

    @GET("f9bf-2cp4.json?")
    suspend fun getSatDetails(@Query("dbn") schoolId: String): List<SatDetails>
}

