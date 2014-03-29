//@author nahata2
//@review jliu67
package game;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class imp
{
	private char ar[][]=new char[5][5];
	private boolean blocked[][]=new boolean[5][5];
	private File f=new File("game/Dictionary.txt");
	private String word[];
	private int counter=0;
	private String solved[]=new String[2000];
	private boolean c[]=new boolean[2000];
	private int score=0,pos=50;
	private void eval(String x)//Checks to see if you word is valid.
	{
		for(int i=0;i<solved.length;i++)
		{
			if(x.equalsIgnoreCase(solved[i])&&c[i])
			{
				System.out.println("Good try : "+solved[i]);
				Zen.drawText(solved[i], 350, pos);
				pos+=25;
				score+=2*(x.length()-1);
				c[i]=false;
				return;
			}
		}
	}
	private int getScore()
	{
		return score;
	}
	public void disp()
	{
		Zen.create(600, 400, "");
		int x=50,y=50;
		for(int i=0;i<5;System.out.println(),i++)
		{
			for(int j=0;j<5;j++)
			{
				System.out.print(ar[i][j]);
				Zen.drawText(Character.toString(ar[i][j]), x, y);
				x+=50;
			}
			x=50;
			y+=50;
		}
	}
	public void calc()
	{
		int i,count=0;
		Scanner sn;
		try
		{
			sn=new Scanner(f);
			while(sn.hasNextLine())
			{
				sn.nextLine();
				count++;
			}
		}
		catch (FileNotFoundException e) 
		{
			 e.printStackTrace();
		}
		word=new String[count];
		try
		{
			sn=new Scanner(f);
			for(i=0;i<count;i++)
			{
				word[i]=sn.nextLine();
			}
		}
		catch (FileNotFoundException e) 
		{
			 e.printStackTrace();
		}
	}
	public void fill()
	{
		int i,j;
		for(i=0;i<5;i++)
		{
			for(j=0;j<5;j++)
			{
				ar[i][j]=(char)(97+(int)(Math.random()*26));
				blocked[i][j]=false;
			}
		}
		for(i=0;i<1000;c[i]=true,i++);
	}
	public void solve() //calls each array element
	{
		int i,j;
		for(i=0;i<5;i++)
		{
			for(j=0;j<5;j++)
			{
				solve(i,j,4,4,"",7);
			}
		}
	}
	private void solve(int x, int y, int mx, int my, String c, int n) //Recursively searches the stuff
	{
		check(c,0,word.length-1);
		if(x<0||x>mx||y<0||y>my||n<=0)
			return;
		if(blocked[x][y])
			return;
		blocked[x][y]=true;
		c+=ar[x][y];
		solve(x+1,y,4,4,c,n-1);
		solve(x+1,y+1,4,4,c,n-1);
		solve(x-1,y,4,4,c,n-1);
		solve(x-1,y+1,4,4,c,n-1);
		solve(x,y+1,4,4,c,n-1);
		solve(x+1,y-1,4,4,c,n-1);
		solve(x,y-1,4,4,c,n-1);
		solve(x-1,y-1,4,4,c,n-1);
		blocked[x][y]=false;
	}
	private void check(String s, int lo, int hi) //Binary Search
	{
		if(lo>hi)
			return;
		int mid=(lo+hi)/2;
		if(s.equals(word[mid]))
		{
			solved[counter++]=word[mid];
		}
		else if(s.compareTo(word[mid])<0)
			check(s,0,mid-1);
		else
			check(s,mid+1,hi);
	}
}
