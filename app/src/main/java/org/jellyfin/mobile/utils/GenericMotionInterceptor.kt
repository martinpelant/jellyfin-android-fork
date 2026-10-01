package org.jellyfin.mobile.utils

import android.view.MotionEvent

/**
 * Additional hook for handling generic motion events (e.g. mouse hover, scroll, button press)
 * in [Fragments][androidx.fragment.app.Fragment] (see [onInterceptGenericMotionEvent]).
 */
interface GenericMotionInterceptor {
    /**
     * Called when a generic motion event occurs while this fragment is currently visible.
     *
     * @return `true` if the event was intercepted and handled by the fragment,
     *         `false` otherwise.
     */
    fun onInterceptGenericMotionEvent(event: MotionEvent): Boolean = false
}
