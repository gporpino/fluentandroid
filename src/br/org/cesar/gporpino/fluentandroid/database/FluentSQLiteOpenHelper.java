package br.org.cesar.gporpino.fluentandroid.database;

import java.util.ArrayList;
import java.util.List;

import br.org.cesar.gporpino.fluentandroid.FluentAndroid;
import br.org.cesar.gporpino.fluentandroid.database.config.Table;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public abstract class FluentSQLiteOpenHelper extends SQLiteOpenHelper {

	private List<Table> mTables;

	protected FluentSQLiteOpenHelper(String name, int version) {
		super(FluentAndroid.getInstance().getContext(), name, null, version);
		mTables = new ArrayList<Table>();

		setupTables(mTables);
	}

	protected abstract void setupTables(List<Table> tables);

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.v(FluentSQLiteOpenHelper.class.getName(),
				"onCreate - begin - Creation tables");
		for (Table table : mTables) {
			db.execSQL(table.creationScript());
		}
		Log.v(FluentSQLiteOpenHelper.class.getName(),
				"onCreate - end - Creation tables");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.v(FluentSQLiteOpenHelper.class.getName(),
				"onUpgrade - begin - Creation tables");
		for (Table table : mTables) {
			db.execSQL(table.deletionScript());
		}
		onCreate(db);
		Log.v(FluentSQLiteOpenHelper.class.getName(),
				"onUpgrade - end - Creation tables");
	}

	@Override
	public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.v(FluentSQLiteOpenHelper.class.getName(),
				"onDowngrade - begin - Creation tables");
		onUpgrade(db, oldVersion, newVersion);
		Log.v(FluentSQLiteOpenHelper.class.getName(),
				"onDowngrade - end - Creation tables");
	}

}
