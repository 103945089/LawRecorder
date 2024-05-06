package com.zhgs.lawrecorder.activity

import android.Manifest
import android.content.Context
import android.os.Build
import android.provider.Settings
import android.telephony.TelephonyManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.zhgs.baseproject.ExtendFunction.loge
import com.zhgs.baseproject.base.BaseActivity
import com.zhgs.lawrecorder.databinding.AtyLoginBinding
import com.zhgs.lawrecorder.viewModel.LoginViewModel


class LoginAty:BaseActivity<AtyLoginBinding,LoginViewModel>(AtyLoginBinding::inflate){

    override fun collectData() {

    }

    override fun initListener() {
    }

    override fun initView() {

    }

    override fun logicStart() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            if (!Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)
                    .equals("")
            ) {

                Toast.makeText(this,"1",Toast.LENGTH_LONG).show()

                // 用户已授权，可以获取ANDROID_ID
                val uniqueId: String =
                    Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)

                Toast.makeText(this,uniqueId,Toast.LENGTH_LONG).show()

            } else {
                Toast.makeText(this,"2",Toast.LENGTH_LONG).show()

                // 请求用户授权
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf<String>(Manifest.permission.READ_PHONE_STATE),
                    10012
                )
            }
        }else{
            val uniqueId: String =
                Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)
            Toast.makeText(this,uniqueId,Toast.LENGTH_LONG).show()
        }
    }
}