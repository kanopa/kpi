package com.example.lab4.ui.notifications

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.lab4.R
import kotlinx.android.synthetic.main.fragment_notifications.*

class NotificationsFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_notifications, container, false)
        val videoPlayer2: VideoView =  root.findViewById(R.id.videoPlayer2) as VideoView
        val uri: Uri = Uri.parse("https://www.videvo.net/videvo_files/converted/2015_02/preview/Weaver.mp435523.webm")
        videoPlayer2.setVideoURI(uri)
        val pl = root.findViewById(R.id.button1) as Button
        val ps = root.findViewById(R.id.button2) as Button
        val st = root.findViewById(R.id.button3) as Button
        val choose = root.findViewById(R.id.button) as Button
        val input = root.findViewById(R.id.button5) as Button
        pl.setOnClickListener {
            start(videoPlayer2);
        }
        ps.setOnClickListener {
            pause(videoPlayer2);
        }
        st.setOnClickListener {
            stop(videoPlayer2);
        }
        choose.setOnClickListener {
            chooseFile()
        }
        input.setOnClickListener {
            input(root)
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
    fun input(root: View){
        val mediaController = MediaController(context)
        val text = root.findViewById(R.id.editText) as EditText
        videoPlayer2.setMediaController(mediaController)
        videoPlayer2.setVideoURI(Uri.parse(text.text.toString()))
    }
    fun chooseFile(){
        val intent = Intent()
        intent.setType("video/*")
        intent.setAction(Intent.ACTION_GET_CONTENT)
        startActivityForResult(intent, 101)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK && data != null)
        {
            if(requestCode == 101)
            {
                val mediaController = MediaController(context)
                videoPlayer2.setMediaController(mediaController)
                mediaController.setMediaPlayer(videoPlayer2)


                val uri = data.data!!
                videoPlayer2.setVideoURI(uri)
            }
        }
    }
}
