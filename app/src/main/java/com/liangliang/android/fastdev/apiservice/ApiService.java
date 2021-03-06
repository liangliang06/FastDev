package com.liangliang.android.fastdev.apiservice;

import com.liangliang.android.fastdev.bean.SpotBean;
import com.liangliang.android.fastdev.bean.base.ResponseBean;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * 接口
 */
public interface ApiService {
    /**
     * get方式获取景点数据
     */
    @GET("spot")
    Observable<ResponseBean<SpotBean>> getSpot(@Query("id") String id, @Query("output") String output);

    /**
     * post方式获取景点数据
     */
    @FormUrlEncoded
    @POST("spot")
    Observable<ResponseBean<SpotBean>> postSpot(@Field("id") String id, @Field("output") String output);

    /**
     * 自定义方式获取景点数据
     */
    @POST("spot")
    Observable<ResponseBean<List<SpotBean>>> querySpot(@Body RequestBody requestBody);

    /**
     * post方式获取景点数据
     */
    @FormUrlEncoded
    @POST("spot")
    Flowable<ResponseBean<SpotBean>> postSpotFlowable(@Field("id") String id, @Field("output") String output);
}
