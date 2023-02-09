import java.util.Random;

public class Chessboard
{
	public static final Random rand = new Random ();

	// undagtagsklass
	class NotValidFieldException extends Exception
	{
		public NotValidFieldException() {}

		public NotValidFieldException(String message)
		{
			super(message);
		}
	}

    public static class Field
    {
        private char    row;
        private byte    column;
        private Chesspiece    piece = null;
        private boolean    marked = false;

		// Konstruktor som beskriver raderna och kolumnerna
		// på schackbräd fältet
        public Field (char row, byte column)
        {
			this.row = row;
			this.column = column;
		}

		// Sätter en chesspiece pjäs på fältet
        public void put (Chesspiece piece) {this.piece = piece;}

		// Tar bort en chesspiece från fältet
        public Chesspiece take ()
        {
			Chesspiece p = this.piece;
			this.piece = null;
			return p;
		}

		// Sätter marked till true
		// när en position på fältet är taget
        public void mark () {this.marked = true;}

		// Sätter marked till false och avmarkerar
		// positionerna på fältet
        public void unmark ()  {this.marked = false;}

		// Retunerar positionerna som xx eller --
		// beroende på markerarde platser
        public String toString ()
        {
            String    s = (marked)? "xx" : "--";
            return (piece == null)? s : piece.toString ();
        }
    }

    public static final int    NUMBER_OF_ROWS = 8;
    public static final int    NUMBER_OF_COLUMNS = 8;

    public static final int    FIRST_ROW = 'a';
    public static final int    FIRST_COLUMN = 1;

	// Skapar en två dimensionel array som beskriver fältet
	// och som lagrar element med lämpliga rader och kolumner
    private Field[][]    fields;

    public Chessboard ()
    {
        fields = new Field[NUMBER_OF_ROWS][NUMBER_OF_COLUMNS];
        char    row = 0;

		byte    column = 0;
		for (int r = 0; r < NUMBER_OF_ROWS; r++)
		{
			row = (char) (FIRST_ROW + r);
			column = FIRST_COLUMN;
			for (int c = 0; c < NUMBER_OF_COLUMNS; c++)
			{
				fields[r][c] = new Field (row, column);
				column++;
			}
		}
    }

	// Retunerar spelbrädad med rader och kolumner
	// och de markerade fälten
    public String toString ()
    {
		String s = "   1  2  3  4  5  6  7  8 " + "\n";
		for(int i = 1; i <= NUMBER_OF_ROWS; i++)
		{
			char c = (char) (i + 96);
			s += c + " ";

			for(int j = 1; j <= NUMBER_OF_COLUMNS; j++)
			{
				s += " " + this.fields[i - 1][j - 1].toString();
			}

			s += "\n";
		}

		return s;
	}

	// Kollar om ett specifikt fält är giltigt
    public boolean isValidField (char row, byte column)
    {
    	return row >= 'a' && row <= 'h' && column > 0 && column <= NUMBER_OF_COLUMNS;
	}

    public abstract class Chesspiece
    {
		private char    color;
		// w - white, b - black

		private char    name;
		// K - King, Q - Queen, R - Rook, B - Bishop, N - Knight,
		// P � Pawn

		protected char    row = 0;
		protected byte    column = -1;

		// Chesspiece konstruktor
		protected Chesspiece (char color, char name)
		{
			this.color = color;
			this.name = name;
		}

		// Skriver ut färgen och namnet på en spelpjäs
		public String toString () {return "" + color + name;}

		// Kollar om pjässen är på ett specifikt fält
		public boolean isOnBoard () {return Chessboard.this.isValidField (row, column);}

		// Tar in en specifik rad och kolumn och sätter den valda pjäsen
		// på den plastsen
		public void moveTo (char row, byte column) throws NotValidFieldException
		{
			if (!Chessboard.this.isValidField (row, column))
		 		throw new NotValidFieldException ("bad field: " + row + column );

			this.row = row;
			this.column = column;

			int    r = row - FIRST_ROW;
			int    c = column - FIRST_COLUMN;
			Chessboard.this.fields[r][c].put (this);

		}

		// Tar bort pjäsen från den specifika platsen
		public void moveOut ()
		{
			Chessboard.this.fields[row - FIRST_ROW][column - FIRST_COLUMN].take();
			this.row = 0;
			this.column = -1;
		}

		// Sätter en pjäs på en random plats i fältet
		public void randomMove ()
		{
			char[] r = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
			byte[] c = {1, 2, 3, 4, 5, 6, 7, 8};

			int i = rand.nextInt(3);
			int j = rand.nextInt(8);
			this.row = r[i];
			this.column = c[j];

			int    row = r[i] - FIRST_ROW;
			int    col = c[j] - FIRST_COLUMN;
			Chessboard.this.fields[row][col].put (this);

		}

		public abstract void markReachableFields ();

		public abstract void unmarkReachableFields ();
	}

	// bonde klass
	public class Pawn extends Chesspiece
	{
		// Konstruktor
		public Pawn (char color, char name)
		{
			super (color, name);
		}

		public void markReachableFields ()
		{
			byte    col = (byte) (column + 1);
			if (Chessboard.this.isValidField (row, col))
			{
				int    r = row - FIRST_ROW;
				int    c = col - FIRST_COLUMN;
				Chessboard.this.fields[r][c].mark ();
			}
		}

		public void unmarkReachableFields ()
		{
			byte    col = (byte) (column + 1);
			if (Chessboard.this.isValidField (row, col))
			{
				int    r = row - FIRST_ROW;
				int    c = col - FIRST_COLUMN;
				Chessboard.this.fields[r][c].unmark ();
			}
		}
    }

	// Torn klass
    public class Rook extends Chesspiece
    {
		// Konstruktor
		public Rook (char color, char name)
		{
			super(color, name);
		}

		public void markReachableFields ()
		{
			for(char ro = FIRST_ROW; ro <= 'h'; ro++)
			{
				if (Chessboard.this.isValidField (ro, this.column))
				{
					int    r = ro - FIRST_ROW;
					int    c = this.column - FIRST_COLUMN;
					Chessboard.this.fields[r][c].mark ();
				}
			}

			for(byte col = FIRST_COLUMN; col <= NUMBER_OF_COLUMNS; col++)
			{
				if (Chessboard.this.isValidField (this.row, col))
				{
					int    r = this.row - FIRST_ROW;
					int    c = col - FIRST_COLUMN;
					Chessboard.this.fields[r][c].mark ();
				}
			}
		}

		public void unmarkReachableFields ()
		{
			for(char row = FIRST_ROW; row <= 'h'; row++)
			{
				if (Chessboard.this.isValidField (row, this.column))
				{
					int    r = row - FIRST_ROW;
					int    c = this.column - FIRST_COLUMN;
					Chessboard.this.fields[r][c].unmark ();
				}
			}

			for(byte col = FIRST_COLUMN; col <= NUMBER_OF_COLUMNS; col++)
			{
				if (Chessboard.this.isValidField (this.row, col))
				{
					int    r = this.row - FIRST_ROW;
					int    c = col - FIRST_COLUMN;
					Chessboard.this.fields[r][c].unmark ();
				}
			}
		}
	}

	// Häst klass
    public class Knight extends Chesspiece
    {
		// Konstruktor
		public Knight (char color, char name)
		{
			super (color, name);
		}

		public void markReachableFields ()
		{
			char row = this.row;
			byte col = this.column;

			int[] v = {-2, 1, 2, 1, 0, -1, -2, -1};
			int[] w = {-1, -1, 0, 1, 2, 1, 0, -1};

			for(int i = 0, j = 0; i < v.length; i++, j++)
			{
				row += v[i];
				col += w[j];
				if (Chessboard.this.isValidField (row, col))
				{
					int    r = row - FIRST_ROW;
					int    c = col - FIRST_COLUMN;
					Chessboard.this.fields[r][c].mark ();
				}
			}
		}

		public void unmarkReachableFields()
		{
			char row = this.row;
			byte col = this.column;

			int[] v = {-2, 1, 2, 1, 0, -1, -2, -1};
			int[] w = {-1, -1, 0, 1, 2, 1, 0, -1};

			for(int i = 0, j = 0; i < v.length; i++, j++)
			{
				row += v[i];
				col += w[j];
				if (Chessboard.this.isValidField (row, col))
				{
					int    r = row - FIRST_ROW;
					int    c = col - FIRST_COLUMN;
					Chessboard.this.fields[r][c].unmark ();
				}
			}
		}
	}

	// Löpare klass
    public class Bishop extends Chesspiece
    {
		// Konstruktor
		public Bishop (char color, char name)
		{
			super (color, name);
		}

		public void markReachableFields ()
		{
			char row = this.row;
			byte col = this.column;
			boolean check = false;
			int reset = 0;

			while(reset <= 3)
			{
				col = reset >= 2 ? (col += 1) : (col -= 1);

				row = check ? (row += 1) : (row -= 1);

				if (Chessboard.this.isValidField (row, col))
				{
					int    r = row - FIRST_ROW;
					int    c = col - FIRST_COLUMN;
					Chessboard.this.fields[r][c].mark ();
				}
				else
				{
					reset += 1;
					row = this.row;
					col = this.column;
					check = true;
				}
				if(reset == 3)
					check = false;
			}
		}

		public void unmarkReachableFields()
		{
			char row = this.row;
			byte col = this.column;
			boolean check = false;
			int reset = 0;

			while(reset <= 3)
			{
				col = reset >= 2 ? (col += 1) : (col -= 1);

				row = check ? (row += 1) : (row -= 1);

				if (Chessboard.this.isValidField (row, col))
				{
					int    r = row - FIRST_ROW;
					int    c = col - FIRST_COLUMN;
					Chessboard.this.fields[r][c].unmark ();
				}
				else
				{
					reset += 1;
					row = this.row;
					col = this.column;
					check = true;
				}
				if(reset == 3)
					check = false;
			}
		}
	}

	// Dam klass
    public class Queen extends Chesspiece
    {
		// Konstruktor
		public Queen (char color, char name)
		{
			super(color, name);
		}

		public void markReachableFields ()
		{
			char row = this.row;
			byte col = this.column;
			boolean check = false;
			int reset = 0;

			while(reset <= 3)
			{
				col = reset >= 2 ? (col += 1) : (col -= 1);

				row = check ? (row += 1) : (row -= 1);

				if (Chessboard.this.isValidField (row, col))
				{
					int    r = row - FIRST_ROW;
					int    c = col - FIRST_COLUMN;
					Chessboard.this.fields[r][c].mark ();
				}
				else
				{
					reset += 1;
					row = this.row;
					col = this.column;
					check = true;
				}
				if(reset == 3)
					check = false;
			}

			for(row = FIRST_ROW; row <= 'h'; row++)
			{
				if (Chessboard.this.isValidField (row, this.column))
				{
					int    r = row - FIRST_ROW;
					int    c = this.column - FIRST_COLUMN;
					Chessboard.this.fields[r][c].mark ();
				}
			}

			for(col = FIRST_COLUMN; col <= NUMBER_OF_COLUMNS; col++)
			{
				if (Chessboard.this.isValidField (this.row, col))
				{
					int    r = this.row - FIRST_ROW;
					int    c = col - FIRST_COLUMN;
					Chessboard.this.fields[r][c].mark ();
				}
			}
		}

		public void unmarkReachableFields()
		{
			char row = this.row;
			byte col = this.column;
			boolean check = false;
			int reset = 0;

			while(reset <= 3)
			{
				col = reset >= 2 ? (col += 1) : (col -= 1);

				row = check ? (row += 1) : (row -= 1);

				if (Chessboard.this.isValidField (row, col))
				{
					int    r = row - FIRST_ROW;
					int    c = col - FIRST_COLUMN;
					Chessboard.this.fields[r][c].unmark ();
				}
				else
				{
					reset += 1;
					row = this.row;
					col = this.column;
					check = true;
				}
				if(reset == 3)
					check = false;
			}

			for(row = FIRST_ROW; row <= 'h'; row++)
			{
				if (Chessboard.this.isValidField (row, this.column))
				{
					int    r = row - FIRST_ROW;
					int    c = this.column - FIRST_COLUMN;
					Chessboard.this.fields[r][c].unmark ();
				}
			}

			for(col = FIRST_COLUMN; col <= NUMBER_OF_COLUMNS; col++)
			{
				if (Chessboard.this.isValidField (this.row, col))
				{
					int    r = this.row - FIRST_ROW;
					int    c = col - FIRST_COLUMN;
					Chessboard.this.fields[r][c].unmark ();
				}
			}
		}
	}

	// Kung klass
    public class King extends Chesspiece
    {
		// Konstruktor
		public King (char color, char name)
		{
			super(color, name);
		}

		public void markReachableFields ()
		{
			byte col = (byte)(this.column - 1);
			char row = (char)(this.row + 1);
			byte q = col;
			char w = row;

			for(col = (byte)(this.column - 1); col < (q + 3); col++)
			{
				for(row = (char)(this.row + 1); row > (w - 3); row--)
				{
					if (Chessboard.this.isValidField (row, col))
					{
						int    r = row - FIRST_ROW;
						int    c = col - FIRST_COLUMN;
						Chessboard.this.fields[r][c].mark ();
					}
				}
			}
		}

		public void unmarkReachableFields()
		{
			byte co = this.column;
			char ro = this.row;

			for(byte col = (byte)(this.column - 1); col < (co + 3); col++)
			{
				for(char row = (char)(this.row + 1); row > (ro - 3); row--)
				{
					if (Chessboard.this.isValidField (row, col))
					{
						int    r = row - FIRST_ROW;
						int    c = col - FIRST_COLUMN;
						Chessboard.this.fields[r][c].unmark ();
					}
				}
			}
		}
	}
}