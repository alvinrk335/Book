//Purpose: recycler view's assistant
//Binds Book data to the RecyclerView (the scrolling list of books).
// *Takes a List<Book> and creates a list item for each book.
// *Uses ViewHolder to recycle views efficiently (like reusing a bookshelf slot for new books).
// *Handles clicks to launch BookDetailActivity with the selected book.

package com.example.book.Book;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.book.R;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    private List<Book> bookList;

    public static class BookViewHolder extends RecyclerView.ViewHolder {
        public ImageView bookCover; // Added ImageView
        public TextView bookTitle, bookAuthor, bookRating, bookLanguage, bookPrice;

        public BookViewHolder(View itemView) {
            super(itemView);
            bookCover = itemView.findViewById(R.id.bookImage);
            bookTitle = itemView.findViewById(R.id.bookTitle);
            bookAuthor = itemView.findViewById(R.id.bookAuthor);
            bookRating = itemView.findViewById(R.id.bookRating);
            bookLanguage = itemView.findViewById(R.id.bookLanguage);
            bookPrice = itemView.findViewById(R.id.bookPrice);
        }
    }

    public BookAdapter(List<Book> bookList) {
        this.bookList = bookList;
    }

    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.book_item, parent, false);
        return new BookViewHolder(v);
    }

    @Override
    public void onBindViewHolder(BookViewHolder holder, int position) {
        Book book = bookList.get(position);

        // Set image resource
        holder.bookCover.setImageResource(book.getCoverImageId());

        // Set text fields
        holder.bookTitle.setText(book.getName());
        holder.bookAuthor.setText(book.getAuthor());
        holder.bookRating.setText("★ " + book.getRating());
        holder.bookLanguage.setText(book.getLanguage());
        holder.bookPrice.setText(book.getPrice());

        // Click listener
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), BookDetailActivity.class);
            intent.putExtra("book", book);
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public void updateBooks(List<Book> newBooks) {
        bookList = newBooks;
        notifyDataSetChanged();
    }
}