package com.iot.eround.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.iot.eround.R;

import java.util.ArrayList;
import java.util.HashMap;

public class MainMenuAdapter extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<String> arrayGroup;
    private HashMap<String, ArrayList<String>> arrayChild;

    public MainMenuAdapter(Context context, ArrayList<String> arrayGroup, HashMap<String, ArrayList<String>> arrayChild){
        super();
        this.context = context;
        this.arrayGroup = arrayGroup;
        this.arrayChild = arrayChild;
    }

    @Override
    public int getGroupCount() { return arrayGroup.size(); }

    @Override
    public int getChildrenCount(int groupPosition) {
        return arrayChild.get(arrayGroup.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) { return arrayGroup.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return arrayChild.get(arrayGroup.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        String groupName = arrayGroup.get(groupPosition);
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.expandable_menu_group, null);
        }
        TextView testGroup = view.findViewById(R.id.expandable_menu_group_id_item);
        testGroup.setText(groupName);

        return view;

    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        String childName = arrayChild.get(arrayGroup.get(groupPosition)).get(childPosition);
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.expandable_menu_child, null);
        }
        TextView testGroup = view.findViewById(R.id.expandable_menu_child_id_item);
        testGroup.setText(childName);

        return view;

    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }



}
