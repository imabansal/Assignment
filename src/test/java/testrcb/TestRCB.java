//package
package testrcb;

//import json parser
import org.json.simple.parser.JSONParser;

//testng and assertions
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

//file reader imports 
import java.io.FileReader;
import java.io.IOException;

//import exceptions

//json imports
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class TestRCB {
	

		static int wicketKeeperCount=0; 	//keeping count of number of wicketkeepers
		static int foreginPlayersCount=0;	////keeping foreign players count
		
		public static void main(String args) throws IOException, org.json.simple.parser.ParseException{
		
			JSONParser jsonparser = new JSONParser();
			FileReader reader = new FileReader(".\\schema.json");
			
			Object obj = jsonparser.parse(reader);
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
			
			System.out.println("WicketKeeperCount is "+wicketKeeperCount );
			System.out.println("foreginPlayersCount is "+foreginPlayersCount );

		}
		
		@Test
		public void validateonlyFourForeignPlayers() {
			
			 SoftAssert softassert = new SoftAssert();
			 softassert.assertEquals(foreginPlayersCount, 4);
			
		}
		
		@Test
		public void validateonlyAtleastOneWicketKeeper() {
			
			 SoftAssert softassert = new SoftAssert();
			 softassert.assertEquals(wicketKeeperCount, 1);
			
		}
		
		
	}


