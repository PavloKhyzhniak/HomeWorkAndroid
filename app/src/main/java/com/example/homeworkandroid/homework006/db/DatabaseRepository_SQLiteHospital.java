package com.example.homeworkandroid.homework006.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import com.example.homeworkandroid.homework006.models.Address;
import com.example.homeworkandroid.homework006.models.User;
import com.example.homeworkandroid.homework006.modelview.DoctorPriceView;
import com.example.homeworkandroid.homework006.modelview.DoctorView;
import com.example.homeworkandroid.homework006.modelview.PatientView;
import com.example.homeworkandroid.homework006.modelview.SpecializationAvgRateView;
import com.example.homeworkandroid.homework006.modelview.VisitMaxPriceView;
import com.example.homeworkandroid.homework006.modelview.VisitView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// Репозиторий для БД - для конкретной таблицы
public class DatabaseRepository_SQLiteHospital {

    // поля для работы с БД
    private DatabaseHelper_SQLiteHospital dbHelper;
    private SQLiteDatabase database;

    public DatabaseRepository_SQLiteHospital(Context context) {
        dbHelper = new DatabaseHelper_SQLiteHospital(context.getApplicationContext());
    } // DatabaseRepository

    public DatabaseRepository_SQLiteHospital open() {
        // getWritableDatabase() ВСЕГДА открывает БД в памяти устройства
        // getReadableDatabase() открывает БД по заданному пути (либо в памяти устройства либо
        //                       во внешней памяти)
        database = dbHelper.openDataBase();
        return this;
    } // open

    public void close() {
        dbHelper.close();
    }

    Date getDate(String dbSqlDate) throws ParseException {
        if (dbSqlDate.contains("-"))
            return new SimpleDateFormat("yyyy-MM-dd").parse(dbSqlDate);
        else if (dbSqlDate.contains("."))
            return new SimpleDateFormat("dd.MM.yyyy").parse(dbSqlDate);
        else
            return new Date(Long.parseLong(dbSqlDate));
    }
    //region SpecialRequests

    //--Выбирает информацию о пациентах с фамилиями, начинающимися на заданную параметром последовательность букв
//    select u.*,a.* from Patient p
//    join User u on p.UserId = u.Id
//    join Address a on a.Id = u.AddressId
//    where u.LastName like 'H%'
//
    public List<PatientView> request01(String parameter) throws ParseException {
        List<PatientView> patientViews = new ArrayList<>();
        try {
            String query = String.format("SELECT u.*,a.* from Patient p join User u on p.UserId = u.Id join Address a on a.Id = u.AddressId where u.LastName like ?");
            Cursor cursor = database.rawQuery(query, new String[]{parameter});
//
//        private long Id;       // ид
//        private String FirstName;   // имя
//        private String SecondName;   // имя
//        private String LastName;   // фамилия
//        private Date BirthDate;   // ДР
//
//        private String Street;   // улица
//        private int House;   // дом
//        private int Appartment;   // квартира
//
            // чтение данных, если они получены
            if (cursor.moveToFirst()) {
                do {
                    int iId = cursor.getColumnIndex(DatabaseHelper_SQLiteHospital.PATIENT_COLUMN_ID);
                    int iFirstName = cursor.getColumnIndex(DatabaseHelper_SQLiteHospital.USER_COLUMN_FIRSTNAME);
                    int iSecondName = cursor.getColumnIndex(DatabaseHelper_SQLiteHospital.USER_COLUMN_SECONDNAME);
                    int iLastName = cursor.getColumnIndex(DatabaseHelper_SQLiteHospital.USER_COLUMN_LASTNAME);
                    int iBirthDate = cursor.getColumnIndex(DatabaseHelper_SQLiteHospital.USER_COLUMN_BIRTHDATE);

                    int iStreet = cursor.getColumnIndex(DatabaseHelper_SQLiteHospital.ADDRESS_COLUMN_STREET);
                    int iHouse = cursor.getColumnIndex(DatabaseHelper_SQLiteHospital.ADDRESS_COLUMN_HOUSE);
                    int iAppartment = cursor.getColumnIndex(DatabaseHelper_SQLiteHospital.ADDRESS_COLUMN_APPARTMENT);

                    long Id = cursor.getLong(iId);
                    String FirstName = cursor.getString(iFirstName);
                    String SecondName = cursor.getString(iSecondName);
                    String LastName = cursor.getString(iLastName);

                    Date BirthDate;
                    String dbSqlDate = cursor.getString(iBirthDate);
                    BirthDate = getDate(dbSqlDate);

                    String Street = cursor.getString(iStreet);
                    int House = cursor.getInt(iHouse);
                    int Appartment = cursor.getInt(iAppartment);

                    // добавление в коллекцию
                    patientViews.add(new PatientView(Id, FirstName, SecondName, LastName, BirthDate, Street, House, Appartment));
                } while (cursor.moveToNext());
            } // if

            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return patientViews;
    } //

    //            --Выбирает информацию о врачах, для которых значение в поле Процент отчисления на зарплату, больше 2.3% (задавать параметром)
//    select d.*,u.* from Doctors d
//    join User u on d.UserId = u.Id
//    join Address a on a.Id = u.AddressId
//    where d.Rate>2.3
//
    public ArrayList<DoctorView> request02(double parameter) throws ParseException {
        ArrayList<DoctorView> doctorViews = new ArrayList<>();
        try {
            String query = String.format(
                    "select d.*,u.* from Doctors d " +
                            "join User u on d.UserId = u.Id " +
                            "join Address a on a.Id = u.AddressId " +
                            "where d.Rate>?");

            Cursor cursor = database.rawQuery(query, new String[]{String.valueOf(parameter)});
//
//        private long Id;       // ид
//        private String FirstName;   // имя
//        private String SecondName;   // имя
//        private String LastName;   // фамилия
//        private Date BirthDate;   // ДР
//
//    private String Specialization;   // специализация
//    private double Rate;      // ставка
//
            // чтение данных, если они получены
            if (cursor.moveToFirst()) {
                do {
                    int iId = cursor.getColumnIndex(DatabaseHelper_SQLiteHospital.DOCTORS_COLUMN_ID);
                    int iFirstName = cursor.getColumnIndex(DatabaseHelper_SQLiteHospital.USER_COLUMN_FIRSTNAME);
                    int iSecondName = cursor.getColumnIndex(DatabaseHelper_SQLiteHospital.USER_COLUMN_SECONDNAME);
                    int iLastName = cursor.getColumnIndex(DatabaseHelper_SQLiteHospital.USER_COLUMN_LASTNAME);
                    int iBirthDate = cursor.getColumnIndex(DatabaseHelper_SQLiteHospital.USER_COLUMN_BIRTHDATE);

                    int iSpecialization = cursor.getColumnIndex(DatabaseHelper_SQLiteHospital.DOCTORS_COLUMN_SPECIALIZATION);
                    int iRate = cursor.getColumnIndex(DatabaseHelper_SQLiteHospital.DOCTORS_COLUMN_RATE);

                    long Id = cursor.getLong(iId);
                    String FirstName = cursor.getString(iFirstName);
                    String SecondName = cursor.getString(iSecondName);
                    String LastName = cursor.getString(iLastName);

                    Date BirthDate;
                    String dbSqlDate = cursor.getString(iBirthDate);
                    BirthDate = getDate(dbSqlDate);


                    String Specialization = cursor.getString(iSpecialization);
                    double Rate = cursor.getDouble(iRate);

                    // добавление в коллекцию
                    doctorViews.add(new DoctorView(Id, FirstName, SecondName, LastName, BirthDate, Specialization, Rate));
                } while (cursor.moveToNext());
            } // if
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return doctorViews;
    } //

    //            --Выбирает информацию о приемах за некоторый период, заданный параметрами
//    select v.*,u1.* , u2.* from Visit v
//    join User u1 on v.PatientId = u1.Id
//    join User u2 on v.DoctorId = u2.Id
//    where v.Date > date('now','-1 month') and v.Date <  date('now','+1 month')
//
    public ArrayList<VisitView> request03() throws ParseException {
        ArrayList<VisitView> visitViews = new ArrayList<>();
        String query = String.format(
                "select v.*,u1.* , u2.FirstName s, u2.SecondName ss,u2.LastName sss from Visit v " +
                        "join User u1 on v.PatientId = u1.Id " +
                        "join User u2 on v.DoctorId = u2.Id " +
                        "where v.Date > date('now','-1 month') and v.Date <  date('now','+1 month')");
        Cursor cursor = database.rawQuery(query, null);
//
//    private long Id;       // ид
//
//    private String DoctorFirstName;   // имя
//    private String DoctorSecondName;   // имя
//    private String DoctorLastName;   // фамилия
//
//    private Date Date;   // дата приема
//    private int Price;      // цена
//
//    private String PatientFirstName;   // имя
//    private String PatientSecondName;   // имя
//    private String PatientLastName;   // фамилия
//
        // чтение данных, если они получены
        if (cursor.moveToFirst()) {
            do {
                int iId = cursor.getColumnIndex(DatabaseHelper_SQLiteHospital.VISIT_COLUMN_ID);
                int iDoctorFirstName = cursor.getColumnIndex(DatabaseHelper_SQLiteHospital.USER_COLUMN_FIRSTNAME);
                int iDoctorSecondName = cursor.getColumnIndex(DatabaseHelper_SQLiteHospital.USER_COLUMN_SECONDNAME);
                int iDoctorLastName = cursor.getColumnIndex(DatabaseHelper_SQLiteHospital.USER_COLUMN_LASTNAME);
                int iDate = cursor.getColumnIndex(DatabaseHelper_SQLiteHospital.VISIT_COLUMN_DATE);
                int iPrice = cursor.getColumnIndex(DatabaseHelper_SQLiteHospital.VISIT_COLUMN_PRICE);
                int iPatientFirstName = cursor.getColumnIndex("s");
                int iPatientSecondName = cursor.getColumnIndex("ss");
                int iPatientLastName = cursor.getColumnIndex("sss");

                long Id = cursor.getLong(iId);
                String DoctorFirstName = cursor.getString(iDoctorFirstName);
                String DoctorSecondName = cursor.getString(iDoctorSecondName);
                String DoctorLastName = cursor.getString(iDoctorLastName);

                Date Date;
                String dbSqlDate = cursor.getString(iDate);
                Date = getDate(dbSqlDate);


                int Price = cursor.getInt(iPrice);

                String PatientFirstName = cursor.getString(iPatientFirstName);
                String PatientSecondName = cursor.getString(iPatientSecondName);
                String PatientLastName = cursor.getString(iPatientLastName);

                // добавление в коллекцию
                visitViews.add(new VisitView(Id, DoctorFirstName, DoctorSecondName, DoctorLastName,
                        Date, Price,
                        PatientFirstName, PatientSecondName, PatientLastName));
            } while (cursor.moveToNext());
        } // if

        cursor.close();
        return visitViews;
    } //

    //            --Выбирает информацию о докторах, специальность которых задана параметром
//    select * from Doctors d
//    join User u on u.Id = d.UserId
//    join Address a on a.Id = u.AddressId
//    where d.Specialization like 'T%'
//
    public ArrayList<DoctorView> request04(String parameter) throws ParseException {
        ArrayList<DoctorView> doctorViews = new ArrayList<>();
        String query = String.format(
                "select * from Doctors d " +
                        "join User u on u.Id = d.UserId " +
                        "join Address a on a.Id = u.AddressId " +
                        "where d.Specialization like ?");
        Cursor cursor = database.rawQuery(query, new String[]{String.valueOf(parameter)});
//
//        private long Id;       // ид
//        private String FirstName;   // имя
//        private String SecondName;   // имя
//        private String LastName;   // фамилия
//        private Date BirthDate;   // ДР
//
//    private String Specialization;   // специализация
//    private double Rate;      // ставка
//
        // чтение данных, если они получены
        if (cursor.moveToFirst()) {
            do {
                int iId = cursor.getColumnIndex(DatabaseHelper_SQLiteHospital.DOCTORS_COLUMN_ID);
                int iFirstName = cursor.getColumnIndex(DatabaseHelper_SQLiteHospital.USER_COLUMN_FIRSTNAME);
                int iSecondName = cursor.getColumnIndex(DatabaseHelper_SQLiteHospital.USER_COLUMN_SECONDNAME);
                int iLastName = cursor.getColumnIndex(DatabaseHelper_SQLiteHospital.USER_COLUMN_LASTNAME);
                int iBirthDate = cursor.getColumnIndex(DatabaseHelper_SQLiteHospital.USER_COLUMN_BIRTHDATE);

                int iSpecialization = cursor.getColumnIndex(DatabaseHelper_SQLiteHospital.DOCTORS_COLUMN_SPECIALIZATION);
                int iRate = cursor.getColumnIndex(DatabaseHelper_SQLiteHospital.DOCTORS_COLUMN_RATE);

                long Id = cursor.getLong(iId);
                String FirstName = cursor.getString(iFirstName);
                String SecondName = cursor.getString(iSecondName);
                String LastName = cursor.getString(iLastName);

                Date BirthDate;
                String dbSqlDate = cursor.getString(iBirthDate);
                BirthDate = getDate(dbSqlDate);


                String Specialization = cursor.getString(iSpecialization);
                double Rate = cursor.getDouble(iRate);

                // добавление в коллекцию
                doctorViews.add(new DoctorView(Id, FirstName, SecondName, LastName, BirthDate, Specialization, Rate));
            } while (cursor.moveToNext());
        } // if

        cursor.close();
        return doctorViews;
    } //


    //--Вычисляет размер заработной платы врача за каждый прием. Включает поля Фамилия врача, Имя врача, Отчество врача, Специальность врача, Стоимость приема, Зарплата. Сортировка по полю Специальность врача
//    select u.*,Price*Rate*(1-0.13)/100 summary,d.Specialization from visit v
//    join Doctors d on v.DoctorId = d.Id
//    join User u on u.Id = d.UserId
//    group by v.DoctorId
//
    public ArrayList<DoctorPriceView> request05() throws ParseException {
        ArrayList<DoctorPriceView> doctorPriceView = new ArrayList<>();
        String query = String.format(
                "select u.*,Price*Rate*(1-0.13)/100 summary,d.Specialization, d.Rate from visit v " +
                        "join Doctors d on v.DoctorId = d.Id " +
                        "join User u on u.Id = d.UserId " +
                        "group by v.DoctorId");
        Cursor cursor = database.rawQuery(query, null);
//
//        private long Id;       // ид
//        private String FirstName;   // имя
//        private String SecondName;   // имя
//        private String LastName;   // фамилия
//        private Date BirthDate;   // ДР
//
//    private String Specialization;   // специализация
//    private double Rate;      // ставка
//
//    private double Summary;      // зарплата за вычетом 13 процентов
//

        // чтение данных, если они получены
        if (cursor.moveToFirst()) {
            do {
                int iId = cursor.getColumnIndex(DatabaseHelper_SQLiteHospital.DOCTORS_COLUMN_ID);
                int iFirstName = cursor.getColumnIndex(DatabaseHelper_SQLiteHospital.USER_COLUMN_FIRSTNAME);
                int iSecondName = cursor.getColumnIndex(DatabaseHelper_SQLiteHospital.USER_COLUMN_SECONDNAME);
                int iLastName = cursor.getColumnIndex(DatabaseHelper_SQLiteHospital.USER_COLUMN_LASTNAME);
                int iBirthDate = cursor.getColumnIndex(DatabaseHelper_SQLiteHospital.USER_COLUMN_BIRTHDATE);

                int iSpecialization = cursor.getColumnIndex(DatabaseHelper_SQLiteHospital.DOCTORS_COLUMN_SPECIALIZATION);
                int iRate = cursor.getColumnIndex(DatabaseHelper_SQLiteHospital.DOCTORS_COLUMN_RATE);

                int iSummary = cursor.getColumnIndex("summary");

                long Id = cursor.getLong(iId);
                String FirstName = cursor.getString(iFirstName);
                String SecondName = cursor.getString(iSecondName);
                String LastName = cursor.getString(iLastName);

                Date BirthDate;
                String dbSqlDate = cursor.getString(iBirthDate);
                BirthDate = getDate(dbSqlDate);


                String Specialization = cursor.getString(iSpecialization);
                double Rate = cursor.getDouble(iRate);

                double Summary = cursor.getDouble(iSummary);

                // добавление в коллекцию
                doctorPriceView.add(new DoctorPriceView(Id, FirstName, SecondName, LastName, BirthDate, Specialization, Rate, Summary));
            } while (cursor.moveToNext());
        } // if

        cursor.close();
        return doctorPriceView;
    } //

    //--Выполняет группировку по полю Дата приема. Для каждой даты вычисляет максимальную стоимость приема
//    select v.*,MAX(v.Price) from Visit v
//    group by v.Date
//
    public ArrayList<VisitMaxPriceView> request06() throws ParseException {
        ArrayList<VisitMaxPriceView> visitMaxPriceViews = new ArrayList<>();
        String query = String.format(
                "select v.*,MAX(v.Price) from Visit v " +
                        "group by v.Date");
        Cursor cursor = database.rawQuery(query, null);

        // чтение данных, если они получены
        if (cursor.moveToFirst()) {
            do {
                int iDate = cursor.getColumnIndex(DatabaseHelper_SQLiteHospital.VISIT_COLUMN_DATE);
                int iPrice = cursor.getColumnIndex(DatabaseHelper_SQLiteHospital.VISIT_COLUMN_PRICE);

                Date Date;
                String dbSqlDate = cursor.getString(iDate);
                Date = getDate(dbSqlDate);


                int Price = cursor.getInt(iPrice);


                // добавление в коллекцию
                visitMaxPriceViews.add(new VisitMaxPriceView(Date, Price));
            } while (cursor.moveToNext());
        } // if

        cursor.close();
        return visitMaxPriceViews;
    } //

    //--Выполняет группировку по полю Специальность. Для каждой специальности вычисляет средний Процент отчисления на зарплату от стоимости приема
//    select d.*,AVG(d.Rate) avgrate from Doctors d
//    group by d.Specialization
//
    public ArrayList<SpecializationAvgRateView> request07() throws ParseException {
        ArrayList<SpecializationAvgRateView> specializationAvgRateView = new ArrayList<>();
        String query = String.format(
                "select d.*,AVG(d.Rate) avgrate from Doctors d " +
                        "group by d.Specialization");
        Cursor cursor = database.rawQuery(query, null);

        // чтение данных, если они получены
        if (cursor.moveToFirst()) {
            do {
                int iSpecialization = cursor.getColumnIndex(DatabaseHelper_SQLiteHospital.DOCTORS_COLUMN_SPECIALIZATION);
                int iAVG_Rate = cursor.getColumnIndex("avgrate");

                String Specialization = cursor.getString(iSpecialization);
                double AVG_Rate = cursor.getDouble(iAVG_Rate);


                // добавление в коллекцию
                specializationAvgRateView.add(new SpecializationAvgRateView(Specialization, AVG_Rate));
            } while (cursor.moveToNext());
        } // if

        cursor.close();
        return specializationAvgRateView;
    } //
    //endregion


    //region User Table
    // !!! оболочка к запросу select * from users
    private Cursor getAllUserEntries() {
        String[] columns = new String[]{
                DatabaseHelper_SQLiteHospital.USER_COLUMN_ID,
                DatabaseHelper_SQLiteHospital.USER_COLUMN_FIRSTNAME,
                DatabaseHelper_SQLiteHospital.USER_COLUMN_SECONDNAME,
                DatabaseHelper_SQLiteHospital.USER_COLUMN_LASTNAME,
                DatabaseHelper_SQLiteHospital.USER_COLUMN_BIRTHDATE,
                DatabaseHelper_SQLiteHospital.USER_COLUMN_ADDRESSID
        };
        // 1-й параметр - таблица
        // 2-й параметр - массив строк - имен столбцов
        return database.query(
                DatabaseHelper_SQLiteHospital.TABLE_USER, columns, null, null, null,
                null, null);
    } // getAllEntries

    // метод паттерна Репозиторий
    public long getUserCount() {
        return DatabaseUtils.queryNumEntries(database, DatabaseHelper_SQLiteHospital.TABLE_USER);
    }

    // возвращает коллекцию пользователей из таблицы БД
    public List<User> getUsers() throws ParseException {
        ArrayList<User> users = new ArrayList<>();
        Cursor cursor = getAllUserEntries();  // запрос к БД
        if (cursor.moveToFirst()) {
            do {
                int iId = cursor.getColumnIndex(DatabaseHelper_SQLiteHospital.USER_COLUMN_ID);
                int iFirstName = cursor.getColumnIndex(DatabaseHelper_SQLiteHospital.USER_COLUMN_FIRSTNAME);
                int iSecondName = cursor.getColumnIndex(DatabaseHelper_SQLiteHospital.USER_COLUMN_SECONDNAME);
                int iLastName = cursor.getColumnIndex(DatabaseHelper_SQLiteHospital.USER_COLUMN_LASTNAME);
                int iBirthDate = cursor.getColumnIndex(DatabaseHelper_SQLiteHospital.USER_COLUMN_BIRTHDATE);
                int iAddressId = cursor.getColumnIndex(DatabaseHelper_SQLiteHospital.USER_COLUMN_ADDRESSID);

                long Id = cursor.getLong(iId);
                String FirstName = cursor.getString(iFirstName);
                String SecondName = cursor.getString(iSecondName);
                String LastName = cursor.getString(iLastName);
                long AddressId = cursor.getLong(iAddressId);

                Date BirthDate;
                String dbSqlDate = cursor.getString(iBirthDate);
                BirthDate = getDate(dbSqlDate);


                // добавление в коллекцию объекта User
                users.add(new User(Id, FirstName, SecondName, LastName, BirthDate, AddressId));
            } while (cursor.moveToNext());
        } // if
        cursor.close();
        return users;
    } // getUsers

    // получить одного пользователя по id
    public User getUser(long id) throws ParseException {
        User user = null;
        String query = String.format(
                "SELECT * FROM %s WHERE %s=?",
                DatabaseHelper_SQLiteHospital.TABLE_USER,
                DatabaseHelper_SQLiteHospital.USER_COLUMN_ID);
        Cursor cursor = database.rawQuery(query, new String[]{String.valueOf(id)});

        // чтение данных, если они получены
        if (cursor.moveToFirst()) {
            int iId = cursor.getColumnIndex(DatabaseHelper_SQLiteHospital.USER_COLUMN_ID);
            int iFirstName = cursor.getColumnIndex(DatabaseHelper_SQLiteHospital.USER_COLUMN_FIRSTNAME);
            int iSecondName = cursor.getColumnIndex(DatabaseHelper_SQLiteHospital.USER_COLUMN_SECONDNAME);
            int iLastName = cursor.getColumnIndex(DatabaseHelper_SQLiteHospital.USER_COLUMN_LASTNAME);
            int iBirthDate = cursor.getColumnIndex(DatabaseHelper_SQLiteHospital.USER_COLUMN_BIRTHDATE);
            int iAddressId = cursor.getColumnIndex(DatabaseHelper_SQLiteHospital.USER_COLUMN_ADDRESSID);

            long Id = cursor.getLong(iId);
            String FirstName = cursor.getString(iFirstName);
            String SecondName = cursor.getString(iSecondName);
            String LastName = cursor.getString(iLastName);
            long AddressId = cursor.getLong(iAddressId);

            Date BirthDate;
            String dbSqlDate = cursor.getString(iBirthDate);
            BirthDate = getDate(dbSqlDate);


            user = new User(Id, FirstName, SecondName, LastName, BirthDate, AddressId);
        } // if

        cursor.close();
        return user;
    } // getUser

    // метод - оболочка для запроса insert
    public long insertUser(User user) {
        // ContentValues - для добавдения данных
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper_SQLiteHospital.USER_COLUMN_FIRSTNAME, user.getFirstName());
        cv.put(DatabaseHelper_SQLiteHospital.USER_COLUMN_SECONDNAME, user.getSecondName());
        cv.put(DatabaseHelper_SQLiteHospital.USER_COLUMN_LASTNAME, user.getLastName());
        cv.put(DatabaseHelper_SQLiteHospital.USER_COLUMN_BIRTHDATE,
                new SimpleDateFormat("yyyy-MM-dd").format(user.getBirthDate()));
        cv.put(DatabaseHelper_SQLiteHospital.USER_COLUMN_ADDRESSID, user.getAddressId());

        return database.insert(DatabaseHelper_SQLiteHospital.TABLE_USER, null, cv);
    } // insert

    // метод - оболочка для запроса delete
    public long deleteUser(long userId) {

        String whereClause = DatabaseHelper_SQLiteHospital.USER_COLUMN_ID + " = ?"; // условие удаления
        String[] whereArgs = new String[]{String.valueOf(userId)}; // параметр для условия
        return database.delete(DatabaseHelper_SQLiteHospital.TABLE_USER, whereClause, whereArgs);
    } // delete

    // метод - оболочка для запроса update
    public long updateUser(User user) {

        String whereClause = DatabaseHelper_SQLiteHospital.USER_COLUMN_ID + " = " + user.getId();
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper_SQLiteHospital.USER_COLUMN_FIRSTNAME, user.getFirstName());
        cv.put(DatabaseHelper_SQLiteHospital.USER_COLUMN_SECONDNAME, user.getSecondName());
        cv.put(DatabaseHelper_SQLiteHospital.USER_COLUMN_LASTNAME, user.getLastName());
        cv.put(DatabaseHelper_SQLiteHospital.USER_COLUMN_BIRTHDATE,
                new SimpleDateFormat("yyyy-MM-dd").format(user.getBirthDate()));
        cv.put(DatabaseHelper_SQLiteHospital.USER_COLUMN_ADDRESSID, user.getAddressId());

        return database.update(DatabaseHelper_SQLiteHospital.TABLE_USER, cv, whereClause, null);
    } // update
    //endregion


    //region Address Table
    // !!! оболочка к запросу select * from users
    private Cursor getAllAddressEntries() {
        String[] columns = new String[]{
                DatabaseHelper_SQLiteHospital.ADDRESS_COLUMN_ID,
                DatabaseHelper_SQLiteHospital.ADDRESS_COLUMN_STREET,
                DatabaseHelper_SQLiteHospital.ADDRESS_COLUMN_HOUSE,
                DatabaseHelper_SQLiteHospital.ADDRESS_COLUMN_APPARTMENT
        };
        // 1-й параметр - таблица
        // 2-й параметр - массив строк - имен столбцов
        return database.query(
                DatabaseHelper_SQLiteHospital.TABLE_ADDRESS, columns, null, null, null,
                null, null);
    } // getAllEntries

    // метод паттерна Репозиторий
    public long getAddressCount() {
        return DatabaseUtils.queryNumEntries(database, DatabaseHelper_SQLiteHospital.TABLE_ADDRESS);
    }

    // возвращает коллекцию пользователей из таблицы БД
    public List<Address> getAddresses() throws ParseException {
        ArrayList<Address> addresses = new ArrayList<>();
        Cursor cursor = getAllAddressEntries();  // запрос к БД
        if (cursor.moveToFirst()) {
            do {
                int iId = cursor.getColumnIndex(DatabaseHelper_SQLiteHospital.ADDRESS_COLUMN_ID);
                int iStreet = cursor.getColumnIndex(DatabaseHelper_SQLiteHospital.ADDRESS_COLUMN_STREET);
                int iHouse = cursor.getColumnIndex(DatabaseHelper_SQLiteHospital.ADDRESS_COLUMN_HOUSE);
                int iAppartment = cursor.getColumnIndex(DatabaseHelper_SQLiteHospital.ADDRESS_COLUMN_APPARTMENT);

                long Id = cursor.getLong(iId);
                String Street = cursor.getString(iStreet);
                int House = cursor.getInt(iHouse);
                int Appartment = cursor.getInt(iAppartment);

                // добавление в коллекцию объекта User
                addresses.add(new Address(Id, Street, House, Appartment));
            } while (cursor.moveToNext());
        } // if
        cursor.close();
        return addresses;
    } // getUsers

    // получить одного пользователя по id
    public Address getAddress(long id) throws ParseException {
        Address address = null;
        String query = String.format(
                "SELECT * FROM %s WHERE %s=?",
                DatabaseHelper_SQLiteHospital.TABLE_ADDRESS,
                DatabaseHelper_SQLiteHospital.ADDRESS_COLUMN_ID);
        Cursor cursor = database.rawQuery(query, new String[]{String.valueOf(id)});

        // чтение данных, если они получены
        if (cursor.moveToFirst()) {
            int iId = cursor.getColumnIndex(DatabaseHelper_SQLiteHospital.ADDRESS_COLUMN_ID);
            int iStreet = cursor.getColumnIndex(DatabaseHelper_SQLiteHospital.ADDRESS_COLUMN_STREET);
            int iHouse = cursor.getColumnIndex(DatabaseHelper_SQLiteHospital.ADDRESS_COLUMN_HOUSE);
            int iAppartment = cursor.getColumnIndex(DatabaseHelper_SQLiteHospital.ADDRESS_COLUMN_APPARTMENT);

            long Id = cursor.getLong(iId);
            String Street = cursor.getString(iStreet);
            int House = cursor.getInt(iHouse);
            int Appartment = cursor.getInt(iAppartment);

            // добавление в коллекцию объекта User
            address = new Address(Id, Street, House, Appartment);
        } // if

        cursor.close();
        return address;
    } // getUser

    // метод - оболочка для запроса insert
    public long insertAddress(Address address) {
        // ContentValues - для добавдения данных
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper_SQLiteHospital.ADDRESS_COLUMN_HOUSE, address.getStreet());
        cv.put(DatabaseHelper_SQLiteHospital.ADDRESS_COLUMN_HOUSE, address.getHouse());
        cv.put(DatabaseHelper_SQLiteHospital.ADDRESS_COLUMN_APPARTMENT, address.getAppartment());

        return database.insert(DatabaseHelper_SQLiteHospital.TABLE_ADDRESS, null, cv);
    } // insert

    // метод - оболочка для запроса delete
    public long deleteAddress(long addressId) {

        String whereClause = DatabaseHelper_SQLiteHospital.ADDRESS_COLUMN_ID + " = ?"; // условие удаления
        String[] whereArgs = new String[]{String.valueOf(addressId)}; // параметр для условия
        return database.delete(DatabaseHelper_SQLiteHospital.TABLE_ADDRESS, whereClause, whereArgs);
    } // delete

    // метод - оболочка для запроса update
    public long updateAddress(Address address) {

        String whereClause = DatabaseHelper_SQLiteHospital.ADDRESS_COLUMN_ID + " = " + address.getId();
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper_SQLiteHospital.ADDRESS_COLUMN_HOUSE, address.getStreet());
        cv.put(DatabaseHelper_SQLiteHospital.ADDRESS_COLUMN_HOUSE, address.getHouse());
        cv.put(DatabaseHelper_SQLiteHospital.ADDRESS_COLUMN_APPARTMENT, address.getAppartment());

        return database.update(DatabaseHelper_SQLiteHospital.TABLE_ADDRESS, cv, whereClause, null);
    } // update
    //endregion


} // class DatabaseRepository