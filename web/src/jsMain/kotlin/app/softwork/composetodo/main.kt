package app.softwork.composetodo

import kotlinx.coroutines.*
import org.jetbrains.compose.web.*

fun main() {
    // https://youtrack.jetbrains.com/issue/KTOR-539
    js(
        """
window.originalFetch = window.fetch;
window.fetch = function (resource, init) {
    return window.originalFetch(resource, Object.assign({ credentials: 'include' }, init || {}));
};
"""
    )
    renderComposable(rootElementId = "root") {
        val appContainer = Container(MainScope())
        MainApp(appContainer)
    }
}