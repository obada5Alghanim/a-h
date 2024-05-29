package com.example.al_rewaq;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.util.List;



public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder> {

    private List<Book> favoriteBooks;
    private OnDeleteClickListener onDeleteClickListener;

    public FavoritesAdapter(List<Book> favoriteBooks, OnDeleteClickListener onDeleteClickListener) {
        this.favoriteBooks = favoriteBooks;
        this.onDeleteClickListener = onDeleteClickListener;
    }

    @NonNull
    @Override
    public FavoritesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favorite_book, parent, false);
        return new FavoritesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoritesViewHolder holder, int position) {
        Book book = favoriteBooks.get(position);
        Picasso.get().load(book.getImageUrl()).into(holder.bookImage);
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onDeleteClickListener != null) {
                    int currentPosition = holder.getAdapterPosition();
                    if (currentPosition != RecyclerView.NO_POSITION) {
                        onDeleteClickListener.onDeleteClick(currentPosition);
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return favoriteBooks.size();
    }

    public static class FavoritesViewHolder extends RecyclerView.ViewHolder {
        ImageView bookImage;
        ImageButton deleteButton;

        public FavoritesViewHolder(@NonNull View itemView) {
            super(itemView);
            bookImage = itemView.findViewById(R.id.book_image);
            deleteButton = itemView.findViewById(R.id.delete_button);
        }
    }

    public interface OnDeleteClickListener {
        void onDeleteClick(int position);
    }
}
