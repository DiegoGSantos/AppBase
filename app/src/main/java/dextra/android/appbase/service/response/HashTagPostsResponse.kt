package dextra.android.appbase.service.response

import dextra.android.appbase.features.home.model.Graphql
import dextra.android.appbase.features.home.model.Post

/**
 * Created by diegosantos on 1/2/18.
 */
class HashTagPostsResponse (val graphql: Graphql) {
    fun getPosts() : List<Post> {
        return graphql.hashTag.media.posts
    }
}