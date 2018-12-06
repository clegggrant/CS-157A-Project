package common_util;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.swing.JComboBox;

public class StaticFunc {
	// UTIL/HELPER FUNCTIONS
		public static final JComboBox<String> getDayComboBox(int selectedYear, int monthDate){
			YearMonth yearMonthObj = YearMonth.of(selectedYear, monthDate);
			int numOfDays = yearMonthObj.lengthOfMonth();
			ArrayList daysInMonth = (ArrayList) IntStream.rangeClosed(1, numOfDays)
											.mapToObj(i -> Integer.toString(i))
											.collect(Collectors.toList());
			return new JComboBox<String>((String[]) daysInMonth.toArray(new String[0]));
			
		}
		
		
	
}
