package com.example.pijus.regitrosklausimynas;
import android.widget.ImageView;
import android.widget.Toast;
import java.util.HashMap;
import java.util.List;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class CustomExpandableListAdapter extends BaseExpandableListAdapter{
    private Context context;
    private List<String> expandableListTitle;
    private HashMap<String, List<OneMistake>> expandableListDetail;

    public CustomExpandableListAdapter(Context context, List<String> expandableListTitle, HashMap<String, List<OneMistake>> expandableListDetail){
        this.context=context;
        this.expandableListTitle=expandableListTitle;
        this.expandableListDetail=expandableListDetail;
    }
    @Override
    public Object getChild(int listPosition, int expandedListPosition){
        return this.expandableListDetail.get(this.expandableListTitle.get(listPosition)).get(expandedListPosition).getOneMistake();
    }
    @Override
    public long getChildId(int listPosition, int expandedListPosition){
        return expandedListPosition;
    }
    @Override
    public View getChildView(int listPosition, final int expandedListPosition,boolean isLastChild, View convertView, ViewGroup parent){
        String expandedListText=(String) getChild(listPosition, expandedListPosition);
        if (convertView==null){
            LayoutInflater layoutInflater=(LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.list_item, null);
        }
        TextView expandedListTextView=(TextView) convertView.findViewById(R.id.expandedListItem);
        ImageView selected=(ImageView) convertView.findViewById(R.id.checkbox);
        if(expandableListDetail.get(expandableListTitle.get(listPosition)).get(expandedListPosition).isSelected==true){
            selected.setBackgroundResource(R.drawable.checked);
        }
        else{
            selected.setBackgroundResource(R.drawable.check);
        }
        expandedListTextView.setText(expandedListText);
        return convertView;
    }
    @Override
    public int getChildrenCount(int listPosition){
        return this.expandableListDetail.get(this.expandableListTitle.get(listPosition)).size();
    }
    @Override
    public Object getGroup(int listPosition){
        return this.expandableListTitle.get(listPosition);
    }
    @Override
    public int getGroupCount(){
        return this.expandableListTitle.size();
    }
    @Override
    public long getGroupId(int listPosition){
        return listPosition;
    }
    @Override
    public View getGroupView(int listPosition, boolean isExpanded, View convertView, ViewGroup parent){
        String listTitle=(String)getGroup(listPosition);
        if (convertView==null){
            LayoutInflater layoutInflater=(LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.list_group, null);
        }
        TextView listTitleTextView=(TextView)convertView.findViewById(R.id.listTitle);
        listTitleTextView.setTypeface(null, Typeface.BOLD);
        listTitleTextView.setText(listTitle);
        return convertView;
    }
    @Override
    public boolean hasStableIds(){
        return false;
    }
    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition){
        return true;
    }
}
