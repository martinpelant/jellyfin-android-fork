package org.jellyfin.mobile.utils

import android.view.MotionEvent
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class GenericMotionInterceptorTest {

    @Test
    fun `default onInterceptGenericMotionEvent returns false`() {
        val interceptor = object : GenericMotionInterceptor {}
        val motionEvent = mockk<MotionEvent>()

        assertFalse(interceptor.onInterceptGenericMotionEvent(motionEvent))
    }

    @Test
    fun `custom interceptor consumes handled generic motion events`() {
        val interceptor = object : GenericMotionInterceptor {
            override fun onInterceptGenericMotionEvent(event: MotionEvent): Boolean {
                return when (event.action) {
                    MotionEvent.ACTION_HOVER_MOVE,
                    MotionEvent.ACTION_HOVER_ENTER,
                    MotionEvent.ACTION_HOVER_EXIT,
                    MotionEvent.ACTION_SCROLL,
                    MotionEvent.ACTION_BUTTON_PRESS,
                    MotionEvent.ACTION_BUTTON_RELEASE -> true
                    else -> false
                }
            }
        }

        val hoverMove = mockk<MotionEvent> { every { action } returns MotionEvent.ACTION_HOVER_MOVE }
        val hoverEnter = mockk<MotionEvent> { every { action } returns MotionEvent.ACTION_HOVER_ENTER }
        val hoverExit = mockk<MotionEvent> { every { action } returns MotionEvent.ACTION_HOVER_EXIT }
        val scroll = mockk<MotionEvent> { every { action } returns MotionEvent.ACTION_SCROLL }
        val buttonPress = mockk<MotionEvent> { every { action } returns MotionEvent.ACTION_BUTTON_PRESS }
        val buttonRelease = mockk<MotionEvent> { every { action } returns MotionEvent.ACTION_BUTTON_RELEASE }
        val otherAction = mockk<MotionEvent> { every { action } returns MotionEvent.ACTION_OUTSIDE }

        assertTrue(interceptor.onInterceptGenericMotionEvent(hoverMove))
        assertTrue(interceptor.onInterceptGenericMotionEvent(hoverEnter))
        assertTrue(interceptor.onInterceptGenericMotionEvent(hoverExit))
        assertTrue(interceptor.onInterceptGenericMotionEvent(scroll))
        assertTrue(interceptor.onInterceptGenericMotionEvent(buttonPress))
        assertTrue(interceptor.onInterceptGenericMotionEvent(buttonRelease))
        assertFalse(interceptor.onInterceptGenericMotionEvent(otherAction))
    }
}
