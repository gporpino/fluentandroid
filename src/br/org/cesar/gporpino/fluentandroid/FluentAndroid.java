package br.org.cesar.gporpino.fluentandroid;

import br.org.cesar.gporpino.fluentandroid.database.FluentSQLiteOpenHelper;
import android.content.Context;

public class FluentAndroid {
	private static FluentAndroid instance;
	private Context mContext;
	private FluentSQLiteOpenHelper mDatabaseHelper;
	
	public static FluentAndroid getInstance(){
		if (instance == null){
			instance = new FluentAndroid();
		}
		return instance;
	}
	
	private FluentAndroid(){
		
	}
	
	public FluentAndroid init(Context context){
		mContext = context;
		
		return instance;
	}
	

	public FluentAndroid configureDatabase(FluentSQLiteOpenHelper helper){
		
		mDatabaseHelper = helper;
		
		return instance;
	}
	
	public FluentSQLiteOpenHelper getDatabaseHelper() {
		return mDatabaseHelper;
	}

	public Context getContext(){
		return mContext;
	}
}

