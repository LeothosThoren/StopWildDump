package fr.leothosthoren.stopwilddump.ui.wildumplist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import fr.leothosthoren.stopwilddump.R
import fr.leothosthoren.stopwilddump.data.models.wilddump.WildDumpsItem
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_wild_dump.*

class WildDumpAdapter(
    private val wildDumps: List<WildDumpsItem>,
    private val listener: OnIconClickListener
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface OnIconClickListener {
        fun onIconClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_wild_dump, null)
        return WildDumpViewHolder(view)
    }

    override fun getItemCount(): Int = wildDumps.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        WildDumpViewHolder(holder.itemView).updateView(wildDumps[position], position, listener)
    }
}

class WildDumpViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
    LayoutContainer {

    fun updateView(
        wildDumps: WildDumpsItem,
        position: Int,
        onIconClickListener: WildDumpAdapter.OnIconClickListener
    ) {
        itemTitle.text = wildDumps.name
        item_Description.text = wildDumps.description
        Glide.with(containerView.context)
            .setDefaultRequestOptions(RequestOptions().centerCrop())
            .load(wildDumps.image)
            .placeholder(R.drawable.stop_decharges)
            .into(item_Image)
        if (wildDumps.type!!.contains("Ramassage")) {
            item_icon.setImageDrawable(
                ContextCompat.getDrawable(
                    containerView.context,
                    R.drawable.ic_dump_clean
                )
            )
        }
        // Click
        containerView.setOnClickListener {
            onIconClickListener.onIconClick(position)
        }
    }
}