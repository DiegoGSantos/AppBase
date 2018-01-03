package dextra.android.appbase.features.home.model

import com.google.gson.annotations.SerializedName

/**
 * Created by diegosantos on 1/2/18.
 */
class Post (val node: Node) {
    fun getImage() : String {
        return node.thumbnailSrc
    }
}