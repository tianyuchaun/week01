package com.bawei.week01.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import com.bawei.week01.R;
import com.bawei.week01.model.bean.GsonBean;
import com.bawei.week01.util.NetUtil;

import java.util.List;

public class MyAdapter extends BaseAdapter {
    Context context;
    List<GsonBean.DataBean> list;
    public MyAdapter(Context context, List<GsonBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Aaa aaa;
        if (view==null){
            view = View.inflate(context,R.layout.itme,null);
            aaa = new Aaa();
            aaa.imageView = view.findViewById(R.id.iv);
            aaa.textView = view.findViewById(R.id.tv);
            view.setTag(aaa);
        }else {
            aaa = (Aaa) view.getTag();
        }
        NetUtil.getInstance().getPhoto(list.get(i).getGoods_thumb(),aaa.imageView);
        aaa.textView.setText(list.get(i).getGoods_name());
        return view;
    }
    public class Aaa{
        ImageView imageView;
        TextView textView;
    }
}
