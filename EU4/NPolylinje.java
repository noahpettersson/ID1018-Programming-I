import java.util.Arrays;
import java.util.Iterator;

public class NPolylinje implements Polylinje
{
    private static class Nod
    {
	    public Punkt    horn;
	    public Nod      nastaNod;

	    public Nod (Punkt horn)
	    {
			this.horn = horn;
			nastaNod = null;
	    }
	}

	private Nod    horn;
	private String     farg = "svart";
	private int        bredd = 1;  // pixlar

	public NPolylinje ()
	{
	    this.horn = null;
	}

	public NPolylinje (Punkt[] horn)
	{
	    if (horn.length > 0)
	    {
			Nod   nod = new Nod (new Punkt (horn[0]));
			this.horn = nod;
			int    pos = 1;
			while (pos < horn.length)
			{
		    	nod.nastaNod = new Nod (new Punkt (horn[pos++]));
		    	nod = nod.nastaNod;
			}
	    }
	}

	// ytterligare kod h�r
	public String toString()
	{
		String s = "{[";
		Nod n = this.horn;
		if(n != null)
		{
			s += n.horn;
			n = n.nastaNod;
			while(n != null)
			{
				s += n.horn;
				n = n.nastaNod;
			}

			s += "]}" + ", " + farg + ", " + bredd + "}";
		}

		return s;
	}

	public Punkt[] getHorn ()
	{
		int p = 0;
		Nod n = this.horn;

		while(n != null)
		{
			n = n.nastaNod;
			p++;
		}

		Punkt[] h = new Punkt[p];
		n = this.horn;
		for(int i  = 0; i < p; i++)
		{
			h[i] = n.horn;
			n = n.nastaNod;
		}

		return h;
	}

	public String getFarg () {return this.farg;}

	public int getBredd () {return this.bredd;}

	public double langd ()
	{
		int p;
		Nod n = horn;
		for(p = 0; n != null; p++)
			n = n.nastaNod;
		return p;
	}

	public void setFarg (String farg) {this.farg = farg;}

	public void setBredd (int bredd) {this.bredd = bredd;}

	public void laggTill (Punkt horn)
	{
		if(this.horn == null)
			this.horn = new Nod(horn);
		else
		{
			Nod n = this.horn;
			while(n.nastaNod != null)
				n = n.nastaNod;

			n.nastaNod = new Nod(horn);
		}

	}

	public void laggTillFramfor (Punkt horn, String hornNamn)
	{
		Nod n = this.horn;
		while(n != null)
		{
			if(n.nastaNod.horn.getNamn().equals(hornNamn))
			{
				Nod t = n.nastaNod;
				n.nastaNod = new Nod(horn);
				n.nastaNod.nastaNod = t;

				return;
			}

			n = n.nastaNod;
		}
	}

    public void taBort (String hornNamn)
    {
		Nod n = this.horn;
		while(n != null)
		{
			if(n.nastaNod.horn.getNamn().equals(hornNamn))
			{
				n.nastaNod = n.nastaNod.nastaNod;
				return;
			}

			n = n.nastaNod;
		}
	}

	public Iterator<Punkt> iterator()
	{
		return Arrays.asList(this.getHorn()).iterator();
	}
}