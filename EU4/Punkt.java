class Punkt
{
	private String namn;
	private int x;
	private int y;

	// konstruktorer
	public Punkt ()
	{
		this.namn = "0";
		this.x = 0;
		this.y = 0;
	}

	// konstruktor med parametrar för namn,
	// värde på x och y koordinaterna
	Punkt (String namn, int x, int y)
	{
		this.namn = namn;
		this.x = x;
		this.y = y;
	}

	// konstruktor kopia
	public Punkt (Punkt p)
	{
		this.namn = p.namn;
		this.x = p.x;
		this.y = p.y;
	}

	// toString metod, retunerar som en sträng
	public String toString ()
	{
		return "(" + this.namn + ", " + this.x + ", " + this.y + ")";
	}

	// retunerar nämnet av en punkt
	public String getNamn ()
	{
		return this.namn;
	}

	// retunerar x-värdet av en punkt
	public int getX ()
	{
		return this.x;
	}

	// retunerar y-värdet av en punkt
	public int getY ()
	{
		return this.y;
	}

	// tar en punkt om parameter och retunerar
	// avståndet mellan den punkten och en annan
	public double avstand (Punkt p)
	{
		return Math.sqrt (p.x - this.x) * (p.x - this.x)
					   + (p.y - this.y) * (p.y - this.y);
	}

	// tar in en punkt som parameter och retunerar
	// ett boolean värde på om den punkten
	// och en anna är lika
	public boolean equals (Punkt p)
	{
		boolean resultat;

		if(p.x == this.x && p.y == this.y)
			resultat = true;
		else
			resultat = false;

		return resultat;
	}

	// tar in ett namn som parameter och
	// sätter det namnet som ett nytt namn
	// för en punkt
	public void setNamn (String namn)
	{
		this.namn = namn;
	}

	// tar in ett x-värde som parameter och
	// sätter det x-värde som ett nytt x-värde
	// för en punkt
	public void setX (int x)
	{
		this.x = x;
	}

	// tar in ett y-värde som parameter och
	// sätter det y-värde som ett nytt y-värde
	// för en punkt
	public void setY (int y)
	{
		this.y = y;
	}
}