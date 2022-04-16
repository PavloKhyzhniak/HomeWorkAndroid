package com.example.homeworkandroid.homework007.models;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Aviasales implements Parcelable {

    String Destination;
    int FlightNumber;
    String LastName;
    String FirstName;
    Date DepartureDate;

    protected Aviasales(@NonNull Parcel in) throws ParseException {
        Destination = in.readString();
        FlightNumber = in.readInt();
        LastName = in.readString();
        FirstName = in.readString();
        DateFormat formatter = new SimpleDateFormat(date_pattern, Locale.getDefault());
        DepartureDate = (Date) formatter.parse(in.readString());
    }

    @Override
    public int describeContents() {
        return 0;
    }
    public static String date_pattern = "yyyy-MM-dd hh:mm";

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(Destination);
        dest.writeInt(FlightNumber);
        dest.writeString(LastName);
        dest.writeString(FirstName);
        dest.writeString(new SimpleDateFormat(date_pattern, Locale.getDefault()).format(DepartureDate));
    }
    public static final Creator<Aviasales> CREATOR = new Creator<Aviasales>() {
        @Nullable
        @Override
        public Aviasales createFromParcel(Parcel in) {
            try {
                return new Aviasales(in);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public Aviasales[] newArray(int size) {
            return new Aviasales[size];
        }
    };

    // для вывода в ListView без применения адаптера
    @NonNull
    @SuppressLint("SimpleDateFormat")
    @Override
    public String toString() {
        return String.format(Locale.UK, "%s %s : %s : %d : %s ",
                getLastName() ,getFirstName() , getDestination(), getFlightNumber(), new SimpleDateFormat("yyyy-MM-dd hh:mm").format(getDepartureDate())) ;
    } // toString

    public Aviasales()
    {
        this("Москва",12345,"Ivanov","Ivan",new Date());
    }

    public Aviasales(
            String _Destination,
            int _FlightNumber,
            String _LastName,
            String _FirstName,
            Date _DepartureDate
    )
    {
        this.Destination = _Destination;
        this.FlightNumber = _FlightNumber;
        this.LastName = _LastName;
        this.FirstName = _FirstName;
        this.DepartureDate = _DepartureDate;
    }

    public String getDestination() {
        return Destination;
    }

    public void setDestination(@NonNull String destination) {
        if (!destination.isEmpty())
            Destination = destination;
    }

    public int getFlightNumber() {
        return FlightNumber;
    }

    public void setFlightNumber(int flightNumber) {
        if (flightNumber > 0)
            FlightNumber = flightNumber;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(@NonNull String lastName) {
        if (!lastName.isEmpty())
            LastName = lastName;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(@NonNull String firstName) {
        if (!firstName.isEmpty())
            FirstName = firstName;
    }

    public Date getDepartureDate() {
        return DepartureDate;
    }

    public void setDepartureDate(@NonNull Date departureDate) {
        if (departureDate.after(new Date()))
            DepartureDate = departureDate;
    }



}
