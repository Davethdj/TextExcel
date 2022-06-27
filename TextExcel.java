package textExcel;
import java.io.FileNotFoundException;
import java.util.Scanner;
// Update this file with your own code.
public class TextExcel{
	public static void main(String[] args){
		Spreadsheet sht = new Spreadsheet();
		Scanner input = new Scanner(System.in);
		String str= input.nextLine();
		while(str.toLowerCase().equals("quit")) {
			System.out.println(sht.processCommand(str));
			str = input.nextLine();
		}
		input.close();
	}
}