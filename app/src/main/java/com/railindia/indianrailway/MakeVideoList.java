
package com.railindia.indianrailway;

import java.util.ArrayList;
import java.util.HashMap;

public class MakeVideoList {

	public static ArrayList< ArrayList<HashMap<String,String>> > rootArrayList;
	public static ArrayList< HashMap<String, String> > catArrayList;
	public static ArrayList< HashMap<String, String> > videoArrayList;
	public static HashMap<String, String> hashMap;


	public static void createCategoryForWebsite(String category_name, Integer drawable, String url){
		rootArrayList.add(videoArrayList);
		hashMap = new HashMap<>();
		hashMap.put("category_name", category_name);
		hashMap.put("img", String.valueOf(drawable));
		hashMap.put("url", url);
		catArrayList.add(hashMap);
		videoArrayList = new ArrayList<>();
	}



	//----------------------------------------------------
	public static void createMyAlbums(){
		rootArrayList = new ArrayList();
		catArrayList = new ArrayList<>();
		videoArrayList = new ArrayList<>();



		createCategoryForWebsite("Book Ticket", R.drawable.ticket, "https://irctc.corover.ai/");
		createCategoryForWebsite("My Booking", R.drawable.booking, "https://www.irctc.co.in/nget/profile/user-registration");
		createCategoryForWebsite("PNR Status", R.drawable.pnr, "https://www.indianrail.gov.in/enquiry/PNR/PnrEnquiry.html?locale=en");
		createCategoryForWebsite("Live Train Status", R.drawable.location, "https://runningstatus.in/livetrains");
		createCategoryForWebsite("Train Schedule", R.drawable.shedule, "https://www.indianrail.gov.in/enquiry/SCHEDULE/TrainSchedule.html?locale=enzzzzzzzzzzzzzz");
		createCategoryForWebsite("Passenger Enquiry", R.drawable.enquary, "https://www.indianrail.gov.in/enquiry/PNR/PnrEnquiry.html?locale=en");
		createCategoryForWebsite("Chart Vacancy", R.drawable.chartvacancy, "https://www.irctc.co.in/online-charts/");
		createCategoryForWebsite("Trains B/W Stations", R.drawable.bewten, "https://www.indianrail.gov.in/enquiry/TBIS/TrainBetweenImportantStations.html?locale=en");
		createCategoryForWebsite("Boarding change", R.drawable.exchange, "https://www.operations.irctc.co.in/ctcan/SystemTktCanLogin.jsf");
		createCategoryForWebsite("Live Station Status", R.drawable.station_status, "https://runningstatus.in/livetrains");
		createCategoryForWebsite("Seat availability", R.drawable.seat, "https://www.indianrail.gov.in/enquiry/SEAT/SeatAvailability.html?locale=en");
		createCategoryForWebsite("Fare Enquiry", R.drawable.fare, "https://www.indianrail.gov.in/enquiry/FARE/FareEnquiry.html?locale=en");
		createCategoryForWebsite("Cancel Ticket", R.drawable.cancel, "https://www.irctc.co.in/nget/profile/user-registration");
		createCategoryForWebsite("About", R.drawable.info, "https://www.irctc.com/about.html");
		createCategoryForWebsite("Privacy Policy", R.drawable.privacy, "");


	}




}

