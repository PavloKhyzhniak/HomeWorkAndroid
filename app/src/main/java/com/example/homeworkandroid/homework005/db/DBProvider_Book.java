package com.example.homeworkandroid.homework005.db;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.example.homeworkandroid.homework005.models.Book;

import java.util.ArrayList;
import java.util.List;

public class DBProvider_Book extends BaseTester
{
	private static String tag = "Provider Tester";
	public DBProvider_Book(Context ctx, IReportBack target)
	{
		super(ctx, target);
	}
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
        Cursor c = a.getContentResolver().query(uri,
                null, //projection
                null, //selection string
                null, //selection args array of strings
                null); //sort order
//		Cursor c = dbBooks.rawQuery(
//				"select * from " + BookProviderMetaData.BookTableMetaData.TABLE_NAME , new String[]{});

		int iID = c.getColumnIndex(BookProviderMetaData.BookTableMetaData.BOOK_ID);
		int iAuthorID = c.getColumnIndex(BookProviderMetaData.BookTableMetaData.BOOK_AUTHOR_ID);
		int iCategoryID = c.getColumnIndex(BookProviderMetaData.BookTableMetaData.BOOK_CATEGORY_ID);
		int iTitle = c.getColumnIndex(BookProviderMetaData.BookTableMetaData.BOOK_TITLE);
		int iYear = c.getColumnIndex(BookProviderMetaData.BookTableMetaData.BOOK_YEAR);
		int iPrice = c.getColumnIndex(BookProviderMetaData.BookTableMetaData.BOOK_PRICE);
		int iAmount = c.getColumnIndex(BookProviderMetaData.BookTableMetaData.BOOK_AMOUNT);

		List<Book> list = new ArrayList<Book>();

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
					, (int)Double.parseDouble(price)
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
        Cursor c = a.managedQuery(uri,
                null, //projection
                null, //selection string
                null, //selection args array of strings
                null); //sort order
//		Cursor c = dbBooks.rawQuery(
//				"select * from " + BookProviderMetaData.BookTableMetaData.TABLE_NAME , new String[]{});

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
