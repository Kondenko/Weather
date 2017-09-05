package com.aleksandersh.weather.utils;


import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jakewharton.rxbinding2.view.RxView;

import java.util.ArrayList;

import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import timber.log.Timber;


/**
 * Created by Vladimir Kondenko on 09.08.17.
 */

public abstract class BaseRxAdapter<I, VH extends BaseRxAdapter.BaseViewHolder<I>> extends RecyclerView.Adapter<VH> {

    protected final PublishSubject<I> itemClickSubject = PublishSubject.create();

    protected Context context;
    protected LayoutInflater inflater;
    protected ArrayList<I> items;

    public BaseRxAdapter(Context context) {
        this(context, new ArrayList<>());
    }

    public BaseRxAdapter(Context context, ArrayList<I> items) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public abstract VH onCreateViewHolder(ViewGroup viewGroup, int i);

    @Override
    @CallSuper
    public void onBindViewHolder(VH vh, int i) {
        I item = items.get(i);
        vh.bindItem(item);
        RxView.clicks(vh.getView())
                .map(o -> item)
                .subscribe(itemClickSubject);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
//        itemClickSubject.onComplete();
    }

    public Observable<I> clicks() {
        return itemClickSubject;
    }

    public void setData(ArrayList<I> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public void addItem(I city) {
        if (!items.contains(city)) {
            items.add(city);
            notifyDataSetChanged();
//        notifyItemInserted(items.size() - 1);
        }
    }

    public I getItem(int i) {
        return items.get(i);
    }

    public void remove(int i) {
        Timber.i("Removing item " + i);
        items.remove(i);
        notifyDataSetChanged();
//        notifyItemRemoved(i);
    }

    public void remove(I item) {
        items.remove(item);
        notifyDataSetChanged();
//        int index = items.indexOf(item);
//        notifyItemRemoved(index);
    }

    public void clear() {
        items.clear();
        notifyDataSetChanged();
//        notifyItemRangeRemoved(0, items.size() - 1);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public abstract static class BaseViewHolder<I> extends RecyclerView.ViewHolder {

        private I item;

        public BaseViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        @CallSuper
        public void bindItem(I item) {
            this.item = item;
        }

        public I getItem() { return item; }

        public View getView() {
            return itemView;
        }

    }
}
