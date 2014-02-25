package br.org.cesar.gporpino.fluentandroid.database.config;

public enum Constraint {
	PRIMARY_KEY("PRIMARY KEY"), NOT_NULL("NOT NULL"), UNIQUE("UNIQUE");
	
	private String mDescription;

	private Constraint(String description) {
		mDescription = description;
	}
	
	public String description() {
		return mDescription;
	}
}
