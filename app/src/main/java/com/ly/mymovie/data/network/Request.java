package com.ly.mymovie.data.network;

import com.ly.mymovie.data.model.FilmmakerMessage;
import com.ly.mymovie.data.model.Message;
import com.ly.mymovie.data.model.MovieMessage;
import com.ly.mymovie.data.model.NorthAmericaMessage;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by LanYang on 2017/12/12.
 * Email:568240761@qq.com
 * 网络请求的接口
 */

public interface Request {

    /***
     * 请求当前城市的正在热映的电影
     * @param city  城市
     * @return
     */
    @GET("v2/movie/in_theaters")
    Observable<Message> getHitMessage(@Query("city") String city);

    /**
     * 请求即将上映的电影信息
     * @return
     */
    @GET("v2/movie/coming_soon")
    Observable<Message> getComingMessage();

    /**
     * 请求豆瓣Top250的电影信息
     * @return
     */
    @GET("v2/movie/top250")
    Observable<Message> getTop250Message(@Query("start") int start,@Query("count") int count);

    /**
     * 请求北美票房榜的电影信息
     * @return
     */
    @GET("v2/movie/us_box")
    Observable<NorthAmericaMessage> getNorthAmericaMessage();

    /**
     * 请求电影的相关信息
     * @param id
     * @return
     */
    @GET("v2/movie/subject/{id}")
    Observable<MovieMessage> getMovieMessage(@Path("id")String id);

    /**
     * 请求电影人的相关信息
     * @param id
     * @return
     */
    @GET("v2/movie/celebrity/{id}")
    Observable<FilmmakerMessage> getFilmmakerMessage(@Path("id")String id);

    /**
     * 请求某个类型的相关信息
     * @return
     */
    @GET("v2/movie/search")
    Observable<Message> getSortMessage(@Query("tag") String tag,@Query("start") int count);

    /**
     * 搜索关键词的相关信息
     * @param tag
     * @param count
     * @return
     */
    @GET("v2/movie/search")
    Observable<Message> getSearchMessage(@Query("q") String tag,@Query("start") int count);
}
