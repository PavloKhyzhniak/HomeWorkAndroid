package com.example.homeworkandroid.homework002;

import android.os.Parcel;
import android.os.Parcelable;

public class Ship  implements Parcelable {

    public Ship() { this("Первопроходец",25,"Сингапур",ShipType.dryCargoShip,"мусор",2000,1600,false,false,true); }

    public Ship(
            String _Name,
            int _CarryingCapacity,
            String _Destination,
            ShipType _Type,
            String _CargoType,
            int _CargoWeight,
            int _CargoPrice,
            boolean _IndicationSpecialPier,
            boolean _IndicationAnchoragePlace,
            boolean _IndicationRefueling
    ) {
        this.Name = _Name;
        this.CarryingCapacity = _CarryingCapacity;
        this.Destination = _Destination;
        this.Type = _Type;
        this.CargoType = _CargoType;
        this.CargoWeight = _CargoWeight;
        this.CargoPrice = _CargoPrice;
        this.IndicationSpecialPier = _IndicationSpecialPier;
        this.IndicationAnchoragePlace = _IndicationAnchoragePlace;
        this.IndicationRefueling = _IndicationRefueling;
    }

    protected Ship(Parcel in) {
        Name = in.readString();
        CarryingCapacity = in.readInt();
        Destination = in.readString();
        CargoType = in.readString();
        CargoWeight = in.readInt();
        CargoPrice = in.readInt();
        IndicationSpecialPier = in.readByte() != 0;
        IndicationAnchoragePlace = in.readByte() != 0;
        IndicationRefueling = in.readByte() != 0;
        this.Type = ShipType.valueOf(in.readString());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Name);
        dest.writeInt(CarryingCapacity);
        dest.writeString(Destination);
        dest.writeString(CargoType);
        dest.writeInt(CargoWeight);
        dest.writeInt(CargoPrice);
        dest.writeByte((byte) (IndicationSpecialPier ? 1 : 0));
        dest.writeByte((byte) (IndicationAnchoragePlace ? 1 : 0));
        dest.writeByte((byte) (IndicationRefueling ? 1 : 0));
        dest.writeString(this.Type.name());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Ship> CREATOR = new Creator<Ship>() {
        @Override
        public Ship createFromParcel(Parcel in) {
            return new Ship(in);
        }

        @Override
        public Ship[] newArray(int size) {
            return new Ship[size];
        }
    };

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getCarryingCapacity() {
        return CarryingCapacity;
    }

    public void setCarryingCapacity(int carryingCapacity) {
        if(carryingCapacity>0)
            CarryingCapacity = carryingCapacity;
    }

    public String getDestination() {
        return Destination;
    }

    public void setDestination(String destination) {
        Destination = destination;
    }

    public ShipType getType() {
        return Type;
    }

    public void setType(ShipType type) {
        Type = type;
    }

    public String getCargoType() {
        return CargoType;
    }

    public void setCargoType(String cargoType) {
        CargoType = cargoType;
    }

    public int getCargoWeight() {
        return CargoWeight;
    }

    public void setCargoWeight(int cargoWeight) {
        if(cargoWeight>0)
            CargoWeight = cargoWeight;
    }

    public int getCargoPrice() {
        return CargoPrice;
    }

    public void setCargoPrice(int cargoPrice) {
        if(cargoPrice>0)
            CargoPrice = cargoPrice;
    }

    public boolean isIndicationSpecialPier() {
        return IndicationSpecialPier;
    }

    public void setIndicationSpecialPier(boolean indicationSpecialPier) {
        IndicationSpecialPier = indicationSpecialPier;
    }

    public boolean isIndicationAnchoragePlace() {
        return IndicationAnchoragePlace;
    }

    public void setIndicationAnchoragePlace(boolean indicationAnchoragePlace) {
        IndicationAnchoragePlace = indicationAnchoragePlace;
    }

    public boolean isIndicationRefueling() {
        return IndicationRefueling;
    }

    public void setIndicationRefueling(boolean indicationRefueling) {
        IndicationRefueling = indicationRefueling;
    }

    private String Name;
    private int CarryingCapacity;
    private String Destination;
    private ShipType Type;
    private String CargoType;
    private int CargoWeight;
    private int CargoPrice;
    private boolean IndicationSpecialPier;
    private boolean IndicationAnchoragePlace;
    private boolean IndicationRefueling;
}
