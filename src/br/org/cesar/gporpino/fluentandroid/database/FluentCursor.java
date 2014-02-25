package br.org.cesar.gporpino.fluentandroid.database;

import android.database.Cursor;

public class FluentCursor {

	private Cursor mCursor;

	public FluentCursor(Cursor cursor) {
		mCursor = cursor;
		if (mCursor != null){
			mCursor.moveToFirst();
		}
	}

	public String getString(String columnName) {

		return mCursor.getString(mCursor.getColumnIndex(columnName));
	}

	public int getInt(String columnName) {

		return mCursor.getInt(mCursor.getColumnIndex(columnName));
	}

	public double getDouble(String columnName) {

		return mCursor.getDouble(mCursor.getColumnIndex(columnName));
	}

	public double getFloat(String columnName) {

		return mCursor.getFloat(mCursor.getColumnIndex(columnName));
	}
	
	public short getShort(String columnName) {

		return mCursor.getShort(mCursor.getColumnIndex(columnName));
	}
	
	public byte[] getBlob(String columnName) {

		return mCursor.getBlob(mCursor.getColumnIndex(columnName));
	}
	
	public int getCount(){
		return mCursor.getCount();
	}

	public void moveToNext(){
		mCursor.moveToNext();
	}
	
	public Cursor getCursor(){
		return mCursor;
	}
	
}
