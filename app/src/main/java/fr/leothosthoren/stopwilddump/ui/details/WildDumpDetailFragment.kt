package fr.leothosthoren.stopwilddump.ui.details


import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import fr.leothosthoren.stopwilddump.R
import fr.leothosthoren.stopwilddump.base.BaseFragment
import fr.leothosthoren.stopwilddump.ui.common.CommonViewModel
import kotlinx.android.synthetic.main.fragment_wild_dump_detail.*

class WildDumpDetailFragment : BaseFragment() {

    private val sharedViewModel by lazy {
        activity?.let {
            ViewModelProviders.of(it).get(CommonViewModel::class.java)
        }
    }
    val args: WildDumpDetailFragmentArgs by navArgs()


    override fun getLayoutId(): Int = R.layout.fragment_wild_dump_detail

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val position = args.WildDumpPosition
        Log.d("position detail", position.toString())

        bindDetailViews(position)
    }

    private fun bindDetailViews(position: Int?) {
        sharedViewModel?.wildDumpData?.observe(this, Observer { dumpData ->
            detailId.text = context?.getString(
                R.string.detail_wild_dump_id,
                position?.let { dumpData?.wildDumps?.get(it)?.id.toString() })
            detailType.text = context?.getString(R.string.type,
                position?.let { dumpData?.wildDumps?.get(it)?.type })
            if (position?.let { dumpData?.wildDumps?.get(it)?.type } == "Ramassage") {
                detailIcon.setImageDrawable(context?.let {
                    ContextCompat.getDrawable(it, R.drawable.ic_dump_clean)
                })
            }
            detailZipCode.text = context?.getString(R.string.zip,
                position?.let { dumpData?.wildDumps?.get(it)?.zipcode.toString() })
            detailCity.text = context?.getString(R.string.city,
                position?.let { dumpData?.wildDumps?.get(it)?.country })
            detailLat.text = context?.getString(R.string.detail_latitude,
                position?.let { dumpData?.wildDumps?.get(it)?.latitude.toString() })
            detailLong.text = context?.getString(R.string.detail_longitude,
                position?.let { dumpData?.wildDumps?.get(it)?.longitude.toString() })
            detailSignalDate.text = context?.getString(R.string.signal_date,
                position?.let { dumpData?.wildDumps?.get(it)?.reportDate })
            detailSignalClean.text = context?.getString(R.string.clean_date,
                position?.let { dumpData?.wildDumps?.get(it)?.cleanningDate })
            if (!position?.let { dumpData?.wildDumps?.get(it)?.cleanningDate }.isNullOrBlank()) {
                detailSignalClean.isVisible = true
            }
            detailDescriptionContent.text =
                position?.let { dumpData?.wildDumps?.get(it)?.description }
            context?.let {
                Glide.with(it)
                    .setDefaultRequestOptions(RequestOptions().centerCrop())
                    .load(position?.let { pos -> dumpData.wildDumps?.get(pos)?.image })
                    .into(detailImage)
            }
        })
    }

}
