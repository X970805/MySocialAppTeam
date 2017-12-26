package code.xp.mysocialappteam.present;

import org.greenrobot.eventbus.Subscribe;

import code.xp.mysocialappteam.control.MyControl;
import code.xp.mysocialappteam.control.RecommendControl;
import code.xp.mysocialappteam.model.ArticalModel;
import code.xp.mysocialappteam.model.MyModel;
import code.xp.mysocialappteam.view.bean.MyArticleBean;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by dell on 2017/12/18.
 */

public class ArticalPresent {
    private ArticalModel articalModel;
    private RecommendControl myControl;

    public ArticalPresent(RecommendControl c) {
        articalModel = new ArticalModel();
        this.myControl = c;
    }

    public void setArticalModel(String user, String flag, String fromUid, String page) {
        Observable<MyArticleBean> myArticleBeanObservable = articalModel.getMyArtical(user, flag, fromUid, page);
        if (myArticleBeanObservable == null) {
            return;
        } else {
            myArticleBeanObservable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<MyArticleBean>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(MyArticleBean articleBean) {
                            myControl.getMyArtical(articleBean);
                        }

                        @Override
                        public void onError(Throwable e) {
                            //    System.out.println("onError = "+ e );
                            myControl.getMyArtical(null);
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }
    }
}
