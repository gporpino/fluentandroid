package br.org.cesar.gporpino.fluentandroid.database.direct;

import android.content.ContentValues;
import br.org.cesar.gporpino.fluentandroid.FluentAndroid;
import br.org.cesar.gporpino.fluentandroid.database.FluentSQLiteOpenHelper;

public class Insert {

	private String mTableName;
	private FluentSQLiteOpenHelper mDatabaseHelper;
	private ContentValues mValues;

	public Insert() {
		mDatabaseHelper = FluentAndroid.getInstance().getDatabaseHelper();
		mValues = new ContentValues();
	}

	public Insert into(String tableName) {
		mTableName = tableName;
		return this;
	}

	public long execute() {
		return mDatabaseHelper.getWritableDatabase().insert(mTableName, null,
				mValues);
	}

	public Insert value(String key, Boolean value) {
		mValues.put(key, value);
		return this;
	}

	public Insert value(String key, Long value) {
		mValues.put(key, value);
		return this;
	}

	public Insert value(String key, Integer value) {
		mValues.put(key, value);
		return this;
	}

	public Insert value(String key, String value) {
		mValues.put(key, value);
		return this;
	}

	public Insert value(String key, byte[] value) {
		mValues.put(key, value);
		return this;
	}

	public Insert value(String key, Short value) {
		mValues.put(key, value);
		return this;
	}

	public Insert value(String key, Double value) {
		mValues.put(key, value);
		return this;
	}

	public Insert value(String key, Float value) {
		mValues.put(key, value);
		return this;
	}

	public Insert values(ContentValues values) {
		mValues = values;
		return this;
	}

}
