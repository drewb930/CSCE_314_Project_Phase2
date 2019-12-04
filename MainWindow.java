import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.math.BigInteger;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.SwingConstants;

public class MainWindow extends JFrame
{
	private static final long serialVersionUID = -3880026026104218593L;
	private Primes m_Primes;
	private JTextField tfPrimeFileName;
	private JTextField tfCrossFileName;
	private JLabel lblPrimeCount;
	private JLabel lblCrossCount;
	private JLabel lblLargestPrime;
	private JLabel lblLargestCross;
	private JLabel lblStatus;
	private boolean primesGenerated = false; //for setting specific status
	private boolean crossesGenerated = false; 
	MainWindow(String name, Primes p)
	{
		m_Primes = new Primes();
		String string3 = "The largest prime has 0 digits.";
		
		String string4 = "The largest cross has 0 and 0 digits.";
		
		JFrame frame = new JFrame(Config.APPLICATIONNAME);
		frame.setSize(1000,400);

		
		
		JPanel mainPanel = new JPanel(new GridBagLayout()); // sets main panel with default grid layout
		
		mainPanel.setBackground(new Color(128,0,0));
		
		GridBagConstraints mainGB = new GridBagConstraints(); // sets contraints fro panel 1
		mainGB.fill = GridBagConstraints.HORIZONTAL;
		mainGB.anchor = GridBagConstraints.WEST;
		mainGB.ipady = 10;
		mainGB.weightx = .5;
		mainGB.insets = new Insets(0,2,0,0);
		mainGB.gridx = 0;
		mainGB.gridy = 0;
		
		
		
		

		JPanel panel1 = new JPanel(new GridBagLayout()); // makes first panel
		
		GridBagConstraints gb1 = new GridBagConstraints(); // sets contraints fro panel 1
		gb1.fill = GridBagConstraints.HORIZONTAL;
		gb1.anchor = GridBagConstraints.WEST;
		gb1.ipady = 10;
		gb1.weightx = .5;
		gb1.insets = new Insets(0,2,0,0);
		gb1.gridx = 0;
		gb1.gridy = 0;
		
		
		tfPrimeFileName = new JTextField();
		tfPrimeFileName.setColumns(75);
		panel1.add(tfPrimeFileName,gb1); // adds text field to panel 1 with gb1 constraints
		
		lblPrimeCount = new JLabel("0"); // makes label for number of primes in text file
		gb1.gridx = 1;
		lblPrimeCount.setHorizontalAlignment(SwingConstants.RIGHT);
		panel1.add(lblPrimeCount,gb1); // adds to panel with gb1 constraints
		
		
		
		JLabel text1 = new JLabel("Primes File"); // makes label for number of primes in text file
		text1.setFont(new Font("Tahoma", Font.PLAIN, 22));
		gb1.gridy = 1;
		gb1.gridx = 0;
		panel1.add(text1,gb1); // adds to panel with gb1 constraints
		
		
		JButton loadButton = new JButton("Load");
		gb1.gridx = 1;
		loadButton.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		    	  if(primesGenerated) {
		    		  m_Primes.clearPrimes();
		    	  }
		    	  if(tfPrimeFileName.getText().contentEquals("")) {
		    		  lblStatus.setText("Status: Please input a name for the file first :)");
		    	  }
		    	  else {

		    		 
		    		  boolean b;
		    		  b = FileAccess.loadPrimes(m_Primes, Config.DATAPATH + tfPrimeFileName.getText());
				    	  
		    		  if(b) {
		    			  lblStatus.setText("Status: Prime Files have been loaded!! WooHoo!!");
		    			  primesGenerated = true;
		    			  updateStats();
		    		  }
		    		  else {
		    			  lblStatus.setText("Status: Error, your prime files were not loaded :( ");
		    		  }
		    	  }
		    	  
		      }
		} );
		panel1.add(loadButton,gb1);
		
		JButton saveButton = new JButton("Save");
		gb1.gridx = 2;
		saveButton.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		    	  
		    	  if(tfPrimeFileName.getText().contentEquals("")) {
		    		  lblStatus.setText("Status: Please input a name for the file first :)");
		    	  }
		    	  else {
		    		  if(!primesGenerated) {
		    			  lblStatus.setText("Status: Please Generate primes before saving");
		    		  }
		    		  else {
				    	  boolean b;
				    	  b = FileAccess.savePrimes(m_Primes, Config.DATAPATH + tfPrimeFileName.getText());
				    	  
				    	  if(b) {
				    		  lblStatus.setText("Status: Prime Files have been saved!! WooHoo!!");
				    	  }
				    	  else {
				    		  lblStatus.setText("Status: Error, your prime files were not saved :( ");
				    	  }
		    		  }
		    	  }
		    	  
		      }
		} );
		panel1.add(saveButton,gb1);

		mainPanel.add(panel1,mainGB);

		
		
		JPanel panel2 = new JPanel(new GridBagLayout()); // makes first panel
		
		GridBagConstraints gb2 = new GridBagConstraints(); // sets contraints fro panel 1
		gb2.fill = GridBagConstraints.HORIZONTAL;
		gb2.anchor = GridBagConstraints.WEST;
		gb2.ipady = 10;
		gb2.weightx = .5;
		gb2.insets = new Insets(0,2,0,0);
		gb2.gridx = 0;
		gb2.gridy = 0;
		
		tfCrossFileName = new JTextField(); // makes text field for primes File
		tfCrossFileName.setColumns(75);
		panel2.add(tfCrossFileName,gb2); // adds text field to panel 1 with gb1 constraints
		
		lblCrossCount = new JLabel("0"); // makes label for number of primes in text file
		gb2.gridx = 1;
		lblCrossCount.setHorizontalAlignment(SwingConstants.RIGHT);
		panel2.add(lblCrossCount,gb2); // adds to panel with gb1 constraints
		
		
		
		JLabel text2 = new JLabel("Hex File"); // makes label for number of primes in text file
		text2.setFont(new Font("Tahoma", Font.PLAIN, 22));
		gb2.gridy = 1;
		gb2.gridx = 0;
		panel2.add(text2,gb2); // adds to panel with gb1 constraints
		
		
		JButton loadButton2 = new JButton("Load");
		gb2.gridx = 1;
		loadButton2.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		    	 
		    	  if(tfCrossFileName.getText().contentEquals("")) {
		    		  lblStatus.setText("Status: Please input a name for the file first :)");
		    	  }
		    	  else {
		    		  if(crossesGenerated) {
		    			  m_Primes.clearCrosses();
		    		  }
		    		 
		    		  boolean b;
		    		  b = FileAccess.loadCrosses(m_Primes, Config.DATAPATH + tfCrossFileName.getText());
				    	  
		    		  if(b) {
		    			  lblStatus.setText("Status: Cross Files have been loaded!! WooHoo!!");
		    			  crossesGenerated = true;
		    			  updateStats();
		    		  }
		    		  else {
		    			  lblStatus.setText("Status: Error, your cross file could not be found :( ");
		    		  }
		    	  }
		    	  
		      }
		} );
		panel2.add(loadButton2,gb2);
		
		JButton saveButton2 = new JButton("Save");
		saveButton2.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		    	  
		    	  if(tfCrossFileName.getText().contentEquals("")) {
		    		  lblStatus.setText("Status: Please input a name for the file first :)");
		    	  }
		    	  else {
		    		  if(!crossesGenerated) {
		    			  lblStatus.setText("Status: Please Generate crosses before saving");
		    		  }
		    		  else {
				    	  boolean b;
				    	  b = FileAccess.saveCrosses(m_Primes, Config.DATAPATH + tfCrossFileName.getText());
				    	  
				    	  if(b) {
				    		  lblStatus.setText("Status: Cross Files have been saved!! WooHoo!!");
				    	  }
				    	  else {
				    		  lblStatus.setText("Status: Error, your cross files were not saved :( ");
				    	  }
		    		  }
		    	  }
		    	  
		      }
		} );
		gb2.gridx = 2;
		panel2.add(saveButton2,gb2);
		
		mainGB.gridy = 1;
		
		mainPanel.add(panel2,mainGB);
		
		
		
		
		JPanel panel3 = new JPanel(new GridBagLayout()); // makes first panel
		
		GridBagConstraints gb3 = new GridBagConstraints(); // sets contraints fro panel 1
		gb3.fill = GridBagConstraints.HORIZONTAL;
		gb3.anchor = GridBagConstraints.WEST;
		gb3.ipady = 10;
		gb3.weightx = .5;
		gb3.insets = new Insets(0,2,0,0);
		gb3.gridx = 0;
		gb3.gridy = 0;
		
		
		JButton generatePrimes = new JButton("Generate Primes");
		generatePrimes.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		      		popupGeneratePrimes();
		      		//frame.dispose();
		      }
		} );
		panel3.add(generatePrimes,gb3);
		
		
		JPanel subPanel = new JPanel(new GridBagLayout()); // makes first panel
		
		GridBagConstraints sub = new GridBagConstraints(); // sets contraints fro panel 1
		sub.fill = GridBagConstraints.HORIZONTAL;
		sub.anchor = GridBagConstraints.WEST;
		sub.ipady = 10;
		sub.weightx = .5;
		sub.insets = new Insets(0,2,0,0);
		sub.gridx = 0;
		sub.gridy = 0;
		
		
		lblLargestPrime = new JLabel(string3);
		subPanel.add(lblLargestPrime);
		
		lblLargestCross = new JLabel(string4);
		sub.gridy = 1;
		subPanel.add(lblLargestCross,sub);
		
		panel3.add(subPanel);
		
		JButton generateCrosses = new JButton("Generate Crosses");
		generateCrosses.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {

		    	  if(!primesGenerated) {
		    		  lblStatus.setText("Status: Primes have not yet been generated.");
		    	  }
		    	  else {
		    		  if(crossesGenerated) {
		    			  m_Primes.clearCrosses();
		    		  }
		    		  m_Primes.generateTwinPrimes();
		    		  m_Primes.generateHexPrimes();
		    		  if(m_Primes.getCrossList().size() == 0) {
		    			  lblStatus.setText("Status: No Hex Values were generated.");
		    			  crossesGenerated = false;
		    		  }
		    		  else {
		    			  lblStatus.setText("Status: Hex Values have been generated.");
		    			  crossesGenerated = true; 
		    		  }
		    		  updateStats();
		    		  
		    	  }
		      }
		});
		gb3.gridx = 2;
		panel3.add(generateCrosses,gb3);
		
		mainGB.gridy = 2;
		
		mainPanel.add(panel3,mainGB);
		
		
		JPanel statusPanel = new JPanel(); // makes first panel
		
		String status = "Bored."; // this will be getStatus Later
		String stringStatus = "Status: " + status;
		
		lblStatus = new JLabel(stringStatus);
		statusPanel.add(lblStatus);
		
		mainGB.gridy = 3;
		
		mainPanel.add(statusPanel,mainGB);
		
		frame.getContentPane().add(mainPanel);
		
		
		frame.setVisible(true);
		
		

	}

	protected void popupGeneratePrimes()
	{
		JDialog dPrimes = new JDialog(this, "Prime Number Generation");
		GridBagLayout gridLayout = new GridBagLayout();
		dPrimes.getContentPane().setBackground(new Color(128, 0, 0));
		dPrimes.getContentPane().setLayout(gridLayout);
		
		
		
		GridBagConstraints gbcDialog = new GridBagConstraints();
		gbcDialog.fill = GridBagConstraints.HORIZONTAL;
		gbcDialog.anchor = GridBagConstraints.WEST;
		gbcDialog.ipady = 10;
		gbcDialog.weightx = .5;
		gbcDialog.insets = new Insets(1,2,0,0);
		gbcDialog.gridx = 0;
		gbcDialog.gridy = 0;
		
		GridBagConstraints gbcPanel = new GridBagConstraints();
		gbcPanel.anchor = GridBagConstraints.WEST;
		gbcPanel.weightx = .5;
		gbcPanel.insets = new Insets(1,2,0,0);
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 0;
		
		JPanel pnlGenerate = new JPanel();
		pnlGenerate.setLayout(new GridBagLayout());
		
		JLabel lblCount = new JLabel("Number of Primes to Generate");
		lblCount.setFont(new Font("Tahoma", Font.PLAIN, 12));
		pnlGenerate.add(lblCount, gbcPanel);
		
		JTextField tfCount = new JTextField();
		lblCount.setLabelFor(tfCount);
		tfCount.setColumns(30);
		gbcPanel.gridx = 1;
		pnlGenerate.add(tfCount, gbcPanel);
		
		JLabel lblStart = new JLabel("Starting Number (does not have to be prime)");
		lblStart.setFont(new Font("Tahoma", Font.PLAIN, 12));
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 1;
		pnlGenerate.add(lblStart, gbcPanel);
		
		JTextField tfStart = new JTextField();
		lblStart.setLabelFor(tfStart);
		tfStart.setColumns(30);
		gbcPanel.gridx = 1;
		pnlGenerate.add(tfStart, gbcPanel);
		
		dPrimes.add(pnlGenerate, gbcDialog);
		
		JPanel pnlButtons = new JPanel();
		pnlButtons.setLayout(new GridBagLayout());
		
		JButton btnGeneratePrimes = new JButton("Generate Primes");
		btnGeneratePrimes.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
      	try
      	{
      		BigInteger start = new BigInteger(tfStart.getText());
      		int count = Integer.parseInt(tfCount.getText());
       		m_Primes.generatePrimes(start, count);
       		
       		lblStatus.setText("Status: Excited. Primes have been generated.");
       		primesGenerated = true;
       		updateStats();
      		dPrimes.dispose();
      	}
      	catch(NumberFormatException ex)
      	{
      		lblStatus.setText("You failed to type in an integer successfully. You are terrible at math. Shame.");
      		dPrimes.dispose();
      	}
      	
      }
    });
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 0;
		pnlButtons.add(btnGeneratePrimes, gbcPanel);
		
		JButton btnCancel = new JButton("Cancel Generation");
		btnCancel.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
      	dPrimes.dispose();
      }
    });
		gbcPanel.anchor = GridBagConstraints.EAST;
		gbcPanel.gridx = 1;
		pnlButtons.add(btnCancel, gbcPanel);		
		
		gbcDialog.gridy = 1;
		dPrimes.add(pnlButtons, gbcDialog);
		
		JPanel pnlStatus = new JPanel();
		pnlStatus.setLayout(new GridBagLayout());

		gbcPanel.anchor = GridBagConstraints.SOUTHWEST;
		gbcPanel.weightx = .5;
		gbcPanel.insets = new Insets(1,2,0,0);
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 1;

		JLabel lblNotice = new JLabel("Warning: This application is single threaded, and will freeze while generating primes.");
		lblNotice.setFont(new Font("Tahoma", Font.PLAIN, 12));
		pnlStatus.add(lblNotice, gbcPanel);
		
		gbcDialog.gridy = 2;
		dPrimes.add(pnlStatus, gbcDialog);
		
		dPrimes.setSize(200,200);
		dPrimes.pack(); // Knowing what this is and why it is needed is important. You should read the documentation on this function!
		dPrimes.setVisible(true);		
	}
	
	
	


	// This function updates all the GUI statistics. (# of primes, # of crosses, etc)
	private void updateStats()
	{
		//lblPrimeCount = getPrimeCount();
		//lblCrossCount = getCrossCount;

		lblPrimeCount.setText(Integer.toString(m_Primes.getList().size()));
		if(primesGenerated) {
			String lPrime = "The largest prime has " + Integer.toString(m_Primes.sizeofLastPrime()) + " digits.";
			lblLargestPrime.setText(lPrime);
		}
		if(!crossesGenerated) {
			String cPrime = "The largest cross has 0 and 0 digits.";
			lblLargestCross.setText(cPrime);
		}
		else if(primesGenerated || crossesGenerated) {
			if(m_Primes.getCrossList().size() != 0) {
				String cPrime = "The largest cross has " + Integer.toString(m_Primes.sizeofLastCross().left()) + " and " + Integer.toString(m_Primes.sizeofLastCross().right()) + " digits.";
				lblLargestCross.setText(cPrime);
				crossesGenerated = true;
			}
			else {
				String cPrime = "The largest cross has 0 and 0 digits.";
				lblLargestCross.setText(cPrime);
			}
		}
		
		lblCrossCount.setText(Integer.toString(m_Primes.getCrossList().size()));
		
 	}

}
