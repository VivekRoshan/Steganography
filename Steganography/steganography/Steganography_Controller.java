
import java.io.File;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.io.*;
public class Steganography_Controller
{
	private Steganography_View	view;
	private Steganography		model;
	
	private JPanel		decode_panel;
	private JPanel		encode_panel;
	private JPanel		encrypt_panel;

	private JTextArea 	input;
	private JButton		encodeButton,decodeButton,encryptButton,decryptButton;
	private JLabel		image_input;

	private JMenuItem 	encode,encrypt;
	private JMenuItem 	decode,decrypt;
	private JMenuItem 	exit,close;
	
	private Encode			enc;
	private Encrypt 			encry;
	private Decode			dec;
	private Decrypt			decry;
	private EncodeButton	encButton;
	private DecodeButton	decButton;
	private EncryptButton encryButton;
	private DecryptButton decryButton;
	
	private String			stat_path = "";
	private String			stat_name = "";
	
	public Steganography_Controller(Steganography_View aView, Steganography aModel)
	{
		//program variables
		view  = aView;
		model = aModel;
		
		encode_panel	= view.getTextPanel();
		decode_panel	= view.getImagePanel();
		encrypt_panel	= view.getEncryptPanel();
		input			= view.getText();
		image_input		= view.getImageInput();
		encodeButton	= view.getEButton();
		decodeButton	= view.getDButton();
		encryptButton 	= view.getEncryptButton();
		decryptButton	= view.getDecryptButton();
		encode			= view.getEncode();
		decode			= view.getDecode();
		exit			= view.getExit();
		encrypt			=view.getEncrypt();
		decrypt			=view.getDecrypt();
		close			=view.getClose();
		
		enc = new Encode();
		encode.addActionListener(enc);
		
		encry = new Encrypt();
		encrypt.addActionListener(encry);

		dec = new Decode();
		decode.addActionListener(dec);
		
		decry = new Decrypt();
		decrypt.addActionListener(decry);

		exit.addActionListener(new Exit());
		close.addActionListener(new Close());
		
		encButton = new EncodeButton();
		encodeButton.addActionListener(encButton);
		
		encryButton = new EncryptButton();
		encryptButton.addActionListener(encryButton);
		
		decButton = new DecodeButton();
		decodeButton.addActionListener(decButton);
		
		decryButton = new DecryptButton();
		decryptButton.addActionListener(decryButton);

		encode_view();
	}
	
	private void encode_view()
	{
		update();
		view.setContentPane(encode_panel);
		view.setVisible(true);
	}
	
	private void encrypt_view()
	{
		view.setContentPane(encrypt_panel);
		view.setVisible(true);
	}
	private void decrypt_view()
	{
		//fra.setVisible(true);
	}

	private void decode_view()
	{
		update();
		view.setContentPane(decode_panel);
		view.setVisible(true);
	}
	

	private class Encode implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			encode_view(); //show the encode view
		}
	}
	
	private class Encrypt implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{	
			encrypt_view();
			
		}
	}
	
	private class Decrypt implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{	
			decrypt_view(); //show the encode view
		}
	}	
	private class Decode implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			decode_view(); //show the decode view
			
			JFileChooser chooser = new JFileChooser("./");
			chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			chooser.setFileFilter(new Image_Filter());
			int returnVal = chooser.showOpenDialog(view);
			if (returnVal == JFileChooser.APPROVE_OPTION){
				File directory = chooser.getSelectedFile();
				try{
					String image = directory.getPath();
					stat_name = directory.getName();
					stat_path = directory.getPath();
					stat_path = stat_path.substring(0,stat_path.length()-stat_name.length()-1);
					stat_name = stat_name.substring(0, stat_name.length()-4);
					image_input.setIcon(new ImageIcon(ImageIO.read(new File(image))));
				}
				catch(Exception except) {
				JOptionPane.showMessageDialog(view, "The File cannot be opened!", 
					"Error!", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
	}
	
	private class Exit implements ActionListener
	{
		/*
		 *handles the click event
		 *@param e The ActionEvent Object
		 */
		public void actionPerformed(ActionEvent e)
		{
			System.exit(0); //exit the program
		}
	}
	
	private class Close implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			System.exit(0); //exit the program
		}
	}
	private class EncodeButton implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			JFileChooser chooser = new JFileChooser("./");
			chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			chooser.setFileFilter(new Image_Filter());
			int returnVal = chooser.showOpenDialog(view);
			if (returnVal == JFileChooser.APPROVE_OPTION){
				File directory = chooser.getSelectedFile();
				try{
					String text = input.getText();
					String ext  = Image_Filter.getExtension(directory);
					String name = directory.getName();
					String path = directory.getPath();
					path = path.substring(0,path.length()-name.length()-1);
					name = name.substring(0, name.length()-4);
					
					String stegan = JOptionPane.showInputDialog(view,
									"Enter output file name:", "File name",
									JOptionPane.PLAIN_MESSAGE);
					
					if(model.encode(path,name,ext,stegan,text))
					{
						JOptionPane.showMessageDialog(view, "The Image was encoded Successfully!", 
							"Success!", JOptionPane.INFORMATION_MESSAGE);
					}
					else
					{
						JOptionPane.showMessageDialog(view, "The Image could not be encoded!", 
							"Error!", JOptionPane.INFORMATION_MESSAGE);
					}
					decode_view();
					image_input.setIcon(new ImageIcon(ImageIO.read(new File(path + "/" + stegan + ".png"))));
				}
				catch(Exception except) {
				JOptionPane.showMessageDialog(view, "The File cannot be opened!", 
					"Error!", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
		
	}
	
	private class DecryptButton implements ActionListener
	{
	public void actionPerformed(ActionEvent e)
		{}
	}
	private class EncryptButton extends JFrame implements ActionListener
	{
		JLabel l1,l2,l3;
	JButton b1,b2,b;
	JTextField t1,t2,t3;
	public void actionPerformed(ActionEvent e)
	{
		l1=new JLabel("ENTER THE KEY(8 alphabetical characters without repetition)");
		l2=new JLabel("ENTER THE TEXT FILE PATH");
		l3=new JLabel("TEXT FILE PATH AFTER ENCRYPTION/DECRYPTION");
		b1=new JButton("ENCRYPT");
		b2=new JButton("DECRYPT");
		b=new JButton("browse");
		t1=new JTextField(30);
		t2=new JTextField(30);
		t3=new JTextField(30);
		setSize(400,300);
		setVisible(true);
		setLayout(new FlowLayout());
		this.add(l1);
		this.add(t1);
		this.add(l2);
		this.add(t2);
		this.add(b);
		this.add(b1);
		this.add(b2);
		this.add(l3);
		this.add(t3);
		b.addActionListener(this);
		b1.addActionListener(this);
		b2.addActionListener(this);
		String str=e.getActionCommand();
		if(str.equals("browse"))
		{
		Chooser frame = new Chooser();
		t2.setText(frame.fileName);
		}
		

	}	
	}
	private class DecodeButton implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String message = model.decode(stat_path, stat_name);
			System.out.println(stat_path + ", " + stat_name);
			if(message != "")
			{
				encode_view();
				JOptionPane.showMessageDialog(view, "The Image was decoded Successfully!", 
							"Success!", JOptionPane.INFORMATION_MESSAGE);
				input.setText(message);
			}
			else
			{
				JOptionPane.showMessageDialog(view, "The Image could not be decoded!", 
							"Error!", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
	
	public void update()
	{
		input.setText("");			//clear textarea
		image_input.setIcon(null);	//clear image
		stat_path = "";				//clear path
		stat_name = "";				//clear name
	}
	
	public static void main(String args[])
	{
		new Steganography_Controller(
									new Steganography_View("Steganography"),
									new Steganography()
									);
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
