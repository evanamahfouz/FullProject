package com.example.fullproject.data.repos

import android.util.Log
import com.example.fullproject.data.db.DataBase
import com.example.fullproject.data.db.VolumeInfoEntity
import com.example.fullproject.data.model.BookObject
import com.example.fullproject.data.model.VolumeInfo
import com.example.fullproject.data.network.PostClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception


@Suppress("DEPRECATION")
class Repo {
    private val dB = DataBase.getInstance()

    fun getVolumeList(callback: DataCallback<List<VolumeInfo>>) {
        //val isConnected = true

        // val cm =
        //   App.application().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        //val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        //val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true
        // Log.v("InsideVoulmDataBase",isConnected.toString()
        //  )
        val Connect = true
        if (Connect) {
            PostClient.Instant.getCallBookObject()?.enqueue(object : Callback<BookObject> {
                override fun onResponse(call: Call<BookObject>, response: Response<BookObject>) {

                    val newData: List<VolumeInfo> = changeToVolum(response)!!
                    Log.v("helloNoResp", "Yes Internet Connection")
                    insertData(newData)

                    callback.onSuccess(newData)

                }

                override fun onFailure(call: Call<BookObject>, t: Throwable) {
                    Log.v("helloNoResp", "No Internet Connection")
                    callback.onError(t)
                }
            })
        } else {
            try {
                val items = dB.volumeInfoDOA().getAll()
                Log.v("InsideVoulmDataBase", items.map {
                    it.mapToVolumInfo()
                }.size.toString())
                callback.onSuccess(items.map {
                    it.mapToVolumInfo()
                })
            } catch (ex: Exception) {
                callback.onError(ex)
            }
        }
    }

    fun insertData(response: List<VolumeInfo>) {
        dB.volumeInfoDOA().insertAll(response.map {
            it.mapToVolumInfoEntity()
        })

    }

    private fun changeToVolum(response: Response<BookObject>): List<VolumeInfo>? {
        return response.body()?.items?.mapNotNull {
            it.volumeInfo
        }
    }

    fun showData(): List<VolumeInfoEntity> {
        return dB.volumeInfoDOA().getAll()
    }
}
