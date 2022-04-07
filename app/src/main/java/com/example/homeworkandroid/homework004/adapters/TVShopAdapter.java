package com.example.homeworkandroid.homework004.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homeworkandroid.homework004.activity.TVShopEditActivity;
import com.example.homeworkandroid.homework004.models.TVShop;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Locale;

import com.example.homeworkandroid.R;
import com.google.android.material.snackbar.Snackbar;

public class TVShopAdapter extends RecyclerView.Adapter<TVShopAdapter.ViewHolder> {

    // коды активностей для вызова
    public static final int TVSHOP_ACTIVITY = 1010;

    // коды завершения работы активности
    public static final int RESULT_OK = 0, RESUL_ERR = 1;


    private final LayoutInflater inflater;    // загрузчик разметки
    private final List<TVShop> tvShopList;       // коллекция данных

    // В конструкторе получаем контекст создания адаптера - чтобы
    // в свою очередь получить из него загрузчик разметки и ссылку
    // на коллекцию отображаемых данных
    public TVShopAdapter(Context context, List<TVShop> _tvShopList) {
        this.tvShopList = _tvShopList;
        this.inflater = LayoutInflater.from(context);
    } // AnimalButtonAdapter

    // возвращает экземпляр ViewHolder
    @NonNull
    @Override
    public TVShopAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.homework004_tvshop_item, parent, false));
    }

    // Привязчик данных к элементам интерфейса
    @Override
    public void onBindViewHolder(@NonNull TVShopAdapter.ViewHolder holder, int position) {
        // получить данные - ссылку на элемент из коллекции
        TVShop tvShop = tvShopList.get(position);

        // связать разметку и ссылки на элементы отображения
        ShowImg(holder.imgManufacturer,tvShop.getManufacturer().getBase());
        ShowImg(holder.imgType,tvShop.getType().getBase());

        holder.txvTypeName.setText(tvShop.getName());
        holder.txvSize.setText(String.format(Locale.UK, "%.3f", tvShop.getSize()));
        holder.txvHorizontal.setText(String.format(Locale.UK, "%3d", tvShop.getHorizontal()));
        holder.txvVertical.setText(String.format(Locale.UK, "%3d", tvShop.getVertical()));
        holder.txvPrice.setText(String.format(Locale.UK, "%3d", tvShop.getPrice()));

        // назначить обработчики кликов по элементам
        holder.imgType.setOnClickListener(v -> clickHandler(v, position));
        holder.layout_item.setOnClickListener(v -> clickHandler(v, position));

        holder.btnEdit.setOnClickListener(v -> edit(position));
        holder.btnDelete.setOnClickListener(v -> delete(v, position));
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

    // возвращает количество записей в коллекции
    @Override
    public int getItemCount() {
        return tvShopList.size();
    }

    // пример обработчка события клика по элементам интерфейса
    private void clickHandler(View view, int position) {
        // ссылка на элемент коллекции
        tvShop = tvShopList.get(position);

        String text = "Клик по элементу " + tvShop.getName();
        Snackbar sb = Snackbar.make(view, text, Snackbar.LENGTH_INDEFINITE);
        sb.setAction("OK", v -> {});
        sb.show();
    } // clickHandler

    private TVShop tvShop;
    // пример редактирования данных в элементе
    private void edit(int position) {
        // ссылка на элемент коллекции
        tvShop = tvShopList.get(position);

        if(tvShop!=null) {
            Intent intent = new Intent(inflater.getContext(), TVShopEditActivity.class);
            intent.putExtra(TVShop.class.getCanonicalName(), tvShop);
            ((Activity)inflater.getContext()).startActivityForResult(intent, TVSHOP_ACTIVITY);
        }
    } // edit

    // пример удаления записи из коллекции
    @SuppressLint("NotifyDataSetChanged")
    private void delete(View view, int position) {
        String str = tvShopList.get(position).getName();

        tvShopList.remove(position);    // удаление записи по индексу
        notifyDataSetChanged();      // применить изменения к адаптеру

        Snackbar.make(view, String.format("Удален '%s'", str), Snackbar.LENGTH_LONG).show();
    } // delete

    // обработчик события получения данных из другой активности
    @SuppressLint({"DefaultLocale", "NotifyDataSetChanged"})
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (resultCode != RESULT_OK) {
            Toast.makeText(inflater.getContext(),
                    String.format("Ошибка %d в активности %d", resultCode, requestCode),
                    Toast.LENGTH_LONG)
                    .show();
            return;
        } // if

        TVShop tmp_elem;
        // при успешной работе активностей - принять данные, вывести в TextView
        switch (requestCode) {
            case TVSHOP_ACTIVITY:
                assert data != null;
                tmp_elem = data.getParcelableExtra(TVShop.class.getCanonicalName());
                updateTVShop(tmp_elem);

                notifyDataSetChanged();
                break;
        } // switch
    }
    private void updateTVShop(TVShop tmp_elem) {
        tvShop.setType(tmp_elem.getType());
        tvShop.setManufacturer(tmp_elem.getManufacturer());
        tvShop.setPrice(tmp_elem.getPrice());
    }

    // класс для хранения элементов интерфейса
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // ссылки на элементы интерфейса
        final RelativeLayout layout_item;
        final ImageView imgManufacturer,imgType;
        final TextView txvTypeName, txvSize, txvHorizontal, txvVertical, txvPrice;

        final Button btnEdit;
        final Button btnDelete;

        // связь разметки и ссылок на элементы интерфейса
        public ViewHolder(@NonNull View view) {
            super(view);

            layout_item = view.findViewById(R.id.layout_item);

            imgManufacturer = view.findViewById(R.id.imgManufacturer);
            imgType = view.findViewById(R.id.imgType);

            txvTypeName = view.findViewById(R.id.txvTypeName);
            txvSize = view.findViewById(R.id.txvSize);
            txvHorizontal = view.findViewById(R.id.txvHorizontal);
            txvVertical = view.findViewById(R.id.txvVertical);
            txvPrice = view.findViewById(R.id.txvPrice);

            btnEdit = view.findViewById(R.id.btnEdit);
            btnDelete = view.findViewById(R.id.btnRemove);
        } // ViewHolder
    } // class ViewHolder
}
