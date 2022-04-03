package com.example.homeworkandroid.homework004.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.homeworkandroid.homework002.ShipType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TVShop implements Parcelable {

    //производителя
    private TVShopManufacturer manufacturer;
    // и типа телевизора – это одно поле (например, Samsung OLED55DT2020)
//имя файла с фотографией телевизора в папке assets
//диагонали экрана в дюймах
//разрешения по вертикали в пикселях
//разрешения по горизонтали в пикселях
    private TVShopType type;
    //цена в рублях
    private int price;

    TVShop() {
    }

    TVShop(TVShopManufacturer _manufacturer, String _filename, String _name, double _size, int _vertical, int _horizontal, int _price) {
        this.setManufacturer(_manufacturer);
        this.setType(new TVShopType(_manufacturer, _filename, _name, _size, _vertical, _horizontal));
        this.setPrice(_price);
    }


    protected TVShop(Parcel in) {
        type = in.readParcelable(TVShopType.class.getClassLoader());
        price = in.readInt();
        this.manufacturer = TVShopManufacturer.valueOf(in.readString());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(type, flags);
        dest.writeInt(price);
        dest.writeString(this.manufacturer.name());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<TVShop> CREATOR = new Creator<TVShop>() {
        @Override
        public TVShop createFromParcel(Parcel in) {
            return new TVShop(in);
        }

        @Override
        public TVShop[] newArray(int size) {
            return new TVShop[size];
        }
    };

    public static List<TVShop> init() {
        return
                new ArrayList<>(Arrays.asList(
                        new TVShop(TVShopManufacturer.Sony, "Sony_KDL_43WF804.jpeg", "Sony_KDL_43WF804", 42.5, 1920, 1080, 11200)
                        , new TVShop(TVShopManufacturer.Sony, "Sony_KDL_43WG665.jpeg", "Sony_KDL_43WG665", 42.8, 1920, 1080, 1200)
                        , new TVShop(TVShopManufacturer.Sony, "Sony_KDL_32WD756.jpeg", "Sony_KDL_32WD756", 31.5, 1920, 1080, 1200)
                        , new TVShop(TVShopManufacturer.Sony, "Sony_KD_49XH8596.jpeg", "Sony_KD_49XH8596", 48.5, 1920, 1080, 12200)
                        , new TVShop(TVShopManufacturer.Sony, "Sony_KD_49XH8096.jpeg", "Sony_KD_49XH8096", 48.5, 3840, 2160, 14200)
                        , new TVShop(TVShopManufacturer.Sony, "Sony_KD_55XG7005.jpeg", "Sony_KD_55XG7005", 54.6, 3840, 2160, 15200)
                        , new TVShop(TVShopManufacturer.Sony, "Sony_KD_55XH9505.jpeg", "Sony_KD_55XH9505", 54.6, 3840, 2160, 16200)
                        , new TVShop(TVShopManufacturer.Sony, "Sony_KD_55XH9096.jpeg", "Sony_KD_55XH9096", 43., 1920, 1080, 17200)
                        , new TVShop(TVShopManufacturer.Sony, "Sony_KD_65A8.jpeg", "Sony_KD_65A8", 43., 1920, 1080, 15200)
                        , new TVShop(TVShopManufacturer.Sony, "Sony_KD_65AG9.jpeg", "Sony_KD_65AG9", 43., 1920, 1080, 15200)
                        , new TVShop(TVShopManufacturer.Sony, "Sony_KD_75ZH8.jpeg", "Sony_KD_75ZH8", 75.4, 7680, 4320, 16200)


                        , new TVShop(TVShopManufacturer.LG, "LG_32LK519B.jpeg", "LG_32LK519B", 32.4, 7680, 4320, 143200)
                        , new TVShop(TVShopManufacturer.LG, "LG_28TN525S_PZ.jpeg", "LG_28TN525S_PZ", 28.4, 7680, 4320, 12200)
                        , new TVShop(TVShopManufacturer.LG, "LG_22TN610V_PZ.jpeg", "LG_22TN610V_PZ", 22.4, 7680, 4320, 1200)

                        , new TVShop(TVShopManufacturer.Samsung, "Samsung_UE32T4510AU.jpeg", "Samsung_UE32T4510AU", 32.4, 7680, 4320, 11200)
                        , new TVShop(TVShopManufacturer.Samsung, "Samsung_UE43N5500AU.jpeg", "Samsung_UE43N5500AU", 43.4, 7680, 4320, 16200)
                        , new TVShop(TVShopManufacturer.Samsung, "Samsung_UE43N5000AU.jpeg", "Samsung_UE43N5000AU", 43.4, 7680, 4320, 1200)

                        , new TVShop(TVShopManufacturer.Panasonic, "TX_58GXR700.jpeg", "TX_58GXR700", 58.4, 7680, 4320, 12400)
                        , new TVShop(TVShopManufacturer.Panasonic, "TX_32FR250W.jpeg", "TX_32FR250W", 32.4, 7680, 4320, 1200)
                        , new TVShop(TVShopManufacturer.Panasonic, "TX_65GXR900.jpeg", "TX_65GXR900", 65.4, 7680, 4320, 17200)

                        , new TVShop(TVShopManufacturer.Philips, "Philips_43PFS5813.jpeg", "Philips_43PFS5813", 43.4, 7680, 4320, 174200)
                        , new TVShop(TVShopManufacturer.Philips, "Philips_50PUS6704.jpeg", "Philips_50PUS6704", 50.4, 7680, 4320, 1200)
                        , new TVShop(TVShopManufacturer.Philips, "Philips_65PUS7303.jpeg", "Philips_65PUS7303", 65.4, 7680, 4320, 15200)

                ));
    }

    public double getSize() {
        if (type != null)
            return type.getSize();
        return -1;
    }

    public int getHorizontal() {
        if (type != null)
            return type.getHorizontal();
        return -1;
    }

    public int getVertical() {
        if (type != null)
            return type.getVertical();
        return -1;
    }

    public String getName() {
        if (type != null)
            return type.getName();
        return "";
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        if (price > 0)
            this.price = price;
    }

    public TVShopType getType() {
        return type;
    }

    public void setType(TVShopType type) {
        this.type = type;
    }

    public TVShopManufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(TVShopManufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }


}
