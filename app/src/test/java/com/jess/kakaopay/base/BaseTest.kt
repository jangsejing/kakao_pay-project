package com.jess.kakaopay.base

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jess.kakaopay.util.CoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.mockito.MockitoAnnotations

/**
 * Base Test
 *
 * @author jess
 * @since 2020.03.05
 */
open class BaseTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule() // LiveData 테스트를 위한 객체

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutineRule = CoroutineRule() // Coroutine 테스트를 위한 객체

    @Before
    open fun setUp() {
        MockitoAnnotations.initMocks(this)
    }
}