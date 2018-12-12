
public class Board {
	int rows,cols;
	Cells [][]board;
	int noOfBombs;
	boolean finish=false;
	boolean win=false;
	
	
	public void winning()
	{
		int count=0;
		int count1=0;
		for(int i=0;i<rows;i++)
			for(int j=0;j<cols;j++)
			{
				if(board[i][j].flag==true && board[i][j].hasBomb==true)
					count++;
			}
		for(int i=0;i<rows;i++)
			for(int j=0;j<cols;j++)
			{
				if(board[i][j].revealed==true && board[i][j].hasBomb==false)
					count1++;
			}
		if(count==this.noOfBombs && count1==(this.rows*this.cols)-count)
			win=true;
		else
			win=false;
		
	}
	public void startBoard(int row,int col)
	{	for(int i=0;i<row;i++)
		{
			for(int j=0;j<col;j++)
			{
				board[i][j]=new Cells();
			}
		}
	}
	
	public void plantingMines(int x,int y)
	{
		for(int i=0;i<this.rows;i++)
		{
			for(int j=0;j<this.cols;j++)
			{
				
				if(x==i && j==y)
				{
					board[i][j].hasBomb=true;
				}
				else
				{
					board[i][j].noOfBombsAround=0;
				}
			}
		}
	}
	public void countNeighbours(Cells [][]board)
	{
		for(int i=0;i<this.rows;i++)
		{
			for(int j=0;j<this.cols;j++)
			{
				board[i][j].noOfBombsAround=Cells.countNeighbours(i,j,board,rows,cols);
			}
		}
	}
	public void coord()
	{
		for(int i=0;i<rows;i++)
			for(int j=0;j<cols;j++)
			{
				if(board[i][j].hasBomb)
					System.out.println(""+i+" "+j);
			}
	}
	
	public void open(int x,int y,int flag)
	{
		if(flag==0 && board[x][y].flag==false)
		{
			board[x][y].flag=true;
			return;
		}
		if(flag==1 && board[x][y].hasBomb==true && board[x][y].flag==true)
		{
			finish=true;
			board[x][y].revealed=true;
			board[x][y].flag=false;
			showBoard();
			return;
		}
		if(board[x][y].hasBomb==false && board[x][y].noOfBombsAround>0 && board[x][y].revealed==false && flag==1 || board[x][y].flag==true)
		{
			board[x][y].revealed=true;
			board[x][y].flag=false;
		}
		else if(board[x][y].hasBomb==false && board[x][y].noOfBombsAround==0 && board[x][y].revealed==false && flag==1 || board[x][y].flag==true)
		{
			openBlanks(x,y);
			board[x][y].flag=false;
		}
		else if(board[x][y].hasBomb==true && board[x][y].flag==false)
		{
			finish=true;
			board[x][y].revealed=true;
			showBoard();
			return;
		}
	}
	
	public void openBlanks(int x,int y)
	{
		if(!(x<0 || y<0|| x>rows-1 || y>cols-1) && board[x][y].noOfBombsAround>0 && board[x][y].revealed==false)
		{
			board[x][y].revealed=true;
			return;
		}
		if(x<0 || y<0|| x>rows-1 || y>cols-1 || board[x][y].hasBomb==true || board[x][y].revealed==true )return;
		if(board[x][y].hasBomb==false)
		{
			board[x][y].revealed=true;
			openBlanks(x-1, y);
			openBlanks(x,y-1);
			openBlanks(x, y+1);
			openBlanks(x+1, y);
		}
	}
	
	public void showBoard()
	{
		for(int i=0;i<rows;i++)
		{
			for(int j=0;j<cols;j++)
			{
				if(board[i][j].flag==true)
				{
					System.out.print(" | ");
				}
				else{
				if(board[i][j].revealed==true && board[i][j].hasBomb==true)
					System.out.print(" * ");
				else if(board[i][j].revealed==true && board[i][j].hasBomb==false)
					System.out.print(" "+board[i][j].noOfBombsAround+" ");
				else if(board[i][j].revealed==false && board[i][j].flag==false)
					System.out.print(" - ");
				}
			}
			System.out.println("");
		}
	}
	
	public void showOldBoard(Cells [][]oldBoard)
	{
		for(int i=0;i<rows;i++)
		{
			for(int j=0;j<cols;j++)
			{
				if(oldBoard[i][j].flag)
				{
					System.out.print(" | ");
				}
				else{
				if(oldBoard[i][j].revealed==true && oldBoard[i][j].hasBomb==true)
					System.out.print(" * ");
				else if(oldBoard[i][j].revealed==true && oldBoard[i][j].hasBomb==false)
					System.out.print(" "+oldBoard[i][j].noOfBombsAround+" ");
				else if(oldBoard[i][j].revealed==false && oldBoard[i][j].flag==false)
					System.out.print(" - ");
				}
			}
			System.out.println("");
		}
	}
	

	

}
