package Technical;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.*;

public class DeloitteCaseStudy {

	private static final String fileName = "D:\\Deloitte\\act.txt";
	static int teamNumber = 1;
	private
	
	static List<String> actlist = new ArrayList<String>();

	public String getFileName(String fileName) {
		return fileName;

	}

	public static void main(String[] args) throws IOException {
		
		// creating multiple instances of a DeloitteCaseStudy class

		Object[] CreateInstances  = new Object[5]; 
		for(Object i:CreateInstances){
			i= new DeloitteCaseStudy();
			((DeloitteCaseStudy) i).TeamsAccess((DeloitteCaseStudy)i);
		}
		
		
		
		

	}

	// Method to check whether file is not null,empty and not too large to read
	public boolean FindFilesize(String fileName1) {
		int sizeInbytes = 5000;
		File file = new File(fileName1);
		long filesize = file.length();
		// System.out.println("filesize = "+filesize+"bytes");
		if (filesize < sizeInbytes) {
			return true;
		}

		return false;

	}

	// method to prepare the list of activities

	public List<String> ActivityList(String fileName2, boolean fileIsNotEmpty)
			throws IOException {

		try (Stream<String> stream = Files.lines(Paths.get(fileName2))) {

			actlist = stream
					.map(line -> line.replaceAll("[^a-zA-Z0-9' ']+", ""))
					// removing any special character if any.
					.flatMap(
							string -> Stream.of(string.split("min |\\*sprint")))
					.collect(Collectors.toList());
		}

		return actlist;
	}

	// method to give activity Schedule for team

	public void ActivitySCheduleForTeam(List<String> list) {

		String str2;
		char c1;
		char c2;
		String time;
		int activityduration = 0;
		int staffSpeech = 415;
		int breakflunch = 150;// after 11.30
		int cnt = 1;

		// System.out.println(list.size());
		// System.out.println(list);

		Collections.shuffle(list);
		// System.out.println(list);

		// For thread safety ArrayList is converted into CopyOnWriteArrayList
		CopyOnWriteArrayList<String> cw = new CopyOnWriteArrayList<String>(list);

		Object[] activityArr = cw.toArray();

		for (Object obj : activityArr) {

			String str1 = obj.toString();
			int n = str1.length();

			// preparing list with min for activites

			if (Character.isDigit(str1.charAt(n - 1))
					&& Character.isDigit(str1.charAt(n - 2)) == true) {

				c1 = str1.charAt(n - 1);
				c2 = str1.charAt(n - 2);
				str2 = Character.toString(c2) + Character.toString(c1);
				str1 = str1 + "min";
			} else {
				str2 = "15";
			}

			/*
			 * Activity list with min and sprint timing
			 * System.out.println(str1+"    "+str2);
			 */

			// Adding schedule timing am and pm

			LocalTime t1 = LocalTime.of(9, 0);
			LocalTime t2;

			if (cnt == 1) {
				t2 = t1;
				cnt++;
			}

			else {

				t2 = t1.plusMinutes(activityduration);

				if (activityduration >= breakflunch && cnt == 2) {

					cnt++;
					str1 = "Lunch Break 60min";
				}

			}

			// Finishing activity with Staff motivational speech

			DateTimeFormatter dateTimeFormatter = DateTimeFormatter
					.ofPattern("hh:mm a");
			time = t2.format(dateTimeFormatter);
			if (activityduration > staffSpeech) {
				System.out.println(time + ":" + "Staff movtivational speech");
				break;
			}

			System.out.println(time + ":" + str1);

			if (str1 == "Lunch Break 60min") {
				activityduration = activityduration + 60;

			}

			else {
				activityduration = activityduration + Integer.parseInt(str2);
			}

			c1 = '\u0000';
			c2 = '\u0000';
			str2 = null;

		}
		int teamlimit = 20;
		if (teamNumber < teamlimit) {
			++teamNumber;
		}
	}
	
	public void TeamsAccess(DeloitteCaseStudy team) throws IOException {

		boolean fileIsNotEmpty = team.FindFilesize(fileName);
		// System.out.println("Is the given file not empty?   " +
		// fileIsNotEmpty);
		team.ActivityList(fileName, fileIsNotEmpty);
		// System.out.println(team1.ActivityList(fileName, fileIsNotEmpty));
		System.out.println("Team : " + teamNumber);
		team.ActivitySCheduleForTeam(actlist);
		System.out.println("******************");

	}

}


/*  output

Team : 1
09:00 AM:2wheeled Segways 45min
09:45 AM:Salsa  Pickles sprint 
10:00 AM:Duck Herding 60min
11:00 AM:Learning Magic Tricks 40min
11:40 AM:Lunch Break 60min
12:40 PM:Archery 45min
01:25 PM:New Zealand Haka 30min
01:55 PM:Cricket 2020 60min
02:55 PM:Viking Axe Throwing 60min
03:55 PM:Giant Digital Graffiti 60min
04:55 PM:Staff movtivational speech
******************
Team : 2
09:00 AM:Giant Digital Graffiti 60min
10:00 AM:Giant Puzzle Dinosaurs 30min
10:30 AM:Laser Clay Shooting 60min
11:30 AM:Lunch Break 60min
12:30 PM:Digital Tresure Hunt 60min
01:30 PM:Arduino Bonanza 30min
02:00 PM:Salsa  Pickles sprint 
02:15 PM:2wheeled Segways 45min
03:00 PM:Cricket 2020 60min
04:00 PM:Staff movtivational speech
******************
Team : 3
09:00 AM:Indiano Drizzle 45min
09:45 AM:Archery 45min
10:30 AM:Duck Herding 60min
11:30 AM:Lunch Break 60min
12:30 PM:Human Table Football 30min
01:00 PM:Arduino Bonanza 30min
01:30 PM:Cricket 2020 60min
02:30 PM:Giant Digital Graffiti 60min
03:30 PM:Salsa  Pickles sprint 
03:45 PM:Wine Tasting sprint 
04:00 PM:Staff movtivational speech
******************
Team : 4
09:00 AM:Laser Clay Shooting 60min
10:00 AM:Time Tracker sprint 
10:15 AM:Cricket 2020 60min
11:15 AM:Learning Magic Tricks 40min
11:55 AM:Lunch Break 60min
12:55 PM:Digital Tresure Hunt 60min
01:55 PM:Salsa  Pickles sprint 
02:10 PM:Indiano Drizzle 45min
02:55 PM:Human Table Football 30min
03:25 PM:2wheeled Segways 45min
04:10 PM:Staff movtivational speech
******************
Team : 5
09:00 AM:Wine Tasting sprint 
09:15 AM:Time Tracker sprint 
09:30 AM:Buggy Driving 30min
10:00 AM:Giant Digital Graffiti 60min
11:00 AM:Duck Herding 60min
12:00 PM:Lunch Break 60min
01:00 PM:Cricket 2020 60min
02:00 PM:Arduino Bonanza 30min
02:30 PM:Human Table Football 30min
03:00 PM:New Zealand Haka 30min
03:30 PM:Giant Puzzle Dinosaurs 30min
04:00 PM:Staff movtivational speech
******************

*/
