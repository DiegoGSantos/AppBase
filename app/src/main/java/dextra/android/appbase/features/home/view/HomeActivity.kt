package dextra.android.appbase.features.home.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.SearchView
import android.widget.ArrayAdapter
import android.widget.Toast
import dextra.android.appbase.R
import dextra.android.appbase.base.BaseActivity
import dextra.android.appbase.features.home.adapter.PostsAdapter
import dextra.android.appbase.features.home.model.Post
import dextra.android.appbase.features.home.viewModel.HomeViewModel
import kotlinx.android.synthetic.main.activity_main.*

class HomeActivity : BaseActivity<HomeViewModel>() {

    override fun loadUI() {
        searchViewSetup()
    }

    override fun loadCallbacks() {
        viewModel()?.uiMessageCallback = { message ->
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }

        viewModel()?.handleErrorCallback = { error ->
            if (error is String) {
                Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun loadObservers() {
        viewModel()?.postsLiveData?.observe(this, Observer<List<Post>> { posts ->
            posts?.let {
                updateListOfPosts(posts)
            }
        })
    }

    override fun viewModel(): HomeViewModel? {
        return ViewModelProviders.of(this).get(HomeViewModel::class.java)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    private fun searchViewSetup() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override
            fun onQueryTextSubmit(query: String): Boolean {
                viewModel()?.searchPostsByHashTag(searchView.query.toString())
                return false
            }

            override
            fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
    }

    // TODO: Create a RecyclerView Adapter
    private fun updateListOfPosts(posts: List<Post>) {
        hashTagsView.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(this.context, 3)
            adapter = PostsAdapter(this.context, posts)
        }
    }
}
