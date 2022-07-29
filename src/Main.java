import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		
		Scanner read = new Scanner(System.in);
		System.out.print("Beírod a kódolni való szöveget? ");
		String text = read.next();
		
		yesOrNo(text);
		read.close();
	}
	
//---------------------------Metódusok----------------------------------------	

	private static void yesOrNo(String text) throws IOException{
		if (text.equals("y")) {
			System.out.print("Caesar (c) vagy Vigenére (v) titkosítást szeretne használni? ");
			Scanner scan = new Scanner(System.in);
			String caesarOrVigenere = scan.next();
			
			if (caesarOrVigenere.equals("c")) {
			System.out.print("Add meg a kódolni kívánt szót: ");

			// Megadom a szót String-ként
			Scanner scan2 = new Scanner(System.in);
			String word = scan2.next();

			ceasarEncryption(word);
			
			scan.close();		// Ha a "if (cOrV.equals("c")) {" sor elé rakom hibába fut...
			scan2.close();
			
			} else if (caesarOrVigenere.equals("v")) {
				System.out.println("Coming soon...");
			}
			
		} else if (text.equals("n")) {
			String fileContent = readFromTxt();
			
			System.out.println("A kódolt szöveg: " + fileContent + "\n");
			
			System.out.print("Szeretné menteni egy .txt fájlba a kódolt szöveget? ");
			
			Scanner scan4 = new Scanner(System.in);
			String decide = scan4.next();
			scan4.close();
			
			
			if (decide.equals("y")) {
				String result = readFromTxt();
				
				FileWriter writer = new FileWriter("encrypted_text.txt");
				writer.write(result);
				writer.close();
				
				System.out.println("Sikeresen mentve az 'encrypted_text.txt' fájlba.");
			}
			else {
				System.out.println("End.");
			}
			
		} else {
			System.out.println("Nem megfelelő válasz, add meg újra: ");      // ??? do-while
		}
	}

 static String readFromTxt() throws FileNotFoundException {
	String fileContent = "";
	
	File file = new File("text.txt");
	Scanner scan3 = new Scanner(file);
	while(scan3.hasNextLine()) {		
		fileContent = fileContent.concat(scan3.nextLine()+ "\n");
	}
	scan3.close();
	return ceasarEncryption(fileContent);
}
	
//----------------------------------------------------------------------------

	static String ceasarEncryption(String word) {

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
		
		return encryptString;
	}
	
//----------------------------------------------------------------------------

	
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