import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.Color;

//Game page class
public class Game extends JPanel {
	public JTextField textField;
	public JButton startbutton, submitbutton;
	private JPanel imagepanel,answerpanel,infopanel,logpanel;
	public JPanel time;
	public JLabel image,rounder,scoer,timer;
	public JTextArea logText;
	private JScrollPane scrollPane;
	private JPanel backgroundPanel;
	private ImageIcon button=new ImageIcon(Title.class.getResource("/resources/button/button.png"));
	private ImageIcon button_click=new ImageIcon(Title.class.getResource("/resources/button/button-c.png"));

	public Game() {
		setLayout(null);
		
		//animal image panel
		imagepanel = new JPanel();
		imagepanel.setBounds(60, 150, 500, 500);
		imagepanel.setBorder(new LineBorder(new Color(255, 255, 255), 3, true));
		imagepanel.setBackground(new Color(255, 255, 255));
		add(imagepanel);
		imagepanel.setLayout(null);
		
		image = new JLabel("");
		image.setBounds(0, 0, 500, 500);
		imagepanel.add(image);
		
		//user's input panel
		answerpanel = new JPanel();
		answerpanel.setBounds(60, 680, 500, 80);
		answerpanel.setBorder(new LineBorder(new Color(255, 255, 255), 3, true));
		answerpanel.setBackground(new Color(247, 148, 29));
		add(answerpanel);
		answerpanel.setLayout(null);
		
		textField = new JTextField();
		textField.setFont(new Font("Dialog", Font.PLAIN, 20));
		textField.setBounds(100, 15, 390, 50);
		textField.setColumns(10);
		answerpanel.add(textField);
		
		JLabel answer = new JLabel("Answer: ");
		answer.setForeground(new Color(255, 255, 255));
		answer.setFont(new Font("Cooper Black", Font.PLAIN, 16));
		answer.setBounds(10, 15, 80, 50);
		answerpanel.add(answer);
		
		//chat log panel
		logpanel = new JPanel();
		logpanel.setBounds(618, 150, 350, 500);
		logpanel.setBorder(new LineBorder(new Color(255, 255, 255), 3, true));
		logpanel.setBackground(new Color(247, 148, 29));
		add(logpanel);
		
		logText = new JTextArea("");
		logText.setBounds(1, 1, 348, 498);;
		logpanel.setLayout(null);
		logText.setEditable(false);
		logText.setFont(new Font("Dialog", Font.PLAIN, 20));
		
		scrollPane = new JScrollPane(logText);
		scrollPane.setBounds(10, 10, 330, 480);
		logpanel.add(scrollPane);
		
		//info(score, score, time) panel
		infopanel = new JPanel();
		infopanel.setBounds(62, 20, 900, 100);
		infopanel.setBorder(new LineBorder(new Color(255, 255, 255), 3, true));
		infopanel.setBackground(new Color(247, 148, 29));
		add(infopanel);
		infopanel.setLayout(null);
		
		//round panel in info panel 
		JPanel round = new JPanel();
		round.setBounds(10, 15, 250, 70);
		round.setBackground(new Color(247, 148, 29));
		infopanel.add(round);
		round.setLayout(null);
		
		rounder = new JLabel("Round: ");
		rounder.setForeground(new Color(255, 255, 255));
		rounder.setFont(new Font("Cooper Black", Font.PLAIN, 30));
		rounder.setBounds(5, 5, 240, 60);
		round.add(rounder);
		
		//score panel in info panel
		JPanel score = new JPanel();
		score.setBounds(325, 15, 250, 70);
		score.setBackground(new Color(247, 148, 29));
		infopanel.add(score);
		score.setLayout(null);
		
		scoer = new JLabel("Score: ");
		scoer.setForeground(new Color(255, 255, 255));
		scoer.setFont(new Font("Cooper Black", Font.PLAIN, 30));
		scoer.setBounds(5, 5, 240, 60);
		score.add(scoer);
		
		//time panel in info panel
		time = new JPanel();
		time.setBounds(640, 15, 250, 70);
		time.setBackground(new Color(247, 148, 29));
		infopanel.add(time);
		time.setLayout(null);
		
		timer = new JLabel("Time: ");
		timer.setForeground(new Color(255, 255, 255));
		timer.setFont(new Font("Cooper Black", Font.PLAIN, 30));
		timer.setBounds(5, 5, 100, 60);
		time.add(timer);
		
		//start, submit button
		startbutton = new JButton("Game start!",button);
		startbutton.setPressedIcon(button_click);
		startbutton.setForeground(new Color(255, 255, 255));
		startbutton.setFont(new Font("Cooper Black", Font.PLAIN, 19));
		startbutton.setBounds(818, 694, 150, 50);
		startbutton.setBorderPainted(false);
        startbutton.setContentAreaFilled(false);
        startbutton.setHorizontalTextPosition(SwingConstants.CENTER);
		add(startbutton);
		
		submitbutton = new JButton("Submit",button);
		submitbutton.setPressedIcon(button_click);
		submitbutton.setForeground(new Color(255, 255, 255));
		submitbutton.setFont(new Font("Cooper Black", Font.PLAIN, 19));
		submitbutton.setBounds(618, 694, 150, 50);
		submitbutton.setBorderPainted(false);
        submitbutton.setContentAreaFilled(false);
        submitbutton.setHorizontalTextPosition(SwingConstants.CENTER);
		add(submitbutton);
		startbutton.setVisible(false);
		//start, submit button's clickListener is in gamescree class
		
		//background panel
		backgroundPanel = new JPanel();
		backgroundPanel.setBounds(0, 0, 1068, 832);
		add(backgroundPanel);
		backgroundPanel.setLayout(null);
				
		JLabel background = new JLabel("image");
		background.setBounds(0, 0, 1058, 822);
		background.setVerticalAlignment(SwingConstants.TOP);
		background.setIcon(new ImageIcon(Answer.class.getResource("/resources/image/background.jpg")));
		backgroundPanel.add(background);
	}
}
