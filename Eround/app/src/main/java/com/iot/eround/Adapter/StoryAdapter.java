package com.iot.eround.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.iot.eround.R;
import com.iot.eround.VO.Board;

import java.util.ArrayList;

public class StoryAdapter extends BaseAdapter {

    private ArrayList<Board> boardList = new ArrayList<>();
    private ArrayList<Bitmap> bitmapList = new ArrayList<>();

    private Context context;

    public ArrayList<Board> getBoardList() {
        return boardList;
    }

    public StoryAdapter() {

    }

    @Override
    public int getCount() {
        return boardList.size();
    }

    @Override
    public Object getItem(int position) {
        return boardList.get(position);
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
            view = inflater.inflate(R.layout.list_story_group, parent, false);
        }

        TextView tagList_Content1 = view.findViewById(R.id.list_story_group_id_content1);
        TextView tagList_Content2 = view.findViewById(R.id.list_story_group_id_content2);
        TextView tagList_Content3 = view.findViewById(R.id.list_story_group_id_content3);
        ImageView tagList_BackGround = view.findViewById(R.id.list_story_group_id_tag_background);

        Board board = boardList.get(position);
        Bitmap bitmap = bitmapList.get(position);

        tagList_Content1.setText(board.getBoardContent());
        tagList_Content2.setText(board.getBoardCreateDate());
        tagList_Content3.setText("0");
        tagList_BackGround.setImageBitmap(bitmap);

        return view;

    }

    public void addItem(ArrayList<Board> boardList, ArrayList<Bitmap> bitmapList) {
        this.boardList = boardList;
        this.bitmapList = bitmapList;
    }

}
