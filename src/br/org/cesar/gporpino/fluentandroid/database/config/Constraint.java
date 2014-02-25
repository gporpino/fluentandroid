package br.org.cesar.gporpino.fluentandroid.database.config;

public enum Constraint {
	PRIMARY_KEY("PRIMARY KEY"), AUTO_INCREMENT("AUTOINCREMENT"), UNIQUE("UNIQUE"), NOT_NULL("NOT NULL"), ;
	
	private String mDescription;

	private Constraint(String description) {
		mDescription = description;
	}
	
	public String description() {
		return mDescription;
	}
}
