package com.imglow.ElementMMO.desktop;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import com.imglow.ElementMMO.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import java.net.Socket;

import javax.imageio.ImageIO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.sql.*;

public class LoginFrame extends JFrame {
	
	// VARIABLES START ------------------------------------
	
	// game connection
	Socket mySocket;
	SQLManager mySQLManager;
	String hostname = "127.0.0.1";
	int port = 1337;
	
	// gui elements
	JPanel mainPanel;
	JPanel loginPanel;
	JLabel loginErrorLabel;
	JLabel charErrorLabel;
	JLabel teamErrorLabel;
	Image logo;
	
	// user data
	String newUser;
	String newPW;
	int newCharID;
	
	// VARIABLES END --------------------------------------
	
	
	
	public LoginFrame () {
		super("Element MMO - Login");
		Dimension screenDim = new Dimension(1280, 720);
		setSize(screenDim);
		this.setMinimumSize(new Dimension(screenDim));
		this.setMaximumSize(new Dimension(screenDim));
		setLocation(0, 0);
		
		mySQLManager = new SQLManager();
		
		// main card panel
		mainPanel = new JPanel(new CardLayout());	// holds login and char select panels as cards
		
		// login panel
		loginPanel = new JPanel(new BorderLayout()) {
			protected void paintComponent (Graphics g) {
				try {
					super.paintComponent(g);
					// draw logo
					logo = ImageIO.read(new File("logo.png"));
					g.drawImage(logo, 210, 80, loginPanel);
				} catch (IOException ioe) { System.out.println(ioe.getMessage()); }
			}
		};	// empty, provides border inset
		JPanel stuffPanel = new JPanel(new BorderLayout());	// holds all elements
		JPanel fieldsPanel = new JPanel(new GridLayout(2,1));	// holds text fields
		JPanel buttonsPanel = new JPanel(new GridLayout(1,2));	// holds buttons
		
		// login panel elements
		JLabel loginLabel = new JLabel("Log In:");
		loginLabel.setForeground(Color.WHITE);
		JTextField userField = new JTextField("(username)");
		JTextField pwField = new JTextField("(password)");
		JButton loginButton = new JButton("Log In");
		loginButton.setHorizontalAlignment(SwingConstants.CENTER);
		loginButton.addActionListener(new LoginListener(userField, pwField, true));
		JButton registerButton = new JButton("Register");
		registerButton.setHorizontalAlignment(SwingConstants.CENTER);
		registerButton.addActionListener(new LoginListener(userField, pwField, false));
		loginErrorLabel = new JLabel(".");
		loginErrorLabel.setForeground(Color.BLACK);
		
		// build login panel
		buttonsPanel.add(loginButton);
		buttonsPanel.add(registerButton);
		fieldsPanel.add(userField);
		fieldsPanel.add(pwField);
		fieldsPanel.setOpaque(false);
		stuffPanel.add(loginLabel, BorderLayout.NORTH);
		stuffPanel.add(fieldsPanel, BorderLayout.CENTER);
		stuffPanel.add(buttonsPanel, BorderLayout.SOUTH);
		stuffPanel.setOpaque(false);
		loginPanel.add(stuffPanel, BorderLayout.CENTER);
		loginPanel.add(loginErrorLabel, BorderLayout.SOUTH);
		loginPanel.setBorder(new EmptyBorder(220, 420, 330, 420));
		loginPanel.setBackground(Color.BLACK);
		loginPanel.repaint();
		
		// character select panel
		JPanel charSelectPanel = new JPanel(new BorderLayout());	// empty, provides border inset
		JPanel charSelectStuffPanel = new JPanel(new BorderLayout());	// panel elements
		JPanel charPanel = new JPanel(new GridLayout(2,2));	// character images
		
		// character select panel elements
		JLabel chooseLabel = new JLabel("Choose a Character!");
		chooseLabel.setForeground(Color.WHITE);
		chooseLabel.setBorder(new EmptyBorder(20, 20, 50, 20));
		chooseLabel.setHorizontalAlignment(SwingConstants.CENTER);
		charErrorLabel = new JLabel(".");
		charErrorLabel.setForeground(Color.BLACK);
		
		// build character select panel
		JLabel[] charLabels = new JLabel[4];
		BufferedImage unscaledImage;
		Image scaledImage;
		try {
			for (int i=0; i<charLabels.length; i++) {
				if (i== 1) unscaledImage = ImageIO.read(new File("Spikey.png"));
				else if (i==2) unscaledImage = ImageIO.read(new File("chika.png"));
				else if (i==0) unscaledImage = ImageIO.read(new File("cop.png"));
				else unscaledImage = ImageIO.read(new File("naked_man.png"));
				scaledImage = unscaledImage.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
				charLabels[i] = new JLabel(new ImageIcon(scaledImage));
				charLabels[i].setOpaque(false);
				charLabels[i].addMouseListener(new CharSelectListener(i, this));
				charPanel.add(charLabels[i]);
			}
		} catch (Exception e) { System.out.println("ERROR: " + e.getMessage()); return; }
		
		charPanel.setBackground(Color.BLACK);
		charSelectStuffPanel.add(chooseLabel, BorderLayout.NORTH);
		charSelectStuffPanel.add(charPanel, BorderLayout.CENTER);
		charSelectStuffPanel.setBackground(Color.BLACK);
		charSelectPanel.add(charSelectStuffPanel, BorderLayout.CENTER);
		charSelectPanel.add(charErrorLabel, BorderLayout.SOUTH);
		charSelectPanel.setBorder(new EmptyBorder(120, 350, 200, 350));
		charSelectPanel.setBackground(Color.BLACK);
		
		// team selection panel
		JPanel teamSelectPanel = new JPanel(new BorderLayout());	// empty, holds border
		JPanel teamStuffPanel = new JPanel(new BorderLayout());	// holds all team selection elements
		JPanel teamOptionsPanel = new JPanel(new GridLayout(1,2));	// holds team choice elements
		
		// team selection panel elements
		JLabel chooseTeamLabel = new JLabel("Choose your team!");
		chooseTeamLabel.setForeground(Color.WHITE);
		chooseTeamLabel.setBorder(new EmptyBorder(20, 20, 50, 20));
		chooseTeamLabel.setHorizontalAlignment(SwingConstants.CENTER);
		teamErrorLabel = new JLabel(".");
		teamErrorLabel.setForeground(Color.BLACK);
		
		// build team selection panel
		JLabel[] teamLabels = new JLabel[2];
		try {
			for (int i=0; i<teamLabels.length; i++) {
				if (i==0) unscaledImage = ImageIO.read(new File("blueteam.png"));
				else unscaledImage = ImageIO.read(new File("redteam.png"));
				scaledImage = unscaledImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
				teamLabels[i] = new JLabel(new ImageIcon(scaledImage));
				teamOptionsPanel.add(teamLabels[i]);
			}
		} catch (Exception e) { System.out.println("ERROR: " + e.getMessage()); return; }
		teamLabels[0].addMouseListener(new TeamSelectListener(true, this));
		teamLabels[1].addMouseListener(new TeamSelectListener(false, this));
		
		teamOptionsPanel.setBackground(Color.BLACK);
		teamStuffPanel.add(chooseTeamLabel, BorderLayout.NORTH);
		teamStuffPanel.add(teamOptionsPanel, BorderLayout.CENTER);
		teamStuffPanel.setBackground(Color.BLACK);
		teamSelectPanel.add(teamStuffPanel, BorderLayout.CENTER);
		teamSelectPanel.add(teamErrorLabel, BorderLayout.SOUTH);
		teamSelectPanel.setBorder(new EmptyBorder(180, 350, 200, 350));
		teamSelectPanel.setBackground(Color.BLACK);
		
		// add login and char select panels to main panel as cards
		mainPanel.add(loginPanel, "login");
		mainPanel.add(charSelectPanel, "charselect");
		mainPanel.add(teamSelectPanel, "teamselect");
		add(mainPanel);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	
	
	// Button listener
	class LoginListener implements ActionListener
	{
		JTextField userF;
		JTextField pwF;
		boolean returning;
		
		// Constructor
		public LoginListener (JTextField userF, JTextField pwF, boolean returning)
		{
			this.userF = userF;
			this.pwF = pwF;
			this.returning = returning;
		}
		
		// Override actionperformed
		public void actionPerformed (ActionEvent ae)
		{
			// get entered text
			String userAttempt = userF.getText();
			String pwAttempt = pwF.getText();
			
			// if at least one is empty or unchanged, display error
			if (userAttempt.equals("") || userAttempt.equals("(username)") || pwAttempt.equals("") || pwAttempt.equals("(password)")) {
				displayLoginError(0);
				return;
			}
			
			// look for specified user's entry in player database
			boolean exists = mySQLManager.userExists(userAttempt);
			System.out.println("user exists? = " + exists);
			// if trying to log in to existing user, verify that user actually exists
			if (returning)
			{
				// (only proceed in logging user in if specified username exists)
				if (exists) {
					// verify user exists and credentials match
					if (mySQLManager.isValidLogin(userAttempt, pwAttempt)) {
						try {
							ResultSet myChar = mySQLManager.getUserEntry(userAttempt);
							myChar.next();
							
							// cache user's previously-made username and character selection
							newUser = myChar.getString("username");
							newCharID = myChar.getInt("char_id");
							
							// advance to team select screen
							CardLayout mainCL = (CardLayout)mainPanel.getLayout();
							mainCL.show(mainPanel, "teamselect");
							
							//startGame(userAttempt, myCharID);
						}
						catch (Exception e) { System.out.println("ERROR GETTING PLAYER DATA"); }
					}
					else {
						displayLoginError(1);
					}
				}
				else {
					displayLoginError(2);
				}
			}
			
			// if making new user, cache username/pw and signal character select screen (card)
			else
			{
				// (only proceed in making new user if specified username doesn't already exist)
				if (!exists)
				{
					// (only proceed in making new user if specified username is valid format)
					if (isValidUsername(userAttempt))
					{
						// store user/pw
						newUser = userAttempt;
						newPW = pwAttempt;
						
						// advance to char select screen
						CardLayout mainCL = (CardLayout)mainPanel.getLayout();
						mainCL.show(mainPanel, "charselect");
					}
					else {
						displayLoginError(4);
					}
				}
				else {
					displayLoginError(3);
				}
			}
			
			// store username and pw
//			currentUser = userAttempt;
		}
	}
	
	
	
	// Char select listener
	class CharSelectListener extends MouseAdapter {
		
		int charID;

		// Constructor
		public CharSelectListener (int charID, LoginFrame introFrame)
		{
			this.charID = charID;
		}
		
		// Override mouseClicked
		public void mouseClicked (MouseEvent me)
		{
			// store character ID
			newCharID = charID;
			
			// enter new user into database
			mySQLManager.createUser(newUser, newPW, newCharID);
			
			// advance to team select screen
			CardLayout mainCL = (CardLayout)mainPanel.getLayout();
			mainCL.show(mainPanel, "teamselect");
		}
	}
	
	
	
	// Team select listener
	class TeamSelectListener extends MouseAdapter {
		
		boolean team1;

		// Constructor
		public TeamSelectListener (boolean team1, LoginFrame introFrame)
		{
			this.team1 = team1;
		}
		
		// Override mouseClicked
		public void mouseClicked (MouseEvent me)
		{
			System.out.println(team1);
			// start game with chosen character
			startGame(newUser, newCharID, team1);
		}
	}
	
	
	
	// Return whether specified username is valid
	boolean isValidUsername (String user)
	{
		// disallow system tokens
		if (user.equals("msg") || user.equals("all") || user.equals("team1") || user.equals("team2")) return false;
		
		// disallow whitespace
		for (int i=0; i<user.length(); i++) {
			if (Character.isWhitespace(user.charAt(i))) return false;
		}
		
		// if no special cases broken, name is valid
		return true;
	}
	
	
	
	// Start main game client
	void startGame (String user, int charID, boolean team1)
	{
		try {
			// initialize socket
			mySocket = new Socket(hostname, port);

			// make this frame invisible
			setVisible(false);
			
			LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
			config.width = 1280;
			config.height = 720;
			config.resizable = false;
//			System.out.println("carid: " + charID);
			new LwjglApplication(new MainClient(mySocket, mySQLManager, charID, user, team1), config);
			
			dispose();
		}
		catch (IOException ioe) { System.out.println("IOException in CharSelectListener: " + ioe.getMessage()); }
	}
	
	
	
	// Display login error
	void displayLoginError (int error)
	{
		if (error < 5) loginErrorLabel.setForeground(Color.RED);
		else charErrorLabel.setForeground(Color.RED);
		
		switch (error)
		{
		case 0: loginErrorLabel.setText("Please fill all fields!"); break;
		case 1: loginErrorLabel.setText("Incorrect username/password!"); break;
		case 2: loginErrorLabel.setText("This username does not exist!"); break;
		case 3: loginErrorLabel.setText("This username already exists!"); break;
		case 4: loginErrorLabel.setText("This username is not allowed!"); break;
		case 5: loginErrorLabel.setText("Unable to connect to server!"); break;
		
		case 6: charErrorLabel.setText("Unable to connect to server!"); break;
		
		default: break;
		}
	}
	
	
	
	public static void main (String[] args) {
		LoginFrame testF = new LoginFrame();
	}

}























