package com.infernal93.listwithsearchmvvm.remote

import androidx.lifecycle.MutableLiveData
import com.infernal93.listwithsearchmvvm.entity.Category
import com.infernal93.listwithsearchmvvm.views.interfaces.CategoryListener
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

/**
 * Created by Armen Mkhitaryan on 30.12.2019.
 */
class CategoryClient {

    companion object {
        const val KEY = "5de979d34658275ac9dc2375"
    }

    var mutableLiveData = MutableLiveData<ArrayList<Category>>()

    fun loadCategory(): MutableLiveData<ArrayList<Category>>{
        // x-apikey interceptor for restdb API
        fun createOkHttpClient(): OkHttpClient? {
            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(object : Interceptor {
                @Throws(IOException::class)
                override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
                    val original = chain.request()
                    val originalHttpUrl = original.url
                    val url = originalHttpUrl.newBuilder()
                        .addQueryParameter("apikey", KEY)
                        .build()
                    val requestBuilder = original.newBuilder()
                        .url(url)
                    val request = requestBuilder.build()
                    return chain.proceed(request)
                }
            })
            // logging interceptor
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            httpClient.addInterceptor(logging)
            return httpClient.build()
        }

        val retrofit = Retrofit.Builder()
            .baseUrl("https://testcategory-d6d7.restdb.io/rest/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(createOkHttpClient())
            .build()

        val api = retrofit.create(CategoryApi::class.java)
        api.fetchAllCategory().enqueue(object : Callback<List<Category>> {
            override fun onFailure(call: Call<List<Category>>, t: Throwable) {
            }

            override fun onResponse(call: Call<List<Category>>, response: Response<List<Category>>) {
                //Log.d(TAG, "onResponse: ${response.body()!![0].name}")
                mutableLiveData.postValue(response.body() as ArrayList<Category>?)
                 //presenter.setupCategoryList(categoryList = category as ArrayList<Category>)
            }
        })
        return mutableLiveData
    }
}