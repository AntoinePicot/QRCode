package ie.itsligo.roomRoute;
import java.util.StringTokenizer;

public class Room {
	private String room;

	public Room() {
		
	}
	
	public String get(String data)
	{
		
		// TODO extract the room number eg E2004 from the data passed in
		// Here you write the code to parse the data string and extract the room
		
		StringTokenizer st = new StringTokenizer(data);
		
		while (st.hasMoreTokens()) {
			if(st.nextToken().startsWith("Room")){
				room = st.nextToken(":").trim();
			}
			
		}
		
		return(room);
	}

}
