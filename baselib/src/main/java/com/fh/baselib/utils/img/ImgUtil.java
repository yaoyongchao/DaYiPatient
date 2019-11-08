package com.fh.baselib.utils.img;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.fh.baselib.R;

import io.reactivex.annotations.Nullable;

/**
 * Author: YongChao
 * Date: 19-8-21 下午5:49
 * Description:
 */
public class ImgUtil {
    public static void loadImgFace(Context context, String face_url, final ImageView img) {
//        String imgUrl = WcConfig.INSTANCE.getFileUrl()+face_url;
        RequestOptions options = new RequestOptions()
//                .placeholder(dPlaceHolder)
//                .error(dError)
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.NONE);
        Glide.with(context)
                .load(face_url)
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        img.setImageDrawable(resource);
                    }
                });
    }

    public static void loadImgCircle(Context context, String imgUrl, ImageView img, int dPlaceHolder, int dError) {
        RequestOptions options = new RequestOptions()
                .placeholder(dPlaceHolder)
                .error(dError)
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .diskCacheStrategy(DiskCacheStrategy.ALL);

        Glide.with(context).load(imgUrl).apply(options).into(img);
    }

    /**
     * @param: [context, imgUrl, img]
     * @return: void
     * @description: 设置加载占位符及加载失败
     */
    public static void loadImgPlaceHolderError(Context context, String imgUrl, ImageView img, int dPlaceHolder, int dError) {
        RequestOptions options = new RequestOptions()
                .placeholder(dPlaceHolder)
                .error(dError)
                .diskCacheStrategy(DiskCacheStrategy.ALL);

        Glide.with(context).load(imgUrl).apply(options).into(img);
    }
    public static void loadImg(Context context, String imgUrl, ImageView img) {
        loadImgPlaceHolderError(context,imgUrl,img, R.drawable.ic_avatar, R.drawable.ic_avatar);
    }

    public static void loadImgNoPlaceHolder(Context context, String imgUrl, ImageView img) {
        RequestOptions options = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(context).load(imgUrl).apply(options).into(img);
    }

    public static void loadGifImg (Context context, int imgUrl, ImageView img)  {
        Glide.with(context).asGif().load(imgUrl).into(img);
    }

    public static void zoomImg(Context context, String imgUrl, ImageView img) {


        Glide.with(context)
                .asBitmap()
                .load(imgUrl)
                .into(new TransformationUtils(img));
    }

    /**
     * 圆角图片
     * @param context
     * @param imgUrl
     * @param img
     * @param dPlaceHolder
     * @param dError
     */
    public static void loadRoundImg(Context context, String imgUrl, ImageView img,int dPlaceHolder, int dError) {
        RequestOptions options = new RequestOptions()
                .placeholder(dPlaceHolder)
                .error(dError)
                .diskCacheStrategy(DiskCacheStrategy.ALL);

        Glide.with(context).load(imgUrl).transform(new GlideRoundTransform(context,5)).apply(options).into(img);
    }

}
