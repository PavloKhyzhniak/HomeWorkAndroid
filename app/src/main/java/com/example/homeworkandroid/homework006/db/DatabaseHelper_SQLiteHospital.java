package com.example.homeworkandroid.homework006.db;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

// создание БД, заполнение таблиц
public class DatabaseHelper_SQLiteHospital extends SQLiteOpenHelper {

    protected Context mContext;

    private static String PACKAGE_NAME;
    private static String YOUR_PACKAGE = "com.example.homeworkandroid";
    // путь к базе данных вашего приложения
    private static String DB_PATH;
    private static final String DATABASE_NAME = "SQLite_Hospital.db";

    private static final int SCHEMA = 1; // версия базы данных

    // название таблицы в БД
    public static final String TABLE_USER = "User";

    // названия столбцов
    public static final String USER_COLUMN_ID = "Id";
    public static final String USER_COLUMN_FIRSTNAME = "FirstName";
    public static final String USER_COLUMN_SECONDNAME = "SecondName";
    public static final String USER_COLUMN_LASTNAME = "LastName";
    public static final String USER_COLUMN_BIRTHDATE = "BirthDate";
    public static final String USER_COLUMN_ADDRESSID = "AddressId";

    // название таблицы в БД
    public static final String TABLE_ADDRESS = "Address";

    // названия столбцов
    public static final String ADDRESS_COLUMN_ID = "Id";
    public static final String ADDRESS_COLUMN_STREET = "Street";
    public static final String ADDRESS_COLUMN_HOUSE = "House";
    public static final String ADDRESS_COLUMN_APPARTMENT = "Appartment";

    // название таблицы в БД
    public static final String TABLE_PATIENT = "Patient";

    // названия столбцов
    public static final String PATIENT_COLUMN_ID = "Id";
    public static final String PATIENT_COLUMN_USERID = "UserId";

    // название таблицы в БД
    public static final String TABLE_DOCTORS = "Doctors";

    // названия столбцов
    public static final String DOCTORS_COLUMN_ID = "Id";
    public static final String DOCTORS_COLUMN_SPECIALIZATION = "Specialization";
    public static final String DOCTORS_COLUMN_USERID = "UserId";
    public static final String DOCTORS_COLUMN_RATE = "Rate";

    // название таблицы в БД
    public static final String TABLE_VISIT = "Visit";

    // названия столбцов
    public static final String VISIT_COLUMN_ID = "Id";
    public static final String VISIT_COLUMN_DOCTORID = "DoctorId";
    public static final String VISIT_COLUMN_PATIENTID = "PatientId";
    public static final String VISIT_COLUMN_PRICE = "Price";
    public static final String VISIT_COLUMN_DATE = "Date";

    // создание БД
    public DatabaseHelper_SQLiteHospital(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA);

        mContext = context;
        PACKAGE_NAME = mContext.getApplicationContext().getPackageName();
        DB_PATH = "/data/data/"+YOUR_PACKAGE+"/databases/";
//        DB_PATH = mContext.getFilesDir().getPath() + '/' + YOUR_PACKAGE+"/databases/";
        createDataBase();
    }

    // при подключении - создаем таблицу/таблицы если ее нет
    // заполненение таблиц
    @Override
    public void onCreate(SQLiteDatabase db) {

//        db.execSQL("CREATE TABLE users (" + COLUMN_ID
//                + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_NAME
//                + " TEXT, " + COLUMN_YEAR + " INTEGER);");
//
//        // добавление начальных данных
//        db.execSQL("INSERT INTO "+ TABLE +" (" + COLUMN_NAME
//                + ", " + COLUMN_YEAR  + ") VALUES ('Том Смит', 1981);");
//		db.execSQL("INSERT INTO "+ TABLE +" (" + COLUMN_NAME
//                + ", " + COLUMN_YEAR  + ") VALUES ('Саша Васин', 1998);");
//		db.execSQL("INSERT INTO "+ TABLE +" (" + COLUMN_NAME
//                + ", " + COLUMN_YEAR  + ") VALUES ('Даша Иванова', 1991);");
//		db.execSQL("INSERT INTO "+ TABLE +" (" + COLUMN_NAME
//                + ", " + COLUMN_YEAR  + ") VALUES ('Игорь Киров', 1987);");
//		db.execSQL("INSERT INTO "+ TABLE +" (" + COLUMN_NAME
//                + ", " + COLUMN_YEAR  + ") VALUES ('Валя Никитина', 1985);");
    } // onCreate

    // при передаче новой версии БД - пересоздать таблицы
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,  int newVersion) {
//        db.execSQL("DROP TABLE IF EXISTS "+TABLE);
//        onCreate(db);
    } // onUpgrade

    /**
     * Создает пустую базу данных и перезаписывает ее нашей собственной базой
     * */
    public void createDataBase() {
        boolean dbExist = checkDataBase();

        if(dbExist){
            //ничего не делать - база уже есть
        }else{
            //вызывая этот метод создаем пустую базу, позже она будет перезаписана
            this.getReadableDatabase();

            try {
                copyDataBase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    /**
     * Проверяет, существует ли уже эта база, чтобы не копировать каждый раз при запуске приложения
     * @return true если существует, false если не существует
     */
    private boolean checkDataBase(){
        SQLiteDatabase checkDB = null;

        try{
            String myPath = DB_PATH + DATABASE_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        }catch(SQLiteException e){
            //база еще не существует
        }
        if(checkDB != null){
            checkDB.close();
        }
        return checkDB != null;
    }

    /**
     * Копирует базу из папки assets заместо созданной локальной БД
     * Выполняется путем копирования потока байтов.
     * */
    private void copyDataBase() throws IOException{
        //Открываем локальную БД как входящий поток
        InputStream myInput = mContext.getAssets().open(DATABASE_NAME);

        //Путь ко вновь созданной БД
        String outFileName = DB_PATH + DATABASE_NAME;

        //Открываем пустую базу данных как исходящий поток
        OutputStream myOutput = new FileOutputStream(outFileName);

        //перемещаем байты из входящего файла в исходящий
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer))>0){
            myOutput.write(buffer, 0, length);
        }

        //закрываем потоки
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    public SQLiteDatabase openDataBase() throws SQLException {
        //открываем БД
        String myPath = DB_PATH + DATABASE_NAME;
        return SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
    }

} // class DatabaseHelper