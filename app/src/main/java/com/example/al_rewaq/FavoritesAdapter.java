package com.example.al_rewaq;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.util.List;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder> {

    private List<Book> favoriteBooks;

    public FavoritesAdapter(List<Book> favoriteBooks) {
        this.favoriteBooks = favoriteBooks;
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
    }

    @Override
    public int getItemCount() {
        return favoriteBooks.size();
    }

    public static class FavoritesViewHolder extends RecyclerView.ViewHolder {
        ImageView bookImage;

        public FavoritesViewHolder(@NonNull View itemView) {
            super(itemView);
            bookImage = itemView.findViewById(R.id.book_image);
        }
    }
}
