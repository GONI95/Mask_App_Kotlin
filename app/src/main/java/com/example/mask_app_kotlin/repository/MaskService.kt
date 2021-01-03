package com.example.mask_app_kotlin.repository

import com.example.mask_app_kotlin.model.StoreInfo
import retrofit2.Call
import retrofit2.http.GET


interface MaskService {

    // 약국 위치정보 요청
    @GET("sample.json")
    suspend fun  // www -> sample.json 파일
    // suspend : 비동기로 사용되는 코드라는 것을 표현
    // suspend 메서드는 suspend 메서드 안에서만 실행이 가능하지만 자바의 Thread를 대체하는 코루틴 안에서도 사용가능
            fetchStoreInfo(): StoreInfo
    // retrofit에서 코틀린을 지원하기 때문에 StoreInfo를 반환 받을 수 있음(자바에선 Call 사용해야함)
    
    companion object {  // 상수, 싱글턴에 사용
        //String BASE_URL = "https://gist.githubusercontent.com/junsuk5/bb7485d5f70974deee920b8f0cd1e2f0/raw/063f64d9b343120c2cb01a6555cf9b38761b1d94/";
        const val BASE_URL = "https://raw.githubusercontent.com/GONI95/sample/main/https%3A/sjs4209.cafe24.com/"
        // cafe24 서버에 있는 json 파일을 Github에 연결되어 있는 파일로 가져오도록 했음
    }
}

/*
     @GET("sample.json/?m=5000")
    // www -> sample.json 파일
    Call<StoreInfor> fetchStoreInfo(@Query("lat") double lat,
                                    @Query("lng") double lng);  // 약국 위치정보 요청
     */