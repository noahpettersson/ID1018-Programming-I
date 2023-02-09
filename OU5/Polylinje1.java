public class Polylinje1
{
	 private Punkt[] horn;
	 private String farg = "svart";
	 private int bredd = 1;

	public Polylinje1 ()
	{
		this.horn = new Punkt[0];
	}

	public Polylinje1 (Punkt[] horn)
	{
		Punkt[] h = horn;
		this.horn = new Punkt[h.length];
		for (int i = 0; i < h.length; i++)
			this.horn[i] = h[i];
	}

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

	public Punkt[] getHorn ()
	{
		return this.horn;
	}

	public String getFarg ()
	{
		return this.farg;
	}

	public int getBredd ()
	{
		return this.bredd;
	}

	public void setFarg (String farg)
	{
		this.farg = farg;
	}

	public void setBredd (int bredd)
	{
		this.bredd = bredd;
	}

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

	public void laggTill (Punkt horn)
	{
		Punkt[] h = new Punkt[this.horn.length + 1];

		 int i = 0;
		 for (i = 0; i < this.horn.length; i++)
			h[i] = this.horn[i];
		 h[i] = horn;

		 this.horn = h;
	}

	public void laggTillFramfor (Punkt horn, String hornNamn)
	{
		Punkt[] h = new Punkt[this.horn.length + 1];

		boolean check = false;
		for(int i = 0; i < this.horn.length; i++)
		{
			if(this.horn[i].getNamn() == hornNamn)
			{
				h[i] = horn;
				h[i + 1] = this.horn[i];
				check = true;
			}

			int j = check == true ? i + 1 : i;

			h[j] = this.horn[i];
		}

		this.horn = h;
	}

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

			int j = check == true ? i - 1 : i;

			h[j] = this.horn[i];
		}

		this.horn = h;
	}
}
