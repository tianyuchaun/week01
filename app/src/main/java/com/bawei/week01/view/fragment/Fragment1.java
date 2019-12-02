package com.bawei.week01.view.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.bawei.week01.R;
import com.bawei.week01.base.BaseFragment;
import com.bawei.week01.contract.IMyContract;
import com.bawei.week01.model.bean.GsonBean;
import com.bawei.week01.presenter.MyPresenter;
import com.bawei.week01.view.adapter.MyAdapter;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment1 extends BaseFragment {

    private GridView lv;

    @Override
    protected void getView(View inflate) {
        lv = inflate.findViewById(R.id.gv);
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_fragment1;
    }

    @Override
    protected void getData() {
        MyPresenter myPresenter = new MyPresenter();
        myPresenter.getMyData(new IMyContract.IViewMyCallBack() {
            @Override
            public void onSuccess(GsonBean gsonBean) {
                List<GsonBean.DataBean> data = gsonBean.getData();

                MyAdapter myAdapter = new MyAdapter(getActivity(),data);

                lv.setAdapter(myAdapter);
            }

            @Override
            public void onFailure(Throwable throwable) {
                Log.d("xx",throwable.toString());
            }
        });
    }

}
