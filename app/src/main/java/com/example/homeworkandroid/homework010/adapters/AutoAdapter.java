package com.example.homeworkandroid.homework010.adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.homeworkandroid.R;
import com.example.homeworkandroid.homework007.fragments.EditAviasalesDialogFragment;
import com.example.homeworkandroid.homework007.models.Aviasales;
import com.example.homeworkandroid.homework010.fragments.EditAutoDialogFragment;
import com.example.homeworkandroid.homework010.models.Auto;

import java.util.List;
import java.util.Locale;

// адаптер для вывода товара в ListView
public class AutoAdapter extends ArrayAdapter<Auto> {

    private Context context;               // пример использования Context в адаптере
    private LayoutInflater layoutInflater; // загрузчик разметки
    private int layout;                      // ид разметки
    private List<Auto> itemsList;         // ссылка на коллекцию данных

    public AutoAdapter(@NonNull Context context, int resource, @NonNull List<Auto> itemsList) {
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
        Auto item = itemsList.get(position);

        // вывести данные в элементы разметки view
        viewHolder.txvBrandName.setText(item.getBrandName());
        viewHolder.txvModelName.setText(item.getModelName());
        viewHolder.txvYearOfManufacture.setText(String.format(Locale.UK, "%d", item.getYearOfManufacture()));
        viewHolder.txvEnginePower.setText(String.format(Locale.UK, "%.2f", item.getEnginePower()));
        viewHolder.txvPrice.setText(String.format(Locale.UK, "%d", item.getPrice()));
        viewHolder.txvSymbolicImage.setText(item.getSymbolicImage());

        viewHolder.ibtnEdit.setOnClickListener(v -> dialogEdit(position));

//        // добавляем для списка слушатель
//        viewHolder.layout_item.setOnClickListener((v) -> {
//                    //через диалоги
//                    dialogInfo(position);
//                }
//        );

        // вернуть сформированное представление
        return convertView;
    } // getView


    private void dialogEdit(int position) {
        // ссылка на элемент коллекции
        Auto item = itemsList.get(position);

        if (item != null) {

            // создать диалог, передать ему строку - элемент списка
            EditAutoDialogFragment dialog = new EditAutoDialogFragment();

            // передача параметров в диалог - через Bundle
            Bundle args = new Bundle();    // объект для передачи параметров в диалог

            args.putInt("select", position);
            args.putParcelable("item", item);

            // метод базового класса DialogFragment
            dialog.setArguments(args);

            // отображение диалогового окна
            dialog.show(((AppCompatActivity) context).getSupportFragmentManager(), "dialogEditAuto");
        }
    }

    // внутренний класс - для хранения ссылок на элементы разметки
    // исключаем повторные операции поиска элементов в разметке по id
    private class ViewHolder {
        final TextView txvBrandName;
        final TextView txvModelName;
        final TextView txvYearOfManufacture;
        final TextView txvEnginePower;
        final TextView txvPrice;
        final TextView txvSymbolicImage;

        final ImageButton ibtnEdit;

        private int position;

        public ViewHolder(View view, int position) {
            // получить элементы отображения
            txvBrandName = view.findViewById(R.id.txvBrandName);
            txvModelName = view.findViewById(R.id.txvModelName);
            txvYearOfManufacture = view.findViewById(R.id.txvYearOfManufacture);
            txvEnginePower = view.findViewById(R.id.txvEnginePower);
            txvPrice = view.findViewById(R.id.txvPrice);
            txvSymbolicImage = view.findViewById(R.id.txvSymbolicImage);

            ibtnEdit = view.findViewById(R.id.ibtnEdit);

            this.position = position;
        } // ViewHolder
    } // class ViewHolder

} // class GoodsAdapter
