package ru.netology.nmedia.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val viewModel: PostViewModel by viewModels()
        viewModel.data.observe(this) { post ->
            with(binding) {
                author.text = post.author
                published.text = post.published
                content.text = post.content
                likesCounter.text = counterNumbersToShortString(post.likes)
                sharedCounter.text = counterNumbersToShortString(post.shares)
                countViews.text = counterNumbersToShortString(post.views)
                likesButton.setImageResource(
                    if (post.likedByMe) R.drawable.ic_liked_24 else R.drawable.ic_like_24
                )
            }
        }

        binding.likesButton.setOnClickListener {
            viewModel.like()
        }

        binding.shareButton.setOnClickListener {
            viewModel.share()
        }

    }
    private fun counterNumbersToShortString(number: Int): String {
        return when {
            number in 1_000..1_099 -> "1K"
            number in 1_100..9_999 && number % 1000 == 0 -> "${number / 1000}K"
            number in 1_100..9_999 && number % 1000 != 0 -> ((number / 100).toDouble() / 10).toString() + "K"
            number in 10_000..999_999 -> "${number / 1000}K"
            number >= 1_000_000 && number % 1000000 == 0 -> "${number / 1000000}M"
            number >= 1_000_000 && number % 1000000 != 0 -> ((number / 100000).toDouble() / 10).toString() + "M"
            else -> number.toString()
        }
    }

}