package com.android.teamnovelvn.sakuranovel.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.android.teamnovelvn.sakuranovel.R;

import java.util.HashMap;
import java.util.List;

import me.grantland.widget.AutofitHelper;
import me.grantland.widget.AutofitTextView;

/**
 * Created by Akito on 24/11/2016.
 */

public class ExpandableAdapters extends BaseExpandableListAdapter{
    private Context mContext;
    private List<String> mHeader;
    private HashMap<String, List<String>> mDataChild;
    public ExpandableAdapters(Context context, List<String> header, HashMap<String, List<String>> mdata) {
        this.mContext = context;
        this.mHeader = header;
        this.mDataChild = mdata;
    }

    @Override
    public int getGroupCount() {
        return mHeader.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mDataChild.get(mHeader.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mHeader.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mDataChild.get(mHeader.get(groupPosition)).get(childPosition);
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
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.headergroup_layout,parent,false);
        }
        TextView head = (TextView) convertView.findViewById(R.id.tv_header);
        head.setText(mHeader.get(groupPosition));
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.child_row_layout,parent,false);
        }
        AutofitTextView ten = (AutofitTextView) convertView.findViewById(R.id.tv_row_name);
        ten.setText(getChild(groupPosition, childPosition).toString());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
