package com.thirtysparks.tutorial.designlib;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView nameTextView;
        public MyViewHolderClick mListener;
        public ViewHolder(View itemView, MyViewHolderClick listener){
            super(itemView);
            mListener = listener;

            nameTextView = (TextView) itemView.findViewById(R.id.tv_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mListener.clickOnView(v, getLayoutPosition());
        }

        public interface MyViewHolderClick {
            void clickOnView(View v, int position);
        }
    }

    private List<Contact> mContacts;

    public ContactsAdapter(List<Contact> contacts){
        mContacts = contacts;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        View contactView = LayoutInflater.from(context).inflate(R.layout.item_contact, viewGroup, false);

        ViewHolder viewHolder = new ViewHolder(contactView, new ViewHolder.MyViewHolderClick() {
            @Override
            public void clickOnView(View v, int position) {
                Contact contact = mContacts.get(position);
                Snackbar.make(v, contact.getName(), Snackbar.LENGTH_LONG).show();
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Contact contact = mContacts.get(position);
        TextView nameTextView = viewHolder.nameTextView;
        nameTextView.setText(contact.getName());
    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }
}
