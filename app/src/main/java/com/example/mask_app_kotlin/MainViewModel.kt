package com.example.mask_app_kotlin

import android.inputmethodservice.InputMethodService
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mask_app_kotlin.model.Store
import com.example.mask_app_kotlin.model.StoreInfo
import com.example.mask_app_kotlin.repository.MaskService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

//https://iosroid.tistory.com/88
//https://doitddo.tistory.com/90
class MainViewModel : ViewModel() {
    var itemLiveData = MutableLiveData<List<Store>>()
    var loadingLiveData = MutableLiveData<Boolean>()

    private val service: MaskService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(MaskService.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        service = retrofit.create(MaskService::class.java)

        fetchStoreInfo()    // MainViewModel이 생성되면서 요청을 통해 정보를 반환받는 메서드를 실행
    }

    fun fetchStoreInfo() {
        loadingLiveData.value = true

        viewModelScope.launch(Dispatchers.Main) {
            val storeInfo = service.fetchStoreInfo()    // 결과를 storeInfo에 반환
            itemLiveData.value = storeInfo.stores   // 반환된 stores를 LiveData에 저장
                .filter { item ->
                    item.remain_stat != null
                }
                // null 값을 제외한 List로 필터링
                .sortedBy { item ->
                    item.name
                }
                // 이름 순으로 정렬
            loadingLiveData.value = false   //  Progress Bar 값 false

        }
    }


}