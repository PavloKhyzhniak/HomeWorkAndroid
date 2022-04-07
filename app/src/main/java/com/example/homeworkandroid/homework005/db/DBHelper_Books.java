package com.example.homeworkandroid.homework005.db;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;

import com.example.homeworkandroid.homework005.models.Book;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class DBHelper_Books extends SQLiteOpenHelper {


    private static final String tag = "Provider Tester Books";

    protected IReportBack mReportTo;
    protected Context mContext;
    private static String PACKAGE_NAME;
    private static String YOUR_PACKAGE = "com.example.homeworkandroid";
    // путь к базе данных вашего приложения
    private static String DB_PATH;
    private static final String DB_NAME = "books_2.db";
    private SQLiteDatabase dbBooks;
    /**
     * Конструктор
     * Принимает и сохраняет ссылку на переданный контекст для доступа к ресурсам приложения
     */
    @SuppressLint("SdCardPath")
    public DBHelper_Books(Context ctx, IReportBack target) throws IOException {
        super(ctx, DB_NAME, null, 1);
        mReportTo = target;
        mContext = ctx;
        PACKAGE_NAME = ctx.getApplicationContext().getPackageName();
        DB_PATH = "/data/data/"+YOUR_PACKAGE+"/databases/";
//        DB_PATH = ctx.getFilesDir().getPath() + '/' + YOUR_PACKAGE+"/databases/";
        createDataBase();

    }

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
            String myPath = DB_PATH + DB_NAME;
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
        InputStream myInput = mContext.getAssets().open(DB_NAME);

        //Путь ко вновь созданной БД
        String outFileName = DB_PATH + DB_NAME;

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

    public void openDataBase() throws SQLException {
        //открываем БД
        String myPath = DB_PATH + DB_NAME;
        dbBooks = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
    }

    @Override
    public synchronized void close() {
        if(dbBooks != null)
            dbBooks.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    // Здесь можно добавить вспомогательные методы для доступа и получения данных из БД
    // вы можете возвращать курсоры через "return myDataBase.query(....)", это облегчит их использование
    // в создании адаптеров для ваших view

    public void addBook(Book book)
    {
        Log.d(tag,"Adding a book");
        ContentValues cv = new ContentValues();
        cv.put(BookProviderMetaData.BookTableMetaData.BOOK_AUTHOR_ID, book.getAuthor_id());
        cv.put(BookProviderMetaData.BookTableMetaData.BOOK_CATEGORY_ID, book.getCategory_id());
        cv.put(BookProviderMetaData.BookTableMetaData.BOOK_TITLE, book.getTitle());
        cv.put(BookProviderMetaData.BookTableMetaData.BOOK_YEAR, book.getYear());
        cv.put(BookProviderMetaData.BookTableMetaData.BOOK_PRICE, book.getPrice());
        cv.put(BookProviderMetaData.BookTableMetaData.BOOK_AMOUNT, book.getAmount());

        ContentResolver cr = this.mContext.getContentResolver();
        Uri uri = BookProviderMetaData.BookTableMetaData.CONTENT_URI;
        Log.d(tag,"book insert uri:" + uri);
        Uri insertedUri = cr.insert(uri, cv);
        Log.d(tag,"inserted uri:" + insertedUri);
        this.reportString("Inserted Uri:" + insertedUri);
    }
    public void popBook() throws Exception {
        removeBook(getCount());
    }
    public void removeBook(int i) throws Exception {
        if(i>getCount())
            throw new Exception("index out of range");
        ContentResolver cr = this.mContext.getContentResolver();
        Uri uri = BookProviderMetaData.BookTableMetaData.CONTENT_URI;
        Uri delUri = Uri.withAppendedPath(uri, Integer.toString(i));
        reportString("Del Uri:" + delUri);
        cr.delete(delUri, null, null);
        this.reportString("Newcount:" + getCount());
    }
    public List<Book> getBooks()
    {
        Uri uri = BookProviderMetaData.BookTableMetaData.CONTENT_URI;
        Activity a = (Activity)this.mContext;
//        Cursor c = a.getContentResolver().query(uri,
//                null, //projection
//                null, //selection string
//                null, //selection args array of strings
//                null); //sort order
        Cursor c = dbBooks.rawQuery(
                "select * from " + BookProviderMetaData.BookTableMetaData.TABLE_NAME , new String[]{});

        int iID = c.getColumnIndex(BookProviderMetaData.BookTableMetaData.BOOK_ID);
        int iAuthorID = c.getColumnIndex(BookProviderMetaData.BookTableMetaData.BOOK_AUTHOR_ID);
        int iCategoryID = c.getColumnIndex(BookProviderMetaData.BookTableMetaData.BOOK_CATEGORY_ID);
        int iTitle = c.getColumnIndex(BookProviderMetaData.BookTableMetaData.BOOK_TITLE);
        int iYear = c.getColumnIndex(BookProviderMetaData.BookTableMetaData.BOOK_YEAR);
        int iPrice = c.getColumnIndex(BookProviderMetaData.BookTableMetaData.BOOK_PRICE);
        int iAmount = c.getColumnIndex(BookProviderMetaData.BookTableMetaData.BOOK_AMOUNT);

        List<Book> list = new ArrayList<>();


        //walk through the rows based on indexes
        for(c.moveToFirst();!c.isAfterLast();c.moveToNext()) {

            //Gather values
            String id = c.getString(iID);
            String author = c.getString(iAuthorID);
            String category = c.getString(iCategoryID);
            String title = c.getString(iTitle);
            String year = c.getString(iYear);
            String price = c.getString(iPrice);
            String amount = c.getString(iAmount);

            list.add(new Book(
                    Integer.parseInt(id)
                    , Integer.parseInt(author)
                    , Integer.parseInt(category)
                    , title
                    , Integer.parseInt(year)
                    , (int)(Double.parseDouble(price))
                    , Integer.parseInt(amount)));
        }

        //Close the cursor
        //ideally this should be done in
        //a finally block.
        c.close();

        return  list;
    }
    private int getCount()
    {
        Uri uri = BookProviderMetaData.BookTableMetaData.CONTENT_URI;
        Activity a = (Activity)this.mContext;
//        Cursor c = a.managedQuery(uri,
//                null, //projection
//                null, //selection string
//                null, //selection args array of strings
//                null); //sort order
        Cursor c = dbBooks.rawQuery(
                "select * from " + BookProviderMetaData.BookTableMetaData.TABLE_NAME , new String[]{});

        int numberOfRecords = c.getCount();
        c.close();
        return numberOfRecords;
    }
    private void report(int id)
    {
        this.mReportTo.reportBack(tag,this.mContext.getString(id));
    }
    private void reportString(String s)
    {
        this.mReportTo.reportBack(tag,s);
    }
    private void reportString(String s, int id)
    {
        this.mReportTo.reportBack(tag,s);
        report(id);
    }
}