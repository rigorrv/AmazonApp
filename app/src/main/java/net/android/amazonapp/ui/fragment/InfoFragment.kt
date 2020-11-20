package net.android.amazonapp.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import net.android.amazonapp.R
import net.android.amazonapp.databinding.InfoFragmentBinding

var imageInfo =""
var itemInfo  : String? = null
var ratinInfo = 0
var priceInfo = 0
class InfoFragment : Fragment() {

    private lateinit var infoFragment: InfoFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        infoFragment = InfoFragmentBinding.inflate(inflater,container,false)
        infoFragment.callback = this
        return infoFragment.root
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.share, menu)
        menu.findItem(R.id.share)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }


    @SuppressLint("SetTextI18n")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as AppCompatActivity?)!!.supportActionBar?.show()
        (activity as AppCompatActivity?)!!.supportActionBar?.setDisplayShowHomeEnabled(true)
        (activity as AppCompatActivity?)!!.supportActionBar?.setLogo(R.drawable.ic_logo)
        (activity as AppCompatActivity?)!!.supportActionBar?.setDisplayUseLogoEnabled(true)
        (activity as AppCompatActivity?)!!.supportActionBar?.setDisplayShowTitleEnabled(false)

        Picasso.get()
                .load(imageInfo)
                .into(infoFragment.itemPicture)
            infoFragment.itemName.text = itemInfo
            infoFragment.priceItem.text = "$$priceInfo"
            infoFragment.ratingItem.numStars = ratinInfo
    }
}