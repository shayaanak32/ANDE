package com.example.freelancerhomescreen;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CalendarViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public final TextView dayOfMonth;
    private final CalendarAdapter.OnItemListener onItemListener;

    public CalendarViewHolder(@NonNull View itemView, CalendarAdapter.OnItemListener onItemListener)
    {
        super(itemView);
        dayOfMonth = itemView.findViewById(R.id.cellDayText);
        this.onItemListener = onItemListener;
        itemView.setOnClickListener(this);
    }

// the adapter has this because we are going to inalte this View onto the calendar itself
    @Override
    public void onClick(View view) {
        // get position, and the number that you actually clicked on
        onItemListener.onItemClick(getAdapterPosition(), (String) dayOfMonth.getText());
    }
}
