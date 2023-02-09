import java.util.*;

/********************************************************************

Programmet anropar metoder som har skapats i classen DenKortasteVagen
och använder dessa för att mata in och skriva ut mellanstationerna
för den kortaste vägen och distansen för den kortaste vägen.

********************************************************************/

class BestamDenKortasteVagen
{
	public static void main(String[] args)
	{
		//Skapar objekt för class denKortasteVagen
		DenKortasteVagen myObj = new DenKortasteVagen();

		//Inmatningsvektyg
		Scanner in = new Scanner (System.in);

		//Matar in antal stationer i zonerna Z2 och Z3
		System.out.print ("Antal stationer i Z2: ");
		int m = in.nextInt();

		System.out.print ("Antal stationer i Z3: ");
		int n = in.nextInt();

		//Distansen mellan de olika stationerna lagras
		double[] a = new double[m + 1];
		double[][] b = new double[m + 1][n + 1];
		double[] c = new double[n + 1];

		//Matar in längderna mellan de olika stationerna
		//Inmatning för ai
		System.out.println ("Langderna for a: ");
		myObj.langderMellanStationer(a);

		//Inmatning för bij
		System.out.println ("Langderna for b: ");
		myObj.langdBij(b);

		//Inmatning för cj
		System.out.println ("Langderna for c: ");
		myObj.langderMellanStationer(c);

		System.out.println("");

		//Sparar metoden mellanStationer i en ny vektor
		int[] stationer = myObj.mellanStationer(a, b, c);

		//Skriver ut längden på den kortaste vägen och
		//mellanstationerna av den
		System.out.println("Langden av den minsta vagen: "
							+ myObj.langd(a, b, c));
		System.out.println("Mellanstationerna: U"
							+ stationer[1] + " och V"
							+ stationer[2]);

	}
}