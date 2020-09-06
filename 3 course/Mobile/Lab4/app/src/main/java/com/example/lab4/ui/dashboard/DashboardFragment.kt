package com.example.lab4.ui.dashboard

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.MediaController
import android.widget.VideoView
import androidx.fragment.app.Fragment
import com.example.lab4.R

class DashboardFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        val videoPlayer: VideoView =  root.findViewById(R.id.videoPlayer) as VideoView
        val uri: Uri = Uri.parse("android.resource://" + getActivity()?.getPackageName() + "/" + R.raw.videoplayback)
        val pl = root.findViewById(R.id.play) as Button
        val ps = root.findViewById(R.id.pause) as Button
        val st = root.findViewById(R.id.stop) as Button
        val mediaController = MediaController(root.context)
        videoPlayer.setMediaController(mediaController)
        mediaController.setMediaPlayer(videoPlayer)
        videoPlayer.setVideoURI(uri)
        pl.setOnClickListener {
            start(videoPlayer);
        }
        ps.setOnClickListener {
            pause(videoPlayer);
        }
        st.setOnClickListener {
            stop(videoPlayer);
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
