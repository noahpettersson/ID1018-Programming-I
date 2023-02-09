//EnTriangelOchDessCirklar.java

import java.util.*; //Scanner, Locale

class EnTriangelOchDessCirklar
{
	public static void main (String[] args)
	{
		//Skapar ett objekt
		Triangel obj = new Triangel();

		//Inmatningssystem
		Scanner in = new Scanner (System.in);

		System.out.println("Skriv in sidorna av tringeln, "
							+ "summan av tva sidor maste vara storre an den tredje sidan");

		//Inmatning för trianglens sidor
		System.out.print("Sida a: ");
		double a = in.nextDouble();

		System.out.print("Sida b: ");
		double b = in.nextDouble();

		System.out.print("Sida c: ");
		double c = in.nextDouble();

		//Skriver ut resultaten
		System.out.println ("Inskriven cirkel: " + obj.inTriangleRadie(a, b, c));
		System.out.println ("Omskriven cirkel: " + obj.cirkelOmskrivenTriangel(a, b, c));
	}
}