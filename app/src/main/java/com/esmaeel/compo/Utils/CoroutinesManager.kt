package com.esmaeel.compo.Utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch


fun doOnBackground(givenFunction: () -> Unit) =
    CoroutineScope(Dispatchers.IO).launch { givenFunction.invoke() }

fun doOnMain(givenFunction: () -> Unit) =
    CoroutineScope(Dispatchers.Main).launch { givenFunction.invoke() }

fun doSafely(givenFunction: () -> Unit, onSuccess: () -> Unit, onError: (String) -> Unit) =
    try {
        givenFunction.invoke()
        onSuccess.invoke()
    } catch (e: Exception) {
        onError(e.localizedMessage ?: "")
    }

fun doSafelyOnIO(givenFunction: () -> Unit, onSuccess: () -> Unit, onError: (String) -> Unit) =
    CoroutineScope(Dispatchers.IO).launch {
        try {
            givenFunction.invoke()
            CoroutineScope(Dispatchers.Main).launch { onSuccess.invoke() }
        } catch (e: Exception) {
            CoroutineScope(Dispatchers.Main).launch { onError(e.localizedMessage ?: "") }
        }
    }


fun Example() {

    doSafely(
        givenFunction = {
            /* On Main */
            /* CoroutineScope(Dispatchers.Main).launch { heavy() } */
            /* doOnBackground { heavy() } */
        },
        onSuccess = {/* On Main */ },
        onError = {/* On Main */ }
    )

    doSafelyOnIO(
        givenFunction = {
            // TODO:  On IO
            CoroutineScope(Dispatchers.Main).launch { heavy() }
            /* doOnBackground { heavy() } */
        },
        onSuccess = {/* On Main */ },
        onError = {/* On Main */ }
    )
}

suspend fun heavy() = flow { emit("Hi") }
fun notHeavy() = "Hi"

