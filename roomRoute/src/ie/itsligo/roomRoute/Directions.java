package ie.itsligo.roomRoute;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Directions {
	private final int ROOM_LENGTH = 5; // size of the room string
	private char building ;
	private char floor;
	private String locaationOnFloor = null;
	private int SOUND_A = 01, SOUND_B = 02, SOUND_C = 03, SOUND_D = 04, SOUND_E = 05, SOUND_F = 06;
	private int SOUND_0 = 10, SOUND_1 = 11, SOUND_2 = 12;
	private int SOUND_003 = 23, SOUND_004 = 24, SOUND_006 = 26, SOUND_007 = 27;
	private int SOUND_ERROR_BUILDING = 30, SOUND_ERROR_FLOOR = 31, SOUND_ERROR_ROOM = 32;
	private int T[]= {32,32,32};

	public Directions() {

	}

	
	public char getBuilding() {
		return building;
	}


	public void setBuilding(char building) {
		this.building = building;
	}


	public char getFloor() {
		return floor;
	}


	public void setFloor(char floor) {
		this.floor = floor;
	}


	public String getLocaationOnFloor() {
		return locaationOnFloor;
	}

	public void setLocaationOnFloor(String locaationOnFloor) {
		this.locaationOnFloor = locaationOnFloor;
	}

	/*
	 * This method takes in a room eg E2004 and splits up into the correct block
	 * (Engineering, Science, Business, etc) The correct floor The correct room
	 * number
	 */
	public boolean validate(String room) {
		
		if (room.length() != ROOM_LENGTH) {			
			return false;
		}
		if (Character.isLetter(room.charAt(0)) == false) {
			return false; // room must start with a letter
		}
		for (int i = 1; i < ROOM_LENGTH; i++) {			
			if (Character.isDigit(room.charAt(i)) == false) {
				return false; // room must start with a letter
			}
		}
		
		// all ok - store the info
		building = room.charAt(0);
		floor = room.charAt(1);
		locaationOnFloor = room.substring(2);
		T[0]=SOUND_ERROR_ROOM;
		
		return true;
	}
	
	/*
	 * Get directions to building
	 */
	public String toBuilding() {
		String directions = null;
		switch (this.building) {
		case 'A':
			directions = "From reception, walk straight ahead and then turn to your right";
			T[0]=SOUND_A;
			break;
		case 'B':
			directions = "From reception, go up main stairs on your left and turn right.  Walk for 40m past Library until you read the Booknest";
			T[0]=SOUND_B;

			break;
		case 'C':
			directions = "From reception, go up main stairs on your left and turn right.  Walk for 40m past Library until you read the Booknest, then turn left and conrinute through long corridor";
			T[0]=SOUND_C;
			break;
		case 'D':		
			directions = "rom reception, go up main stairs on your left and turn right.  Walk for 40m past Library until you read the Booknest, then turn left and conrinute through long corridor";
			T[0]=SOUND_D;
			break;
		case 'E':
			directions = "From reception, move the the centre of reception and turn left into the engineering building";
			T[0]=SOUND_E;

			break;
		case 'F':
			directions = "From reception, walk outside and turn to your right.  Walk past the engineering building and the F block is straigt in front";
			T[0]=SOUND_F;
			break;
		default:
			directions = "Sorry, that building in not recognised";
			T[0]=SOUND_ERROR_BUILDING;
			break;
			
		}
		return(directions);
	}
	
	/*
	 * Get directions to floor
	 */
	public String toFloor() {
		String directions = null;
		switch (this.floor) {
		case '0':
			directions = "Stay on this floor";
			T[1]=SOUND_0;
			break;
		case '1':
			directions = "Ascend the stairs or take the lift to the first floor";
			T[1]=SOUND_1;
			break;
		case '2':
			directions = "Ascend two flight of stairs or take the lift to the second floor";
			T[1]=SOUND_2;
			break;
		default:
			directions = "Sorry, that floor is not recognised";
			T[1]=SOUND_ERROR_FLOOR;
			break;
			
		}
		return(directions);
	}

	/*
	 * Get directions to floor
	 */
	public String toLocation() {
		String directions = null;
		switch (this.locaationOnFloor) {
		case "006":
			directions = "This is a room to the right on this level";
			T[2]=SOUND_006;
			break;
		case "007":
			directions = "This is a room to the right on this level";
			T[2]=SOUND_007;
			break;
		case "003":
			directions = "This is the last room to the right on this level";
			T[2]=SOUND_003;
			break;
		case "004":
			directions = "This is the second last room to the right on this level";
			T[2]=SOUND_004;
			break;
		default:
			directions = "Sorry, that room in not recognised";
			T[2]=SOUND_ERROR_ROOM;
			break;
			
		}
		return(directions);
	}
	



	public void playit() {
		
		String fn = null;
		File sound;
		if(T[0]==30)
			fn = "./src/ie/itsligo/roomRoute/resources/sounds/sound_00.wav";
		else if(T[1]==31)
			fn = "./src/ie/itsligo/roomRoute/resources/sounds/sound_01.wav";
		else if(T[2]==32)
			fn = "./src/ie/itsligo/roomRoute/resources/sounds/sound_02.wav";
		else {
		for(int i = 0; i < T.length ; i++) {
				switch (T[i]) {
				case 30:
					fn = "./src/ie/itsligo/roomRoute/resources/sounds/sound_00.wav";
					break;
				case 1:
					fn = "./src/ie/itsligo/roomRoute/resources/sounds/sound_1.wav";
					break;
				case 2:
					fn = "./src/ie/itsligo/roomRoute/resources/sounds/sound_2.wav";
					break;
				case 3:
					fn = "./src/ie/itsligo/roomRoute/resources/sounds/sound_3_4.wav";
					break;
				case 4:
					fn = "./src/ie/itsligo/roomRoute/resources/sounds/sound_3_4.wav";
					break;
				case 5:
					fn = "./src/ie/itsligo/roomRoute/resources/sounds/sound_5.wav";
					break;
				case 6:
					fn = "./src/ie/itsligo/roomRoute/resources/sounds/sound_6.wav";
					break;
				case 10:
					fn = "./src/ie/itsligo/roomRoute/resources/sounds/sound_10.wav";
					break;
				case 11:
					fn = "./src/ie/itsligo/roomRoute/resources/sounds/sound_11.wav";
					break;
				case 12:
					fn = "./src/ie/itsligo/roomRoute/resources/sounds/sound_12.wav";
					break;
				case 23:
					fn = "./src/ie/itsligo/roomRoute/resources/sounds/sound_23.wav";
					break;
				case 24:
					fn = "./src/ie/itsligo/roomRoute/resources/sounds/sound_24.wav";
					break;
				case 26:
					fn = "./src/ie/itsligo/roomRoute/resources/sounds/sound_26_27.wav";
					break;
				case 27:
					fn = "./src/ie/itsligo/roomRoute/resources/sounds/sound_26_27.wav";
					break;
				case 31:
					fn = "./src/ie/itsligo/roomRoute/resources/sounds/sound_01.wav";
					break;
				case 32:
					fn = "./src/ie/itsligo/roomRoute/resources/sounds/sound_02.wav";
					break;
				default:
					break;

				}

				// Go for it!
				try {
					// Open an audio input stream.
					sound = new File(fn);
					AudioInputStream audioIn = AudioSystem.getAudioInputStream(sound);
					Clip clip = AudioSystem.getClip();
					// Open audio clip and load samples from the audio input stream.
					clip.open(audioIn);
					clip.start();
					while(clip.getMicrosecondLength() != clip.getMicrosecondPosition())
					{
					}
					// plays
				} catch (UnsupportedAudioFileException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (LineUnavailableException e) {
					e.printStackTrace();
				} catch (Throwable e) {
					e.printStackTrace();
				}
			}
		}
		
		if(T[0] == 30 || T[1] == 31 || T[2] == 32 ) {
			try {
				// Open an audio input stream.
				sound = new File(fn);
				AudioInputStream audioIn = AudioSystem.getAudioInputStream(sound);
				Clip clip = AudioSystem.getClip();
				// Open audio clip and load samples from the audio input stream.
				clip.open(audioIn);
				clip.start();
				while(clip.getMicrosecondLength() != clip.getMicrosecondPosition())
				{
				}
				// plays
			} catch (UnsupportedAudioFileException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
		
	}
		
    
}
