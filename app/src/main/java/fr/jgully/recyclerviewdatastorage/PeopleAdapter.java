package fr.jgully.recyclerviewdatastorage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.PeopleViewHolder> {

    private List<People> peoples;

    public PeopleAdapter(List<People> peoples) {
        this.peoples = peoples;
    }

    @NonNull
    @Override
    public PeopleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View peopleRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_people_row, parent, false);
        PeopleViewHolder viewHolder = new PeopleViewHolder(peopleRow);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PeopleViewHolder holder, int position) {
        holder.bind(peoples.get(position));
    }

    @Override
    public int getItemCount() {
        return peoples.size();
    }

    class PeopleViewHolder extends RecyclerView.ViewHolder {

        public PeopleViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bind(People people) {
            TextView titleTextView = itemView.findViewById(R.id.row_name);
            titleTextView.setText(people.getName());
        }
    }

}
