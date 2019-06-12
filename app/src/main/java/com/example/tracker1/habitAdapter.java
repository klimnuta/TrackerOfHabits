package com.example.tracker1;

import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class habitAdapter extends RecyclerView.Adapter<habitAdapter.ViewHolder>{

        private LayoutInflater inflater;
        private List<Habit> Habits;

        habitAdapter(Context context, List<Habit> habits) {
            this.Habits = habits;
            this.inflater = LayoutInflater.from(context);
        }
        @Override
        public habitAdapter.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {

            final View view = inflater.inflate(R.layout.habit_card, parent, false);
            final RecyclerView recyclerView = (RecyclerView) parent.findViewById(R.id.list);
            view.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(final View view) {
                                            int itemPosition = recyclerView.getChildAdapterPosition(view);
                                     //       Habit item = Habits.get(itemPosition);
                                            Context context = view.getContext();
                                            Intent intent = new Intent(context, habitDone.class);
                                            intent.putExtra("ItemPosition", itemPosition);

                                            context.startActivity(intent);
//                                          item.setStatus(1);
//                                          notifyDataSetChanged();
//                                          Log.e("qwe", String.valueOf(item.getStatus()));
                                        }
                                    }
            );
            view.setOnLongClickListener(new View.OnLongClickListener(){

                @Override
                public boolean onLongClick(final View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(parent.getContext());
                    builder.setTitle("")
                            .setMessage(R.string.delete)
                            .setCancelable(false)
                            .setNegativeButton(R.string.yesDelete,
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            dialog.cancel();
                                            MainActivity.Habits.remove(recyclerView.getChildAdapterPosition(view));//.delete(array.get(position).toString());
                                            notifyDataSetChanged();
                                        }
                                    });
                    AlertDialog alert = builder.create();
                    alert.show();


                    return true;
                }
            });
            return new ViewHolder(view);
        }


    @Override
        public void onBindViewHolder(habitAdapter.ViewHolder holder, int position) {
            Habit habit = Habits.get(position);
            holder.imageView.setImageResource(habit.getImage());
            holder.nameView.setText(habit.getName());
            if (habit.getStatus() == 0) {
                holder.statusView.setText(R.string.notDone);
                holder.statusView.setTextColor(Color.RED);
            } else {
                holder.statusView.setText(R.string.Done);
                holder.statusView.setTextColor(Color.GREEN);
            }

        }

        @Override
        public int getItemCount() {
            return Habits.size();
        }


        public class ViewHolder extends RecyclerView.ViewHolder {
            final ImageView imageView;
            final TextView nameView, statusView;
            ViewHolder(View view){
                super(view);
                imageView = (ImageView)view.findViewById(R.id.image);
                nameView = (TextView) view.findViewById(R.id.name);
                statusView = view.findViewById(R.id.status);
            }
        }

}
