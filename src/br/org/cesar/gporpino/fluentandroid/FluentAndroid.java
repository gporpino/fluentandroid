package br.org.cesar.gporpino.fluentandroid;

import android.content.Context;

public class FluentAndroid {
	private static FluentAndroid instance;
	private Context mContext;
	
	public static FluentAndroid getInstance(){
		if (instance == null){
			instance = new FluentAndroid();
		}
		return instance;
	}
	
	private FluentAndroid(){
		
	}
	
	public void init(Context context){
		mContext = context;
	}
	
	public Context getContext(){
		return mContext;
	}
}

