package net.android.amazonapp.ui.fragment

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import net.android.amazonapp.R
import net.android.amazonapp.databinding.IntroFragmentBinding
import java.io.IOException

class IntroFragment : Fragment() {

    init {
        isConnected()
    }

    @Throws(IllegalAccessException::class, IOException::class)
    fun isConnected(): Boolean {
        val command = "ping -c 1 nonstopcode.com"
        return Runtime.getRuntime().exec(command).waitFor() == 0
    }

    lateinit var fragment: IntroFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragment = IntroFragmentBinding.inflate(inflater, container, false)
        fragment.callback = this
        return fragment.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as AppCompatActivity?)!!.supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        if (isConnected())
            playURLVideo()
        else
            playRawVideo()
    }
    private fun playRawVideo(){
        val video = Uri.parse("android.resource://net.android.amazonapp/raw/intro")
        val uri = Uri.parse(video.toString())
        fragment.videoView.setVideoURI(uri)
        fragment.videoView.requestFocus()
        fragment.videoView.start()
        fragment.videoView.setOnCompletionListener {
            findNavController().navigate(R.id.action_introFragment_to_mainFragment)
        }
    }
    private fun playURLVideo() {
        val uri: Uri = Uri.parse("http://nonstopcode.com/rigo/amazon/video/intro.mp4")
        fragment.videoView.setVideoURI(uri)
        fragment.videoView.requestFocus()
        fragment.videoView.start()
        fragment.videoView.setOnCompletionListener {
            findNavController().navigate(R.id.action_introFragment_to_mainFragment)
        }
    }

}