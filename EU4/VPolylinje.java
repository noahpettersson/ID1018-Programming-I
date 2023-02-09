import java.util.*; //Arrays;

public class VPolylinje implements Polylinje
{
	// Globala variabler
	private Punkt[] horn;
	private String farg = "svart";
	private int bredd = 1;

	// konstruktor
	public VPolylinje ()
	{
		this.horn = new Punkt[0];
	}

	// konstruktor med en array parameter f�r h�rnen
	public VPolylinje (Punkt[] horn)
	{
		this.horn = new Punkt[horn.length];
		for (int i = 0; i < horn.length; i++)
			this.horn[i] = new Punkt (horn[i]);
	}

	// toString metod, retunerar som en str�ng
	public String toString ()
	{
		String s = "{[";

		for(int i = 0; i < horn.length; i++)
		{
			s += horn[i];
		}
		s += "]}" + ", " + farg + ", " + bredd + "}";

		return s;
	}

	// metod som retunerar en array av alla h�rn
	public Punkt[] getHorn ()
	{
		Punkt[] h = new Punkt[this.horn.length];
		for(int i = 0; i < this.horn.length; i++)
			h[i] = new Punkt(this.horn[i]);

		return h;
	}

	// metod som retunerar f�rgen av en polylinje
	public String getFarg ()
	{
		return this.farg;
	}

	// metod som retunerar bredden p� en polylinje
	public int getBredd ()
	{
		return this.bredd;
	}

	// tar in en str�ng f�rg som parameter
	// och �ndrar f�rgen p� polylinjen till
	// parametern
	public void setFarg (String farg)
	{
		this.farg = farg;
	}

	// tar in en bredd som parameter
	// och �ndrar bredden p� polylinjen till
	// parametern
	public void setBredd (int bredd)
	{
		this.bredd = bredd;
	}

	// metod som retunerar l�ngden av en polylinje
	public double langd ()
	{
		double langd = 0;
		for(int i = 0; i < horn.length - 1; i++)
		{
			langd += Math.sqrt(Math.pow(horn[i].getX() - horn[i + 1].getX(), 2)
										+ Math.pow(horn[i].getY() - horn[i + 1].getY(), 2));
		}

		return langd;
	}

	// metod som tar in ett h�rn som parameter
	// och l�gger in h�rnet l�ngst bak i polylinjen
	public void laggTill (Punkt horn)
	{
		Punkt[] h = new Punkt[this.horn.length + 1];

		 int i = 0;
		 for (i = 0; i < this.horn.length; i++)
			h[i] = this.horn[i];
		 h[i] = new Punkt (horn);

		 this.horn = h;
	}

	// metod som tar in ett h�rn och ett namn som parameter,
	// och s�tter h�rnet framf�r ett annat h�rn med namnet
	// som i parametern
	public void laggTillFramfor (Punkt horn, String hornNamn)
	{
		Punkt[] h = new Punkt[this.horn.length + 1];

		boolean check = false;
		for(int i = 0; i < this.horn.length; i++)
		{
			if(this.horn[i].getNamn() == hornNamn)
			{
				h[i] = new Punkt(horn);
				h[i + 1] = new Punkt(this.horn[i]);
				check = true;
			}

			int j = check ? i + 1 : i;

			h[j] = new Punkt (this.horn[i]);
		}

		this.horn = h;
	}

	// metod som tar in ett namn p� ett h�rn
	// och tar bort detta h�rn fr�n polylinjen
	public void taBort (String hornNamn)
	{
		Punkt[] h = new Punkt[this.horn.length - 1];
		boolean check = false;

		for(int i = 0; i < this.horn.length; i++)
		{
			if(this.horn[i].getNamn() == hornNamn)
			{
				i += 1;
				check = true;
				if(i == this.horn.length)
					break;
			}

			if(i == this.horn.length - 1 && check == false)
				throw new IllegalArgumentException (hornNamn + " doesn't exist");

			int j = check ? i - 1 : i;

			h[j] = new Punkt(this.horn[i]);
		}

		this.horn = h;
	}

	public Iterator<Punkt> iterator()
	{
		return Arrays.asList(horn).iterator();
	}
}
