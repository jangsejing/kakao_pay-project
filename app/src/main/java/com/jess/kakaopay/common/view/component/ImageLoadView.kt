package com.jess.kakaopay.common.view.component

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.jess.kakaopay.R
import com.jess.kakaopay.databinding.ImageLoadViewBinding
import kotlinx.android.synthetic.main.image_load_view.view.*
import timber.log.Timber

/**
 * 평점
 *
 * @author jess
 * @since 2020.06.12
 */
class ImageLoadView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val binding = ImageLoadViewBinding.inflate(LayoutInflater.from(context), this, true)
    private var isCorners: Boolean = false

    init {
        binding.isLoaded = true
    }

    /**
     * 라운드 설정
     *
     * @param isCorners
     */
    fun setCorners(isCorners: Boolean) = apply {
        this.isCorners = isCorners
    }

    @SuppressLint("CheckResult")
    fun load(url: String) {
        Timber.d(">> load $url")

        val glide = Glide.with(context)
            .load(url)
            .transition(DrawableTransitionOptions.withCrossFade())
            .centerCrop()

        if (isCorners) {
            glide.apply(
                RequestOptions.bitmapTransform(
                    RoundedCorners(
                        context.resources.getDimensionPixelSize(
                            R.dimen.dp8
                        )
                    )
                )
            )
        }

        glide.listener(object : RequestListener<Drawable> {

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                binding.isLoaded = true
                Timber.d(">> onResourceReady $url")
                return false
            }

            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                binding.isLoaded = false
                Timber.d(">> onLoadFailed $url")
                Timber.d(">> onLoadFailed ${e?.message}")
                return false
            }
        }).into(iv_succeed)
    }
}
