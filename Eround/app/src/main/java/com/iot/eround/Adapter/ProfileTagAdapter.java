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

public class ProfileTagAdapter extends RecyclerView.Adapter<ProfileTagAdapter.ViewHolder> {

    private ArrayList<String> tagList = new ArrayList<>();
    private OnItemClickListener mListener;

    public ArrayList<String> gettagList() {
        return tagList;
    }

    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener ;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView text1;

        public ViewHolder(@NonNull View itemView){

            super(itemView);

            text1 = itemView.findViewById(R.id.list_profile_tag_group_id_text1);

            itemView.setOnClickListener(v -> {

                int pos = getAdapterPosition();
                if (pos != RecyclerView.NO_POSITION){
                    if (mListener != null) {
                        mListener.onItemClick(v, pos) ;
                    }
                }

            });

        }

        public void setItem(String tag){

            text1.setText(tag);

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

        String tag = tagList.get(i);
        viewHolder.setItem(tag);

    }

    @Override
    public int getItemCount() {
        return tagList.size();
    }

    public void setItem(String tag){
        this.tagList.add(tag);

    }

    public void setItems(ArrayList<String> tagList){
        this.tagList = tagList;

    }

}
