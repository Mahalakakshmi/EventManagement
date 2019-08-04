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
