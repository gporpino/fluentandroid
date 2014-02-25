package br.org.cesar.gporpino.fluentandroid.database.config;

import java.util.ArrayList;
import java.util.List;

public class Table {

	private String mName;
	private List<Column> mColumns;

	public Table(String name) {
		mName = name;
	}

//	public Table addPrimaryKey(String name, Type type) {
//		if (mPrimaryKeyName != null){
//			throw new IllegalArgumentException("A primary key already set. Primary Key should be unique.");
//		}
//		mPrimaryKeyName = name;
//
//		addColumn(name, type);
//		return this;
//	}
	
	public Table addColumn(String name, Type type, Constraint... constraints  ) {
		if (mColumns == null) {
			mColumns = new ArrayList<Column>();
		}
		
		Column column = new Column();
		column.setName(name);
		column.setType(type);
		column.setConstraints(constraints);

		mColumns.add(column);
		return this;
	}

	public String creationScript() {
		
		if(mColumns.size() == 0){
			throw new IllegalStateException("Table should have any column");
		}

		StringBuilder script = new StringBuilder();

		script.append("CREATE TABLE " + mName);

		script.append(" ( ");
		int count = 0;
		for (Column column : mColumns) {
			script.append(column.getName() + " " + column.getType());
			
			configureConstraints(script, column);
			
			count++;
			
			if (count != mColumns.size()) {
				script.append(", ");
			}
		}
		script.append(" ) ");

		return script.toString();
	}
	
	private void configureConstraints(StringBuilder script, Column column) {
		if (column.getConstraints() != null){
			
			for (int i = 0; i < column.getConstraints().length; i++) {
				Constraint constraint = column.getConstraints()[i];
				script.append(" " + constraint.description() + " ");
			}
		
		}
	}

	public String deletionScript(){
		StringBuilder script = new StringBuilder();

		script.append("DROP TABLE IF EXISTS " + mName);
		return script.toString();
	}

	private class Column {
		private String name;
		private Type type;
		private Constraint[] constraints;
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Type getType() {
			return type;
		}
		public void setType(Type type) {
			this.type = type;
		}
		public Constraint[] getConstraints() {
			return constraints;
		}
		public void setConstraints(Constraint[] constraints) {
			this.constraints = constraints;
		}
	}
	
}
