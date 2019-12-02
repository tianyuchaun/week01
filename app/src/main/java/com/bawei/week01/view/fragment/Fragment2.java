package com.bawei.week01.view.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.bawei.week01.R;
import com.bawei.week01.base.BaseFragment;
import com.bawei.week01.view.activity.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment2 extends BaseFragment {

    private Button bt;

    @Override
    protected void getView(View inflate) {
        bt = inflate.findViewById(R.id.bt);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity activity = (MainActivity)getActivity();
                activity.tiao();
            }
        });
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_fragment2;
    }

    @Override
    protected void getData() {

    }

}
