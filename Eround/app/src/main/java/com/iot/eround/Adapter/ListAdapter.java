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

public class ListAdapter extends BaseAdapter {

    private ArrayList<Board> boardList = new ArrayList<>();
    private ArrayList<Bitmap> BitmapList = new ArrayList<>();

    private Context context;

    public ListAdapter() {

    }

    @Override
    public int getCount() {

        return boardList.size();

    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        context = parent.getContext();

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.fragment_story_list_group, parent, false);
        }

        TextView tagList_Content1 = view.findViewById(R.id.tagList_Content1);
        TextView tagList_Content2 = view.findViewById(R.id.tagList_Content2);
        TextView tagList_Content3 = view.findViewById(R.id.tagList_Content3);
        ImageView tagList_BackGround = view.findViewById(R.id.tagList_BackGround);

        Board board = boardList.get(position);
        Bitmap bitmap = BitmapList.get(position);

        tagList_Content1.setText(board.getBoardContent());
        tagList_Content2.setText(board.getBoardCreateDate());
        tagList_Content3.setText("0");
        tagList_BackGround.setImageBitmap(bitmap);

        return view;

    }

    @Override
    public long getItemId(int position) {
        return position ;
    }

    @Override
    public Object getItem(int position) {
        return boardList.get(position) ;
    }

    public void addItem(String BoardContent, String BoardCreateDate, Bitmap image) {

        Board board = new Board();

        try {

            board.setBoardContent(BoardContent);
            board.setBoardCreateDate(BoardCreateDate);

        } catch (Exception e) {

            Log.i("test5 Exception e", e.toString());

        }

        boardList.add(board);
        BitmapList.add(image);

    }

}
