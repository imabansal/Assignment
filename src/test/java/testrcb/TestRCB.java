//package
package testrcb;

//import JSON parser
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

//testng and assertions
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.BeforeTest;

//file reader imports 
import java.io.FileReader;

//import exceptions
import java.io.IOException;
import java.io.FileNotFoundException;

//json imports
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class TestRCB {
	
		static int wicketKeeperCount=0; 	//keeping count of number of wicketkeepers
		static int foreginPlayersCount=0;	//keeping foreign players count
		
		@BeforeTest
		//initial setup for reading json files and getting player array data.
		public void setUp() {
		
			//jsonparser object
			JSONParser jsonparser = new JSONParser();  //json parser object
			FileReader reader = null;	//file reader object
			try {
				reader = new FileReader(".\\src\\test\\resources\\schema.json"); //reading json file in try/catch block to handle exceptions
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Object obj=null;
			try {
				 obj = jsonparser.parse(reader);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			JSONObject jsonObj = (JSONObject)obj; //type casting to JSON Object
			JSONArray playerArray = (JSONArray)jsonObj.get("player"); //getting json array
			
			for(int i=0;i<playerArray.size();i++) {
				JSONObject player = (JSONObject)playerArray.get(i);
					if(player.get("role").equals("Wicket-Keeper")) { 
					wicketKeeperCount++; //if role matches wicket-keeper, increase count
					}
					
					if(!player.get("country").equals("India")) {
						foreginPlayersCount++; //if role matches foreign-players, increase count
				}
			} 
			
		}
		
		
		@Test //TestCase 1
		public void verifyonlyFourForeignPlayers() {
			
			 SoftAssert softassert = new SoftAssert();
			 softassert.assertEquals(foreginPlayersCount, 4);
			
		}
		
		@Test //TestCase 2
		public void verifyonlyAtleastOneWicketKeeper() {
			
			 SoftAssert softassert = new SoftAssert();
			 softassert.assertEquals(wicketKeeperCount, 1);
			
		}
		
		
	}


