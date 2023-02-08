package com.example.nycschools.domain.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SchoolInfo(
    @SerializedName("dbn") val schoolId: String,
    @SerializedName("school_name") val schoolName: String,
    @SerializedName("website") val website: String? = null
) : Parcelable
