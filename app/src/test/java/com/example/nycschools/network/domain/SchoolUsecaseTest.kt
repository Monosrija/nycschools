package com.example.nycschools.network.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.nycschools.MainCoroutineRule
import com.example.nycschools.domain.SchoolUsecase
import com.example.nycschools.domain.NycSchoolsApiService
import com.example.nycschools.domain.data.SatDetails
import com.example.nycschools.domain.data.SchoolInfo
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class SchoolUsecaseTest {
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    lateinit var schoolUsecase: SchoolUsecase
    lateinit var retrofitService: NycSchoolsApiService

    private val schoolId = "School123"
    private val schoolInfo1 = SchoolInfo(
        schoolId,
        "ABC school",
        "abc.com"
    )

    private val schoolInfo2 = SchoolInfo(
        "School789",
        "XYZ school",
        "xyz.com"
    )

    private val satDetails = SatDetails(
        schoolId,
        "ABC school",
        "123",
        "1234",
        "1235",
        "1236"
    )

    @Before
    fun setUp() {
        retrofitService = Mockito.mock(NycSchoolsApiService::class.java)
        schoolUsecase = SchoolUsecase(retrofitService)
    }


    @Test
    fun testGetSchoolList() {
        runBlocking {
            Mockito.`when`(retrofitService.getSchoolList()).thenReturn(listOf(schoolInfo2,schoolInfo1))

            val response = schoolUsecase.getSchoolList()

            verify(retrofitService, times(1)).getSchoolList()
            Assert.assertEquals(2, response.size)
            Assert.assertEquals(schoolId, response[0].schoolId)
            Assert.assertEquals("ABC school", response[0].schoolName)
            Assert.assertEquals("abc.com", response[0].website)

            Assert.assertEquals("School789", response[1].schoolId)
            Assert.assertEquals("XYZ school", response[1].schoolName)
            Assert.assertEquals("xyz.com", response[1].website)
        }
    }

    @Test
    fun testGetSatDetails() {
        runBlocking {
            Mockito.`when`(retrofitService.getSatDetails(schoolId)).thenReturn(listOf(satDetails))

            val response = schoolUsecase.getSatDetails(schoolId)

            verify(retrofitService, times(1)).getSatDetails(schoolId)
            Assert.assertEquals(1, response.size)
            Assert.assertEquals(schoolId, response[0].schoolId)
            Assert.assertEquals("ABC school", response[0].schoolName)
            Assert.assertEquals("123", response[0].numTestTakers)
            Assert.assertEquals("1234", response[0].readingScore)
            Assert.assertEquals("1235", response[0].mathScore)
            Assert.assertEquals("1236", response[0].writingScore)
        }
    }
}
