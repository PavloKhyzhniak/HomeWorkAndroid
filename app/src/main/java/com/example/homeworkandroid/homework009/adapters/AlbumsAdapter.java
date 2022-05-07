package com.example.homeworkandroid.homework009.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.homeworkandroid.R;
import com.example.homeworkandroid.homework008.models.Message;
import com.example.homeworkandroid.homework009.models.Album;

import java.util.List;
import java.util.Locale;

// адаптер для вывода товара в ListView
public class AlbumsAdapter extends ArrayAdapter<Album> {

    private Context context;               // пример использования Context в адаптере
    private LayoutInflater layoutInflater; // загрузчик разметки
    private int layout;                      // ид разметки
    private List<Album> itemsList;         // ссылка на коллекцию данных

    public AlbumsAdapter(@NonNull Context context, int resource, @NonNull List<Album> itemsList) {
        super(context, resource, itemsList);

        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.layout = resource;
        this.itemsList = itemsList;
    } // GoodsAdapter

    // Запись данных в элемент разметки
    // convertView - старое состояние элемента разметки
    // parent      - ссылка на ListView
    public View getView(int position, View convertView, ViewGroup parent) {
        /* Оптимазация. Использование ViewHolder */
        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(this.layout, parent, false);
            viewHolder = new ViewHolder(convertView, position);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            viewHolder.position = position;
        } // if

        // получить ссылку на элемент коллекции
        Album item = itemsList.get(position);

        // вывести данные в элементы разметки view
        viewHolder.txvTitle.setText(item.getTitle());
        viewHolder.txvUserID.setText(String.format(Locale.UK, "%d", item.getUserID()));
        viewHolder.txvID.setText(String.format(Locale.UK, "%d", item.getID()));

//        viewHolder.ibtnEdit.setOnClickListener(v -> dialogEdit(position));

//        // добавляем для списка слушатель
//        viewHolder.layout_item.setOnClickListener((v) -> {
//                    //через диалоги
//                    dialogInfo(position);
//                }
//        );

        // вернуть сформированное представление
        return convertView;
    } // getView


    // внутренний класс - для хранения ссылок на элементы разметки
    // исключаем повторные операции поиска элементов в разметке по id
    private class ViewHolder {
        final TextView txvTitle;
        final TextView txvUserID;
        final TextView txvID;


        private int position;

        public ViewHolder(View view, int position) {
            // получить элементы отображения
            txvTitle = view.findViewById(R.id.txvTitle);
            txvUserID = view.findViewById(R.id.txvUserID);
            txvID = view.findViewById(R.id.txvID);

            this.position = position;
        } // ViewHolder
    } // class ViewHolder

} // class GoodsAdapter
