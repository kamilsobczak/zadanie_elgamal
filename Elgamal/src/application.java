import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class application {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		String Filename;
		
		System.out.println("Witamy w programie podpisu cyfrowego algorytmem Elgamal");
		System.out.println("Podaj nazwe pliku:");
		BufferedReader klaw = new BufferedReader(new InputStreamReader(System.in)); 
		Filename = klaw.readLine();
		Elgamal nowy = new Elgamal(Filename);
		nowy.Signature();
		nowy.Check_Signature();
	}
}
