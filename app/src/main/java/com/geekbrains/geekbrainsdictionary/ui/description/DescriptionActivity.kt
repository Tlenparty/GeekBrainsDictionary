package com.geekbrains.geekbrainsdictionary.ui.description

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import coil.ImageLoader
import coil.request.GetRequest
import coil.request.LoadRequest
import coil.transform.CircleCropTransformation
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.geekbrains.geekbrainsdictionary.R
import com.geekbrains.geekbrainsdictionary.databinding.AcDescriptionBinding
import com.geekbrains.geekbrainsdictionary.extensions.isOnline
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.coroutines.*

class DescriptionActivity : AppCompatActivity() {

    private val binding by lazy { AcDescriptionBinding.inflate(layoutInflater) }

    private val word by lazy { intent?.extras?.getString(KEY_WORD).orEmpty() }
    private val description by lazy { intent?.extras?.getString(KEY_DESCRIPTION).orEmpty() }
    private val imageUrl by lazy { intent?.extras?.getString(KEY_IMAGE_URL) }

    private val coroutineScope = CoroutineScope(
        Dispatchers.Main + SupervisorJob()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setActionBarHomeButton()
        binding.root.setOnRefreshListener {
            startLoadingOrShowError()
        }
        binding.root.isRefreshing = true

        setData()
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineScope.cancel()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setActionBarHomeButton() {
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun startLoadingOrShowError() {
        if (isOnline(this)) {
            setData()
        } else {
            //todo AlertDialogFragment
            stopLoading()
        }
    }

    private fun setData() = with(binding) {
        descriptionHeader.text = word
        descriptionText.text = description
        val imageUrl = imageUrl
        if (imageUrl == null) {
            stopLoading()
        } else {
            //usePicassoLoading(imageUrl)
            //useGlideLoading(imageUrl)
            useCoilToLoadPhoto(imageUrl)
        }
    }

    private fun stopLoading() {
        binding.root.isRefreshing = false
    }

    @SuppressLint("CheckResult")
    private fun useGlideLoading(imageUrl: String) {
        // with(context)
        Glide.with(binding.descriptionImageview)
            // Укаываем что хотим загрузить
            .load("https:$imageUrl")
            .listener(object : RequestListener<Drawable> {

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    stopLoading()
                    return false
                }

                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    stopLoading()
                    binding.descriptionImageview.setImageResource(R.drawable.ic_load_error_vector)
                    return false
                }

            })
            .apply {
                RequestOptions()
                    .placeholder(R.drawable.ic_no_photo_vector)
                    .centerCrop()
            }
            // куда загружаем
            .into(binding.descriptionImageview)
    }

    private fun usePicassoLoading(imageUrl: String) {
        // https потому что без протокола приходит ответ. Указали что грузить
        Picasso.get().load("https:$imageUrl")
            // пока грузим картинку пользвователь увидит это
            .placeholder(R.drawable.ic_no_photo_vector)
            .fit()
            .centerCrop() // Обрезка по середине если не вмещается
            // куда грузить + callback для отображения/скрывания loader
            .into(binding.descriptionImageview, object : Callback {
                override fun onSuccess() {
                    stopLoading()
                }

                override fun onError(e: Exception?) {
                    stopLoading()
                    binding.descriptionImageview.setImageResource(R.drawable.ic_load_error_vector)
                }

            })
    }

    private fun useCoilToLoadPhoto(imageUrl: String) {
        val request = LoadRequest.Builder(this)
            .data("https:$imageUrl")
            .target(
                onStart = {},
                onSuccess = { result ->
                    stopLoading()
                    binding.descriptionImageview.setImageDrawable(result)
                },
                onError = {
                    binding.descriptionImageview.setImageResource(R.drawable.ic_no_photo_vector)
                }
            )
            .transformations(
                CircleCropTransformation()
            ).build()

        //  coroutineScope.launch {
        ImageLoader(this@DescriptionActivity).execute(request)
        //}

    }

    companion object {
        private const val KEY_WORD = "KEY_WORD"
        private const val KEY_DESCRIPTION = "KEY_DESCRIPTION"
        private const val KEY_IMAGE_URL = "KEY_IMAGE_URL"

        fun getIntent(
            context: Context,
            word: String,
            description: String,
            imageUrl: String?
        ) = Intent(context, DescriptionActivity::class.java).apply {
            putExtra(KEY_WORD, word)
            putExtra(KEY_DESCRIPTION, description)
            putExtra(KEY_IMAGE_URL, imageUrl)
        }
    }
}