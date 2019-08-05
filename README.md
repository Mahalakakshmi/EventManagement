# EventManagement
Organizing various entertainment activities to many teams of employees 
  
  Programming Language: Java 8
  Development Tool :  Eclipse IDE
  
  
  Description:
    The employees will be divided into various teams and each team will be performing various activities.
	Activities start from 9am until lunch is served at 12 noon 
	Activities resume from 1pm and must finish in time for a presentation to be delivered by external speaker on “Staff Motivation"
	The presentation can start no earlier than 4:00 and no later than 5:00
	All activity lengths are either in minutes (not hours) or sprint (15 minutes) 
	Due to high spirit of the team there needs to be no gap between each activity.
	
	The features of this EventManagement Application:
	
	The text file is verified for any not empty null and limited size of file. 
	Text file is converted into Stream of String by removing any special characters if exists. 
	The arraylist is prepared by spliting the text based min time and sprint.
	The  ActivitySCheduleForTeam method takes the arraylist and performs sequence of operations and returns activity schedule for teams.
	This is scalable program produces activity schedule for 10000 teams in few seconds.
	
    How to run this application?
	        If this program is run on IDE the output will be produced on the console.
			To apply this application for 10000 teams and get output as text file
 In eclipse right click on the DeloitteCaseStudy.java file run as run configurations in which select Common tab.
 Create a empty output.txt give its path on File tab,Apply and Run.
 For 10000 teams activit schedule will be produced on output.txt. The same result is attached as output.txt to view it use View raw.
