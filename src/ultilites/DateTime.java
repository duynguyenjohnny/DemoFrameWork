package ultilites;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateTime {
	
	public static String getCurrentTime(String timeFormat){
		return new SimpleDateFormat(timeFormat).format(Calendar.getInstance().getTime());
	}
}
