package com.example.easynotes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NotesListAdapter extends RecyclerView.Adapter<NotesViewHolder> {

    Context context;
    List<NotesData> list;

    NotesClickListner listner;

    public NotesListAdapter(Context context, List<NotesData> list, NotesClickListner listner) {
        this.context = context;
        this.list = list;
        this.listner = listner;
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NotesViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.notes_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
        holder.textview_title.setText(list.get(position).getTitle());
        holder.textview_title.setSelected(true);

        holder.textview_notes.setText(list.get(position).getNote());


        if (list.get(position).isPinned()) {
            holder.imageview_pin.setImageResource(R.drawable.ic_baseline_push_pin_24);
        }
//        else
//            holder.imageview_pin.setImageResource(0);
//        else {
//            holder.imageview_pin.setImageResource(R.drawable.transperent_pin);
//        }


        holder.note_Container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listner.onClick(list.get(holder.getAdapterPosition()));
            }
        });

        holder.note_Container.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                listner.onLongclick(list.get(holder.getAdapterPosition()),holder.note_Container);
                return true;
            }
        });
    }



    @Override
    public int getItemCount() {
        return list.size();
    }

    public void filterList (List<NotesData> filteredList){
        list = filteredList;
        notifyDataSetChanged();
    }
}

class NotesViewHolder extends RecyclerView.ViewHolder {

    CardView note_Container;
    TextView textview_notes, textview_title;
    ImageView imageview_pin;
    public NotesViewHolder(@NonNull View itemView) {
        super(itemView);
        note_Container = itemView.findViewById(R.id.note_Container);
        textview_title = itemView.findViewById(R.id.textview_title);
        textview_notes = itemView.findViewById(R.id.textview_notes);

    }
}
