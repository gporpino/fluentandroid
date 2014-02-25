package br.org.cesar.gporpino.fluentandroid.database;

import android.database.Cursor;

public class CursorEntry {
	private Cursor mCursor;
	private int mLinePosition;
	
	CursorEntry(Cursor cursor, int linePosition){
		mCursor = cursor;
		mLinePosition = linePosition;
	}
	
	public String getString(String columnName) {
		mCursor.moveToPosition(mLinePosition);
		return mCursor.getString(mCursor.getColumnIndex(columnName));
	}

	public int getInt(String columnName) {
		mCursor.moveToPosition(mLinePosition);
		return mCursor.getInt(mCursor.getColumnIndex(columnName));
	}
	
	public long getLong(String columnName) {
		mCursor.moveToPosition(mLinePosition);
		return mCursor.getLong(mCursor.getColumnIndex(columnName));
	}

	public double getDouble(String columnName) {
		mCursor.moveToPosition(mLinePosition);
		return mCursor.getDouble(mCursor.getColumnIndex(columnName));
	}

	public double getFloat(String columnName) {
		mCursor.moveToPosition(mLinePosition);
		return mCursor.getFloat(mCursor.getColumnIndex(columnName));
	}
	
	public short getShort(String columnName) {
		mCursor.moveToPosition(mLinePosition);
		return mCursor.getShort(mCursor.getColumnIndex(columnName));
	}
	
	public byte[] getBlob(String columnName) {
		mCursor.moveToPosition(mLinePosition);
		return mCursor.getBlob(mCursor.getColumnIndex(columnName));
	}
}
