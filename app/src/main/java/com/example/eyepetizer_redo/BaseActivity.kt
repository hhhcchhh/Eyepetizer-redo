package com.example.eyepetizer_redo

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import com.example.eyepetizer_redo.databinding.ActivitySplashBinding
import java.lang.ref.WeakReference

open class BaseActivity : AppCompatActivity() {

    /**
     * 判断当前Activity是否在前台。
     */
    protected var isActive: Boolean = false

    /**
     * 当前Activity的实例。
     */
    protected var activity: Activity? = null

    /** 当前Activity的弱引用，防止内存泄露  */
    private var activityWR: WeakReference<Activity>? = null

    /**
     * 日志输出标志
     */
    protected val TAG: String = this.javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        logD(TAG, "BaseActivity-->onCreate()")

        activity = this
        activityWR = WeakReference(activity!!)
//        ActivityCollector.pushTask(activityWR)

//        EventBus.getDefault().register(this)
    }
    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
        setStatusBarBackground(R.color.colorPrimaryDark)
        setupViews()
    }

    override fun setContentView(layoutView: View) {
        super.setContentView(layoutView)
        setStatusBarBackground(R.color.colorPrimaryDark)
        setupViews()
    }
    open fun setStatusBarBackground(@ColorRes statusBarColor: Int) {
        ImmersionBar.with(this).autoStatusBarDarkModeEnable(true, 0.2f).statusBarColor(statusBarColor).fitsSystemWindows(true).init()
    }
    protected open fun setupViews() {
        val navigateBefore = findViewById<ImageView>(R.id.ivNavigateBefore)
        val tvTitle = findViewById<TextView>(R.id.tvTitle)
        navigateBefore?.setOnClickListener { finish() }
        tvTitle?.isSelected = true  //获取焦点，实现跑马灯效果。 //好像没有实现？

    }

}