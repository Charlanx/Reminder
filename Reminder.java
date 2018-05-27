import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Reminder implements KeyListener {

	static String text = "";
	static String finalText = "";
	static JLabel label = new JLabel(text, JLabel.CENTER);
	static boolean isRunning = false;
	static FileWriter fw;
	static BufferedWriter bw;
	static Font font;
	static int screenWidth;
	static int screenHeight;
	static String desktopLocationString;
	static Calendar calendar;
	static FileWriter fw2;
	static BufferedWriter bw2;

	public static void main(String[] args) throws IOException {
		Dimension screenDimensions = Toolkit.getDefaultToolkit().getScreenSize();
		screenWidth = (int) screenDimensions.getWidth();
		screenHeight = (int) screenDimensions.getHeight();
		JFrame frame = new JFrame();
		font = new Font("TimesNewRoman", Font.PLAIN, 100);
		label.setFont(font);
		label.setText("Write your Daily Reminder for Today.");
		frame.setTitle("Daily Reminder Application");
		frame.addKeyListener(new Reminder());
		frame.add(label);
		frame.setSize(screenWidth, screenHeight);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		desktopLocationString = System.getProperty("user.home") + "/Desktop";
		calendar = Calendar.getInstance();
		frame.addWindowListener(new java.awt.event.WindowAdapter() {

			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				try {
					finalText = text;
					File dailyRemindersFile = new File(desktopLocationString + "/DailyReminders");
					if (dailyRemindersFile.exists() == false) {
						dailyRemindersFile.mkdir();
					} else if (dailyRemindersFile.exists() == true) {
					}
					String dailyRemindersPath = desktopLocationString + "\\DailyReminders";
					Date date = calendar.getTime();
					SimpleDateFormat formattedDate = new SimpleDateFormat("MM-dd-YYYY");
					String currentDate = formattedDate.format(date);
					File dailyRemindersLocation = new File(dailyRemindersPath,
							"\\DailyReminders" + currentDate + ".txt");
					fw2 = new FileWriter(dailyRemindersLocation, true);
					bw2 = new BufferedWriter(fw2);
					if (dailyRemindersLocation.exists() == true) {
						bw2.append(finalText + "\n");
					}
					bw2.flush();
					bw2.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if (e.getID() == KeyEvent.KEY_TYPED) {
			text += e.getKeyChar();
			finalText = text;
			label.setText(text);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}
}
