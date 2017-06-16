package com.yat3s.nimblerecyclerview;

import android.content.Context;

import com.yat3s.kitten.adapter.NimbleAdapter;
import com.yat3s.kitten.adapter.NimbleViewHolder;
import com.yat3s.kitten.adapter.StickyHeaderAdapter;

import java.util.List;

/**
 * Created by Yat3s on 16/06/2017.
 * Email: hawkoyates@gmail.com
 * GitHub: https://github.com/yat3s
 */

public class AnimalAdapter extends NimbleAdapter<Animal, NimbleViewHolder> implements
        StickyHeaderAdapter<NimbleViewHolder> {
    public AnimalAdapter(Context context, List<Animal> data) {
        super(context, data);
    }

    @Override
    protected void bindDataToItemView(NimbleViewHolder holder, Animal animal, int position) {
        holder.setTextView(R.id.title_tv, animal.name);
    }

    @Override
    protected int getItemViewLayoutId(int position, Animal data) {
        return R.layout.item_task;
    }

    @Override
    public void onBindHeaderViewHolder(NimbleViewHolder holder, int position) {
        holder.setTextView(R.id.header_tv, mDataSource.get(position).name);
    }

    @Override
    public int getHeaderViewLayoutId(int position) {
        return R.layout.header_layout;
    }

    @Override
    public boolean hasHeader(int position) {
        return position % 8 == 0 && position != 0;
    }

}
