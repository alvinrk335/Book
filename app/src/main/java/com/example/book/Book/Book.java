// Purpose: the data model
//Represents a single book's data (like a "blueprint" for what a book is).
//* Stores book details (title, author, etc.) as variables.
//* Implements Parcelable to allow passing between activities (like packaging a book for shipping).
//* Analogy: Think of this as a "book template" in a library catalog system.

package com.example.book.Book;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Book implements Parcelable {
    // Private fields (immutable for safety)
    private final String name;
    private final String author;
    private final String rating;
    private final String price;
    private final String language;
    private final int coverImageId; // Resource ID (int)

    // Constructor
    public Book(String name, String author, String rating, String price,
                String language, int coverImageId) {
        this.name = name;
        this.author = author;
        this.rating = rating;
        this.price = price;
        this.language = language;
        this.coverImageId = coverImageId;
    }

    //===== Parcelable Implementation =====//
    protected Book(Parcel in) {
        name = in.readString();
        author = in.readString();
        rating = in.readString();
        price = in.readString();
        language = in.readString();
        coverImageId = in.readInt();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    @Override
    public int describeContents() {
        return 0; // Default (no special flags)
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(author);
        dest.writeString(rating);
        dest.writeString(price);
        dest.writeString(language);
        dest.writeInt(coverImageId);
    }

    //===== Getters =====//
    public String getName() { return name; }
    public String getAuthor() { return author; }
    public String getRating() { return rating; }
    public String getPrice() { return price; }
    public String getLanguage() { return language; }
    public int getCoverImageId() { return coverImageId; }

    @NonNull
    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}