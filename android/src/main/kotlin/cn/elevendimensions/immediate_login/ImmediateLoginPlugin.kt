package cn.elevendimensions.immediate_login

import android.content.Context
import android.util.Log
import com.mobile.auth.gatewayauth.PhoneNumberAuthHelper
import com.mobile.auth.gatewayauth.TokenResultListener
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry.Registrar

class ImmediateLoginPlugin : MethodCallHandler {
    companion object {
        private var context: Context? = null
        private var mTokenListener: TokenResultListener = object : TokenResultListener {
            override fun onTokenSuccess(ret: String) {
                println("liuquanfeng$ret")
            }

            override fun onTokenFailed(ret: String) {
                println("liuquanfeng$ret")
            }
        }
        private var mAlicomAuthHelper: PhoneNumberAuthHelper? = null
        @JvmStatic
        fun registerWith(registrar: Registrar) {
            val channel = MethodChannel(registrar.messenger(), "immediate_login")
            channel.setMethodCallHandler(ImmediateLoginPlugin())
            init(registrar)
        }

        private fun init(registrar: Registrar) {
            context = registrar.activeContext()
            mAlicomAuthHelper = PhoneNumberAuthHelper.getInstance(context, mTokenListener)
            mAlicomAuthHelper!!.setAuthListener(mTokenListener)
            mAlicomAuthHelper!!.setLoggerEnable(true)
            /**
             * 控件点击事件回调
             */
            mAlicomAuthHelper!!.setUIClickListener { code, context, jsonObj -> Log.e("authSDK", "OnUIControlClick:code=" + code + ", jsonObj=" + if (jsonObj == null) "" else jsonObj.toJSONString()) }
        }
    }

    override fun onMethodCall(call: MethodCall, result: Result) {
        if (call.method == "login") {
            val token = call.arguments
            mAlicomAuthHelper!!.setAuthSDKInfo(token.toString())
            if (mAlicomAuthHelper!!.checkEnvAvailable()) {
                mAlicomAuthHelper!!.getLoginToken(context, 10000)
            }
        } else {
            result.notImplemented()
        }
    }
}
