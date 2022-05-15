package com.example.homeworkandroid.homework009.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.example.homeworkandroid.R;
import com.example.homeworkandroid.homework008.models.Message;
import com.example.homeworkandroid.homework009.models.Photo;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

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

        requestAdministrator = Volley.newRequestQueue(context);// очередь запросов; /*Attach the Pointer for Volley*/
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
        if (item.getThumbnailURL() == null)
            viewHolder.txvThumbnailURL.setVisibility(View.GONE);
        viewHolder.txvThumbnailURL.setText(item.getThumbnailURL());

        if (item.getURL() != null) {
//            DownloadImage(viewHolder.ivURL, item.getURL());
            DownloadImage(viewHolder.ivURL, "https://static.remove.bg/remove-bg-web/a8b5118d623a6b3f4b7813a78c686de384352145/assets/start_remove-c851bdf8d3127a24e2d137a55b1b427378cd17385b01aec6e59d5d4b5f39d2ec.png");
//            Picasso.with(context)
//                    .load("https://static.remove.bg/remove-bg-web/a8b5118d623a6b3f4b7813a78c686de384352145/assets/start_remove-c851bdf8d3127a24e2d137a55b1b427378cd17385b01aec6e59d5d4b5f39d2ec.png")
//                    //.load(item.getURL())
//                    .into(viewHolder.ivURL, new Callback() {
//                        @Override
//                        public void onSuccess() {
//                            //Online download
//                        }
//
//                        @Override
//                        public void onError() {
//                            Toast.makeText(context, "Download Failed", Toast.LENGTH_SHORT).show();
//                        }
//                    });
        }else
            viewHolder.ivURL.setVisibility(View.GONE);

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
    final RequestQueue requestAdministrator;
    private void DownloadImage(ImageView iv, String url) {
//        RequestQueue requestAdministrator = Volley.newRequestQueue(context);// очередь запросов; /*Attach the Pointer for Volley*/

        ImageRequest ir = new ImageRequest(url, new com.android.volley.Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                // callback
                iv.setImageBitmap(response);
            }
        }, 100, 100, ImageView.ScaleType.FIT_CENTER, Bitmap.Config.RGB_565,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, error.getClass().toString() + ": " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        // 100 is your custom Size before Downloading the Image.
        requestAdministrator.add(ir);
    }

    // внутренний класс - для хранения ссылок на элементы разметки
    // исключаем повторные операции поиска элементов в разметке по id
    private class ViewHolder {
        final TextView txvAlbumID;
        final TextView txvID;
        final TextView txvTitle;
        final TextView txvURL;
        final TextView txvThumbnailURL;
        final ImageView ivURL;

        private int position;

        public ViewHolder(View view, int position) {
            // получить элементы отображения
            txvAlbumID = view.findViewById(R.id.txvAlbumID);
            txvID = view.findViewById(R.id.txvID);
            txvTitle = view.findViewById(R.id.txvTitle);
            txvURL = view.findViewById(R.id.txvURL);
            txvThumbnailURL = view.findViewById(R.id.txvThumbnailURL);
            ivURL = view.findViewById(R.id.ivURL);

            this.position = position;
        } // ViewHolder
    } // class ViewHolder

} // class GoodsAdapter
