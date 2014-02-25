package br.org.cesar.gporpino.fluentandroid.content;

import br.org.cesar.gporpino.fluentandroid.FluentAndroid;
import br.org.cesar.gporpino.fluentandroid.database.FluentCursor;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

public class ContentSelect {

	private String[] mProjection;
	private Context mContext;
	private Uri mUri;

	public ContentSelect(String... projection) {
		mProjection = projection;
		mContext = FluentAndroid.getInstance().getContext();
	} 
	
	public ContentSelect from(Uri uri){
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
