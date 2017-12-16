package code.xp.mysocialappteam.model.utils;



import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by 徐宏福 on 2017/12/15.
 */

public class RxUtils {
//    public static <T> Observable.Transformer<T, T> defaultSchedulers() {
//        return new Observable.Transformer<T, T>() {
//            @Override
//            public Observable<T> call(Observable<T> tObservable) {
//                return tObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
//            }
//        };
//    }
//
//    /**
//     * 所有操作都在io线程上
//     */
//    public static <T> Observable.Transformer ioSchedulers() {
//        return new Observable.Transformer<T, T>() {
//            @Override
//            public Observable<T> call(Observable<T> tObservable) {
//                return tObservable.subscribeOn(Schedulers.io()).observeOn(Schedulers.io());
//            }
//        };
//    }
//
//    /**
//     * 统一处理请求，并且将结果提取出来
//     */
//    public static <T> Observable.Transformer<BaseResult<T>, T> handleResult() {
//        return new Observable.Transformer<BaseResult<T>, T>() {
//            @Override
//            public Observable<T> call(Observable<BaseResult<T>> baseResultObservable) {
//                return baseResultObservable.flatMap(new Func1<BaseResult<T>, Observable<T>>() {
//                    @Override
//                    public Observable<T> call(BaseResult<T> tBaseResult) {
//                        if (tBaseResult.code == 1 || tBaseResult.code == 11 || tBaseResult.code == 12 || tBaseResult.code == 13) {
//                            return Observable.just(tBaseResult.data);
//                        } else if (tBaseResult.code == 0) {
//                            RxBus.getDefault().post(new NotFoundEvent());
//                            return null;
//                        } else
//                            return Observable.error(new ApiException(tBaseResult.code));
//                    }
//                });
//            }
//        };
//    }
}
