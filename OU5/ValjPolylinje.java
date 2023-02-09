import java.util.Random;
import java.util.Arrays;

class ValjPolylinje
{
	public static final Random rand = new Random ();
	public static final int ANTAL_POLYLINJER = 10;

	public static void main (String[] args)
	{
		// skapa ett antal slumpm�ssiga polylinjer
		Polylinje[] polylinjer = new Polylinje[ANTAL_POLYLINJER];
		for (int i = 0; i < ANTAL_POLYLINJER; i++)
		{
			polylinjer[i] = slumpPolylinje ();

			// visa polylinjerna
			System.out.println(polylinjer[i]);
		}

		// best�m den kortaste av de polylinjer som �r gula
		double KortastLangd = 0;
		int j = 0;
		boolean check = false;
		boolean secondCheck = false;
		for(int i = 0; i < ANTAL_POLYLINJER; i++)
		{
			if(polylinjer[i].getFarg() == "yellow")
			{
				secondCheck = true;
				j = check == true ? j : i;
				check = true;
				KortastLangd = polylinjer[j].langd();
				if(polylinjer[i].langd() < KortastLangd)
				{
					j = i;
					KortastLangd = polylinjer[j].langd();
				}
			}
		}

		if(secondCheck == false)
			System.out.println("\n" + "Finns inga gula polylinjer!");

		else
		{
			// visa den valda polylinjen
			System.out.println();
			System.out.println("Kortaste polylinjen: " + "\n" + polylinjer[j] + "\n");
			System.out.println("Langden: " + KortastLangd);
		}
	}

	// slumpPunkt returnerar en punkt med ett slumpm�ssigt namn, som �r en stor bokstav i
	// det engelska alfabetet, och slumpm�ssiga koordinater.
	public static Punkt slumpPunkt ()
	{
		String n = "" + (char) (65 + rand.nextInt (26));
		int x = rand.nextInt (11);
		int y = rand.nextInt (11);

		return new Punkt (n, x, y);
	}

	// slumpPolylinje returnerar en slumpm�ssig polylinje, vars f�rg �r antingen bl�, eller r�d
	// eller gul. Namn p� polylinjens h�rn �r stora bokst�ver i det engelska alfabetet. Tv� h�rn
	// kan inte ha samma namn.
	public static Polylinje slumpPolylinje ()
	{
		// skapa en tom polylinje, och l�gg till h�rn till den
		Polylinje polylinje = new Polylinje ();
		int antalHorn = 2 + rand.nextInt (7);
		int antalValdaHorn = 0;
		boolean[] valdaNamn = new boolean[26];
		// ett och samma namn kan inte f�rekomma flera g�nger
		Punkt valdPunkt = null;
		int charInt = 0;

		while (antalValdaHorn < antalHorn)
		{
			valdPunkt = slumpPunkt();
			charInt = (int) valdPunkt.getNamn().charAt(0) - 65;
			if(valdaNamn[charInt] == false)
			{
				polylinje.laggTill(valdPunkt);
				valdaNamn[charInt] = true;
				antalValdaHorn++;
			}
		}

		// s�tt f�rg
		String[] colour = {"blue", "red", "yellow"};
		int colourIndex = rand.nextInt(3);
		polylinje.setFarg(colour[colourIndex]);

		return polylinje;
	}
}
