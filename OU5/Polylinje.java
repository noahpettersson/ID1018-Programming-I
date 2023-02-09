import java.util.*; //Arrays;

public class Polylinje
{
	// Globala variabler
	private Punkt[] horn;
	private String farg = "svart";
	private int bredd = 1;

	// konstruktor
	public Polylinje ()
	{
		this.horn = new Punkt[0];
	}

	// konstruktor med en array parameter för hörnen
	public Polylinje (Punkt[] horn)
	{
		this.horn = new Punkt[horn.length];
		for (int i = 0; i < horn.length; i++)
			this.horn[i] = new Punkt (horn[i]);
	}

	// toString metod, retunerar som en sträng
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

	// metod som retunerar en array av alla hörn
	public Punkt[] getHorn ()
	{
		Punkt[] h = new Punkt[this.horn.length];
		for(int i = 0; i < this.horn.length; i++)
		{
			h[i] = new Punkt(this.horn[i]);
		}

		return h;
	}

	// metod som retunerar färgen av en polylinje
	public String getFarg ()
	{
		return this.farg;
	}

	// metod som retunerar bredden på en polylinje
	public int getBredd ()
	{
		return this.bredd;
	}

	// tar in en sträng färg som parameter
	// och ändrar färgen på polylinjen till
	// parametern
	public void setFarg (String farg)
	{
		this.farg = farg;
	}

	// tar in en bredd som parameter
	// och ändrar bredden på polylinjen till
	// parametern
	public void setBredd (int bredd)
	{
		this.bredd = bredd;
	}

	// metod som retunerar längden av en polylinje
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

	// metod som tar in ett hörn som parameter
	// och lägger in hörnet längst bak i polylinjen
	public void laggTill (Punkt horn)
	{
		Punkt[] h = new Punkt[this.horn.length + 1];

		 int i = 0;
		 for (i = 0; i < this.horn.length; i++)
			h[i] = this.horn[i];
		 h[i] = new Punkt (horn);

		 this.horn = h;
	}

	// metod som tar in ett hörn och ett namn som parameter,
	// och sätter hörnet framför ett annat hörn med namnet
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

			int j = check == true ? i + 1 : i;

			h[j] = new Punkt (this.horn[i]);
		}

		this.horn = h;
	}

	// metod som tar in ett namn på ett hörn
	// och tar bort detta hörn från polylinjen
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

			int j = check == true ? i - 1 : i;

			h[j] = new Punkt(this.horn[i]);
		}

		this.horn = h;
	}

	// polylinje iterator
	public class PolylinjeIterator
	{
	    private int    aktuell = -1;

	    public PolylinjeIterator ()
	    {
	        if (Polylinje.this.horn.length > 0)
	            aktuell = 0;
	    }

	    public boolean finnsHorn ()
	    {
	        return aktuell != -1;
	    }

	    public Punkt horn () throws java.util.NoSuchElementException
	    {
	        if (!this.finnsHorn ())
	            throw new java.util.NoSuchElementException (
	                                     "slut av iterationen");

		Punkt    horn = Polylinje.this.horn[aktuell];

		return horn;
	    }

	    public void gaFram ()
	    {
		if (aktuell >= 0  &&
		    aktuell < Polylinje.this.horn.length - 1)
		    aktuell++;
		else
		    aktuell = -1;
	    }
	}
}
