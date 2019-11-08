package com.fh.baselib.utils.rx;


import android.content.Context;

import com.fh.baselib.BaseApplication;
import com.fh.baselib.utils.LogUtil;
import com.fh.baselib.utils.NetWorkUtils;
import com.fh.baselib.utils.ToastUtil;
import com.fh.baselib.widget.LoadingDialog;

import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

;

/**
 * Author: Austin
 * Date: 2018/10/9
 * Description:
 */
public class MyRxScheduler {
    public static <T> ObservableTransformer<T,T> applySchedulers() {
        return observable -> observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /*public static <T> ObservableTransformer<T,T> ioMain() {
        return observable -> observable.subscribeOn(Schedulers.io())
                .doOnSubscribe(disposable -> {
                    if (!NetWorkUtils.Companion.isConnectedByState(BaseApplication.appContext)) {
                        disposable.dispose();
                        L.Companion.d("doOnSubscribe -- There's no network link.");
                        ToastUtil.Companion.show("网络异常请检查网络");
                    } else {
//                        ToastUtil.Companion.show("网络连接中");
                        L.Companion.d("doOnSubscribe -- There's a network link.");

                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                })
                .observeOn(AndroidSchedulers.mainThread());
    }*/
    public static <T> ObservableTransformer<T,T> ioMain() {
        return observable -> observable.subscribeOn(Schedulers.io())
//                .doOnSubscribe(disposable -> {
//                    if (!NetWorkUtils.Companion.isConnectedByState(BaseApplication.appContext)) {
//                        disposable.dispose();
//                        L.Companion.d("doOnSubscribe -- There's no network link.");
//                        ToastUtil.Companion.show("网络异常请检查网络");
//                    } else {
////                        ToastUtil.Companion.show("网络连接中");
//                        L.Companion.d("doOnSubscribe -- There's a network link.");
//                    }
//                })
//                .doFinally(new Action() {
//                    @Override
//                    public void run() throws Exception {
//
//                    }
//                })
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static <T> ObservableTransformer<T,T> ioMain(Context context,Boolean isShowDialog) {

        final LoadingDialog loadingDialog = new LoadingDialog(context);
        return observable -> observable.subscribeOn(Schedulers.io())
                .doOnSubscribe(disposable -> {
                    if (!NetWorkUtils.Companion.isConnectedByState(BaseApplication.appContext)) {
                        disposable.dispose();
                        LogUtil.Companion.d("doOnSubscribe -- There's no network link.");
                        ToastUtil.Companion.show("网络异常请检查网络");
                    } else {
//                        LogUtil.Companion.d("doOnSubscribe -- There's a network link.");
                        if (!loadingDialog.isShowing() && isShowDialog)
                            loadingDialog.show();
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        if (loadingDialog != null && loadingDialog.isShowing())
                            loadingDialog.dismiss();

                    }
                })
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static <T> ObservableTransformer<T,T> ioMain(Context context) {
        return ioMain(context,true);
        /*final LoadingDialog loadingDialog = new LoadingDialog(context);
        return observable -> observable.subscribeOn(Schedulers.io())
                .doOnSubscribe(disposable -> {
                    if (!NetWorkUtils.Companion.isConnectedByState(BaseApplication.appContext)) {
                        disposable.dispose();
                        LogUtil.Companion.d("doOnSubscribe -- There's no network link.");
                        ToastUtil.Companion.show("网络异常请检查网络");
                    } else {
                        LogUtil.Companion.d("doOnSubscribe -- There's a network link.");
                        if (!loadingDialog.isShowing())
                            loadingDialog.show();
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        if (loadingDialog != null && loadingDialog.isShowing())
                            loadingDialog.dismiss();

                    }
                })
                .observeOn(AndroidSchedulers.mainThread());*/
    }



}
