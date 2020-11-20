package net.android.amazonapp.ui.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.main_items.view.*
import net.android.amazonapp.R
import net.android.amazonapp.model.JsonDataItem
import net.android.amazonapp.ui.fragment.*


class MainAdapter : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    var mainList: List<JsonDataItem> = mutableListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.main_items, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(mainList[position])
        holder.itemView.setOnClickListener {
            val image = mainList[position].name?.replace("%20", "")
            val imageUrl = "http://nonstopcode.com/rigo/amazon/img/$image.jpg"
            imageInfo = imageUrl
            itemInfo = mainList[position].name
            ratinInfo = mainList[position].stars
            priceInfo = mainList[position].price

            holder.itemView.findNavController().navigate(
                R.id.action_mainFragment_to_infoFragment
            )
        }
    }

    override fun onViewAttachedToWindow(holder: ViewHolder) {
        super.onViewAttachedToWindow(holder)
        Log.d("TAG", "onViewAttachedToWindow: Loaded")
        fragment.progressBar.visibility = View.GONE
        fragment.sortBox.visibility = View.VISIBLE

    }


    override fun getItemCount(): Int {
        return mainList.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val infoText = itemView.infoText
        private val itemImage = itemView.itemImage
        private val priceText = itemView.priceText
        private val ratingBar = itemView.ratingBar

        @SuppressLint("SetTextI18n")
        fun onBind(mainList: JsonDataItem) {
            infoText.text = mainList.name
            priceText.text = "$${mainList.price}"
            ratingBar.numStars = mainList.stars
            Picasso.get()
                .load(mainList.image)
                .into(itemImage)
        }
    }
}
