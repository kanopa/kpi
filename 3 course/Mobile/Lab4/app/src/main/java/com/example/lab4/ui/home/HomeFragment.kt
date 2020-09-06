package com.example.lab4.ui.home

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.media.MediaPlayer
import android.media.MediaPlayer.OnCompletionListener
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.SeekBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.lab4.R
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {

    var mPlayer: MediaPlayer? = null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        mPlayer=MediaPlayer.create(root.context, R.raw.news);
        val playMusic = root.findViewById(R.id.button6) as Button
        val pauseMusic = root.findViewById(R.id.button7) as Button
        val stopMusic = root.findViewById(R.id.button8) as Button
        val chooseFile = root.findViewById(R.id.button4) as Button
        val audioManager = context!!.getSystemService(Context.AUDIO_SERVICE) as AudioManager
        val maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        val curValue = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        val volume = root.findViewById(R.id.seekBar) as SeekBar
        mPlayer!!.setOnCompletionListener(OnCompletionListener {
            stopPlay()
        })
        volume.setMax(maxVolume);
        volume.setProgress(curValue);
        playMusic.setOnClickListener {
            play()
        }
        pauseMusic.setOnClickListener {
            pause()
        }
        stopMusic.setOnClickListener {
            stop()
        }
        chooseFile.setOnClickListener {
            chooseFile()
        }
        volume.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, i, 0);
            }
            override fun onStartTrackingTouch(seekBar: SeekBar) {
            }
            override fun onStopTrackingTouch(seekBar: SeekBar) {
            }
        })
        return root
    }

    private fun stopPlay() {
        mPlayer!!.stop()
        button7.setEnabled(false)
        button8.setEnabled(false)
        try {
            mPlayer!!.prepare()
            mPlayer!!.seekTo(0)
            button6.setEnabled(true)
        } catch (t: Throwable) {
            Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun chooseFile()
    {
        val intent = Intent()
        intent.setType("audio/*")
        intent.setAction(Intent.ACTION_GET_CONTENT)
        startActivityForResult(intent, 101)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK && data != null)
        {
            if(requestCode == 101)
            {
                val uri = data.data!!
                button6.setEnabled(true)
                mPlayer = MediaPlayer.create(context, uri);
                mPlayer!!.setOnCompletionListener { stopPlay() }
            }
        }
    }

    fun play() {
        mPlayer!!.start()
        button6.setEnabled(false)
        button7.setEnabled(true)
        button8.setEnabled(true)
    }

    fun pause() {
        mPlayer!!.pause()
        button6.setEnabled(true)
        button7.setEnabled(false)
        button8.setEnabled(true)
    }
    fun stop() {
        stopPlay()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mPlayer!!.isPlaying) {
            stopPlay()
        }
    }
}
