package com.esmaeel.composepalygroundtwo

/*
* this data class holds the data with different status to check for
* the same class hold the Data & Status & Error
* */
enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}

data class Contract<out T /* is the out class type whatever the type*/>(
    val status: Status /*Enum to define if i should read from data or from message*/,
    val data: T? /*same*/,
    val message: String?
) {
    companion object {

        fun <T> onSuccess(data: T): Contract<T> =
            Contract(
                status = Status.SUCCESS,
                data = data,
                message = null
            )

        fun <T> onError(data: T, message: String): Contract<T> =
            Contract(
                status = Status.ERROR,
                data = data,
                message = message
            )

        fun <T> onLoading(data: T?): Contract<T?> =
            Contract(
                status = Status.LOADING,
                data = data,
                message = null
            )
    }
}
