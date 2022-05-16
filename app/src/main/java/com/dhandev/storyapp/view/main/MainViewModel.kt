package com.dhandev.storyapp.view.main

import androidx.lifecycle.*
import com.dhandev.storyapp.model.UserModel
import com.dhandev.storyapp.model.UserPreference
import kotlinx.coroutines.launch

class MainViewModel(private val pref: UserPreference) : ViewModel() {

    fun getUser(): LiveData<UserModel> {
        return pref.getUser().asLiveData()
    }

    fun logout() {
        viewModelScope.launch {
            pref.logout()
        }
    }

}