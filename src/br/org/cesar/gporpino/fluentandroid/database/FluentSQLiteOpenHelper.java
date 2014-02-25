package br.org.cesar.gporpino.fluentandroid.database;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public abstract class FluentSQLiteOpenHelper extends SQLiteOpenHelper{
	
	private List<Table> mTables;

	public FluentSQLiteOpenHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		mTables = new ArrayList<Table>();
		
		configureTables(mTables);
	}
	
	
	protected abstract void configureTables(List<Table> tables);

	@Override
	public void onCreate(SQLiteDatabase db) {
		for (Table table: mTables){
			db.execSQL(table.creationScript());
		}
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		for (Table table: mTables){
			db.execSQL(table.deletionScript());
		}
		onCreate(db);
	}
	
	@Override
	public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		onUpgrade(db, oldVersion, newVersion); 
		
	}

}
