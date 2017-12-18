package code.xp.mysocialappteam.control;

import code.xp.mysocialappteam.view.bean.HotRecommendBean;
import code.xp.mysocialappteam.view.bean.MyArticleBean;
import code.xp.mysocialappteam.view.bean.YKBean;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by 徐宏福 on 2017/12/15.
 */

public interface ApiInterface {
    @POST("api/surfers")
    Observable<YKBean> ykBean(@Query("deviceId") String deviceId);

    //获取推荐数据列表
    @GET("api/articles")
    Observable<MyArticleBean> getMyArticalBean(@Query("user") String user,@Query("flag") String flag,@Query("fromUid") String uuid,@Query("page") String page);

    //获取热门推荐
    @GET("api/topics")
    Observable<HotRecommendBean> getHotRecommendBean(@Query("user") String user,@Query("flag") String flag,@Query("fromUid") String uuid,@Query("page") String page,@Query("limit") String limit);


}
