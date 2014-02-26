package br.org.cesar.gporpino.fluentandroid.database.direct;

import android.database.Cursor;
import android.util.Log;
import br.org.cesar.gporpino.fluentandroid.FluentAndroid;
import br.org.cesar.gporpino.fluentandroid.FluentUtils;
import br.org.cesar.gporpino.fluentandroid.database.FluentCursor;
import br.org.cesar.gporpino.fluentandroid.database.FluentSQLiteOpenHelper;

public class Select {

	private String[] mProjection;
	private String mTableName;
	private FluentSQLiteOpenHelper mDatabaseHelper;
	private String mSelection;
	private String[] mSelectionArgs;
	private String mGroupBy;
	private String mHaving;
	private String mOrderBy;

	public Select(String... projection) {
		mProjection = projection;
		mDatabaseHelper = FluentAndroid.getInstance().getDatabaseHelper();
	}

	public Select from(String tableName) {
		mTableName = tableName;
		return this;
	}
	
	public Select where(String selection, String...selectionArgs){
		mSelection = selection;
		mSelectionArgs = selectionArgs;
		
		return this;
	}
	
	public Select groupBy(String...columnNames){
		mGroupBy = FluentUtils.join(columnNames, ",");
		
		return this;
	}
	
	public Select having(String condition, String...havingArgs){
		
		mHaving = condition;
		for (String arg : havingArgs) {
			mHaving = condition.replaceFirst("/?", arg);
		}
		
		return this;
	}
	
	public Select orderBy(String...columnNames){
		mOrderBy = FluentUtils.join(columnNames, ",");
		
		return this;
	}

	

	public FluentCursor execute() {
		if (mTableName == null){
			throw new IllegalStateException("Before call execute the method from should be called.");
		}
		try {
			
			Log.v(Select.class.getName(), "Select from " + mTableName);
			Cursor cursor = mDatabaseHelper.getReadableDatabase().query(mTableName, mProjection, mSelection, mSelectionArgs, mGroupBy,
					mHaving, mOrderBy);

			if (cursor != null) {
				return new FluentCursor(cursor);
			}
		} finally {
			Log.v(Select.class.getName(), "Closing database");
			mDatabaseHelper.getReadableDatabase().close();
		}
		return null;
	}

}
