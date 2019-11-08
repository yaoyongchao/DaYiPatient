package com.fh.baselib.net.file;


import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Author: YongChao
 * Date: 19-8-20 下午4:46
 * Description: 文件下载器
 */

public class FileDownloader {

    /**
     * 下载文件法2(使用RXJava更新UI)
     *
     * @param observable
     * @param destDir
     * @param fileName
     * @param progressHandler
     */
    public static void downloadFile2(Observable<ResponseBody> observable, final String destDir, final String fileName, final DownloadProgressHandler progressHandler) {
        final DownloadInfo downloadInfo = new DownloadInfo();
        observable
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .flatMap(new Function<ResponseBody, ObservableSource<DownloadInfo>>() {

                    @Override
                    public ObservableSource<DownloadInfo> apply(final ResponseBody responseBody) throws Exception {

                        return Observable.create(new ObservableOnSubscribe<DownloadInfo>() {
                            @Override
                            public void subscribe(ObservableEmitter<DownloadInfo> emitter) throws Exception {
                                Log.e("aa","请求成功");
                                InputStream inputStream = null;
                                long total = 0;
                                long responseLength = 0;
                                FileOutputStream fos = null;
                                try {
                                    byte[] buf = new byte[2048];
                                    int len = 0;
                                    responseLength = responseBody.contentLength();
                                    inputStream = responseBody.byteStream();
                                    final File file = new File(destDir, fileName);
                                    downloadInfo.setFile(file);
                                    downloadInfo.setFileSize(responseLength);
                                    File dir = new File(destDir);
                                    if (!dir.exists()) {
                                        dir.mkdirs();
                                    }
                                    fos = new FileOutputStream(file);
                                    int progress = 0;
                                    int lastProgress = 0;
                                    long startTime = System.currentTimeMillis(); // 开始下载时获取开始时间
                                    while ((len = inputStream.read(buf)) != -1) {
                                        fos.write(buf, 0, len);
                                        total += len;
                                        lastProgress = progress;
                                        progress = (int) (total * 100 / responseLength);
                                        long curTime = System.currentTimeMillis();
                                        long usedTime = (curTime - startTime) / 1000;
                                        if (usedTime == 0) {
                                            usedTime = 1;
                                        }
                                        long speed = (total / usedTime); // 平均每秒下载速度
                                        // 如果进度与之前进度相等，则不更新，如果更新太频繁，则会造成界面卡顿
                                        if (progress > 0 && progress != lastProgress) {
                                            downloadInfo.setSpeed(speed);
                                            downloadInfo.setProgress(progress);
                                            downloadInfo.setCurrentSize(total);
                                            emitter.onNext(downloadInfo);
                                        }
                                    }
                                    fos.flush();
                                    downloadInfo.setFile(file);
                                    emitter.onComplete();
                                } catch (Exception e) {
                                    downloadInfo.setErrorMsg(e);
                                    emitter.onError(e);
                                } finally {
                                    try {
                                        if (fos != null) {
                                            fos.close();
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    try {
                                        if (inputStream != null) {
                                            inputStream.close();
                                        }
                                    } catch (Exception e) {
                                        Log.e("aa","请求失败" + e.toString());
                                        e.printStackTrace();
                                    }

                                }
                            }
                        });
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DownloadInfo>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(DownloadInfo downloadInfo) {
                        progressHandler.onProgress(downloadInfo.getProgress(), downloadInfo.getFileSize(), downloadInfo.getSpeed());
                    }

                    @Override
                    public void onError(Throwable e) {
                        progressHandler.onError(e);
                    }

                    @Override
                    public void onComplete() {
                        progressHandler.onCompleted(downloadInfo.getFile());
                    }
                });
    }
}
