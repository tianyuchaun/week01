package com.bawei.week01.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class NetUtil {
    private static NetUtil netUtil = new NetUtil();

    public NetUtil() {
    }

    public static NetUtil getInstance() {
        return netUtil;
    }
    //是否有网
    public boolean hasNet(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo akf = connectivityManager.getActiveNetworkInfo();
        if (akf != null&&akf.isAvailable()) {
            return true;
        }else {
            return false;
        }
    }
    //是否是wifi
    public boolean isWifi(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo akf = connectivityManager.getActiveNetworkInfo();
        if (akf != null&&akf.isAvailable()&& akf.getType() == ConnectivityManager.TYPE_WIFI) {
            return true;
        }else {
            return false;
        }
    }
    //是否是移动网络
    public boolean isMobile(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo akf = connectivityManager.getActiveNetworkInfo();
        if (akf != null&&akf.isAvailable()&& akf.getType() == ConnectivityManager.TYPE_MOBILE) {
            return true;
        }else {
            return false;
        }
    }
    // getString
    @SuppressLint("StaticFieldLeak")
    public void getString(final String HttpUrla, final MyCallBack myCallBack){
        new AsyncTask<Void, Void, String>() {
            @Override
            protected void onPostExecute(String s) {
                if (s == null) {
                    myCallBack.onFailure(new Throwable("请求失败"));
                }else {
                    myCallBack.OnGetJson(s);
                }
            }

            @Override
            protected String doInBackground(Void... voids) {
                InputStream inputStream = null;
                String json = "";
                try {
                    URL url = new URL(HttpUrla);
                    HttpURLConnection httpcon = (HttpURLConnection) url.openConnection();
                    httpcon.setRequestMethod("GET");
                    httpcon.setConnectTimeout(5000);
                    httpcon.setReadTimeout(5000);
                    httpcon.connect();
                    if (httpcon.getResponseCode()==200){
                         inputStream = httpcon.getInputStream();
                         json = io2String(inputStream);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                return json;
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }
    // getPhtot
    @SuppressLint("StaticFieldLeak")
    public void getPhoto(final String PhotoUrla, final ImageView imageView){
        new AsyncTask<Void, Void, Bitmap>() {
            @Override
            protected void onPostExecute(Bitmap bitmap) {
                imageView.setImageBitmap(bitmap);
            }

            @Override
            protected Bitmap doInBackground(Void... voids) {
                InputStream inputStream = null;
                Bitmap bitmap = null;
                try {
                    URL url = new URL(PhotoUrla);
                    HttpURLConnection httpcon = (HttpURLConnection) url.openConnection();
                    httpcon.setRequestMethod("GET");
                    httpcon.setConnectTimeout(5000);
                    httpcon.setReadTimeout(5000);
                    httpcon.connect();
                    if (httpcon.getResponseCode()==200){
                        inputStream = httpcon.getInputStream();
                        bitmap = io2Photo(inputStream);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                return bitmap;
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }
    //io2String
    public String io2String(InputStream inputStream) throws IOException {
        byte[] bytes = new byte[1024];
        int len = -1;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        while ((len = inputStream.read(bytes))!=-1){
            outputStream.write(bytes,0,len);
        }
        String json = new String(outputStream.toByteArray());
        return json;
    }
    //io2Photo
    public Bitmap io2Photo(InputStream inputStream){
        return BitmapFactory.decodeStream(inputStream);
    }
    //接口
    public interface MyCallBack{
        void OnGetJson(String json);
        void onFailure(Throwable e);
    }
}
