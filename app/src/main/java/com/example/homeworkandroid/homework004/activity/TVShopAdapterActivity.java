package com.example.homeworkandroid.homework004.activity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.example.homeworkandroid.MainActivity;
import com.example.homeworkandroid.R;
import com.example.homeworkandroid.homework003.adapters.ShipAdapter;
import com.example.homeworkandroid.homework004.adapters.TVShopAdapter;
import com.example.homeworkandroid.homework004.models.TVShop;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TVShopAdapterActivity extends AppCompatActivity {

    // коллекция данных
    private List<TVShop> tvShopList;
    private RecyclerView rcvModTVShop;
    private TVShopAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homework004_activity_tvshop_adapter);

        // получение коллекции для отображения в RecyclerView
        tvShopList = getIntent().getParcelableArrayListExtra(TVShop.class.getCanonicalName());

        // работа с RecyclerView
        rcvModTVShop = findViewById(R.id.rcvModTVShop);
        adapter = new TVShopAdapter(this, tvShopList);
        rcvModTVShop.setAdapter(adapter);
    }

    //region Меню активности
    @Override // создание меню
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.back_menu_tvshop, menu);

        return super.onCreateOptionsMenu(menu);
    } // onCreateOptionsMenu

    @SuppressLint("NonConstantResourceId")
    @Override // обработчик выбора меню
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
// обработка выбора в меню по ид пункта
        switch (item.getItemId()) {
            case R.id.mniSortDescPrice:
                List copy_TVShop = new ArrayList(tvShopList);
                Collections.sort(copy_TVShop, new Comparator<TVShop>() {
                    @Override
                    public int compare(TVShop lhs, TVShop rhs) {
                        // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
                        return Integer.compare(rhs.getPrice(), lhs.getPrice());
                    }
                });
                startActivity(new Intent(this, TVShopAdapterActivity.class).putParcelableArrayListExtra(TVShop.class.getCanonicalName(), (ArrayList<? extends Parcelable>) copy_TVShop));
                finish();
                break;
            case R.id.mniSortSize:
                copy_TVShop = new ArrayList(tvShopList);
                Collections.sort(copy_TVShop, new Comparator<TVShop>() {
                    @Override
                    public int compare(TVShop lhs, TVShop rhs) {
                        // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
                        return Double.compare(rhs.getSize(), lhs.getSize());
                    }
                });
                startActivity(new Intent(this, TVShopAdapterActivity.class).putParcelableArrayListExtra(TVShop.class.getCanonicalName(), (ArrayList<? extends Parcelable>) copy_TVShop));
                finish();
                break;
            case R.id.mniSortManufacturer:
                copy_TVShop = new ArrayList(tvShopList);
                Collections.sort(copy_TVShop, new Comparator<TVShop>() {
                    @Override
                    public int compare(TVShop lhs, TVShop rhs) {
                        // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
                        String obj1 = lhs.getManufacturer().getName();
                        String obj2 = rhs.getManufacturer().getName();
                        if (obj1.equals(obj2)) {
                            return 0;
                        }
                        if (obj1 == null) {
                            return -1;
                        }
                        if (obj2 == null) {
                            return 1;
                        }
                        return obj1.compareTo(obj2);
                    }
                });
                startActivity(new Intent(this, TVShopAdapterActivity.class).putParcelableArrayListExtra(TVShop.class.getCanonicalName(), (ArrayList<? extends Parcelable>) copy_TVShop));
                finish();
                break;
            case R.id.mniBack:
                // возврат из активности
                finish();
                break;
        } // switch
        return super.onOptionsItemSelected(item);
    } // onOptionsItemSelected

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        adapter.onActivityResult(requestCode, resultCode, data);
    }
}