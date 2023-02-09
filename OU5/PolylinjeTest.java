import java.util.*; //Arrays;
import java.io.*; // PrintWriter

class PolylinjeTest
{
	public static void main (String[] args)
	{
		Punkt p1 = new Punkt("A", 0, 6);
		Punkt p2 = new Punkt("B", 1, 10);
		Punkt p3 = new Punkt("C", 14, 7);
		Punkt p4 = new Punkt("D", 5, 5);
		Punkt p5 = new Punkt("E", 9, 3);

		Punkt[] punktArray = {p1, p2, p3};

		Polylinje poly = new Polylinje (punktArray);
		System.out.println(poly);

		Punkt[] p = poly.getHorn();
		String f = poly.getFarg();
		int b = poly.getBredd();

		System.out.println(Arrays.toString(p));
		System.out.println(f);
		System.out.println(b);

		poly.setFarg("gul");
		poly.setBredd(12);
		poly.laggTill(p4);
		poly.laggTillFramfor(p5, "B");
		poly.taBort("A");

		System.out.println(poly.langd());
		System.out.println(poly);
		System.out.println();

		Polylinje.PolylinjeIterator iterator = poly.new PolylinjeIterator();

		while (iterator.finnsHorn())
		{
			System.out.println(iterator.horn() + " ");
			iterator.gaFram();
		}
	}
}
