package com.example.nycschools.ui.schooldetails

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.nycschools.MainCoroutineRule
import com.example.nycschools.domain.data.SatDetails
import com.example.nycschools.domain.SchoolUsecase
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class SchoolDetailsViewModelTest {
    // Set the main coroutines dispatcher for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    lateinit var schoolUsecase: SchoolUsecase
    private val schoolId = "School123"
    private val satDetails = SatDetails(
        schoolId,
        "ABC school",
        "123",
        "1234",
        "1235",
        "1236"
    )


    private lateinit var schoolDetailsViewModel: SchoolDetailsViewModel

    @Before
    fun setUp() {
        schoolUsecase = Mockito.mock(SchoolUsecase::class.java)
        schoolDetailsViewModel = SchoolDetailsViewModel(schoolUsecase)
    }

    @Test
    fun testGetSchoolDetails() {
        runBlocking {
            Mockito.`when`(schoolUsecase.getSatDetails(schoolId)).thenReturn(listOf(satDetails))

            schoolDetailsViewModel.getSchoolDetails(schoolId)

            verify(schoolUsecase, times(1)).getSatDetails(schoolId)

            assertEquals(schoolId, schoolDetailsViewModel.details.value?.schoolId)
            assertEquals("ABC school", schoolDetailsViewModel.details.value?.schoolName)
            assertEquals("123", schoolDetailsViewModel.details.value?.numTestTakers)
            assertEquals("1234", schoolDetailsViewModel.details.value?.readingScore)
            assertEquals("1235", schoolDetailsViewModel.details.value?.mathScore)
            assertEquals("1236", schoolDetailsViewModel.details.value?.writingScore)

        }
    }

    @Test
    fun testGetSchoolDetails_EmptyResponse() {
        runBlocking {
            Mockito.`when`(schoolUsecase.getSatDetails(schoolId)).thenReturn(emptyList())

            schoolDetailsViewModel.getSchoolDetails(schoolId)

            verify(schoolUsecase, times(1)).getSatDetails(schoolId)

            assertEquals(null, schoolDetailsViewModel.details.value?.schoolId)
            assertEquals(null, schoolDetailsViewModel.details.value?.schoolName)
            assertEquals(null, schoolDetailsViewModel.details.value?.numTestTakers)
            assertEquals(null, schoolDetailsViewModel.details.value?.readingScore)
            assertEquals(null, schoolDetailsViewModel.details.value?.mathScore)
            assertEquals(null, schoolDetailsViewModel.details.value?.writingScore)

        }
    }
}