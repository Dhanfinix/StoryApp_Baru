package com.dhandev.storyapp.view.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.dhandev.storyapp.R
import com.dhandev.storyapp.databinding.ActivityDetailBinding
import com.dhandev.storyapp.model.GetAllStory
import com.dhandev.storyapp.model.ListStoryItem
import com.dhandev.storyapp.withDateFormat

class DetailActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = "Detail Story"
        val story = intent.getParcelableExtra<ListStoryItem>("Story") as ListStoryItem
        binding.apply {
            Glide.with(applicationContext)
                .load(story.photoUrl)
                .transition(DrawableTransitionOptions.withCrossFade())
                .centerInside()
                .into(imgItemPhoto)
            tvItemUsername.text = resources.getString(R.string.detail_storyBy, story.name)
            tvItemDate.text = resources.getString(R.string.detail_createdAt, story.createdAt.withDateFormat())
            tvItemDesc.text = story.description
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}