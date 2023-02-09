import java.io.*;
import java.util.*;
import java.util.Date;

class ChessboardTest
{
	public static void main (String[] args) throws Exception
	{
		Chessboard chessBoard = new Chessboard();

		Chessboard.Chesspiece[] pieces = new Chessboard.Chesspiece[6];
		pieces[0] = chessBoard.new Pawn ('w', 'P');
		pieces[1] = chessBoard.new Rook ('w', 'R');
		pieces[2] = chessBoard.new Knight ('w', 'N');
		pieces[3] = chessBoard.new Bishop ('b', 'B');
		pieces[4] = chessBoard.new Queen ('b', 'Q');
		pieces[5] = chessBoard.new King ('b', 'K');

		for(Chessboard.Chesspiece piece : pieces)
		{
			piece.randomMove();
			piece.markReachableFields();
			System.out.println(chessBoard);
			piece.unmarkReachableFields();
			piece.moveOut();
			Thread.sleep(1000);
		}
	}
}