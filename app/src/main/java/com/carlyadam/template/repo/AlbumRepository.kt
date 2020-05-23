package com.carlyadam.template.repo

import android.content.Context
import com.carlyadam.template.R
import com.carlyadam.template.data.api.ApiService
import com.carlyadam.template.data.api.Result
import com.carlyadam.template.data.api.safeApiCall
import com.carlyadam.template.data.model.Album
import java.io.IOException

class AlbumRepository(private val apiService: ApiService, private val context: Context) {

    suspend fun getAlbums() = safeApiCall(
        call = { fetchAlbums() },
        errorMessage = context.getString(R.string.no_connection)
    )

    private suspend fun fetchAlbums(): Result<List<Album>> {
        val response = apiService.getAlbums()
        if (response.code() == 401)
            return Result.UnAuthorized(context.getString(R.string.unauthorized))
        else if (response.isSuccessful)
            return Result.Success(response.body()!!)
        return Result.Error(IOException(context.getString(R.string.no_connection)))
    }
}