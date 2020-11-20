package net.android.amazonapp.ui.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import net.android.amazonapp.R
import net.android.amazonapp.database.ViewModelFactory
import net.android.amazonapp.databinding.MainFragmentBinding
import net.android.amazonapp.model.JsonDataItem
import net.android.amazonapp.ui.adapter.MainAdapter
import net.android.amazonapp.viewmodel.PostListViewModel


lateinit var fragment: MainFragmentBinding

class MainFragment : Fragment() {
    private lateinit var myViewModel: PostListViewModel
    private var mainAdapter = MainAdapter()


    override fun onAttach(context: Context) {
        super.onAttach(context)
        myViewModel =
            ViewModelProvider(this, ViewModelFactory(context)).get(PostListViewModel::class.java)

    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragment = MainFragmentBinding.inflate(inflater, container, false)
        fragment.callback = this
        return fragment.root

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)


    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
        menu.findItem(R.id.navSort)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navSort -> {
                if (PostListViewModel.sortById == "ID") {
                    PostListViewModel.sortById = "Name"
                } else {
                    PostListViewModel.sortById = "ID"

                }
            }
            else -> {
                item.title = PostListViewModel.sortById
            }
        }
        fragment.sortTitle.text = PostListViewModel.sortById
        myViewModel.loadSql()
        return super.onOptionsItemSelected(item)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as AppCompatActivity?)!!.supportActionBar?.show()
        (activity as AppCompatActivity?)!!.supportActionBar?.setDisplayShowHomeEnabled(true)
        (activity as AppCompatActivity?)!!.supportActionBar?.setLogo(R.drawable.ic_logo)
        (activity as AppCompatActivity?)!!.supportActionBar?.setDisplayUseLogoEnabled(true)
        (activity as AppCompatActivity?)!!.supportActionBar?.setDisplayShowTitleEnabled(false)

        if (savedInstanceState != null) {
            PostListViewModel.sortById = savedInstanceState.getString("value").toString()

        }

        fragment.sortTitle.text = PostListViewModel.sortById

        val jsonData = Observer<List<JsonDataItem>> {
            if (it.isEmpty()) {
                fragment.noData.visibility = View.VISIBLE
            } else {
                mainAdapter.mainList = it
                fragment.listRecyclerView.adapter = mainAdapter
                fragment.noData.visibility = View.GONE
            }
        }
        val dataCheck = Observer<Boolean> {
            if (it)
                fragment.noData.visibility = View.VISIBLE
            else
                fragment.noData.visibility = View.GONE

        }
        myViewModel.noData.observe(viewLifecycleOwner, dataCheck)
        myViewModel.jsonData.observe(viewLifecycleOwner, jsonData)

    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString("value", PostListViewModel.sortById)
        super.onSaveInstanceState(outState)
    }

    fun checkDb() {
        myViewModel.checkDB()
    }

}