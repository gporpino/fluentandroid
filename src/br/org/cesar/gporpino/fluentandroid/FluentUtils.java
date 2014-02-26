package br.org.cesar.gporpino.fluentandroid;

public class FluentUtils {
	
	public static final String SEPARATOR = " ";

	public static String join(String[] strings, String joinWith) {
		StringBuilder join = new StringBuilder();
		int count = 0;
		
		for (String string : strings) {
			join.append(string);
			count++;
			if (count != strings.length){
				join.append(joinWith + SEPARATOR);
			}
		}
		return join.toString();
	}
	
}
