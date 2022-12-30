package com.example.task3_database;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder>{

    private Context mCtx;
    private List<Category> categories;

    public CategoriesAdapter(Context mCtx, List<Category> sellers) {
        this.mCtx = mCtx;
        this.categories = sellers;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recyclerview_categories, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {
        Category category = categories.get(position);
        holder.text_view_name.setText("Name: " + category.getName());
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }


    public class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView text_view_name, text_view_surname, text_view_middle_name;

        public CategoryViewHolder(View itemView) {
            super(itemView);

            text_view_name = itemView.findViewById(R.id.textViewName);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Category category = categories.get(getAdapterPosition());

            Intent intent = new Intent(mCtx, UpdateCategoryActivity.class);
            intent.putExtra("category", category);

            mCtx.startActivity(intent);
        }

    }
}
