package com.dhandev.storyapp.paging

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.dhandev.storyapp.model.ListStoryItem

class PagingViewModel(private val storyRepository: StoryRepository) : ViewModel() {

    fun getPagingStory(token: String): LiveData<PagingData<ListStoryItem>> = storyRepository.getStory(token).cachedIn(viewModelScope)

    class PagingViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
        override fun <T: ViewModel> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(PagingViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return PagingViewModel(Injection.provideRepository(context)) as T
            }
            else throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}