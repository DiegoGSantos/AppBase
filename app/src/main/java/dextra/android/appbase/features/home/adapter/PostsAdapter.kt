package dextra.android.appbase.features.home.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import dextra.android.appbase.R
import dextra.android.appbase.features.home.model.Post
import kotlinx.android.synthetic.main.item_post.view.*

/**
 * Created by diegosantos on 1/2/18.
 */
class PostsAdapter(val context: Context, val posts: List<Post>) : RecyclerView.Adapter<PostsAdapter.PostViewHolder>(){

    override fun getItemCount(): Int = posts.size ?: 0

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = posts.get(position)
        Glide.with(context)
                .load(post.getImage())
                .into(holder.postCover)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsAdapter.PostViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        return PostViewHolder(layoutInflater.inflate(R.layout.item_post, parent, false))
    }

    class PostViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val postCover = v.mPostImage
    }
}