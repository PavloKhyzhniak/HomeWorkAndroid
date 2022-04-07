package com.example.homeworkandroid.homework005;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.homeworkandroid.MainActivity;
import com.example.homeworkandroid.R;
import com.example.homeworkandroid.homework004.activity.TVShopAdapterActivity;
import com.example.homeworkandroid.homework004.models.TVShop;
import com.example.homeworkandroid.homework005.activity.DBBookAdapterActivity;

import java.util.ArrayList;

public class MainActivityHW005 extends AppCompatActivity {

    private Button btnGotoExercises001, btnGotoExercises002, btnReturnToMain;
    private TextView txvExercises001,txvExercises002;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homework005_activity_main_hw005);

        findViews();

        setListeners();

        setAnimations();
    }

    private void setAnimations() {
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.milkshake);

        txvExercises001.setAnimation(myAnim);
        txvExercises001.startAnimation(myAnim);
        txvExercises002.setAnimation(myAnim);
        txvExercises002.startAnimation(myAnim);
    }

    private void setListeners() {
        // связь с обработчиком собыия клика по кнопке
        btnGotoExercises001.setOnClickListener(this::gotoExercises001Click);
        btnGotoExercises002.setOnClickListener(this::gotoExercises002Click);

        btnReturnToMain.setOnClickListener(v -> returnToMain());

        // связь с обработчиком собыия клика по кнопке
        txvExercises001.setOnClickListener(this::gotoExercises001Click);
        txvExercises002.setOnClickListener(this::gotoExercises002Click);
    }

    private void findViews() {
        btnGotoExercises001 = findViewById(R.id.btnGoToExercises001);
        btnGotoExercises002 = findViewById(R.id.btnGoToExercises002);
        btnReturnToMain = findViewById(R.id.btnReturnToMain);

        txvExercises001 = findViewById(R.id.txvExercises001);
        txvExercises002 = findViewById(R.id.txvExercises002);
    }

    private void gotoExercises001Click(View view) {
        Intent myIntent = new Intent(this, TVShopAdapterActivity.class);
        myIntent.putParcelableArrayListExtra(TVShop.class.getCanonicalName(), (ArrayList<? extends Parcelable>) TVShop.init());
        myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        myIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(myIntent);
    }
    private void gotoExercises002Click(View view) {
        Intent myIntent = new Intent(this, DBBookAdapterActivity.class);
        myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        myIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(myIntent);
    }
    private void returnToMain() {
        Intent myIntent = new Intent(this, MainActivity.class);
        myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        myIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(myIntent);
        finish();
    }

    // region Работа с главным меню активности
    // обработчик события создани меню
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // связать разметку с ссылкой на меню
        // getMenuInflater() - загрузчик меню
        // inflate()         - загрузка меню
        getMenuInflater().inflate(R.menu.main_menu_hw005, menu);

        return super.onCreateOptionsMenu(menu);
    } // onCreateOptionsMenu


    // обработчик события выбора в меню
    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // обработка выбора в меню по ид пункта
        switch (item.getItemId()) {
            case R.id.mniTVShopAdapter:
                startActivity(new Intent(this, TVShopAdapterActivity.class).putParcelableArrayListExtra(TVShop.class.getCanonicalName(), (ArrayList<? extends Parcelable>) TVShop.init()));
                break;
            case R.id.mniBookAdapter:
                startActivity(new Intent(this, DBBookAdapterActivity.class).putParcelableArrayListExtra(TVShop.class.getCanonicalName(), (ArrayList<? extends Parcelable>) TVShop.init()));
                break;
            case R.id.mniReturn:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.mniExit:
                finish();
                break;
        } // switch
        return super.onOptionsItemSelected(item);
    } // onOptionsItemSelected

    // endregion
}