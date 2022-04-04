package com.example.homeworkandroid.homework005.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.homeworkandroid.R;
import com.example.homeworkandroid.homework005.db.DBProvider_Book;
import com.example.homeworkandroid.homework005.db.IReportBack;
import com.example.homeworkandroid.homework005.models.Book;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;
import java.util.Locale;

public class DBBookOptimizedAdapter extends ArrayAdapter<Book> {
    private final LayoutInflater inflater;     // контекст создания - активность или фрагмент
    private final int            layout;       // ид разметки элемента списка
    private final List<Book> itemList;      // коллекция данных

    private final DBProvider_Book providerTester;

    // для создания адаптера в точке вызова
    public DBBookOptimizedAdapter(@NonNull Context context, int resource, @NonNull List<Book> itemList) {
        super(context, resource, itemList);

        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
        this.itemList = itemList;

        providerTester = new DBProvider_Book(context, (IReportBack) context);
    } // SchoolButtonsAdapter

    // Формирование каждого элемента списка
    // position    - индкес элемента коллекции для отображения
    // convertView - ссылка на элемент списка (при первом обращении - null)
    // parent      - ссылка на сам ListView
    public View getView(int position, View convertView, ViewGroup parent) {
        // связать разметку и ссылку на View

        /*
        // 1й вариант оптимизации - читаем разметку из ресурсов только при первом обращении
        final View view = convertView == null
                ? inflater.inflate(this.layout, parent, false)
                : convertView;
        */

        // 2й вариант оптимизации - использование ViewHolder - внутренний класс
        final ViewHolder viewHolder;

        // создание и сохранение ViewHolder при первом обращении, чтение из
        // места сохранения при последующих обращениях
        if (convertView == null) {
            convertView = inflater.inflate(this.layout, parent, false);
            viewHolder = new ViewHolder(convertView, position);

            // сохранить все ссылки на элементы разметки
            // в поле tag convertView, тип tag - Object
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            viewHolder.position = position;
        } // if

        // получить данные - ссылку на элемент из коллекции
        Book book = itemList.get(position);

       // вывести поля объекта в элементы интерфейса
        // поместить данные из очередного элемента коллекции в элементы
        // отображения
        viewHolder.txvTitle.setText(book.getTitle());
        viewHolder.txvYear.setText(String.format(Locale.UK, "Год: %d", book.getYear()));
        viewHolder.txvPrice.setText(String.format(Locale.UK, "Цена: %d", book.getPrice()));
        viewHolder.txvAmount.setText(String.format(Locale.UK, "Количество: %d", book.getAmount()));

        // вернуть сформированное представление элемента списка
        return convertView;
    } // getView

    // класс ViewHolder - хранит ссылки на элементы разметки
    // исключает повторные операции поиска элементов в разметке
    private class ViewHolder {
        // элементы интерфейса из разметки элемента
        final TextView txvTitle;
        final TextView txvYear;
        final TextView txvPrice;
        final TextView txvAmount;

        final Button btnEdit;
        final Button btnRemove;
        final LinearLayout llTextViews;

        private int position;

        public ViewHolder(View view, int position) {
            // связать разметку и ссылки на элементы отображения
            txvTitle = view.findViewById(R.id.txvTitle);
            txvYear = view.findViewById(R.id.txvYear);
            txvPrice = view.findViewById(R.id.txvPrice);
            txvAmount = view.findViewById(R.id.txvAmount);

            btnEdit = view.findViewById(R.id.btnEditItem);
            btnRemove = view.findViewById(R.id.btnRemoveItem);

            llTextViews = view.findViewById(R.id.llTextViews);

            // позиция элемента в коллекции
            this.position = position;

            // назначаем обработчика клика по элементам разметки, т.к. клик в
            // основной активности блокируется слушателями кнопками
            llTextViews.setOnClickListener(v->onClickListner(v, this.position));

            // обработчики кликов по кнопкам
            btnEdit.setOnClickListener(v -> editItem(this.position));
            btnRemove.setOnClickListener(v -> {
                try {
                    removeItem(v, this.position);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } // ViewHolder
    } // class ViewHolder

    // слушатель клика по остальным элементам - для имитации ожидаемого
    // поведения элемента разметки
    private void onClickListner(View view, int position) {
        String str = itemList.get(position).getTitle();
        Snackbar.make(view, String.format("Выбран '%s'", str), Snackbar.LENGTH_LONG).show();
    }

    // имитируем редактирование
    private void editItem(int position) {
        Book book = itemList.get(position);

        book.setAmount(book.getAmount() + 1);

        notifyDataSetChanged();
    } // editItem

    // удаление из коллекции
    private void removeItem(View view, int position) throws Exception {
        String str = itemList.get(position).getTitle();

//        DBBookAdapterActivity activity = (DBBookAdapterActivity) this.inflater.getContext();
//        activity.dbHelper_books().removeBook(position);
        providerTester.removeBook(position);
        itemList.remove(position);
        notifyDataSetChanged();

        Snackbar.make(view, String.format("Удален '%s'", str), Snackbar.LENGTH_LONG).show();
    } // removeItem
}
