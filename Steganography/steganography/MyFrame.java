import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.io.*;
public class MyFrame extends JFrame implements ActionListener
{
	JLabel l1,l2,l3,l4,l5;
	JButton b1,b2,b;
	JTextField t1,t2,t3,t4;
	MyFrame()
	{
		super("ENCRYPTER DECRYPTER");
		l1=new JLabel("ENTER THE KEY(8 alphabetical characters without repetition)");
		l2=new JLabel("ENTER THE TEXT FILE PATH");
		l3=new JLabel("\n TEXT FILE PATH AFTER ENCRYPTION");
		l4=new JLabel("\n \n TEXT FILE PATH AFTER DECRYPTION");
		l5=new JLabel("                                                                                         ");
		b1=new JButton("ENCRYPT");
		b2=new JButton("DECRYPT");
		b=new JButton("browse");
		t1=new JTextField(30);
		t2=new JTextField(30);
		t3=new JTextField(30);
		t4=new JTextField(30);
		setSize(400,300);
		setVisible(true);
		setLayout(new FlowLayout());
		this.add(l1);
		this.add(t1);
		this.add(l2);
		this.add(t2);
		this.add(b);
		this.add(b1);
		this.add(l3);
		this.add(t3);
		this.add(b2);
		this.add(l5);
		this.add(l4);
		this.add(t4);
		b.addActionListener(this);
		b1.addActionListener(this);
		b2.addActionListener(this);

	}
	public void actionPerformed(ActionEvent e) 
	{
			
		String str=e.getActionCommand();
		if(str.equals("browse"))
		{
		Chooser frame = new Chooser();
		t2.setText(frame.fileName);
		}
		else if(str.equals("ENCRYPT"))
		{
			int i,j,temp=0;
			String s=new String();
			s=t1.getText();
			for(i=0;i<s.length();i++)
			{
				char ch=s.charAt(i);
				for(j=i+1;j<s.length();j++)
				if(ch==s.charAt(j))
				{
					temp=1;
					break;
				}	
				
			}
			char[] c=s.toCharArray();
			if((c.length!=8)||(temp==1))
			{
				ErDialog r=new ErDialog();
			}
			else
			{
				try
				{
				File f=new File(Chooser.fileName);
				FileReader fr=new FileReader(f);
				char a[]=new char[(int)f.length()];
				fr.read(a);
				long l=a.length;
				int m,q;
				m=(int)l/8+1;
				long n=l%8;
				int kn[]=new int[8];
				//System.out.println(a.length);
				//System.out.println(n);
				int u=0;
				char ar[][]=new char[m][8];
				u=0;
				int v=1;
				char key='a';
				while(v<=8)
				{
					
					for(int z=0;z<8;z++)
					{
						if(c[z]==key)
						{
							kn[z]=v;
							v++;
							
						}
					}
					key++;
					//System.out.println(key);
				}
				
				for(int y=0;y<m-1;y++)
				{	for(int z=0;z<8;z++)
					{
						ar[y][z]=a[u];
						u++;
						//System.out.println(ar[y][z]);

					}

				System.out.println("\n");
				}
				for(int z=0;z<n;z++)
				{
					ar[m-1][z]=a[u];
					u++;
			 		//System.out.println(ar[m-1][z]);

				}
				for(int z=(int)n;z<8;z++)
				{
					ar[m-1][z]=' ';
					//System.out.println(ar[m-1][z]);	
				}
				fr.close();
				String path=f.getParentFile()+File.separator+"encryptedfile.txt";
				File file1 = new File(path);
				file1.createNewFile();
				t3.setText(path);
				FileWriter fw = new FileWriter(file1);
	       			BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw);
				int n1=1;
				while(n1<9)
				{
	      			for(int r1=0;r1<8;r1++)
				{ 
					if(kn[r1]==n1)
					for(int c1=0;c1<m;c1++)
					{
						 char x = ar[c1][r1];
						//System.out.println(x);
        					 out.write(x);
					}
				}
				n1++;
				}
				out.close();
				}
				catch(IOException b) 	
				{
					b.printStackTrace();
				}
			}
		}
		else if(str.equals("DECRYPT"))
		{
			int i1,j1,temp1=0;
			String s1=new String();
			s1=t1.getText();
			String s2=new String();
			s2=t3.getText();
			for(i1=0;i1<s1.length();i1++)
			{
				char ch1=s1.charAt(i1);
				for(j1=i1+1;j1<s1.length();j1++)
				if(ch1==s1.charAt(j1))
				{
					temp1=1;
					break;
				}	
				
			}
			char[] c1=s1.toCharArray();
			if((c1.length!=8)||(temp1==1))
			{
				ErDialog r1=new ErDialog();
			}
			else
			{
				try
				{
				File f1=new File(s2);
				FileReader fr1=new FileReader(f1);
				char a1[]=new char[(int)f1.length()];
				fr1.read(a1);
				long l1=a1.length;
				int m1,q1;
				m1=(int)l1/8+1;
				long n1=l1%8;
				int kn1[]=new int[8];
				//System.out.println(a1.length);
				//System.out.println(n1);
				int u1=0;
				char ar1[][]=new char[m1][8];
				int v1=1;
				char key1='a';
				while(v1<=8)
				{
					
					for(int z1=0;z1<8;z1++)
					{
						if(c1[z1]==key1)
						{
							kn1[z1]=v1;
							v1++;
							
						}
					}
					key1++;
					//System.out.println(key1);
				}
				int nn=1;
				while(nn<9)
				{
	      				for(int r2=0;r2<8;r2++)
					{ 
						if(kn1[r2]==nn)
							for(int c2=0;c2<m1-1;c2++)
							{
								ar1[c2][r2]=a1[u1];
								//System.out.println(ar1[c2][r2]);
								u1++;
							}
					}
					nn++;
					//System.out.println("f");
				}
				fr1.close();
				String path1=f1.getParentFile()+File.separator+"decryptedfile.txt";
				//System.out.println(path1);
				t4.setText(path1);
				File file2 = new File(path1);
				file2.createNewFile();
				FileWriter fw1 = new FileWriter(file2);
	       			BufferedWriter bw1 = new BufferedWriter(fw1);
				PrintWriter out1 = new PrintWriter(bw1);
				for(i1=0;i1<m1;i1++)
					for(j1=0;j1<8;j1++)
					{
						char x1 = ar1[i1][j1];
						//System.out.println(ar1[i1][j1]);
        					out1.write(x1);
					}
						
				out1.close();
				}
				catch(IOException b) 	
				{
					b.printStackTrace();
				}
			}
		}
				
	}
	public static void main(String args[])throws IOException
	{
		MyFrame frame=new MyFrame();
		
	}
}
class Chooser extends JFrame 
{

	JFileChooser chooser;
	static String fileName;

	public Chooser() 
	{
		chooser = new JFileChooser();
		int r = chooser.showOpenDialog(new JFrame());
		if (r == JFileChooser.APPROVE_OPTION) 
		{
			fileName = chooser.getSelectedFile().getPath();
		}
	}
}
class ErDialog extends JFrame
{
	public ErDialog()	
	{
		
		new JDialog(this,"Dialog",false);
		setLayout(new FlowLayout());
		this.setVisible(true);
		this.setSize(300,300);		
		this.add(new JLabel("invalid key(key should be 8 characters without repeated letters)"));
		
	}
	
}