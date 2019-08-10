package fr.leothosthoren.stopwilddump.ui.wildumplist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fr.leothosthoren.stopwilddump.R
import fr.leothosthoren.stopwilddump.data.models.wilddump.WildDumpsItem
import kotlinx.android.synthetic.main.item_wild_dump.view.*

class WildDumpAdapter : PagedListAdapter<WildDumpsItem, WildDumpAdapter.WildDumpViewHolder>(DIFF_CALLBACK) {

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<WildDumpsItem>() {
            override fun areItemsTheSame(oldItem: WildDumpsItem, newItem: WildDumpsItem): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: WildDumpsItem, newItem: WildDumpsItem): Boolean =
                oldItem.id == newItem.id
        }
    }

    class WildDumpViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WildDumpViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_wild_dump, parent, false)
        return WildDumpViewHolder(view)
    }

    override fun onBindViewHolder(holder: WildDumpViewHolder, position: Int) {
        val wildDumpItem = getItem(position)
        holder.itemView.apply {
            itemTitle.text = resources.getString(R.string.item_wild_dump_title,wildDumpItem?.id ,wildDumpItem?.name)
            itemSubtitle.text = wildDumpItem?.description
            Glide.with(context)
                .load(wildDumpItem?.image)
                .centerCrop()
                .into(itemImage)

            if (wildDumpItem?.type?.contains("Ramassage")!!) {
                itemIconState.setImageResource(R.drawable.ic_dump_clean)
            }
        }
    }

}