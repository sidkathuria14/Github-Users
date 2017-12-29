package com.example.sidkathuria14.githubapi.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sidkathuria14.githubapi.Models.SearchUser;
import com.example.sidkathuria14.githubapi.R;
import com.example.sidkathuria14.githubapi.interfaces.OnItemClickListener;

import java.util.ArrayList;

import static com.example.sidkathuria14.githubapi.R.id.tvBio;

/**
 * Created by sidkathuria14 on 26/12/17.
 */

public class UserRecyclerAdapter extends RecyclerView.Adapter<UserRecyclerAdapter.MyViewHolder> {

   Context context;
    ArrayList<SearchUser> arrayList;
    OnItemClickListener onItemClickListener;

    public UserRecyclerAdapter(Context context, ArrayList<SearchUser> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    public void showUser(ArrayList<SearchUser> arrayList){
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = li.inflate(R.layout.search_name_layout,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
SearchUser user = arrayList.get(position);
        holder.tvName.setText(user.getItems()[position].getLogin());
        holder.tvBio.setText(user.getItems()[position].getType());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

   public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvName;TextView tvBio;
        public MyViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView)itemView.findViewById(R.id.tvName);
            tvBio = (TextView)itemView.findViewById(R.id.tvBio);

        }
    }
}
