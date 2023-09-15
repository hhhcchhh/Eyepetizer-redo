package com.example.eyepetizer_redo.ui

import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import android.os.PersistableBundle
import com.example.eyepetizer_redo.BaseActivity
import com.example.eyepetizer_redo.GlobalUtil
import com.example.eyepetizer_redo.R
import com.example.eyepetizer_redo.databinding.ActivitySplashBinding
import com.example.eyepetizer_redo.util.DataStoreUtils
import com.permissionx.guolindev.PermissionX
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseActivity(){

    private var _binding: ActivitySplashBinding? = null
    private val binding : ActivitySplashBinding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        requestWriteExternalStoragePermission()
    }
    private fun requestWriteExternalStoragePermission() {
        PermissionX.init(this@SplashActivity).permissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            .onExplainRequestReason { scope, deniedList ->
                val message = GlobalUtil.getString(R.string.request_permission_picture_processing)
                scope.showRequestReasonDialog(deniedList, message,
                    GlobalUtil.getString(R.string.ok),
                    GlobalUtil.getString(R.string.cancel)
                )
            }
            .onForwardToSettings { scope, deniedList ->
                val message = GlobalUtil.getString(R.string.request_permission_picture_processing)
                scope.showForwardToSettingsDialog(deniedList, message,
                    GlobalUtil.getString(R.string.settings),
                    GlobalUtil.getString(R.string.cancel)
                )
            }
            .request { allGranted, grantedList, deniedList ->
                //不管有没有设置权限都会走这里
                //如果需要的话可以使用if(allGranted){}如果获得了全部权限做...
                //grantedList通过的权限列表、deniedList拒绝的权限列表
                requestReadPhoneStatePermission()
            }
    }

    private fun requestReadPhoneStatePermission() {
        PermissionX.init(this@SplashActivity).permissions(Manifest.permission.READ_PHONE_STATE)
            .onExplainRequestReason { scope, deniedList ->
                val message = GlobalUtil.getString(R.string.request_permission_access_phone_info)
                scope.showRequestReasonDialog(deniedList, message,
                    GlobalUtil.getString(R.string.ok),
                    GlobalUtil.getString(R.string.cancel)
                )
            }
            .onForwardToSettings { scope, deniedList ->
                val message = GlobalUtil.getString(R.string.request_permission_access_phone_info)
                scope.showForwardToSettingsDialog(deniedList, message,
                    GlobalUtil.getString(R.string.settings),
                    GlobalUtil.getString(R.string.cancel)
                )
            }
            .request { allGranted, grantedList, deniedList ->
                _binding = ActivitySplashBinding.inflate(layoutInflater)
                setContentView(binding.root)
            }
    }
    companion object {

        /**
         * 是否首次进入APP应用
         */
        var isFirstEntryApp: Boolean
            get() = DataStoreUtils.readBooleanData("is_first_entry_app", true)
            set(value) {
                CoroutineScope(Dispatchers.IO).launch { DataStoreUtils.saveBooleanData("is_first_entry_app", value) }
            }
    }
}