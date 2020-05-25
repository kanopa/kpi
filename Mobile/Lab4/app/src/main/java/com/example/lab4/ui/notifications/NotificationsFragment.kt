package com.example.lab4.ui.notifications

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.MediaController
import android.widget.TextView
import android.widget.VideoView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.lab4.R

class NotificationsFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_notifications, container, false)
        val videoPlayer2: VideoView =  root.findViewById(R.id.videoPlayer2) as VideoView
        val uri: Uri = Uri.parse("http://www.ebookfrenzy.com/android_book/movie.mp4")
        videoPlayer2.setVideoURI(uri)
        val pl = root.findViewById(R.id.button1) as Button
        val ps = root.findViewById(R.id.button2) as Button
        val st = root.findViewById(R.id.button3) as Button
        pl.setOnClickListener {
            start(videoPlayer2);
        }
        ps.setOnClickListener {
            pause(videoPlayer2);
        }
        st.setOnClickListener {
            stop(videoPlayer2);
        }
        return root
    }

    fun start(videoPlayer: VideoView){
        videoPlayer.start();
    }
    fun pause(videoPlayer: VideoView){
        videoPlayer.pause();
    }
    fun stop(videoPlayer: VideoView){
        videoPlayer.stopPlayback();
        videoPlayer.resume();
    }
}
