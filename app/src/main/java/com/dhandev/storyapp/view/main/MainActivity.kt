package com.dhandev.storyapp.view.main

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dhandev.storyapp.R
import com.dhandev.storyapp.ViewModelFactory
import com.dhandev.storyapp.databinding.ActivityMainBinding
import com.dhandev.storyapp.model.UserPreference
import com.dhandev.storyapp.model.GetAllStory
import com.dhandev.storyapp.model.ListStoryItem
import com.dhandev.storyapp.paging.PagingViewModel
import com.dhandev.storyapp.storyItemAdapter
import com.dhandev.storyapp.view.add.AddStoryActivity
import com.dhandev.storyapp.view.login.LoginActivity

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class MainActivity : AppCompatActivity() {
    private lateinit var mainViewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private val pagingViewModel : PagingViewModel by viewModels{
        PagingViewModel.PagingViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()

        getPaging()

        binding.fab.setOnClickListener {
            startActivity(Intent(this@MainActivity, AddStoryActivity::class.java))
        }
    }

    private fun getPaging() {
        val adapter = storyItemAdapter()
        binding.rvGituser.adapter = adapter
        binding.rvGituser.layoutManager = LinearLayoutManager(this)
        binding.rvGituser.setHasFixedSize(true)

        mainViewModel.getUser().observe(this) { user ->
            if (user.isLogin) {
//                mainViewModel.findStory(user.token)
                pagingViewModel.getPagingStory("Bearer ${user.token}").observe(this) {
                    adapter.submitData(lifecycle, it)
                    Log.d("CEK DATA", "$it")
                }
            } else {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }
    }

    private fun setupViewModel() {
        mainViewModel = ViewModelProvider(
            this,
            ViewModelFactory(UserPreference.getInstance(dataStore))
        )[MainViewModel::class.java]
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu1) {
            mainViewModel.logout()
        }
        return true
    }
}