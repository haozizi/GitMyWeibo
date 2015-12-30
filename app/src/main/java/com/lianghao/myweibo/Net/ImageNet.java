package com.lianghao.myweibo.Net;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import com.lianghao.myweibo.R;
import com.marshalchen.common.commonUtils.urlUtils.UniversalImageLoader;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

/**
 * Created by Administrator on 2015/12/23.
 */
public class ImageNet {

    public static void loadImage(String url,int width,int height, final ImageView imageView){

        //AbsListView.LayoutParams params = new
        ImageSize imageSize = new ImageSize(width,height);
        ImageLoader.getInstance().loadImage(url, imageSize,
                UniversalImageLoader.getDefaultImageOptions(), new ImageLoadingListener() {
                    @Override
                    public void onLoadingStarted(String s, View view) {

                    }

                    @Override
                    public void onLoadingFailed(String s, View view, FailReason failReason) {

                    }

                    @Override
                    public void onLoadingComplete(String s, View view, Bitmap bitmap) {
                        imageView.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onLoadingCancelled(String s, View view) {

                    }
                });
    }

    public static void loadImage(String url,final ImageView imageView){

        ImageLoader.getInstance().loadImage(url, UniversalImageLoader.getDefaultImageOptions(),
                new ImageLoadingListener() {
                    @Override
                    public void onLoadingStarted(String s, View view) {
                        imageView.setImageResource(R.mipmap.small_image_default);
                    }

                    @Override
                    public void onLoadingFailed(String s, View view, FailReason failReason) {

                    }

                    @Override
                    public void onLoadingComplete(String s, View view, Bitmap bitmap) {
                        imageView.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onLoadingCancelled(String s, View view) {

                    }
                });
    }

    public static void displayImage(String url, final ImageView imageView){
        ImageLoader.getInstance().displayImage(url, imageView,
                UniversalImageLoader.getDefaultImageOptions(), new ImageLoadingListener() {
                    @Override
                    public void onLoadingStarted(String s, View view) {
                        imageView.setImageResource(R.mipmap.small_image_default);
                    }

                    @Override
                    public void onLoadingFailed(String s, View view, FailReason failReason) {

                    }

                    @Override
                    public void onLoadingComplete(String s, View view, Bitmap bitmap) {
                        imageView.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onLoadingCancelled(String s, View view) {

                    }
                });
    }

    public static void setImage(String url,ImageView iv){
        ImageLoader.getInstance().displayImage(url, iv,
                UniversalImageLoader.getDefaultImageOptions());
    }

}
