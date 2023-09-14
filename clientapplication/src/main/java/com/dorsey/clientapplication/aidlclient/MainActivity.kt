package com.dorsey.clientapplication.aidlclient

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.dorsey.aidlapplication.IMyAidlInterface
import com.dorsey.clientapplication.R

class MainActivity : AppCompatActivity() {
    var imyAidl: IMyAidlInterface?=null
    private val serviceConnection:ServiceConnection= object : ServiceConnection {
        override fun onServiceConnected(p0: ComponentName, p1: IBinder) {
            imyAidl = IMyAidlInterface.Stub.asInterface(p1)
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            Log.e("onServiceDisconnected","p0:$p0")
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val intent = Intent()
        intent.action="com.example.aidl"
        intent.addCategory("android.intent.category.DEFAULT")
        intent.setClassName("com.dorsey.aidlapplication", "com.dorsey.aidlapplication.AidlService")
        Log.e("Tag", "do bindService")
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)

    }

    fun startSum(v: View){
        var sum = 0
        try {
            //调用服务中暴露的接口
            sum = imyAidl?.sum(11, 22)?:0
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("Tag", e.message!!)
        }
        Log.e("Tag", "调用结果：$sum")
    }
}