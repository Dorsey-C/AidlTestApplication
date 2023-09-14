package com.dorsey.aidlapplication

import android.app.Service
import android.content.Intent
import android.os.*
import android.util.Log

/************************************
 * @author: dorsey
 * @描述:
 * @日期: 2023/8/3
 ************************************/
class AidlService: Service() {

    private val mStub:IBinder = object : IMyAidlInterface.Stub() {
        override fun sum(num1: Int, num2: Int): Int {
            return num1 + num2;
        }


    }


    override fun onBind(intent: Intent): IBinder {
        Log.d("JACK", "onBind")
        return mStub
    }

    override fun onCreate() {
        super.onCreate()
        Log.d("JACK", "onCreate")

    }

    override fun onStart(intent: Intent, startId: Int) {
        super.onStart(intent, startId)
        Log.d("JACK", "onStart")
    }

    override fun onUnbind(intent: Intent): Boolean {
        Log.d("JACK", "onUnbind")
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("JACK", "onDestroy")
    }
}