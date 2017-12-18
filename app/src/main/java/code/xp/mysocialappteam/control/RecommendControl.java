package code.xp.mysocialappteam.control;

import code.xp.mysocialappteam.view.bean.HotRecommendBean;
import code.xp.mysocialappteam.view.bean.MyArticleBean;

/**
 * Created by dell on 2017/12/18.
 */

public interface RecommendControl {

    void getMyArtical(MyArticleBean articleBean);

    void  getHotRecommend(HotRecommendBean hotRecommendBean);
}
