package com.example.homeworkandroid.homework004;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

import com.example.homeworkandroid.MainActivity;
import com.example.homeworkandroid.R;
import com.example.homeworkandroid.homework004.activity.TVShopAdapterActivity;
import com.example.homeworkandroid.homework004.models.TVShop;

import java.util.ArrayList;

public class MainActivityHW004 extends AppCompatActivity {

    private Button btnGotoExercises001, btnReturnToMain;
    private TextView txvExercises001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homework004_activity_main_hw004);

        findViews();

        setListeners();

        setAnimations();
    }

    private void setAnimations() {
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.milkshake);

        txvExercises001.setAnimation(myAnim);
        txvExercises001.startAnimation(myAnim);
    }

    private void setListeners() {
        // связь с обработчиком собыия клика по кнопке
        btnGotoExercises001.setOnClickListener(this::gotoExercises001Click);

        btnReturnToMain.setOnClickListener(v -> returnToMain());

        // связь с обработчиком собыия клика по кнопке
        txvExercises001.setOnClickListener(this::gotoExercises001Click);
    }

    private void findViews() {
        btnGotoExercises001 = findViewById(R.id.btnGoToExercises001);
        btnReturnToMain = findViewById(R.id.btnReturnToMain);

        txvExercises001 = findViewById(R.id.txvExercises001);
    }

    private void gotoExercises001Click(View view) {
        Intent myIntent = new Intent(this, TVShopAdapterActivity.class);
        myIntent.putParcelableArrayListExtra(TVShop.class.getCanonicalName(), (ArrayList<? extends Parcelable>) TVShop.init());
        startActivity(myIntent);
    }
    private void returnToMain() {
        Intent myIntent = new Intent(this, MainActivity.class);
        startActivity(myIntent);
    }

    // region Работа с главным меню активности
    // обработчик события создани меню
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // связать разметку с ссылкой на меню
        // getMenuInflater() - загрузчик меню
        // inflate()         - загрузка меню
        getMenuInflater().inflate(R.menu.main_menu_hw004, menu);

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