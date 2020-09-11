package com.example.guloadssms.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.guloadssms.R;
import com.example.guloadssms.models.SMSMessages;

import java.util.ArrayList;

public class SMSMessageAdapter extends RecyclerView.Adapter<SMSMessageAdapter.ViewHolder> {

    private Context context;
    private ArrayList<SMSMessages> messagesArrayList;
    public SMSMessageAdapter(Context context,ArrayList<SMSMessages> arrayList) {
        this.context = context;
        this.messagesArrayList = arrayList;
    }

    @NonNull
    @Override
    public SMSMessageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.message_layout,
                viewGroup, false);
        return new SMSMessageAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SMSMessages messages = messagesArrayList.get(position);
        holder.message.setText(messages.getMessage());
    }

    @Override
    public int getItemCount() {
        return messagesArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView message;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            message = itemView.findViewById(R.id.message);

        }
    }
}