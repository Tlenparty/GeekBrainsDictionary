package com.geekbrains.geekbrainsdictionary.ui.views

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView

// @JvmOverloads - чтобы AppCompatImageView сам определял для себя необходимые конструкторы
class EqualSizeImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {

    // Переопределяем onMeasure и передаём в него ширину изображения в качестве
    // ширины и высоты. Так мы получим аккуратное квадратное изображение
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }
}