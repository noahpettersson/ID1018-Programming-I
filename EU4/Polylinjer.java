public class Polylinjer
{
	public static Polylinje shortestYellow(Polylinje[] lines)
	{
		Polylinje shortestLine = null;
		double shortestLineLength = Double.POSITIVE_INFINITY;
		for(int i = 0; i < lines.length; i++)
		{
			if (lines[i].getFarg() == "gul")
			{
				// Gör faktiskt något

				if (shortestLine == null)
					shortestLine = lines[i];

				else {
					double currentLineLength = 0;

					Punkt[] currentLineCorners = lines[i].getHorn();

					for (int j = 0; j < currentLineCorners.length - 1; j++)
						currentLineLength += currentLineCorners[j].avstand(currentLineCorners[j+1]);

					if (currentLineLength < shortestLineLength)
					{
						shortestLine = lines[i];
						shortestLineLength = currentLineLength;
					}
				}
			}
		}
		return shortestLine;
	}
}