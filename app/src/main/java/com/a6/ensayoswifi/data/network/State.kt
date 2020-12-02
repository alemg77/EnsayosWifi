package com.a6.ensayoswifi.data.network

sealed class State<T>(var data: T? = null) {

    class Success<T>(data: T) : State<T>(data)

    class Loading<T> : State<T>()

    class Empty<T> : State<T>()

    class Error<T>(data: T? = null) : State<T>(data)

    override fun toString(): String {
        return when (this) {
            is Success -> "Success"
            is Loading -> "Loading"
            is Error -> "Error"
            is Empty -> "Empty"
        }
    }
}