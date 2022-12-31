//package
package testrcb;

//import json parser
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
		public void setUp() {
		
			JSONParser jsonparser = new JSONParser();
			FileReader reader = null;
			try {
				reader = new FileReader(".\\schema.json");
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
			
			
			JSONObject jsonObj = (JSONObject)obj;
			JSONArray playerArray = (JSONArray)jsonObj.get("playerArray");
			
			for(int i=0;i<playerArray.size();i++) {
				JSONObject player = (JSONObject)playerArray.get(i);
					if(player.get("role").equals("Wicket-Keeper")) {
					wicketKeeperCount++;
					}
					
					if(!player.get("country").equals("India")) {
						foreginPlayersCount++;
				}
			}
		}
		
		
		@Test //Test1
		public void verifyonlyFourForeignPlayers() {
			
			 SoftAssert softassert = new SoftAssert();
			 softassert.assertEquals(foreginPlayersCount, 4);
			
		}
		
		@Test //Test2
		public void verifyonlyAtleastOneWicketKeeper() {
			
			 SoftAssert softassert = new SoftAssert();
			 softassert.assertEquals(wicketKeeperCount, 1);
			
		}
		
		
	}


