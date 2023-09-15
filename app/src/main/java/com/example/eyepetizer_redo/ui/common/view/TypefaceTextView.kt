package com.example.eyepetizer_redo.ui.common.view

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.example.eyepetizer_redo.R
import com.example.eyepetizer_redo.util.TypeFaceUtil

class TypefaceTextView : AppCompatTextView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ){
        attrs?.let {
            //获取TypedArray 对象，用于获取自定义属性的值
            val typedArray = context.obtainStyledAttributes(it, R.styleable.TypefaceTextView, 0, 0)
            val typefaceType = typedArray.getInt(R.styleable.TypefaceTextView_typeface, 0)
            //设置字体属性
            typeface = getTypeface(typefaceType)
            //回收对象避免内存泄漏
            typedArray.recycle()
        }
    }
    companion object {
        /**
         * 根据字体类型，获取自定义字体。
         */
        fun getTypeface(typefaceType: Int?) = when (typefaceType) {
            TypeFaceUtil.FZLL_TYPEFACE -> TypeFaceUtil.fzlLTypeface
            TypeFaceUtil.FZDB1_TYPEFACE -> TypeFaceUtil.fzdb1Typeface
            TypeFaceUtil.FUTURA_TYPEFACE -> TypeFaceUtil.futuraTypeface
            TypeFaceUtil.DIN_TYPEFACE -> TypeFaceUtil.dinTypeface
            TypeFaceUtil.LOBSTER_TYPEFACE -> TypeFaceUtil.lobsterTypeface
            else -> Typeface.DEFAULT
        }
    }
}