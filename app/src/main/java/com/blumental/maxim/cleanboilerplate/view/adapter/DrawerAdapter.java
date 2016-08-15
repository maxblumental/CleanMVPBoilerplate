package com.blumental.maxim.cleanboilerplate.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blumental.maxim.cleanboilerplate.R;
import com.blumental.maxim.cleanboilerplate.view.drawer.DrawerItemClickListener;
import com.blumental.maxim.cleanboilerplate.view.drawer.DrawerItems;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.view.LayoutInflater.from;
import static com.blumental.maxim.cleanboilerplate.view.drawer.DrawerItems.values;
import static java.util.Arrays.asList;

public class DrawerAdapter extends RecyclerView.Adapter<DrawerAdapter.ViewHolder> {

    private List<DrawerItems> list;

    private DrawerItemClickListener listener;

    public DrawerAdapter(DrawerItemClickListener listener) {
        list = new ArrayList<>(asList(values()));
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = from(parent.getContext()).inflate(R.layout.drawer_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(list.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.icon)
        ImageView icon;

        @BindView(R.id.title)
        TextView title;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(final DrawerItems drawerItem, final DrawerItemClickListener listener) {

            icon.setImageResource(drawerItem.getIconRecourceId());

            title.setText(drawerItem.getTitle());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(drawerItem);
                }
            });
        }
    }
}
