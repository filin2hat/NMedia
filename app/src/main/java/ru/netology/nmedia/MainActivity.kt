package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

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
            likes = 0,
            shares = 0
        )
        with(binding) {
            author.text = post.author
            published.text = post.published
            content.text = post.content
            likesCounter.text = post.likes.toString()
            if (post.likedByMe) {
                likesButton.setImageResource(R.drawable.ic_liked_24)
            }

            likesButton.setOnClickListener {
                post.likedByMe = !post.likedByMe
                likesButton.setImageResource(
                    if (post.likedByMe) R.drawable.ic_liked_24 else R.drawable.ic_like_24
                )
                if (post.likedByMe) post.likes++ else post.likes--
                likesCounter.text = post.likes.toString()
            }

            sharedCounter.text = post.shares.toString()
            shareButton.setOnClickListener {
                post.shares += 10
                sharedCounter.text = post.shares.toString()
            }

        }

    }
}