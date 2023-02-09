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

		Polylinje     polylinje = null;
		//polylinje = new VPolylinje (punktArray);       // (1)
		polylinje = new NPolylinje (punktArray);    // (2)

		System.out.println(polylinje);

		Punkt[] p = polylinje.getHorn();
		String f = polylinje.getFarg();
		int b = polylinje.getBredd();

		System.out.println(Arrays.toString(p));
		System.out.println(f);
		System.out.println(b);

		polylinje.setFarg("gul");
		polylinje.setBredd(12);
		polylinje.laggTill(p4);
		polylinje.laggTillFramfor(p5, "B");
		polylinje.taBort("C");

		System.out.println(polylinje);
		System.out.println();

		for (Punkt horn : polylinje)
    		System.out.println (horn);

	}
}
