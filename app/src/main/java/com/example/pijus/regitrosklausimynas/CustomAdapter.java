package com.example.pijus.regitrosklausimynas;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pijus.regitrosklausimynas.R;

import java.util.List;


public class CustomAdapter extends BaseAdapter {

    Activity activity;
    List<UserModel> users;
    LayoutInflater inflater;

    public CustomAdapter(Activity activity) {

        this.activity = activity;
    }

    public CustomAdapter(Activity activity, List<UserModel> users) {
        this.activity   = activity;
        this.users      = users;
        inflater        = activity.getLayoutInflater();
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder = null;

        if (view == null){

            view = inflater.inflate(R.layout.list_view_item, viewGroup, false);

            holder = new ViewHolder();

            holder.One_mistake = (TextView)view.findViewById(R.id.Mistake);
            holder.Check_Box = (ImageView) view.findViewById(R.id.checkbox);

            view.setTag(holder);
        }
        else {
            holder = (ViewHolder) view.getTag();
        }
        UserModel model = users.get(i);

        holder.One_mistake.setText(model.getUserName());

        if (model.isSelected()) {
            holder.Check_Box.setBackgroundResource(R.drawable.checked);
        }
        else {
            holder.Check_Box.setBackgroundResource(R.drawable.check);
        }
        return view;

    }

    public void updateRecords(List<UserModel> users){
        this.users = users;

        notifyDataSetChanged();
    }

    class ViewHolder{

        TextView One_mistake;
        ImageView Check_Box;

    }
}