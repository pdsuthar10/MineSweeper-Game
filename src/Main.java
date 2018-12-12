/*Name:PRIYAM SUTHAR*/
/*ID: 201501249*/
/*MINESWEEPER*/


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

	public static void main(String args[]) throws FileNotFoundException, CloneNotSupportedException{
		Board game=new Board();
		int input,input1,input2;												/*First I will take the input from the files*/
		Scanner sc=new Scanner(new File("input.txt"));
		input1=sc.nextInt();
		game.rows=input1;											/*Assigning the variables its value*/
		sc.nextLine();
		input2=sc.nextInt();
		game.cols=input2;
		sc.nextLine();
		input=sc.nextInt();
		game.noOfBombs=input;
		sc.nextLine();
		int xco,yco;
		game.board=new Cells[input1][input2];	
		game.startBoard(input1,input2);
		Cells [][]oldBoard=new Cells[input1][input2];
		Cells [][]oldBoard2=new Cells[input1][input2];
		for(int i=0;i<game.rows;i++)
			for(int j=0;j<game.cols;j++)
			{
				oldBoard[i][j]=(Cells)game.board[i][j].clone();
			}
		for(int i=0;i<game.rows;i++)
			for(int j=0;j<game.cols;j++)
			{
				oldBoard2[i][j]=(Cells)game.board[i][j].clone();
			}
		
		for(int i=0;i<game.noOfBombs;i++)
		{
			xco=sc.nextInt();
			yco=sc.nextInt();
			game.plantingMines(xco, yco);
			if(i!=game.noOfBombs-1)
			sc.nextLine();
		}
		sc.nextLine();
		int undo;
		undo=sc.nextInt();
		game.countNeighbours(game.board);
		String field[]=new String[3];
		sc=new Scanner(System.in);
		int turn=0,fl;
		System.out.println("****************M I N E S W E E P E R******************");
		System.out.println("\t<---INSTRUCTIONS--->");
		System.out.println("-->Input Format:: Row Column Flag");
		System.out.println("-->If you want to open the box keep FLAG=1. If you want to keep a flag give FLAG=0");
		System.out.println("-->Input should be given keeping in mind the row number and column number starts from 0");
		System.out.println("\n\nLETS START THE GAME!!");
		System.out.println("You have "+undo+" remaining lives.");
		while(true)
		{
			System.out.println("\n\nGive an input for your play");
			field=sc.nextLine().split(" ");
			xco=Integer.parseInt(field[0]);
			yco=Integer.parseInt(field[1]);
			fl=Integer.parseInt(field[2]);
			if(xco>game.rows-1 || xco<0)
			{
				System.out.println("Enter a valid input of X-coordinate. Input should be between 0 and "+game.rows--);
				continue;
			}
			if(yco>game.cols-1 || yco<0)
			{
				System.out.println("Enter a valid input of Y-coordinate.Input should be between 0 and "+game.cols--);
				continue;
			}
			if(!(fl==0 || fl==1))
			{
				System.out.println("Enter a valid input of Flag. It should be either 0 or 1");
				continue;
			}
			game.open(xco, yco, fl);
			game.winning();
			turn++;
			if(game.win==true){
				System.out.println("$$$$$$$$$$$$-->  C O N G R A T U L A T I O N S  <--$$$$$$$$$$$$\n\t\tYou have won the game!!!!!");
				System.out.println("Total turns played: "+turn);
				System.out.println("Life remaining: "+undo);
				break;
			}
			if(game.finish==false)
			{
				System.out.println("<*******Total turns played: "+turn+" \tLife Remaining:"+undo+"*********>");
				game.showBoard();
				for(int i=0;i<game.rows;i++)
					for(int j=0;j<game.cols;j++)
					{
						oldBoard[i][j]=(Cells)game.board[i][j].clone();
					}
			
			}
			else
			{
				if(undo!=0)
				{
				System.out.println("OOPS!!! You stepped on a bomb.");
				System.out.println("Do you want to use your life? \nType Yes/No \n");
				String in;
				in=sc.nextLine();
					if(in.equalsIgnoreCase("Yes") || in.equalsIgnoreCase("undo"))
					{
						undo--;
						System.out.println("<**********Total turns played: "+turn+" \tLife Remaining:"+undo+"*********>");
						for(int i=0;i<game.rows;i++)
							for(int j=0;j<game.cols;j++)
							{
								game.board[i][j]=(Cells)oldBoard[i][j].clone();
							}
						game.showBoard();
						game.finish=false;
					}
					else 
					{
						System.out.println("<*******Total turns played: "+turn+" \tLife Remaining:"+undo+"*********>");
						System.out.println("BOOM!! You lost the game. Better luck next time!!");
						break;
					}
				}
				else
				{
					System.out.println("Sorry !! You used up all your remaining life.");
					System.out.println("BOOM!! You lost the game. Better luck next time!!");
					break;
				}
			}
		}
		}
	}		
