//@author nahata2
//@review jliu67
//package game;
import java.util.Scanner;
public class see 
{
	public static void main(String args[])
	{	
		Scanner sn=new Scanner(System.in);
		imp obj=new imp();
		obj.fill();
		obj.calc();
		obj.solve();
		obj.disp();
		System.out.println("Your time starts now! You have 1 minute!");
		long time = System.currentTimeMillis();
		long check = System.currentTimeMillis();
		while(check-time<60000)
		{
			System.out.println("Enter your word : ");
			String in=sn.nextLine().toLowerCase();
			obj.eval(in);
			check=System.currentTimeMillis();
		}
		Zen.closeWindow();
		Zen.create(300, 300, "");
		Zen.drawText(Integer.toString(obj.getScore()),100,100);
	}
}
