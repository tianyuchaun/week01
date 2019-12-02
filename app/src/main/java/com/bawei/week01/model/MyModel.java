package com.bawei.week01.model;

import com.bawei.week01.contract.IMyContract;
import com.bawei.week01.model.bean.GsonBean;
import com.bawei.week01.util.NetUtil;
import com.google.gson.Gson;

public class MyModel  {
    String HttpUrla = "http://blog.zhaoliang5156.cn/api/shop/shop1.json";
    public void getMyData(final IMyContract.IPresentarMyCallBack iPresentarMyCallBack){
        NetUtil.getInstance().getString(HttpUrla, new NetUtil.MyCallBack() {
            @Override
            public void OnGetJson(String json) {
                GsonBean gsonBean = new Gson().fromJson(json, GsonBean.class);
                iPresentarMyCallBack.onSuccess(gsonBean);
            }

            @Override
            public void onFailure(Throwable e) {
                iPresentarMyCallBack.onFailure(e);
            }
        });
    }
}
