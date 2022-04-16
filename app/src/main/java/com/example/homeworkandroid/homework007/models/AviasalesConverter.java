package com.example.homeworkandroid.homework007.models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

// http://www.javenue.info/post/gson-json-api
public class AviasalesConverter implements JsonSerializer<Aviasales>, JsonDeserializer<Aviasales> {

    public static String date_pattern = "yyyy-MM-dd hh:mm";

    public String serialize(Aviasales src) {
        Gson gson = new GsonBuilder().setDateFormat(date_pattern).create();
        return gson.toJson(src);
    }

    public Aviasales deserialize(String fromJSON) {
        Gson gson = new GsonBuilder().setDateFormat(date_pattern).create();
        return gson.fromJson(fromJSON,Aviasales.class);
    }


    public JsonElement serialize(Aviasales src, Type type,
                                 JsonSerializationContext context) {
        JsonObject object = new JsonObject();

        object.addProperty("destination", src.getDestination());
        object.addProperty("flightnumber", src.getFlightNumber());
        object.addProperty("lastname", src.getLastName());
        object.addProperty("firstname", src.getFirstName());
        object.addProperty("departuredate", new SimpleDateFormat(date_pattern, Locale.getDefault()).format(src.getDepartureDate()));
        return object;
    }

    public Aviasales deserialize(JsonElement json, Type type,
                                 JsonDeserializationContext context) throws JsonParseException {
        try {
            JsonObject object = json.getAsJsonObject();

            String destination = object.get("destination").getAsString();
            int flightnumber = object.get("flightnumber").getAsInt();
            String lastname = object.get("lastname").getAsString();
            String firstname = object.get("firstname").getAsString();

            DateFormat formatter = new SimpleDateFormat(date_pattern, Locale.getDefault());
            Date departuredate = (Date) formatter.parse(object.get("departuredate").getAsString());

            return new Aviasales(destination, flightnumber, lastname, firstname, departuredate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        throw new JsonParseException("Deserialize Aviasales Error");
    }
}
