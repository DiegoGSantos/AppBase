package dextra.android.appbase.service

import dextra.android.appbase.service.response.HashTagPostsResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

/**
 * Created by diegosantos on 1/2/18.
 */
interface InstagramService {

    @retrofit2.http.GET("tags/{hashTag}/?__a=1")
    fun listPostsFromHashTag(@retrofit2.http.Path("hashTag")hashTag: String): io.reactivex.Observable<HashTagPostsResponse>

    companion object Factory {
        fun create(): InstagramService {

            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)

            val httpClient = OkHttpClient.Builder()

            httpClient.addInterceptor(logging)

            val retrofit = retrofit2.Retrofit.Builder()
                    .addCallAdapterFactory(retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory.create())
                    .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
                    .client(httpClient.build())
                    .baseUrl("https://www.instagram.com/explore/")
                    .build()

            return retrofit.create(InstagramService::class.java);
        }
    }
}