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
import com.example.homeworkandroid.homework009.models.Photo;

import java.util.List;
import java.util.Locale;

// адаптер для вывода товара в ListView
public class PhotosAdapter extends ArrayAdapter<Photo> {

    private Context context;               // пример использования Context в адаптере
    private LayoutInflater layoutInflater; // загрузчик разметки
    private int layout;                      // ид разметки
    private List<Photo> itemsList;         // ссылка на коллекцию данных

    public PhotosAdapter(@NonNull Context context, int resource, @NonNull List<Photo> itemsList) {
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
        Photo item = itemsList.get(position);

        // вывести данные в элементы разметки view
        viewHolder.txvAlbumID.setText(String.format(Locale.UK, "%d", item.getAlbumID()));
        viewHolder.txvID.setText(String.format(Locale.UK, "%d", item.getID()));
        viewHolder.txvTitle.setText(item.getTitle());
        viewHolder.txvURL.setText(item.getURL());
        if(item.getThumbnailURL()==null)
            viewHolder.txvThumbnailURL.setVisibility(View.GONE);
        viewHolder.txvThumbnailURL.setText(item.getThumbnailURL());

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
        final TextView txvAlbumID;
        final TextView txvID;
        final TextView txvTitle;
        final TextView txvURL;
        final TextView txvThumbnailURL;


        private int position;

        public ViewHolder(View view, int position) {
            // получить элементы отображения
            txvAlbumID = view.findViewById(R.id.txvAlbumID);
            txvID = view.findViewById(R.id.txvID);
            txvTitle = view.findViewById(R.id.txvTitle);
            txvURL = view.findViewById(R.id.txvURL);
            txvThumbnailURL = view.findViewById(R.id.txvThumbnailURL);

            this.position = position;
        } // ViewHolder
    } // class ViewHolder

} // class GoodsAdapter
