package com.iot.eround.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iot.eround.R;
import com.iot.eround.VO.Board;

import java.util.ArrayList;

public class ProfileContentAdapter extends RecyclerView.Adapter<ProfileContentAdapter.ViewHolder> {

    private ArrayList<Board> boardList = new ArrayList<>();
    private OnItemClickListener mListener;

    public ArrayList<Board> getBoardList() {
        return boardList;
    }

    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener ;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView text1, text2;

        public ViewHolder(@NonNull View itemView){

            super(itemView);

            text1 = itemView.findViewById(R.id.list_profile_content_group_id_text1);
            text2 = itemView.findViewById(R.id.list_profile_content_group_id_text2);

            itemView.setOnClickListener(v -> {

                int pos = getAdapterPosition();
                if (pos != RecyclerView.NO_POSITION){
                    if (mListener != null) {
                        mListener.onItemClick(v, pos) ;
                    }
                }

            });

        }

        public void setItem(Board board){

            text1.setText(board.getBoardContent());
            text2.setText(board.getBoardCreateDate());

        }

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView =  inflater.inflate(R.layout.list_profile_content_group, viewGroup, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        Board board = boardList.get(i);
        viewHolder.setItem(board);

    }

    @Override
    public int getItemCount() {
        return boardList.size();
    }

    public void setItem(Board board){
        this.boardList.add(board);

    }

    public void setItems(ArrayList<Board> boardList){
        this.boardList = boardList;

    }

}
