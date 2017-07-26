package io.github.p4ndaj.bit.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import io.github.p4ndaj.bit.R;
import io.github.p4ndaj.bit.lists.SavedPasswordList;
import io.github.p4ndaj.bit.utils.FontsUtils;

/**
 * Created by P4ndaJ on 7/26/17.
 */

public class SavedPasswordAdapter extends RecyclerView.Adapter<SavedPasswordAdapter.MyViewHolder> {

    private List<SavedPasswordList> savedPasswordLists;

    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView Title, Password, Tag;

        public ImageView Icon;
        public ImageView Favorite;

        public MyViewHolder(View view) {
            super(view);
            Title = (TextView) view.findViewById(R.id.textViewTitle);
            Tag = (TextView) view.findViewById(R.id.textViewPassword);
            Password = (TextView) view.findViewById(R.id.textViewTag);

            Icon = (ImageView) view.findViewById(R.id.imageViewPasswordList);
            Favorite = (ImageView) view.findViewById(R.id.imageViewFavorite);
        }
    }


    public SavedPasswordAdapter(List<SavedPasswordList> savedPasswordLists, Context context) {
        this.savedPasswordLists = savedPasswordLists;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.saved_password_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        SavedPasswordList savedPasswordList = savedPasswordLists.get(position);
        holder.Title.setText(savedPasswordList.getTitle());
        holder.Password.setText(savedPasswordList.getTag());
        holder.Tag.setText(savedPasswordList.getPassword());

        holder.Icon.setBackgroundResource(savedPasswordList.getIcon());
        if (!savedPasswordList.getFavorite()) {
            holder.Favorite.setVisibility(View.GONE);
        } else {
            holder.Favorite.setVisibility(View.VISIBLE);
        }

        FontsUtils.setLatoRegularFontTextView(holder.Title, context);
        FontsUtils.setLatoRegularFontTextView(holder.Password, context);
        FontsUtils.setLatoRegularFontTextView(holder.Tag, context);
    }

    @Override
    public int getItemCount() {
        return savedPasswordLists.size();
    }
}