import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.Border;

public class ConfigureTimer extends JFrame {
	/**
	 * Serial Version
	 */
	private static final long serialVersionUID = 8644875926172029360L;
	JPanel contentPane;
	JMenuBar jMenuBar1 = new JMenuBar();
	JMenu jMenuFile = new JMenu();
	JMenuItem jMenuFileExit = new JMenuItem();
	JMenu jMenuHelp = new JMenu();
	JMenuItem jMenuHelpShortcut = new JMenuItem();
	JMenuItem jMenuHelpAbout = new JMenuItem();
	JMenu jMenuOptions = new JMenu();
	JMenuItem jMenuOptSave = new JMenuItem();
	JMenuItem jMenuOptLoad = new JMenuItem();


	JTextField caption = new JTextField();
	JLabel currTime = new JLabel();
	JTextField endCaption = new JTextField();
	
	JPanel jPanelSettings = new JPanel();
	
	JLabel jLabelTitle = new JLabel();
	

	//Nombre de tour
	JLabel jLabelNbTour = new JLabel();
	JTextField nbTour = new JTextField();

	//Nombre de donne par tour
	JLabel jLabelNbDonne = new JLabel();
	JTextField nbDonne = new JTextField();

	//Temps d'une donne
	JLabel jLabelTimeDonne = new JLabel();
	JLabel jLabelMinDonne = new JLabel();
	JLabel jLabelSecDonne = new JLabel();
	JTextField minDonne = new JTextField();
	JTextField secDonne = new JTextField();

	//Temps d'un changement
	JLabel jLabelTimeChangement = new JLabel();
	JLabel jLabelMinChangement = new JLabel();
	JLabel jLabelSecChangement = new JLabel();
	JTextField minChangement = new JTextField();
	JTextField secChangement = new JTextField();
	
	JLabel number = new JLabel();
	JLabel text = new JLabel();


	JButton run = new JButton();
	JButton exit = new JButton();

	int dimPanelX;
	int dimPanelY;

	// Construct the frame
	public ConfigureTimer() {
		enableEvents(AWTEvent.WINDOW_EVENT_MASK);
		try {
			jbInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Component initialization
	private void jbInit() throws Exception {
		contentPane = (JPanel) this.getContentPane();

		dimPanelX = 500;
		dimPanelY = 600;
		
		contentPane.setLayout(null);
		
		this.setResizable(false);
		this.setSize(new Dimension(dimPanelX, dimPanelY));
		this.setTitle("Countdown Timer");
		
		jMenuFile.setText("File");
		jMenuFileExit.setText("Exit");
		jMenuFileExit
				.addActionListener(new ConfigureTimer_jMenuFileExit_ActionAdapter(
						this));
		jMenuOptSave
				.addActionListener(new ConfigureTimer_jMenuOptSave_ActionAdapter(
						this));
		jMenuOptLoad
				.addActionListener(new ConfigureTimer_jMenuOptLoad_ActionAdapter(
						this));
		jMenuHelp.setText("Help");
		jMenuHelpShortcut.setText("Shortcut Keys");
		jMenuHelpShortcut
				.addActionListener(new ConfigureTimer_jMenuHelpShortcut_ActionAdapter(
						this));
		jMenuHelpAbout.setText("About");
		jMenuHelpAbout
				.addActionListener(new ConfigureTimer_jMenuHelpAbout_ActionAdapter(
						this));

		jMenuFile.add(jMenuFileExit);
		jMenuHelp.add(jMenuHelpShortcut);
		jMenuHelp.add(jMenuHelpAbout);
		jMenuBar1.add(jMenuFile);
		jMenuBar1.add(jMenuOptions);
		jMenuBar1.add(jMenuHelp);

		jMenuOptions.add(jMenuOptSave);
		jMenuOptions.add(jMenuOptLoad);

		this.setJMenuBar(jMenuBar1);

		jMenuOptions.setText("Options");
		jMenuOptSave.setText("Save Configuration");
		jMenuOptLoad.setText("Load Configuration");



		jPanelSettings.setBorder(BorderFactory.createEtchedBorder());		
		jPanelSettings.setBounds(new Rectangle(25, 15, 450, 300));
		jPanelSettings.setLayout(null);
		
		int startX = 10;
		int startY = 10;
		
		
		// Title
		jLabelTitle
		.setText("Veuillez entrer les informations pour votre partie");
		jLabelTitle.setBounds(new Rectangle(startX, startY, 500, 15));

		jPanelSettings.add(jLabelTitle, null);
		startY += 40;

		//Nombre de tour		
		jLabelNbTour.setText("Nombre de tour");
		jLabelNbTour.setBounds(new Rectangle(startX, startY, 150, 15));
		nbTour.setText("");
		nbTour.setBounds(new Rectangle(250, startY, 50, 20));
		nbTour.addKeyListener(new ConfigureTimer_minutes_keyAdapter(this));

		jPanelSettings.add(jLabelNbTour, null);
		jPanelSettings.add(nbTour, null);
		startY += 30;

		//Nombre de donne
		jLabelNbDonne.setText("Nombre de donnes par tour");
		jLabelNbDonne.setBounds(new Rectangle(startX, startY, 300, 15));
		nbDonne.setText("");
		nbDonne.setBounds(new Rectangle(250, startY, 50, 20));
		nbDonne.addKeyListener(new ConfigureTimer_minutes_keyAdapter(this));
		
		jPanelSettings.add(jLabelNbDonne, null);
		jPanelSettings.add(nbDonne, null);
		startY += 30;

		//Temps par donne
		jLabelTimeDonne.setText("Temps par donne");
		jLabelTimeDonne.setBounds(new Rectangle(startX, startY, 150, 20));

		jLabelMinDonne.setText("Min");
		jLabelMinDonne.setBounds(new Rectangle(220, startY, 50, 20));
		jLabelMinDonne.setToolTipText("Enter the minutes to countdown");
		minDonne.setText("");
		minDonne.setBounds(new Rectangle(250, startY, 50, 20));
		minDonne.addKeyListener(new ConfigureTimer_minutes_keyAdapter(this));

		jLabelSecDonne.setBounds(new Rectangle(320, startY, 50, 20));
		jLabelSecDonne.setText("Sec");
		secDonne.setText("");
		secDonne.setBounds(new Rectangle(350, startY, 50, 20));
		secDonne.addKeyListener(new ConfigureTimer_seconds_keyAdapter(this));
		
		jPanelSettings.add(jLabelTimeDonne, null);
		jPanelSettings.add(jLabelMinDonne, null);
		jPanelSettings.add(minDonne, null);
		jPanelSettings.add(jLabelSecDonne, null);
		jPanelSettings.add(secDonne, null);
		startY += 30;
		
		// Temps pour changement
		jLabelTimeChangement.setText("Durée d'un changement");
		jLabelTimeChangement.setBounds(new Rectangle(startX, startY, 300, 15));
		
		jLabelMinChangement.setText("Min");
		jLabelMinChangement.setBounds(new Rectangle(220, startY, 50, 20));
		minChangement.setText("");
		minChangement.setBounds(new Rectangle(250, startY, 50, 20));
		minChangement.addKeyListener(new ConfigureChangement_minutes_keyAdapter(this));

		jLabelSecChangement.setBounds(new Rectangle(320, startY, 50, 20));
		jLabelSecChangement.setText("Sec");
		secChangement.setText("");
		secChangement.setBounds(new Rectangle(350, startY, 50, 20));
		secChangement.addKeyListener(new ConfigureChangement_seconds_keyAdapter(this));
		
		jPanelSettings.add(jLabelTimeChangement, null);
		jPanelSettings.add(jLabelMinChangement, null);
		jPanelSettings.add(minChangement, null);
		jPanelSettings.add(jLabelSecChangement, null);
		jPanelSettings.add(secChangement, null);
		

		contentPane.add(jPanelSettings);

		contentPane.add(run, null);
		contentPane.add(exit, null);

		
		run.setBounds(new Rectangle(130, 530, 96, 25));
		run.setText("Run");
		run.addActionListener(new ConfigureTimer_run_actionAdapter(this));
		exit.setBounds(new Rectangle(260, 530, 96, 25));
		exit.setText("Exit");
		exit.addActionListener(new ConfigureTimer_exit_actionAdapter(this));
	}



	// File | Exit action performed
	public void jMenuFileExit_actionPerformed(ActionEvent e) {
		System.exit(0);
	}

	// Help | About action performed
	public void jMenuHelpShortcut_actionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(this,
				"Pause / Resume         P\nClose Window            Alt+F4\n",
				"Shortcut Keys", JOptionPane.INFORMATION_MESSAGE);
	}

	// Help | About action performed
	public void jMenuHelpAbout_actionPerformed(ActionEvent e) {
		JOptionPane
				.showMessageDialog(
						this,
						"Countdown Timer\nVersion 0.1\n\nWeb site: http://www.sourceforge.net/projects/countdown/",
						"About the program", JOptionPane.INFORMATION_MESSAGE);
	}

	// Overridden so we can exit when window is closed
	protected void processWindowEvent(WindowEvent e) {
		super.processWindowEvent(e);
		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			jMenuFileExit_actionPerformed(null);
		}
	}

	public void jMenuOptSave_actionPerformed(ActionEvent e) {
		JFileChooser fileChooser = new JFileChooser();
		MyFileFilter filter = new MyFileFilter();

		filter.addExtension("ct");
		fileChooser.setFileFilter(filter);
		if (fileChooser.showSaveDialog(this) != JFileChooser.APPROVE_OPTION)
			return;

		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(fileChooser
					.getSelectedFile().toString() + ".ct"));
			out.write(caption.getText());
			out.newLine();
			out.write(endCaption.getText());
			out.newLine();
			out.write(minDonne.getText());
			out.newLine();
			out.write(secDonne.getText());
			out.newLine();
			out.close();
		} catch (IOException exc) {
			JOptionPane.showMessageDialog(this, "File Could Not be Saved",
					"File Save Error", JOptionPane.WARNING_MESSAGE);
		}
	}

	public void jMenuOptLoad_actionPerformed(ActionEvent e) {
		JFileChooser fileChooser = new JFileChooser();
		MyFileFilter filter = new MyFileFilter();

		filter.addExtension("ct");
		fileChooser.setFileFilter(filter);
		if (fileChooser.showOpenDialog(this) != JFileChooser.APPROVE_OPTION)
			return;

		try {
			BufferedReader in = new BufferedReader(new FileReader(fileChooser
					.getSelectedFile().toString()));
			caption.setText(in.readLine());
			endCaption.setText(in.readLine());
			minDonne.setText(in.readLine());
			secDonne.setText(in.readLine());
			in.close();
		} catch (IOException exc) {
			JOptionPane.showMessageDialog(this, "File Could Not be Loaded",
					"File Load Error", JOptionPane.WARNING_MESSAGE);
		}
	}

	void exit_actionPerformed(ActionEvent e) {
		System.exit(0);
	}


	void minutesDonne(KeyEvent e) {
		if ((int) e.getKeyChar() == 8)
			return;
		if (minDonne.getText().length() == 3)
			e.consume();
		else if (e.getKeyChar() < '0' || e.getKeyChar() > '9')
			e.consume();
	}

	void secondsDonne(KeyEvent e) {
		if ((int) e.getKeyChar() == 8)
			return;
		if (secDonne.getText().length() == 2)
			e.consume();
		else if (e.getKeyChar() < '0' || e.getKeyChar() > '9')
			e.consume();
		else if (secDonne.getText().length() == 1
				&& secDonne.getText().charAt(0) >= '6')
			e.consume();
	}

	public void minutesChangement(KeyEvent e) {
		if ((int) e.getKeyChar() == 8)
			return;
		if (minChangement.getText().length() == 3)
			e.consume();
		else if (e.getKeyChar() < '0' || e.getKeyChar() > '9')
			e.consume();
		
	}

	public void secondsChangement(KeyEvent e) {
		if ((int) e.getKeyChar() == 8)
			return;
		if (secChangement.getText().length() == 2)
			e.consume();
		else if (e.getKeyChar() < '0' || e.getKeyChar() > '9')
			e.consume();
		else if (secChangement.getText().length() == 1
				&& secChangement.getText().charAt(0) >= '6')
			e.consume();
		
	}
	
	void run_actionPerformed(ActionEvent e) {

		// displaying the timer.
		DisplayTimer t = new DisplayTimer();

		if (minDonne.getText().length() == 0)
			minDonne.setText("0");
		if (secDonne.getText().length() == 0)
			secDonne.setText("0");
		if (secDonne.getText().length() == 1)
			secDonne.setText("0" + secDonne.getText());

		t.minsLabel.setForeground(number.getForeground());
		t.secsLabel.setForeground(number.getForeground());
		t.colonLabel.setForeground(number.getForeground());

		t.colon.setFont(new Font(number.getFont().getName(), number.getFont()
				.getStyle(), 120));
		t.colon.setForeground(number.getForeground());
		t.colon.setFont(new Font(t.colon.getFont().getName(), t.colon.getFont()
				.getStyle(), t.getHeight() / 3));
		t.colon.setSize(t.getWidth(), t.getHeight() / 3);
		Dimension frameSize = t.getSize();

		t.colon.setLocation(0, (t.getHeight() - t.colon.getHeight() - 100) / 2);

		t.seconds.setFont(new Font(number.getFont().getName(), number.getFont()
				.getStyle(), 120));
		t.seconds.setForeground(number.getForeground());
		t.seconds.setFont(new Font(t.seconds.getFont().getName(), t.seconds
				.getFont().getStyle(), t.getHeight() / 3));
		t.seconds.setSize(t.getWidth() / 2, t.getHeight() / 3);
		t.seconds.setLocation(t.getWidth() / 2 + 50,
				(t.getHeight() - t.colon.getHeight() - 100) / 2);
		t.seconds.setText(secDonne.getText());

		t.minutes.setFont(new Font(number.getFont().getName(), number.getFont()
				.getStyle(), 120));
		t.minutes.setForeground(number.getForeground());
		t.minutes.setFont(new Font(t.minutes.getFont().getName(), t.minutes
				.getFont().getStyle(), t.getHeight() / 3));
		t.minutes.setSize(t.getWidth() / 2 - 50, t.getHeight() / 3);
		t.minutes.setLocation(0,
				(t.getHeight() - t.colon.getHeight() - 100) / 2);
		t.minutes.setText(minDonne.getText());

		t.endCaption = endCaption.getText();

		t.caption.setText(caption.getText());

		t.caption.setFont(new Font(text.getFont().getName(), text.getFont()
				.getStyle(), 60));
		t.caption.setForeground(text.getForeground());
		t.caption.setSize(t.getWidth(), 80);
		t.caption.setLocation(0,
				(t.getHeight() / 3 - t.caption.getHeight()) / 2);
		t.caption.setText(caption.getText());

	
		t.show();
	}



	void jMenuOptSave_mouseClicked(MouseEvent e) {
		System.out.println("Clicked");
	}





}

class ConfigureTimer_jMenuFileExit_ActionAdapter implements ActionListener {
	ConfigureTimer adaptee;

	ConfigureTimer_jMenuFileExit_ActionAdapter(ConfigureTimer adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.jMenuFileExit_actionPerformed(e);
	}
}

class ConfigureTimer_jMenuHelpShortcut_ActionAdapter implements ActionListener {
	ConfigureTimer adaptee;

	ConfigureTimer_jMenuHelpShortcut_ActionAdapter(ConfigureTimer adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.jMenuHelpShortcut_actionPerformed(e);
	}
}

class ConfigureTimer_jMenuHelpAbout_ActionAdapter implements ActionListener {
	ConfigureTimer adaptee;

	ConfigureTimer_jMenuHelpAbout_ActionAdapter(ConfigureTimer adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.jMenuHelpAbout_actionPerformed(e);
	}
}




class ConfigureTimer_exit_actionAdapter implements
		java.awt.event.ActionListener {
	ConfigureTimer adaptee;

	ConfigureTimer_exit_actionAdapter(ConfigureTimer adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.exit_actionPerformed(e);
	}
}



class ConfigureChangement_minutes_keyAdapter extends java.awt.event.KeyAdapter {
	ConfigureTimer adaptee;

	ConfigureChangement_minutes_keyAdapter(ConfigureTimer adaptee) {
		this.adaptee = adaptee;
	}

	public void keyTyped(KeyEvent e) {
		adaptee.minutesChangement(e);
	}
}

class ConfigureChangement_seconds_keyAdapter extends java.awt.event.KeyAdapter {
	ConfigureTimer adaptee;

	ConfigureChangement_seconds_keyAdapter(ConfigureTimer adaptee) {
		this.adaptee = adaptee;
	}

	public void keyTyped(KeyEvent e) {
		adaptee.secondsChangement(e);
	}
}



class ConfigureTimer_minutes_keyAdapter extends java.awt.event.KeyAdapter {
	ConfigureTimer adaptee;

	ConfigureTimer_minutes_keyAdapter(ConfigureTimer adaptee) {
		this.adaptee = adaptee;
	}

	public void keyTyped(KeyEvent e) {
		adaptee.minutesDonne(e);
	}
}

class ConfigureTimer_seconds_keyAdapter extends java.awt.event.KeyAdapter {
	ConfigureTimer adaptee;

	ConfigureTimer_seconds_keyAdapter(ConfigureTimer adaptee) {
		this.adaptee = adaptee;
	}

	public void keyTyped(KeyEvent e) {
		adaptee.secondsDonne(e);
	}
}

//RUN
class ConfigureTimer_run_actionAdapter implements java.awt.event.ActionListener {
	ConfigureTimer adaptee;

	ConfigureTimer_run_actionAdapter(ConfigureTimer adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.run_actionPerformed(e);
	}
}


class ConfigureTimer_jMenuOptSave_ActionAdapter implements ActionListener {
	ConfigureTimer adaptee;

	ConfigureTimer_jMenuOptSave_ActionAdapter(ConfigureTimer adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.jMenuOptSave_actionPerformed(e);
	}
}

class ConfigureTimer_jMenuOptLoad_ActionAdapter implements ActionListener {
	ConfigureTimer adaptee;

	ConfigureTimer_jMenuOptLoad_ActionAdapter(ConfigureTimer adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.jMenuOptLoad_actionPerformed(e);
	}
}


class MyFileFilter extends javax.swing.filechooser.FileFilter {

	private static String TYPE_UNKNOWN = "Type Unknown";
	private static String HIDDEN_FILE = "Hidden File";

	private Hashtable filters = null;
	private String description = null;
	private String fullDescription = null;
	private boolean useExtensionsInDescription = true;

	public MyFileFilter() {
		this.filters = new Hashtable();
	}

	public boolean accept(File f) {
		if (f != null) {
			if (f.isDirectory()) {
				return true;
			}
			String extension = getExtension(f);

			if (extension != null && filters.get(getExtension(f)) != null) {
				return true;
			}
			;
		}
		return false;
	}

	public String getExtension(File f) {
		if (f != null) {
			String filename = f.getName();
			int i = filename.lastIndexOf('.');

			if (i > 0 && i < filename.length() - 1) {
				return filename.substring(i + 1).toLowerCase();
			}
			;
		}
		return null;
	}

	public void addExtension(String extension) {
		if (filters == null) {
			filters = new Hashtable(5);
		}
		filters.put(extension.toLowerCase(), this);
		fullDescription = null;
	}

	public String getDescription() {
		if (fullDescription == null) {
			if (description == null || isExtensionListInDescription()) {
				fullDescription = description == null ? "(" : description
						+ " (";
				// build the description from the extension list
				Enumeration extensions = filters.keys();

				if (extensions != null) {
					fullDescription += "." + (String) extensions.nextElement();
					while (extensions.hasMoreElements()) {
						fullDescription += ", ."
								+ (String) extensions.nextElement();
					}
				}
				fullDescription += ")";
			} else {
				fullDescription = description;
			}
		}
		return fullDescription;
	}

	public void setDescription(String description) {
		this.description = description;
		fullDescription = null;
	}

	public void setExtensionListInDescription(boolean b) {
		useExtensionsInDescription = b;
		fullDescription = null;
	}

	public boolean isExtensionListInDescription() {
		return useExtensionsInDescription;
	}
}



