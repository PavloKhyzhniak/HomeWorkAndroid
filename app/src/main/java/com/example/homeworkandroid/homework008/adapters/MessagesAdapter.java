package com.example.homeworkandroid.homework008.adapters;

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

import java.util.List;
import java.util.Locale;

// адаптер для вывода товара в ListView
public class MessagesAdapter extends ArrayAdapter<Message> {

    private Context context;               // пример использования Context в адаптере
    private LayoutInflater layoutInflater; // загрузчик разметки
    private int layout;                      // ид разметки
    private List<Message> itemsList;         // ссылка на коллекцию данных

    public MessagesAdapter(@NonNull Context context, int resource, @NonNull List<Message> itemsList) {
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
        Message item = itemsList.get(position);

        // вывести данные в элементы разметки view
        viewHolder.txvText.setText(item.getText());
        viewHolder.txvSenderID.setText(String.format(Locale.UK, "%d", item.getSenderID()));
        viewHolder.chbFlagAttached.setChecked(item.getFlagAttached());

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
        final TextView txvText;
        final TextView txvSenderID;
        final CheckBox chbFlagAttached;


        private int position;

        public ViewHolder(View view, int position) {
            // получить элементы отображения
            txvText = view.findViewById(R.id.txvText);
            txvSenderID = view.findViewById(R.id.txvSenderID);
            chbFlagAttached = view.findViewById(R.id.chbFlagAttached);

            this.position = position;
        } // ViewHolder
    } // class ViewHolder

} // class GoodsAdapter
