package com.iot.eround.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iot.eround.R;
import com.iot.eround.VO.Board;
import com.iot.eround.VO.Reply;

import java.util.ArrayList;

public class ProfileReplyAdapter extends RecyclerView.Adapter<ProfileReplyAdapter.ViewHolder> {

    private ArrayList<Reply> replyList = new ArrayList<>();
    private OnItemClickListener mListener;

    public ArrayList<Reply> getreplyList() {
        return replyList;
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

        public void setItem(Reply reply){

            text1.setText(reply.getReplyContent());
            text2.setText(reply.getReplyCreateDate());

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

        Reply reply = replyList.get(i);
        viewHolder.setItem(reply);

    }

    @Override
    public int getItemCount() {
        return replyList.size();
    }

    public void setItem(Reply reply){
        this.replyList.add(reply);

    }

    public void setItems(ArrayList<Reply> replyList){
        this.replyList = replyList;

    }

}
