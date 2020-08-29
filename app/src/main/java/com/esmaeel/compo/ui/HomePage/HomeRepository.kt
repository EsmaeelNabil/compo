package com.esmaeel.compo.ui.HomePage

import com.esmaeel.compo.data.network.getService
import com.esmaeel.composepalygroundtwo.Contract
import kotlinx.coroutines.flow.flow

/**
 *  fetching the data from the api and handle the errors and the exceptions and return it to the ui with a status.
 */
fun getPersonsData(pageNumber: Int = 0) = flow {
    // emit the first state which is LOADING
    emit(Contract.onLoading(data = null))
    try {
        val response = getService().getPopularPersons(pageNumber = pageNumber)

        if (response.isSuccessful) {
            emit(Contract.onSuccess(data = response.body()))
        } else {
            response.errorBody()?.let { errorBody ->
                // TODO: 8/29/20 here we should extract the error from the errorbody response json and emit it to the ui with an error status
                emit(
                    Contract.onError(
                        data = null,
                        message = "MyUtils.getErrorFromBody(errorBody)"
                    )
                )
            }
        }
    } catch (a: Exception) {
        a.localizedMessage?.let {
            // emitting the exception message to the ui with an error status
            emit(
                Contract.onError(
                    data = null,
                    message = it
                )
            )
        }
    }

}