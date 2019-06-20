package com.iot.eround.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.iot.eround.R;
import com.iot.eround.VO.Tags;

import java.util.ArrayList;
import java.util.Random;

public class HashAdapter extends BaseAdapter {

    Random random = new Random();

    private ArrayList<Tags> tagList = new ArrayList<>();
    private ArrayList<Bitmap> bitmapList = new ArrayList<>();

    private Context context;

    public ArrayList<Tags> gettagList() {
        return tagList;
    }

    public HashAdapter() {

    }

    @Override
    public int getCount() {
        return tagList.size();
    }

    @Override
    public Object getItem(int position) {
        return tagList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View view, ViewGroup parent) {

        context = parent.getContext();

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_hash_group, parent, false);
        }

        TextView tagList_Content1 = view.findViewById(R.id.activity_main_hash_id_tag_content1);
        TextView tagList_Content2 = view.findViewById(R.id.activity_main_hash_id_tag_content2);
        TextView tagList_Content3 = view.findViewById(R.id.activity_main_hash_id_tag_content3);
        ImageView tagList_BackGround = view.findViewById(R.id.activity_main_hash_id_tag_background);

        Tags tags = tagList.get(position);
        Bitmap bitmap = bitmapList.get(position);

        tagList_Content1.setText("#" + tags.getTagName());
        tagList_Content2.setText("이야기 : " + String.valueOf(random.nextInt(1000)));
        tagList_Content3.setText("구독 : " + String.valueOf(random.nextInt(50)));
        tagList_BackGround.setImageBitmap(bitmap);

        return view;

    }

    public void addItem(ArrayList<Tags> tagList, ArrayList<Bitmap> bitmapList) {
        this.tagList = tagList;
        this.bitmapList = bitmapList;
    }

}
