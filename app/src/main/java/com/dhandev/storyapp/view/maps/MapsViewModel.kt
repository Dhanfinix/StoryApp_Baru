package com.dhandev.storyapp.view.maps

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dhandev.storyapp.api.ApiConfig
import com.dhandev.storyapp.model.GetAllStory
import com.dhandev.storyapp.model.ListStoryItem
import com.dhandev.storyapp.model.UserModel
import com.dhandev.storyapp.model.UserPreference
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MapsViewModel (private val pref : UserPreference) : ViewModel() {
    private val _listStory = MutableLiveData<List<ListStoryItem>>()
    val listStory: LiveData<List<ListStoryItem>> = _listStory

    fun getUser(): LiveData<UserModel> {
        return pref.getUser().asLiveData()
    }

    fun findStory(token : String){
        val client = ApiConfig.getApiService().getStoryBasedLocation("Bearer $token")
        client.enqueue(object : Callback<GetAllStory> {
            override fun onResponse(
                call: Call<GetAllStory>,
                response: Response<GetAllStory>
            ) {
                if (response.isSuccessful){
                    _listStory.value = (response.body()?.listStory)
                }
            }

            override fun onFailure(call: Call<GetAllStory>, t: Throwable) {
                Log.d("Failuer", t.message.toString())
            }
        })

    }


    fun getStory() : LiveData<List<ListStoryItem>> {
        return listStory
    }
}