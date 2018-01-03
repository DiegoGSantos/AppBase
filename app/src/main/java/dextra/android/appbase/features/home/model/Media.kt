package dextra.android.appbase.features.home.model

import com.google.gson.annotations.SerializedName

/**
 * Created by diegosantos on 1/2/18.
 */
class Media (@SerializedName("edges") val posts: List<Post>)