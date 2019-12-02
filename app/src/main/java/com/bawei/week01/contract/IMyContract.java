package com.bawei.week01.contract;

import com.bawei.week01.model.bean.GsonBean;

public interface IMyContract {
    interface IPresentarMyCallBack{
        void onSuccess(GsonBean gsonBean);
        void onFailure(Throwable throwable);
    }
    interface IViewMyCallBack{
        void onSuccess(GsonBean gsonBean);
        void onFailure(Throwable throwable);
    }
}
