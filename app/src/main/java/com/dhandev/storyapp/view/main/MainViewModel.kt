package com.dhandev.storyapp.view.main

import android.util.Log
import androidx.lifecycle.*
import com.dhandev.storyapp.api.ApiConfig
import com.dhandev.storyapp.model.UserModel
import com.dhandev.storyapp.model.UserPreference
import com.dhandev.storyapp.model.GetAllStory
import com.dhandev.storyapp.model.ListStoryItem
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(private val pref: UserPreference) : ViewModel() {
    private val _listStory = MutableLiveData<List<ListStoryItem>>()

//    fun findStory(token : String){
//        val client = ApiConfig.getApiService().getStories("Bearer $token")
//        client.enqueue(object : Callback<GetAllStory> {
//            override fun onResponse(
//                call: Call<GetAllStory>,
//                response: Response<GetAllStory>
//            ) {
//                if (response.isSuccessful){
//                    _listStory.value = (response.body()?.listStory)
//                }
//            }
//
//            override fun onFailure(call: Call<GetAllStory>, t: Throwable) {
//                Log.d("Failuer", t.message.toString())
//            }
//        })
//
//    }
//
//
//    fun getStory() : LiveData<List<ListStoryItem>>{
//        return _listStory
//    }

    fun getUser(): LiveData<UserModel> {
        return pref.getUser().asLiveData()
    }

    fun logout() {
        viewModelScope.launch {
            pref.logout()
        }
    }

}