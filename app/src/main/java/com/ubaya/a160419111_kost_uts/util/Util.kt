package com.ubaya.a160419111_kost_uts.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.ubaya.a160419111_kost_uts.R
import java.lang.Exception

fun ImageView.loadImage(url:String?,progressbar: ProgressBar){
    Picasso.get()
        .load(url)
        .resize(400,400)
        .centerCrop()
        .error(R.drawable.ic_baseline_error_24)
        .into(this, object : Callback {
            override fun onSuccess() {
                progressbar.visibility = View.GONE
            }

            override fun onError(e: Exception?) {

            }
        })
}

fun createNotificationChannel(context: Context, importance:Int, showBadge:Boolean, name:String, description:String)
{
    if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
        val channelID = "${context.packageName}--$name"
        val channel = NotificationChannel(channelID,name,importance).apply {
            setShowBadge(showBadge)
        }
        val notificationManager = context.getSystemService(NotificationManager::class.java)
        notificationManager.createNotificationChannel(channel)
    }
}

@BindingAdapter( "android:imageUrl", "android:progressBar")
fun loadPhotoUrl(view:ImageView, url:String, pb:ProgressBar){
    view.loadImage(url, pb)
}
