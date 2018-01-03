package dextra.android.appbase.features.home.model

import android.util.Log
import dextra.android.appbase.service.InstagramService
import dextra.android.appbase.service.response.HashTagPostsResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomeModel {
    fun fetchPostsByHashTag(hashTag: String, callback:(error: Any?, result: HashTagPostsResponse) -> Unit) {
        val instagramService = InstagramService.Factory.create()

        instagramService.listPostsFromHashTag(hashTag)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe ({
                    result ->
                    callback(null, result)
                }, { error ->
                    error.printStackTrace()
                })

    }
}