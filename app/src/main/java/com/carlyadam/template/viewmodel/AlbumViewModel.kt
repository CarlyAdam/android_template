package com.carlyadam.template.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.carlyadam.template.data.api.Result
import com.carlyadam.template.data.model.Album
import com.carlyadam.template.repo.AlbumRepository
import com.carlyadam.template.utilities.Coroutines
import kotlinx.coroutines.Job

class AlbumViewModel(private val albumRepository: AlbumRepository) : ViewModel() {

    private val _responseLiveData = MutableLiveData<List<Album>>()
    val responseLiveData: LiveData<List<Album>> get() = _responseLiveData
    private val _errorLiveData = MutableLiveData<String>()
    val errorLiveData: LiveData<String> get() = _errorLiveData
    private var albumJob: Job? = null

    fun getAlbums() {
        albumJob = Coroutines.io {
            when (val result = albumRepository.getAlbums()) {
                is Result.Success -> _responseLiveData.postValue(result.data)
                is Result.Error -> _errorLiveData.postValue(result.exception.message)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        albumJob?.cancel()
    }
}