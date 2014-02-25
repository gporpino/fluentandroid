package br.org.cesar.gporpino.fluentandroid.database.direct;

import android.database.Cursor;
import br.org.cesar.gporpino.fluentandroid.FluentAndroid;
import br.org.cesar.gporpino.fluentandroid.database.FluentCursor;
import br.org.cesar.gporpino.fluentandroid.database.FluentSQLiteOpenHelper;

public class Select {

	private String[] mProjection;
	private String mTableName;
	private FluentSQLiteOpenHelper mDatabaseHelper;

	public Select(String... projection) {
		mProjection = projection;
		mDatabaseHelper = FluentAndroid.getInstance().getDatabaseHelper();
	}

	public Select from(String tableName) {
		mTableName = tableName;
		return this;
	}

	public FluentCursor execute() {
		if (mTableName == null){
			throw new IllegalStateException("Before call execute the method from should be called.");
		}
		try {
			Cursor cursor = mDatabaseHelper.getReadableDatabase().query(mTableName, mProjection, null, null, null,
					null, null);

			if (cursor != null) {
				return new FluentCursor(cursor);
			}
		} finally {
			mDatabaseHelper.getReadableDatabase().close();
		}
		return null;
	}

}
