import java.awt.Color;
import java.awt.Insets;
import java.awt.Container;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.JMenu;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.io.*;


public class Steganography_View extends JFrame
{
	private static int WIDTH  = 500;
	private static int HEIGHT = 400;
	
	private JTextArea 	input;
	private JScrollBar 	scroll,scroll2;
	private JButton		encodeButton,decodeButton;
	private JButton		encryptButton,decryptButton;
	private JLabel		image_input;
	
	private JMenu 		file,cryp;
	private JMenuItem 	encode,encrypt;
	private JMenuItem 	decode,decrypt;
	private JMenuItem 	exit,close;
	
	public Steganography_View(String name)
	{
		super(name);
		
		JMenuBar menu = new JMenuBar();
		
		JMenu file = new JMenu("STEG");file.setMnemonic('S');
		encode = new JMenuItem("Encode"); encode.setMnemonic('E'); file.add(encode);
		decode = new JMenuItem("Decode"); decode.setMnemonic('D'); file.add(decode);
		file.addSeparator();
		exit = new JMenuItem("Exit"); exit.setMnemonic('x'); file.add(exit);
		
		JMenu cryp = new JMenu("CRYP");cryp.setMnemonic('C');
		encrypt = new JMenuItem("Encrypt"); encrypt.setMnemonic('n'); cryp.add(encrypt);
		decrypt = new JMenuItem("Decrypt"); decrypt.setMnemonic('r'); cryp.add(decrypt);
		cryp.addSeparator();
		close = new JMenuItem("Close"); close.setMnemonic('o'); cryp.add(close);
		menu.add(file);
		menu.add(cryp);
		setJMenuBar(menu);
		setResizable(true);						//allow window to be resized: true?false
		setBackground(Color.lightGray);			//background color of window: Color(int,int,int) or Color.name
		setLocation(100,100);					//location on the screen to display window
        setDefaultCloseOperation(EXIT_ON_CLOSE);//what to do on close operation: exit, do_nothing, etc
        setSize(WIDTH,HEIGHT);					//set the size of the window
        setVisible(true);						//show the window: true?false
	}
	
	
	public JMenuItem	getEncode()		{ return encode;			}
	public JMenuItem	getEncrypt()		{ return encrypt;			}
	public JMenuItem	getDecode()		{ return decode;			}
	public JMenuItem	getDecrypt()		{ return decrypt;			}

	public JMenuItem	getExit()		{ return exit;				}
	public JMenuItem	getClose()		{ return close;				}

	public JTextArea	getText()		{ return input;				}
	public JLabel		getImageInput()	{ return image_input;		}
	public JPanel		getTextPanel()	{ return new Text_Panel();	}
	public JPanel		getEncryptPanel()	{ return new Encrypt_Panel();	}
	public JPanel		getImagePanel()	{ return new Image_Panel();	}
	public JButton		getEButton()	{ return encodeButton;		}
	public JButton		getEncryptButton()	{ return encryptButton;		}

	public JButton		getDButton()	{ return decodeButton;		}
	
	public JButton		getDecryptButton()	{ return decryptButton;		}
	private class Text_Panel extends JPanel
	{
		public Text_Panel()
		{
			GridBagLayout layout = new GridBagLayout(); 
			GridBagConstraints layoutConstraints = new GridBagConstraints(); 
			setLayout(layout);
			
			input = new JTextArea();
			layoutConstraints.gridx 	= 0; layoutConstraints.gridy = 0; 
			layoutConstraints.gridwidth = 1; layoutConstraints.gridheight = 1; 
			layoutConstraints.fill 		= GridBagConstraints.BOTH; 
			layoutConstraints.insets 	= new Insets(0,0,0,0); 
			layoutConstraints.anchor 	= GridBagConstraints.CENTER; 
			layoutConstraints.weightx 	= 1.0; layoutConstraints.weighty = 50.0;
			JScrollPane scroll = new JScrollPane(input,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
			layout.setConstraints(scroll,layoutConstraints);
			scroll.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
	    	add(scroll);
	    	
	    	encodeButton = new JButton("Encode Now");
		layoutConstraints.gridx 	= 0; layoutConstraints.gridy = 1; 
			layoutConstraints.gridwidth = 1; layoutConstraints.gridheight = 1; 
			layoutConstraints.fill 		= GridBagConstraints.BOTH; 
			layoutConstraints.insets 	= new Insets(0,-5,-5,-5); 
			layoutConstraints.anchor 	= GridBagConstraints.CENTER; 
			layoutConstraints.weightx 	= 1.0; layoutConstraints.weighty = 1.0;
			layout.setConstraints(encodeButton,layoutConstraints);
	    	add(encodeButton);

		encryptButton = new JButton("Encrypt Now");
	    	layoutConstraints.gridx 	= 0; layoutConstraints.gridy = 2; 
			layoutConstraints.gridwidth = 1; layoutConstraints.gridheight = 1; 
			layoutConstraints.fill 		= GridBagConstraints.BOTH; 
			layoutConstraints.insets 	= new Insets(0,0,0,0); 
			layoutConstraints.anchor 	= GridBagConstraints.CENTER; 
			layoutConstraints.weightx 	= 1.0; layoutConstraints.weighty = 1.0;
			layout.setConstraints(encryptButton,layoutConstraints);
	    	
		add(encryptButton);
	    	
			setBackground(Color.lightGray);
			setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
		}
	}
	
	private class Image_Panel extends JPanel
	{
		public Image_Panel()
		{
			GridBagLayout layout = new GridBagLayout(); 
			GridBagConstraints layoutConstraints = new GridBagConstraints(); 
			setLayout(layout);
			
			image_input = new JLabel();
			layoutConstraints.gridx 	= 0; layoutConstraints.gridy = 0; 
			layoutConstraints.gridwidth = 1; layoutConstraints.gridheight = 1; 
			layoutConstraints.fill 		= GridBagConstraints.BOTH; 
			layoutConstraints.insets 	= new Insets(0,0,0,0); 
			layoutConstraints.anchor 	= GridBagConstraints.CENTER; 
			layoutConstraints.weightx 	= 1.0; layoutConstraints.weighty = 50.0;
			JScrollPane scroll2 = new JScrollPane(image_input,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
			layout.setConstraints(scroll2,layoutConstraints);
			scroll2.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
			image_input.setHorizontalAlignment(JLabel.CENTER);
	    	add(scroll2);
	    	
	    	decodeButton = new JButton("Decode Now");
	    	layoutConstraints.gridx 	= 0; layoutConstraints.gridy = 1; 
			layoutConstraints.gridwidth = 1; layoutConstraints.gridheight = 1; 
			layoutConstraints.fill 		= GridBagConstraints.BOTH; 
			layoutConstraints.insets 	= new Insets(0,-5,-5,-5); 
			layoutConstraints.anchor 	= GridBagConstraints.CENTER; 
			layoutConstraints.weightx 	= 1.0; layoutConstraints.weighty = 1.0;
			layout.setConstraints(decodeButton,layoutConstraints);
	    	add(decodeButton);
	    	
			setBackground(Color.lightGray);
			setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
	
		decryptButton = new JButton("Decrypt Now");
	    	layoutConstraints.gridx 	= 0; layoutConstraints.gridy = 2; 
			layoutConstraints.gridwidth = 1; layoutConstraints.gridheight = 1; 
			layoutConstraints.fill 		= GridBagConstraints.BOTH; 
			layoutConstraints.insets 	= new Insets(0,0,0,0); 
			layoutConstraints.anchor 	= GridBagConstraints.CENTER; 
			layoutConstraints.weightx 	= 1.0; layoutConstraints.weighty = 1.0;
			layout.setConstraints(decryptButton,layoutConstraints);
	    	
		add(decryptButton);
	    }
	 }
	
	private class Encrypt_Panel extends JPanel
	{
		
		public Encrypt_Panel()
		{
			JLabel l1,l2,l3;
			JButton b1,b2,b;
			JTextField t1,t2,t3;
			GridBagLayout layout = new GridBagLayout(); 
			l1=new JLabel("ENTER THE KEY(8 alphabetical characters without repetition)");
			l2=new JLabel("ENTER THE TEXT FILE PATH");
			l3=new JLabel("TEXT FILE PATH AFTER ENCRYPTION/DECRYPTION");
			b1=new JButton("ENCRYPT");
			b2=new JButton("DECRYPT");
			b=new JButton("browse");
			t1=new JTextField(30);
			t2=new JTextField(30);
			t3=new JTextField(30);
			this.setSize(400,300);
			this.setVisible(true);
			this.setLayout(new FlowLayout());
			this.add(l1);
			this.add(t1);
			this.add(l2);
			this.add(t2);
			this.add(b);
			this.add(b1);
			this.add(b2);
			this.add(l3);
			this.add(t3);
			
		}
	}
			
	public static void main(String args[])
	{
		new Steganography_View("Extended Steganography");
	}
}