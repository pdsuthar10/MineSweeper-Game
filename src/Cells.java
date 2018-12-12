
public class Cells implements Cloneable{
	int noOfBombsAround;
	boolean revealed=false;
	boolean hasBomb=false;
	char figure;
	boolean flag=false;
	
	
	public static int countNeighbours(int x,int y,Cells [][]board,int rows,int cols)
	{
		int bombs=0;
		for(int i=x-1;i<=x+1;i++)
		{
			for(int j=y-1;j<=y+1;j++)
			{
				if(!(i<0 || j<0 || i>rows-1 || j>cols-1) && board[i][j].hasBomb==true && !(i==x && j==y))
					bombs++;
			}
		}
//		System.out.println(bombs);
		
		return bombs;
	}
	
	protected Object clone() throws CloneNotSupportedException{
		return super.clone();
	}

}
