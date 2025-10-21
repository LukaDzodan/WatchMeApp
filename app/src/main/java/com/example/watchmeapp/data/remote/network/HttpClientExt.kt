package com.example.watchmeapp.data.remote.network

import android.util.Log
import com.example.watchmeapp.common.Resource
import com.example.watchmeapp.domain.errors.NetworkError
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.ensureActive
import okio.IOException
import java.net.ConnectException
import kotlin.coroutines.coroutineContext

suspend inline fun <reified T> safeCall(
    execute : () -> HttpResponse
) : Resource<T> {

    val response = try {
        execute()
    }catch (e : SocketTimeoutException){
        return Resource.Error(NetworkError.Timeout.message)
    }catch (e: UnresolvedAddressException) {
        return Resource.Error(NetworkError.NoInternet.message)
    } catch (e: ConnectException) {
        return Resource.Error(NetworkError.NoInternet.message)
    } catch (e: IOException) {
        return Resource.Error(NetworkError.NoInternet.message)
    }catch (e : Exception) {
        Log.e("safeCall", "EXCEPTION: ${e::class.simpleName} - ${e.localizedMessage}")
        e.printStackTrace()
        coroutineContext.ensureActive()
        return Resource.Error(NetworkError.Unknown.message)
    }

    return responseToResult(response)
}

suspend inline fun <reified T> responseToResult(
    response: HttpResponse,
): Resource<T> {
    return when (response.status.value) {
        in 200..299 -> {
            try {
                Resource.Success(response.body<T>())
            } catch (e: NoTransformationFoundException) {
                Resource.Error(NetworkError.Serialization.message)
            }
        }
        408 -> Resource.Error(NetworkError.Timeout.message)
        429 -> Resource.Error(NetworkError.TooManyRequests.message)
        in 500..599 -> Resource.Error(NetworkError.Server.message)
        else -> {
            Log.e("responseToResult", "${response.status}")
            Resource.Error(NetworkError.Unknown.message)
        }
    }
}