import java.util.*; //Scanner, Locale

class Temperaturer
{
	public static void main (String[] args)
	{
		System.out.println ("TEMPERATURER\n");

		//Inmatningsvektyg
		Scanner in = new Scanner (System.in);

		//Mata in uppgifter om antalet veckor och antalet mätningar
		System.out.print ("Antalet veckor: ");
		int antalVeckor = in.nextInt();
		System.out.print ("Antelet matningar per vecka: ");
		int antalMatningarPerVecka = in.nextInt();

		//Plats att lagra temperaturer
		double[][] t = new double[antalVeckor + 1][antalMatningarPerVecka + 1];

		//Mata in temperaturerna veckovis
		for (int vecka = 1; vecka <= antalVeckor; vecka++)
		{
			System.out.println ("temperaturer - vecka " + vecka + ":");
			for (int matning = 1; matning <= antalMatningarPerVecka; matning++)
				t[vecka][matning] = in.nextDouble ();

		}
		System.out.println ();

		//Visa temperaturerna
		System.out.println ("temperaturerna:");
		for (int vecka = 1; vecka <= antalVeckor; vecka++)
		{
			for (int matning = 1; matning <= antalMatningarPerVecka; matning++)
				System.out.print (t[vecka][matning] + " ");
			System.out.println();
		}
		System.out.println();

		//Den minsta, den största och medeletemperaturen - veckovis
		double[] mintT = new double[antalVeckor + 1];
		double[] maxT = new double[antalVeckor + 1];
		double[] sumT = new double[antalVeckor + 1];
		double[] medelT = new double[antalVeckor + 1];

		//koden ska skrivas har
		for (int vecka = 1; vecka <= antalVeckor; vecka++)
		{
			//Beräknar de minsta temperaturerna veckovis
			mintT[vecka] = t[vecka][1];

				for (int matning = 1; matning <= antalMatningarPerVecka; matning++)
				{
					if (t[vecka][matning] < mintT[vecka])
					{
						mintT[vecka] = t[vecka][matning];
					}
				}

			//Beräknar max temperaturerna och summan av temperaturerna veckovis
			maxT[vecka] = t[vecka][1];

				for (int matning = 1; matning <= antalMatningarPerVecka; matning++)
				{
					//Max temperatur
					if (t[vecka][matning] > maxT[vecka])
					{
						maxT[vecka] = t[vecka][matning];
					}

					//Beräknar summan av temperaturerna veckovis
					sumT[vecka] += t[vecka][matning];
				}

			//Ber�knar medel temperaturen
			medelT[vecka] = sumT[vecka] / (antalMatningarPerVecka);

			//visa den minsta, den st?rsta och medeltemperaturen f�r varje vecka
			//koden ska skrivas h�r
			System.out.println("Minsta temperatur - vecka " + vecka + ": " + mintT[vecka]);
			System.out.println("Hogsta temperatur - vecka " + vecka + ": " + maxT[vecka]);
			System.out.println("Medel temperatur - vecka " + vecka + ": " + medelT[vecka]);
			System.out.println();

		}

		//den minsta, den st�rsta och medeltemperaturen - hela m�tperioden
		double minTemp = mintT[1];
		double maxTemp = maxT[1];
		double sumTemp = 0; //sumT[1];
		double medelTemp = 0;
		//koden ska skrivas h�r

		//visa den minsta, den st�rsta och medeltemperaturen i hela m?tperioden
		//koden ska skrivas h�r
		for(int vecka = 1; vecka <= antalVeckor; vecka++)
		{
			//Ber�knar minsta temperaturen för hela mätperioden
			if(mintT[vecka] < minTemp)
			{
				minTemp = mintT[vecka];
			}

			//Ber�knar största temperaturen för hela mätperioden
			if(maxT[vecka] > maxTemp)
			{
				maxTemp = maxT[vecka];
			}

			//Ber�knar summan av alla medel temperaturer
			sumTemp += medelT[vecka];
		}

		//Beröknar den medel temperaturen för hela mätperioden
		medelTemp = sumTemp / (antalVeckor);

		//Skriver ut minsta, största och medel temperaturerna för hela mätperioden
		System.out.println ("Total minsta temperatur: " + minTemp
							+ "\n" + "Total hogsta temperatur: "
							+ maxTemp + "\n"
							+ "Total medel temperatur: " + medelTemp + "\n");
	}
}