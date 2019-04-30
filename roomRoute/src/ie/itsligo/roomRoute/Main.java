package ie.itsligo.roomRoute;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
//import java.util.StringTokenizer;

import javax.swing.JTextField;

import com.google.zxing.EncodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class Main {

		static JTextField textField = null;
		static String qrCodeData ;
		static String filePath ;
		
		static QR qr;
		static Room room = new Room();
		static Directions directions = new Directions();

		@SuppressWarnings({ "unchecked", "rawtypes" })
		public static void main(String[] args) throws WriterException, IOException, NotFoundException {
			// Initial hardcoded data for test program 
			qrCodeData = "\nDay: Tuesday\nTime: 09:00 to 11:00\nSubject: Software Engineering\nRoom: B1004";
			filePath = "myQRCode.png";
			
			qr = new QR(qrCodeData, filePath);
			
			Map hintMap = new HashMap();
			hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

			// create the QR barcode
			qr.createQRCode(qrCodeData, filePath, hintMap, 200, 200);
			System.out.println("QR Code image created successfully!");

			// read the barcode
			String QRdata = qr.readQRCode(filePath, hintMap);
			System.out.println("The barcode reads : " + QRdata);
			
			// Find the room
			String theRoom = room.get(QRdata);
			System.out.println("The room is " + theRoom);
			
			// get directions
			if (directions.validate(theRoom) == false) {
				System.out.println("The directions to this room are unknown");
			}
			else {
				System.out.println("DIRECTIONS");
				if(directions.toBuilding() == "Sorry, that building in not recognised") 
					System.out.println(directions.toBuilding());
				
				else if(directions.toFloor() == "Sorry, that floor is not recognised") 
					System.out.println(directions.toFloor());
				
				else if(directions.toLocation() == "Sorry, that room in not recognised") 
					System.out.println(directions.toLocation());
				else {
					System.out.println(directions.toBuilding());
					System.out.println(directions.toFloor());
					System.out.println(directions.toLocation());
				}
				
			}
			directions.playit();
			
			
		}


}
