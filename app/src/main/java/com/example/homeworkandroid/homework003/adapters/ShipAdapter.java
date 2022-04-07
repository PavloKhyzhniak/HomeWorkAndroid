package com.example.homeworkandroid.homework003.adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.homeworkandroid.homework002.models.Ship;
import com.example.homeworkandroid.homework003.activity.ShipActivity;
import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Locale;

import com.example.homeworkandroid.R;
public class ShipAdapter extends ArrayAdapter<Ship> {
    // коды активностей для вызова
    public static final int SHIP_ACTIVITY = 1010;

    // коды завершения работы активности
    public static final int RESULT_OK = 0, RESUL_ERR = 1;

    private LayoutInflater inflater;     // контекст создания - активность или фрагмент
    private int            layout;       // ид разметки элемента списка
    private List<Ship> ships;      // коллекция данных

    // для создания адаптера в точке вызова
    public ShipAdapter(@NonNull Context context, int resource, @NonNull List<Ship> ships) {
        super(context, resource, ships);

        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
        this.ships = ships;
    }


    // Формирование каждого элемента списка
    // position    - индкес элемента коллекции для отображения
    // convertView - ссылка на элемент списка (при первом обращении - null)
    // parent      - ссылка на сам ListView
    public View getView(int position, View convertView, ViewGroup parent) {
        // связать разметку и ссылку на View
        @SuppressLint("ViewHolder") View view = inflater.inflate(this.layout, parent, false);

        // связать разметку и ссылки на элементы отображения
        findViews(view);

        // получить данные - ссылку на элемент из коллекции
        Ship ship = ships.get(position);

        // поместить данные из очередного элемента коллекции в элементы
        // отображения
        showShip(ship);

        // назначаем обработчика клика по элементам разметки
        setListeners(position);

        // вернуть сформированный view
        return view;
    } // getView

    private LinearLayout llShip;
    private Button btnEditItem, btnRemoveItem;
    private TextView txvShipType, txvShipName, txvShipCarryingCapacity, txvShipDestination,
            txvShipCargoType, txvCargoWeight, txvCargoPrice,
            txvIndicationSpecialPier, txvIndicationAnchoragePlace, txvIndicationRefueling;
    private ImageView imageViewShip;

    private void findViews(View view) {
        llShip = view.findViewById(R.id.llShip);

        // получить ссылки на элементы интерфейса
        txvShipType = view.findViewById(R.id.txvShipType);
        txvShipName = view.findViewById(R.id.txvShipName);
        txvShipCarryingCapacity = view.findViewById(R.id.txvShipCarryingCapacity);
        txvShipDestination = view.findViewById(R.id.txvShipDestination);
        txvShipCargoType = view.findViewById(R.id.txvShipCargoType);
        txvCargoWeight = view.findViewById(R.id.txvCargoWeight);
        txvCargoPrice = view.findViewById(R.id.txvCargoPrice);
        txvIndicationSpecialPier = view.findViewById(R.id.txvIndicationSpecialPier);
        txvIndicationAnchoragePlace = view.findViewById(R.id.txvIndicationAnchoragePlace);
        txvIndicationRefueling = view.findViewById(R.id.txvIndicationRefueling);

        imageViewShip = view.findViewById(R.id.imageViewShip);

        btnEditItem = view.findViewById(R.id.btnEditItem);
        btnRemoveItem = view.findViewById(R.id.btnRemoveItem);
    }
    private void setListeners(int position) {
        // назначаем обработчика клика по элементам разметки, т.к. клик в
        // основной активности блокируется слушателями кнопками
        llShip.setOnClickListener(v->onClickListner(v, position));
        imageViewShip.setOnClickListener(v->onClickListner(v, position));

        // обработчики кликов по кнопкам
        btnEditItem.setOnClickListener(v -> editItem(position));
        btnRemoveItem.setOnClickListener(v -> removeItem(v, position));
    }
    private void showShip(Ship ship) {
        txvShipType.setText(ship.getType().getName());
        txvShipName.setText(ship.getName());
        txvShipCarryingCapacity.setText(String.format(Locale.ROOT, "%3d", ship.getCarryingCapacity()));
        txvShipDestination.setText(ship.getDestination());
        txvShipCargoType.setText(ship.getCargoType());
        txvCargoWeight.setText(String.format(Locale.ROOT, "%3d", ship.getCargoWeight()));
        txvCargoPrice.setText(String.format(Locale.ROOT, "%3d", ship.getCargoPrice()));


        txvIndicationSpecialPier.setVisibility(ship.isIndicationSpecialPier() ? View.VISIBLE : View.GONE);
        txvIndicationAnchoragePlace.setVisibility(ship.isIndicationAnchoragePlace() ? View.VISIBLE : View.GONE);
        txvIndicationRefueling.setVisibility(ship.isIndicationRefueling() ? View.VISIBLE : View.GONE);

        ShowImg(imageViewShip, ship.getType().getBase());
    }

    private void ShowImg(ImageView imageView, String fileName) {
        // чтение файла изображения из assets и вывод его в ImageView
        // получить файл с изображением и поместить его в ImageView
        try (InputStream inputStream = inflater.getContext().getResources().getAssets().open(fileName)) {
            Drawable drawable = Drawable.createFromStream(inputStream, null);
            imageView.setImageDrawable(drawable);

            // программное управление режимом масштвьирования
            //imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        } catch (IOException e) {
            Snackbar snackbar = Snackbar.make(imageView, "Ошибка чтения файла изображения",
                    Snackbar.LENGTH_INDEFINITE);
            snackbar.setAction("OK", v -> {
            });
            snackbar.show();
        } // try-catсh
    }


    // слушатель клика по остальным элементам - для имитации ожидаемого
    // поведения элемента разметки
    private void onClickListner(View view, int position) {
        String str = ships.get(position).getName();
        Snackbar.make(view, String.format("Выбран '%s'", str), Snackbar.LENGTH_LONG).show();
    }

    private Ship ship;

    // имитируем редактирование
    private void editItem(int position) {
        ship = ships.get(position);

        if(ship!=null) {
            Intent intent = new Intent(inflater.getContext(), ShipActivity.class);
            intent.putExtra(Ship.class.getCanonicalName(), ship);
            ((Activity)inflater.getContext()).startActivityForResult(intent, SHIP_ACTIVITY);
        }
    } // editItem

    // удаление из колекции
    private void removeItem(View view, int position) {
        String str = ships.get(position).getName();

        ships.remove(position);
        notifyDataSetChanged();

        Snackbar.make(view, String.format("Удален '%s'", str), Snackbar.LENGTH_LONG).show();
    } // removeItem

    // обработчик события получения данных из другой активности
    @SuppressLint("DefaultLocale")
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (resultCode != RESULT_OK) {
            Toast.makeText(inflater.getContext(),
                    String.format("Ошибка %d в активности %d", resultCode, requestCode),
                    Toast.LENGTH_LONG)
                    .show();
            return;
        } // if

        Ship tmp_ship;
        // при успешной работе активностей - принять данные, вывести в TextView
        switch (requestCode) {
            case SHIP_ACTIVITY:
                assert data != null;
                tmp_ship = data.getParcelableExtra(Ship.class.getCanonicalName());
                updateShip(tmp_ship);

                notifyDataSetChanged();
                break;
        } // switch
    }

    private void updateShip(Ship tmp_ship) {
        ship.setType(tmp_ship.getType());
        ship.setName(tmp_ship.getName());
        ship.setCarryingCapacity(tmp_ship.getCarryingCapacity());
        ship.setDestination(tmp_ship.getDestination());
        ship.setCargoType(tmp_ship.getCargoType());
        ship.setCargoWeight(tmp_ship.getCargoWeight());
        ship.setCargoPrice(tmp_ship.getCargoPrice());

        ship.setIndicationSpecialPier(tmp_ship.isIndicationSpecialPier());
        ship.setIndicationAnchoragePlace(tmp_ship.isIndicationAnchoragePlace());
        ship.setIndicationRefueling(tmp_ship.isIndicationRefueling());
    }
}