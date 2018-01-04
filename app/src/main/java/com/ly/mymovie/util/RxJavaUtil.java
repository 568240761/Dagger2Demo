package com.ly.mymovie.util;

import io.reactivex.disposables.Disposable;

public class RxJavaUtil {

    /**
     * 取消订阅；比如退出UI界面，但请求还没结束
     * @param disposable
     */
    public static void dispose(Disposable disposable) {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
