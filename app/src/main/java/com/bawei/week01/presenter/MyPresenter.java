package com.bawei.week01.presenter;

import com.bawei.week01.contract.IMyContract;
import com.bawei.week01.model.MyModel;
import com.bawei.week01.model.bean.GsonBean;

public class MyPresenter {
    public void getMyData(final IMyContract.IViewMyCallBack iViewMyCallBack){
        MyModel myModel = new MyModel();
        myModel.getMyData(new IMyContract.IPresentarMyCallBack() {
            @Override
            public void onSuccess(GsonBean gsonBean) {
                iViewMyCallBack.onSuccess(gsonBean);
            }

            @Override
            public void onFailure(Throwable throwable) {
                iViewMyCallBack.onFailure(throwable);
            }
        });
    }
}
