import java.util.*;

class DenKortasteVagen
{
	// mellanstationer returnerar en vektor med de mellanstationer som finns p� den kortaste
	// v�gen. Ordningsnummer av den f�rsta mellanstationen finns p� index 1, och ordningsnummer
	// av den andra mellanstationen p� index 2 i vektorn.

	public static int[] mellanStationer (double[] a, double[][] b, double[] c)
	{
		//Skapar en variabel som sätter det första avståndet som det minsta
		double minst = a[1] + b[1][1] + c[1];

		//Skapar en vektor där mellanstationerna lagras
		int[] stationer = new int[3];

		/*En loop som går igenom och hittar det minsta värdet
		  och sedan sätter det i-värdet som motsvarar det minsta värdet
		  i vektorn-stationer, retunerar sedan stationer vektorn*/
		for(int i = 1; i < a.length; i++)
		{
			for(int j = 1; j < b[i].length; j++)
			{
				double var = a[i] + b[i][j] + c[j];
				if(var < minst)
				{
					minst = var;
					stationer[1] = i;
					stationer[2] = j;
				}
			}
		}

		//Retunerar vektorn som innehåller mellan stationerna
		return stationer;
	}

	// langd returnerar l�ngden av den kortaste v�gen.
	public static double langd (double[] a, double[][] b, double[] c)
	{
		/*Skapar en vektor som sätt lika med den retunerade vektorn från
		  metoden mellanStationer*/
		int[] index = mellanStationer(a, b, c);

		/*Tar det i-värde från metoden mellanStationer som motsvarar det minsta värde i a, b och c
		  och sätter de i varsin variabel*/
		double minstA = a[index[1]];
		double minstB = b[index[1]][index[2]];
		double minstC = c[index[2]];

		//Retunerar den minsta vägen
		return minstA + minstB + minstC;

 	}

	//Metod för att bestämma längderna ai och cj
	public static double[] langderMellanStationer (double[] ac)
	{
		Scanner in = new Scanner (System.in);
		for(int i = 1; i < ac.length; i++)
		{
			ac[i] = in.nextDouble();
		}

		return ac;
	}

	//Metod för att beräkna längderna bij
	public static double[][] langdBij (double[][] b)
	{
		Scanner in = new Scanner (System.in);
		for(int i = 1; i < b.length; i++)
		{
			for(int j = 1; j < b[i].length; j++)
			{
				b[i][j] = in.nextDouble();
			}
		}

		return b;
	}


}