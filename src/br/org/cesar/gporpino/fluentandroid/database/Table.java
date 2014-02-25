package br.org.cesar.gporpino.fluentandroid.database;

import java.util.HashMap;
import java.util.Map.Entry;

public class Table {

	private String mName;
	private HashMap<String, Type> mColumns;
	private String mPrimaryKeyName;

	public Table(String name) {
		mName = name;
	}

	public Table addPrimaryKey(String name, Type type) {
		if (mPrimaryKeyName != null){
			throw new IllegalArgumentException("A primary key already set. Primary Key should be unique.");
		}
		mPrimaryKeyName = name;

		addColumn(name, type);
		return this;
	}

	public Table addColumn(String name, Type type) {
		if (mColumns == null) {
			mColumns = new HashMap<String, Type>();
		}

		mColumns.put(name, type);
		return this;
	}

	public String creationScript() {

		StringBuilder script = new StringBuilder();

		script.append("CREATE TABLE " + mName);

		script.append(" ( ");
		int count = 0;
		for (Entry<String, Type> entry : mColumns.entrySet()) {
			script.append(entry.getKey() + " " + entry.getValue());
			
			if (entry.getKey().equals(mPrimaryKeyName)){
				script.append(" PRIMARY KEY ");
			}
			
			count++;
			
			if (count != mColumns.size()) {
				script.append(" ,");
			}
		}
		script.append(" ) ");

		return script.toString();
	}
	
	public String deletionScript(){
		StringBuilder script = new StringBuilder();

		script.append("DROP TABLE IF EXISTS " + mName);
		return script.toString();
	}

}
