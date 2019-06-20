package com.iot.movie2;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder> {

    private ArrayList<Person> persons = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView =  inflater.inflate(R.layout.person_item, viewGroup, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        Person person = persons.get(i);
        viewHolder.setItem(person);

    }

    @Override
    public int getItemCount() {
        return persons.size();
    }

    public void setItem(Person person){
        this.persons.add(person);

    }

    public void setItems(ArrayList<Person> persons){
        this.persons = persons;

    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_name, tv_mobile;
        ImageView img_profile;

        public ViewHolder(@NonNull View itemView){

            super(itemView);

            tv_name = itemView.findViewById(R.id.tv_name);
            tv_mobile = itemView.findViewById(R.id.tv_mobile);
            img_profile = itemView.findViewById(R.id.img_profile);

        }

        public void setItem(Person person){
            tv_name.setText(person.getName());
            tv_mobile.setText(person.getMobile());
            img_profile.setImageResource(person.getProfile());

        }

    }

}
