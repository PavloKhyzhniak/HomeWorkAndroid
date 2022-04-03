package com.example.homeworkandroid.homework005.db;

import android.net.Uri;
import android.provider.BaseColumns;

public class BookProviderMetaData 
{
    public static final String AUTHORITY = "com.example.homeworkandroid.homework005.db.BookProvider";
    
    public static final String DATABASE_NAME = "books_2.db";
    public static final int DATABASE_VERSION = 1;
    public static final String BOOKS_TABLE_NAME = "books";
    
    private BookProviderMetaData() {}
    //inner class describing columns and their types
    public static final class BookTableMetaData implements BaseColumns 
    {
        private BookTableMetaData() {}
        public static final String TABLE_NAME = "books";

        //uri and mime type definitions
        public static final Uri CONTENT_URI = Uri.parse("content://com.example.homeworkandroid.homework005.db.BookProvider/books");
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.homeworkandroid.book";
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.homeworkandroid.book";

        public static final String DEFAULT_SORT_ORDER = "id DESC";

        //Additional Columns start here.
        //string type
        public static final String BOOK_ID = "id";
        public static final String BOOK_AUTHOR_ID = "author_id";
        //string type
        public static final String BOOK_CATEGORY_ID = "category_id";
        //string type
        public static final String BOOK_TITLE = "title";
        //string type
        public static final String BOOK_YEAR = "year";
        //string type
        public static final String BOOK_PRICE = "price";
        //string type
        public static final String BOOK_AMOUNT = "amount";
    }
}
