import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner readFrom = new Scanner(System.in);
		System.out.print("Beírod a kódolni való szöveget? ");
		String text = readFrom.next();
		
		yesOrNo(readFrom, text);
		readFrom.close();
	}
	
	
//------------------Metódusok----------------------------------------	

	private static void yesOrNo(Scanner readFrom, String text) {
		if (text.equals("Y")) {
			Scanner scan = new Scanner(System.in);
			System.out.print("Add meg a kódolni kívánt szót: ");

			// Megadom a szót String-ként
			String word = scan.next();
			scan.close();
			
			ceasarEncryption(word);
			
		} else if (text.equals("N")) {
		    try {
		        File myObj = new File("text.txt");
		        Scanner myReader = new Scanner(myObj);
		        while (myReader.hasNextLine()) {
		          String data = myReader.nextLine();
		          ceasarEncryption(data);
		        }
		        myReader.close();
		      } catch (FileNotFoundException e) {
		        System.out.println("Hiba történt!");
		        e.printStackTrace();
		      }
		} else {
			System.out.println("Nem megfelelő válasz, add meg újra: ");      // ???
		}
	}

	static void ceasarEncryption(String word) {

		char[] abc = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
				't', 'u', 'v', 'w', 'x', 'y', 'z'};
		char[] ABC = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
				'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

		// Konvertálom a beírt String-et Char-ba
		char[] wordStringToChar = word.toCharArray();

		/*for (int i = 0; i < word.length(); i++) {
			wordStringToChar[i] = word.charAt(i);
		}*/

		// System.out.println(wordChar[1]);
		// --------------------------------------

		// Összehasonlítom és eltolom a betűket
		char[] encryptWord = new char[wordStringToChar.length];
		
		int counter = 0;

		for (int i = 0; i < wordStringToChar.length; i++) {
			for (int j = 0; j < abc.length; j++) {
				
				counter++;
				
				// Ha nagy betűvel kezdődik
				if (Character.isUpperCase(wordStringToChar[i])) {
					if (wordStringToChar[i] == ABC[j]) {
						encryptWord[i] = ABC[j + 3];
						//System.out.println(counter + 3);
						
					}
				} else if (wordStringToChar[i] == abc[j]) {
					encryptWord[i] = abc[j + 3];
				}
			}
		}	
		// char-t String-é konvertálja
	
		String encryptString = String.valueOf(encryptWord);
		
		System.out.print("A titkosított szó: " + encryptString);
	}
	
	
	
	static int getIndexOfAbcArray(char[] abc, int position) {
		
		int index = 0;
		
		for (int i = 0; i < abc.length; i++) {
			if (abc[position + 3] > abc[abc.length - 1]) {
				index = 0;
			}
			else {
				index = position + 3;
			}
		}
		return index;
	}
}
