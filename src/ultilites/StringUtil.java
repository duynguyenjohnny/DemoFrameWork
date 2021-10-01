package ultilites;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
	public static int getNumber(String text){
		Pattern p = Pattern.compile("(\\d+)");
		Matcher m = p.matcher(text);
		if(m.find()){
			return Integer.parseInt(m.group());
		}else {
			return 0;
		}
	}
	
	public static int getPageSize(String text){
		text = text.replace(",", "");
//		System.out.println("String text: " + text);
		Pattern p = Pattern.compile("\\d+");
		Matcher m = p.matcher(text);
//		System.out.println(" p:" +p);
//		System.out.println(" m:" +m);
		ArrayList<String> matches = new ArrayList<String>();		
		while (m.find()) {
			matches.add(m.group());			
		}
//		System.out.println("Matches: "+ matches);
		if(matches.size() == 3){			
			float i = (float)Integer.parseInt(matches.get(2))/Integer.parseInt(matches.get(1));			
			return (int) Math.ceil(i);
		}		
		return 0;
	}

	public static String getDateFromText(String all_text) {
		String[] audiotext = all_text.split("\n");
		System.out.println(audiotext[audiotext.length-1]);
		DateTime currentdate = DateTime.now();
		DateTimeFormatter fmt = DateTimeFormat.forPattern("dd MMM yyyy");
		String formatted_currentdate = fmt.print(currentdate);
		return formatted_currentdate;
	}

	public static String getLastLine(String all_text) {
		String[] audiotext = all_text.split("\n");
		System.out.println(audiotext[audiotext.length-1]);
		String lastitem = audiotext[audiotext.length-1];
		return lastitem;
	}
	public static int getNumAtIndex(String text, int index){
		Pattern p = Pattern.compile("\\d+");
		Matcher m = p.matcher(text);
		ArrayList<String> matches = new ArrayList<String>();
		while (m.find()) {
			matches.add(m.group());
		}
		if(matches.size() > index){
			return Integer.parseInt(matches.get(index));
		}
		return 0;
	}
}

