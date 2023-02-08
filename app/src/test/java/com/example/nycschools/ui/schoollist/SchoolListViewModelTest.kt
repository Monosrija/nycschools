package com.example.nycschools.ui.schoollist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.nycschools.MainCoroutineRule
import com.example.nycschools.domain.data.SchoolInfo
import com.example.nycschools.domain.SchoolUsecase
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class SchoolListViewModelTest {
    // Set the main coroutines dispatcher for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    lateinit var schoolUsecase: SchoolUsecase
    private val schoolId = "School123"
    private val schoolInfo = SchoolInfo(
        schoolId,
        "ABC school",
        "abc.com"
    )

    private lateinit var schoolListViewModel: SchoolListViewModel

    @Before
    fun setUp() {
        schoolUsecase = Mockito.mock(SchoolUsecase::class.java)
    }

    @Test
    fun testGetSchoolDetails() {
        runBlocking {
            Mockito.`when`(schoolUsecase.getSchoolList()).thenReturn(listOf(schoolInfo))

            schoolListViewModel = SchoolListViewModel(schoolUsecase)

            verify(schoolUsecase, times(1)).getSchoolList()
            Assert.assertEquals(1, schoolListViewModel.list.value?.size)
            Assert.assertEquals(schoolId, schoolListViewModel.list.value?.get(0)?.schoolId)
            Assert.assertEquals("ABC school", schoolListViewModel.list.value?.get(0)?.schoolName)
            Assert.assertEquals("abc.com", schoolListViewModel.list.value?.get(0)?.website)
        }
    }
}
