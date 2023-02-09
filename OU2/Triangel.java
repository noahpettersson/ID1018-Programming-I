//Triangel.java

import java.util.Scanner;

public class Triangel
{
	public static void main (String[] args)
	{
		double[] y = bisektris(2, 3, 4, 50, 60, 70);
		System.out.println(y[0]);
	}

	//Metoden beräknas och retunerar semiPerimeterna av en triangel
	public static double semiPerimeter (double a, double b, double c)
	{
		double s = (a + b + c) / 2;
		return s;
	}

	//Metod beräknar och retunerar arean av triangeln med hjälp av Herons formel
	public static double area (double a, double b, double c)
	{
		//Ber�knar arean av triangeln given de tre sidorna
		//genom att kalla metoden semiPerimeter
		double area = Math.sqrt(semiPerimeter(a, b, c) * (semiPerimeter(a, b, c) - a)
								* (semiPerimeter(a, b, c) - b) * (semiPerimeter(a, b, c) - c));
		return area;
	}

 	//Metod som använder basen och höjden av en triangel för att retunera den area
	public static double BasHojdArea (double bas, double hojd)
	{
		return (bas * hojd) / 2;
	}

	//Beräknar medianen för triangelns olika sidor och retunerar den
	public static double[] median (double a, double b, double c)
	{
		//Skapar en vektor som håller alla median värden
		double[] med = new double[3];

		//median for a
		med[0] = Math.sqrt((2 * Math.pow(c, 2) + 2 * Math.pow(b, 2) - Math.pow(a, 2))) / 2;

		//median for b
		med[1] = Math.sqrt((2 * Math.pow(c, 2) + 2 * Math.pow(a, 2) - Math.pow(b, 2))) / 2;

		//median for c
		med[2] = Math.sqrt((2 * Math.pow(b, 2) + 2 * Math.pow(a, 2) - Math.pow(c, 2))) / 2;


		return med;
	}

	//Beräknar och retunerar bisektrisen för en triangel given två sidor och vinekln mellan de
	public static double bisek (double a, double b, double vinkel)
	{
		double p = 2 * a * b * Math.cos(vinkel / 2);
		double bis = p / (a + b);

		return bis;
	}

	//Metod som beräknar alla tre bisektris för en triangel med hjälp av metoden bisek
	//Retunerar sedan en vektor som inneåller alla tre bisektris för triangel
	public static double[] bisektris (double a, double b, double c,
									  double cVinkel, double bVinkel, double aVinkel)
	{
		//Vektor som sparar bisektriserna
		double[] bis = new double[3];

		//Ber�knar triangelns bisektris mellan sidorna a och b
		bis[0] = bisek(a, b, cVinkel);

		//Ber�knar triangelns bisektris mellan sidorna a och c
		bis[1] = bisek(a, c, bVinkel);

		//Ber�knar triangelns bisektris mellan sidorna a och b
		bis[2] = bisek(b, c, aVinkel);

		return bis;
	}

	//Metoden beräknar och retunerar triangelns omkrets
	public static double omkrets (double a, double b, double c)
	{
		return a + b + c;
	}

	//Metod för cirkelns radie som är inskriven i triangeln
	public static double inTriangleRadie (double a, double b, double c)
	{
		//Använder metoden semiPerimeter för att beräkna triangelns inskriva cirkel radie
		double inRadie = Math.sqrt(((semiPerimeter(a, b, c)-a) * (semiPerimeter(a, b, c)-b)
									 * (semiPerimeter(a, b, c)-c)) / semiPerimeter(a, b, c));

		return inRadie;
	}

	//Metod för cirkelns radie som är omskriven kring triangeln
	public static double cirkelOmskrivenTriangel (double a, double b, double c)
	{
		double radie = (a * b * c) / (4 * area(a, b, c));

		return radie;
	}


}
