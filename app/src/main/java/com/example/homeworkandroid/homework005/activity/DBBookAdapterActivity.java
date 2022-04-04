package com.example.homeworkandroid.homework005.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.homeworkandroid.R;
import com.example.homeworkandroid.homework005.adapters.DBBookOptimizedAdapter;
import com.example.homeworkandroid.homework005.db.DBHelper_Books;
import com.example.homeworkandroid.homework005.db.IReportBack;
import com.example.homeworkandroid.homework005.models.Book;

import java.io.IOException;
import java.util.List;

public class DBBookAdapterActivity extends AppCompatActivity implements IReportBack {

    private List<Book> itemList;
    private ListView lsvCustomOptimizedAdapter;

    private DBHelper_Books dbHelper_books = null;

    public DBHelper_Books dbHelper_books() {
        return dbHelper_books;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dbbook_adapter);

        try {
            dbHelper_books = new DBHelper_Books(this,this);
            dbHelper_books.openDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }

        initializer();

        // список школьных принадлежностей
        // 1. Получение ссылки на ListView
        lsvCustomOptimizedAdapter = findViewById(R.id.lsvCustomOptimizedAdapter);

        // 2. Создание адаптера
        // адаптер попроще
        DBBookOptimizedAdapter bookOptimizedAdapter = new DBBookOptimizedAdapter(
                this,
                R.layout.book_item,
                itemList);

        // 3. Назаначение адаптера
        lsvCustomOptimizedAdapter.setAdapter(bookOptimizedAdapter);
    }

    void initializer() {
        itemList = dbHelper_books.getBooks();
    } // initializer

    // region Работа с главным меню активности
    // обработчик события создани меню
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // связать разметку с ссылкой на меню
        // getMenuInflater() - загрузчик меню
        // inflate()         - загрузка меню
        getMenuInflater().inflate(R.menu.back_menu, menu);

        return super.onCreateOptionsMenu(menu);
    } // onCreateOptionsMenu


    // обработчик события выбора в меню
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // обработка выбора в меню по ид пункта
        switch (item.getItemId()) {
            case R.id.mniBack:
                finish();
                break;
        } // switch
        return super.onOptionsItemSelected(item);
    } // onOptionsItemSelected

    @Override
    public void reportBack(String tag, String message) {

    }
    // endregion
}