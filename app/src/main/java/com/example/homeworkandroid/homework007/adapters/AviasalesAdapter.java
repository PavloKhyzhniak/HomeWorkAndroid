package com.example.homeworkandroid.homework007.adapters;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.example.homeworkandroid.R;
import com.example.homeworkandroid.homework007.fragments.EditAviasalesDialogFragment;
import com.example.homeworkandroid.homework007.fragments.InfoAviasalesDialogFragment;
import com.example.homeworkandroid.homework007.models.Aviasales;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

// адаптер для вывода товара в ListView
public class AviasalesAdapter extends ArrayAdapter<Aviasales> {

    private Context context;               // пример использования Context в адаптере
    private LayoutInflater layoutInflater; // загрузчик разметки
    private int layout;                      // ид разметки
    private List<Aviasales> itemsList;         // ссылка на коллекцию данных

    public AviasalesAdapter(@NonNull Context context, int resource, @NonNull List<Aviasales> itemsList) {
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
        Aviasales item = itemsList.get(position);

        // вывести данные в элементы разметки view
        viewHolder.txvDestination.setText(item.getDestination());
        viewHolder.txvLastName.setText(item.getLastName());
        viewHolder.txvFirstName.setText(item.getFirstName());
        viewHolder.txvFlightNumber.setText(String.format(Locale.UK, "%d", item.getFlightNumber()));
        viewHolder.txvDepartureDate.setText(String.format(Locale.UK, new SimpleDateFormat("yyyy-MM-dd hh:mm").format(item.getDepartureDate())));

        viewHolder.ibtnEdit.setOnClickListener(v -> dialogEdit(position));

        // добавляем для списка слушатель
        viewHolder.layout_item.setOnClickListener((v) -> {
                    //через диалоги
                    dialogInfo(position);
                }
        );

        // вернуть сформированное представление
        return convertView;
    } // getView

    Dialog dialogCurrent;

    private void dialogInfo(int position) {
        // ссылка на элемент коллекции
        Aviasales item = itemsList.get(position);

        if (item != null) {
            // создать диалог, передать ему строку - элемент списка
            InfoAviasalesDialogFragment dialog = new InfoAviasalesDialogFragment();

            // передача параметров в диалог - через Bundle
            Bundle args = new Bundle();    // объект для передачи параметров в диалог

            args.putInt("select", position);
            args.putParcelable("item", item);

            // метод базового класса DialogFragment
            dialog.setArguments(args);

            // отображение диалогового окна
            dialog.show(((AppCompatActivity) context).getSupportFragmentManager(), "dialogInfoAviasales");

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    //с задержкой на построение получим уже созданные обьект Dialog
                    dialogCurrent = dialog.getDialog();
                    if (dialog.getDialog() != null) {
                        //переназначим обработчики как нам удобно
                        dialogCurrent.findViewById(R.id.btnEdit).setOnClickListener(v ->
                        {
                            //незабываем закрыть текущее диалоговое окно, перед открытием другого
                            if (dialogCurrent != null) {
                                if (dialogCurrent.isShowing()) {
                                    dialogCurrent.dismiss();
                                }
                            }
                            //создание другого диалогового окна
                            dialogEdit(position);
                        });
                    }
                }
            }, 1000);
        }
    }

    private void dialogEdit(int position) {
        // ссылка на элемент коллекции
        Aviasales item = itemsList.get(position);

        if (item != null) {

            // создать диалог, передать ему строку - элемент списка
            EditAviasalesDialogFragment dialog = new EditAviasalesDialogFragment();

            // передача параметров в диалог - через Bundle
            Bundle args = new Bundle();    // объект для передачи параметров в диалог

            args.putInt("select", position);
            args.putParcelable("item", item);

            // метод базового класса DialogFragment
            dialog.setArguments(args);

            // отображение диалогового окна
            dialog.show(((AppCompatActivity) context).getSupportFragmentManager(), "dialogEditAviasales");
        }
    }

    // внутренний класс - для хранения ссылок на элементы разметки
    // исключаем повторные операции поиска элементов в разметке по id
    private class ViewHolder {
        final TextView txvDestination;
        final TextView txvLastName;
        final TextView txvFirstName;
        final TextView txvFlightNumber;
        final TextView txvDepartureDate;
        final ImageButton ibtnEdit;

        final RelativeLayout layout_item;

        private int position;

        public ViewHolder(View view, int position) {
            // получить элементы отображения
            txvDestination = view.findViewById(R.id.txvDestination);
            txvLastName = view.findViewById(R.id.txvLastName);
            txvFirstName = view.findViewById(R.id.txvFirstName);
            txvFlightNumber = view.findViewById(R.id.txvFlightNumber);
            txvDepartureDate = view.findViewById(R.id.txvDepartureDate);

            ibtnEdit = view.findViewById(R.id.ibtnEdit);

            layout_item = view.findViewById(R.id.layout_item);

            this.position = position;
        } // ViewHolder
    } // class ViewHolder

} // class GoodsAdapter
