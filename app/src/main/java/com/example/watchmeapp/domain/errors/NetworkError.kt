package com.example.watchmeapp.domain.errors

sealed class NetworkError(val message : String) : Error {

    object NoInternet : NetworkError("No internet connection")
    object Timeout : NetworkError("Request timed out")
    object Server : NetworkError("Server error")
    object Serialization : NetworkError("Failed to parse response")
    object TooManyRequests : NetworkError("Too many requests — please try again later")
    object Unknown : NetworkError("Unknown error occurred")
}
