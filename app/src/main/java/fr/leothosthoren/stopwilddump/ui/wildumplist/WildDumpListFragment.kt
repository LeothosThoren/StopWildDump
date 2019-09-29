package fr.leothosthoren.stopwilddump.ui.wildumplist


import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import fr.leothosthoren.stopwilddump.R
import fr.leothosthoren.stopwilddump.data.models.wilddump.WildDumpsItem
import fr.leothosthoren.stopwilddump.ui.common.CommonViewModel
import kotlinx.android.synthetic.main.fragment_wild_dump_list.*

class WildDumpListFragment : Fragment(), WildDumpAdapter.OnIconClickListener {

    override fun onIconClick(position: Int) {
        Log.d("position list", position.toString())
        val nextAction =
            WildDumpListFragmentDirections.actionDestinationWildDumpListToDetailListFragment(
                position
            )
        findNavController().navigate(nextAction)
    }

    private lateinit var wildDumpAdapter: WildDumpAdapter
    private val sharedViewModel by lazy {
        activity?.let {
            ViewModelProviders.of(it).get(CommonViewModel::class.java)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_wild_dump_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedViewModel?.wildDumpData?.observe(this, Observer {
            configureRecyclerView(it.wildDumps)
        })
    }

    @SuppressLint("WrongConstant")
    private fun configureRecyclerView(list: List<WildDumpsItem?>?) {
        wildDumpAdapter = WildDumpAdapter(list as List<WildDumpsItem>, this)
        wildDumpList.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        wildDumpList.setHasFixedSize(true)
        wildDumpList.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
        wildDumpList.adapter = wildDumpAdapter
    }
}
