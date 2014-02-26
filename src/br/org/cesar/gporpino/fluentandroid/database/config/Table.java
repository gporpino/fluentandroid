package br.org.cesar.gporpino.fluentandroid.database.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.util.Log;
import br.org.cesar.gporpino.fluentandroid.FluentUtils;

public class Table {

	
	private String mName;
	private List<Column> mColumns;

	public Table(String name) {
		mName = name;
	}

	public Table addColumn(String name, Type type, Constraint... constraints) {
		if (mColumns == null) {
			mColumns = new ArrayList<Column>();
		}

		Column column = new Column();
		column.setName(name);
		column.setType(type);
		for (int i = 0; i < constraints.length; i++) {
			column.getConstraints().add(constraints[i]);
		}

		mColumns.add(column);
		return this;
	}

	public String creationScript() {

		if (mColumns.size() == 0) {
			String msg = "Table should have any column";
			
			IllegalStateException ex = new IllegalStateException(msg);
			Log.e(Table.class.getName(), msg, ex);
			
			throw ex;
		}

		StringBuilder script = new StringBuilder();

		script.append("CREATE TABLE" + FluentUtils.SEPARATOR + mName);

		configureColumns(script);

		Log.v(Table.class.getName(), script.toString());
		return script.toString();
	}

	private void configureColumns(StringBuilder script) {
		script.append(FluentUtils.SEPARATOR + "(" + FluentUtils.SEPARATOR);
		int count = 0;
		for (Column column : mColumns) {
			script.append(column.getName() + FluentUtils.SEPARATOR + column.getType());

			configureConstraints(script, column);

			count++;

			if (count != mColumns.size()) {
				script.append("," + FluentUtils.SEPARATOR);
			}
		}
		script.append(" ) ");
	}

	private void configureConstraints(StringBuilder script, Column column) {
		Collections.sort(column.getConstraints());

		if (column.getConstraints() != null) {

			for (Constraint constraint : column.getConstraints()) {
				script.append(FluentUtils.SEPARATOR + constraint.description() + FluentUtils.SEPARATOR);
			}

		}
	}

	public String deletionScript() {
		StringBuilder script = new StringBuilder();

		script.append("DROP TABLE IF EXISTS" + FluentUtils.SEPARATOR + mName);
		
		Log.v(Table.class.getName(), script.toString());
		return script.toString();
	}

	private class Column implements Comparable<Type> {
		private String name;
		private Type type;
		private List<Constraint> constraints;

		public Column() {
			constraints = new ArrayList<Constraint>();
		}

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

		public List<Constraint> getConstraints() {
			return constraints;
		}

		@Override
		public int compareTo(Type another) {

			return type.ordinal() - another.ordinal();
		}
	}

}
