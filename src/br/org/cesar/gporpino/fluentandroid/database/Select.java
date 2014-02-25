package br.org.cesar.gporpino.fluentandroid.database;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import br.org.cesar.gporpino.fluentandroid.FluentAndroid;

public class Select {

	private String[] mProjection;
	private Context mContext;
	private Uri mUri;

	public Select(String... projection) {
		mProjection = projection;
		mContext = FluentAndroid.getInstance().getContext();
	} 
	
	public Select from(Uri uri){
		mUri = uri;
		return this;
	}
	
	public FluentCursor execute(){
		Cursor cursor = mContext.getContentResolver().query(mUri, mProjection, null, null, null);
		if (cursor != null){
			return new FluentCursor(cursor);
		}
		return null;
	}
	
}
