package com.bishwajit.cinemalyticsbuzz;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by bishwajit on 12/17/2015.
 */
public class VolleySingleton {

    private static VolleySingleton sInstance = null;
    private RequestQueue mRequestQueue;
    private ImageLoader imgl;

    //constructor
    private VolleySingleton()
    {
        mRequestQueue = Volley.newRequestQueue(MyApplication.getContext());
        imgl = new ImageLoader(mRequestQueue, new ImageLoader.ImageCache() {
            private LruCache<String, Bitmap> cache = new LruCache((int)Runtime.getRuntime().maxMemory()/1024/8);
            @Override
            public Bitmap getBitmap(String url) {
                return cache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                cache.put(url,bitmap);
            }
        });
    }

    public static VolleySingleton getsInstance()
    {
        if(sInstance == null)
            sInstance = new VolleySingleton();
        return sInstance;

    }

    public RequestQueue getmRequestQueue()
    {
        return mRequestQueue;
    }

    public ImageLoader getImageLoader()
    {
        return imgl;
    }
}
