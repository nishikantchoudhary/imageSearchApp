package com.axxess.imageapp

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.axxess.imageapp.adapter.ImageAdapter
import com.axxess.imageapp.models.ImageEntity
import com.axxess.imageapp.viewmodels.ImageViewModel
import kotlinx.android.synthetic.main.activity_home_screen.*

class HomeActivity : AppCompatActivity() {

    private lateinit var searchView: SearchView
    private lateinit var layoutManager: GridLayoutManager
    private lateinit var adapter: ImageAdapter
    private lateinit var viewModel: ImageViewModel
    private lateinit var imageList: MutableLiveData<List<ImageEntity>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)
        layoutManager = GridLayoutManager(this, 2)
        recyclerView.layoutManager = layoutManager

        viewModel = ViewModelProviders.of(this).get(ImageViewModel::class.java)
        imageList = viewModel.images
        adapter = ImageAdapter()
        recyclerView.adapter = adapter
        viewModel.images.observe(this, Observer<List<ImageEntity>> { imageEntities ->
            adapter.setImageList(imageEntities)
            adapter.notifyDataSetChanged()
        })
        viewModel.networkError.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show();
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        val searchItem: MenuItem = menu?.findItem(R.id.action_search)!!
        searchView = searchItem.getActionView() as SearchView
        searchView.setOnCloseListener(object : SearchView.OnCloseListener {
            override fun onClose(): Boolean {
                return true
            }
        })

        val searchPlate = searchView.findViewById(androidx.appcompat.R.id.search_src_text) as EditText
        searchPlate.hint = "Search"
        val searchPlateView: View =
            searchView.findViewById(androidx.appcompat.R.id.search_plate)
        searchPlateView.setBackgroundColor(
            ContextCompat.getColor(
                this,
                android.R.color.transparent
            )
        )

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.searchImages(query);
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        val searchManager =
            getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))

        return super.onCreateOptionsMenu(menu)

    }
}
