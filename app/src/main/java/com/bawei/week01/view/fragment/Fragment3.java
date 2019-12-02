package com.bawei.week01.view.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.week01.R;
import com.bawei.week01.base.BaseFragment;
import com.bawei.week01.util.NetUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment3 extends BaseFragment {

    private ImageView iv;
    private TextView tv;

    @Override
    protected void getView(View inflate) {
        iv = inflate.findViewById(R.id.iv);
        tv = inflate.findViewById(R.id.tv);
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_fragment3;
    }

    @Override
    protected void getData() {
        boolean b = NetUtil.getInstance().hasNet(getActivity());
        if (b){
            iv.setVisibility(View.VISIBLE);
            tv.setVisibility(View.GONE);
    }else {
            tv .setVisibility(View.VISIBLE);
            iv.setVisibility(View.GONE);
        }
    }

}
