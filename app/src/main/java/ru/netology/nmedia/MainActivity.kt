package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val debug = "MyLog"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val post = Post(
            id = 1,
            author = "Нетология. Университет интернет-профессий будущего",
            content = "Привет, это новая Нетология! Когда-то Нетология " +
                    "начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по " +
                    "дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти" +
                    " студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с " +
                    "нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше," +
                    " целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать" +
                    " цепочку перемен → http://netolo.gy/fyb",
            published = "21 мая в 18:36",
            likedByMe = false,
            likes = 1100,
            shares = 12000,
            views = 1300000
        )
        binding.root.setOnClickListener{
            Log.d(debug, "ROOT is pressed")
        }
        with(binding) {
            author.text = post.author
            published.text = post.published
            content.text = post.content
            likesCounter.text = counterNumbersToShortString(post.likes)
            sharedCounter.text = counterNumbersToShortString(post.shares)
            countViews.text = counterNumbersToShortString(post.views)

            if (post.likedByMe) {
                likesButton.setImageResource(R.drawable.ic_liked_24)
            }

            likesButton.setOnClickListener {
                Log.d(debug, "LIKE button is pressed")
                post.likedByMe = !post.likedByMe
                likesButton.setImageResource(
                    if (post.likedByMe) R.drawable.ic_liked_24 else R.drawable.ic_like_24
                )
                if (post.likedByMe) post.likes++ else post.likes--
                likesCounter.text = counterNumbersToShortString(post.likes)
            }

            shareButton.setOnClickListener {
                post.shares++
                sharedCounter.text = counterNumbersToShortString(post.shares)
            }

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