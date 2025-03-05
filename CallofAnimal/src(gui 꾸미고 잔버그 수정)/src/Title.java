import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

//title page class
public class Title extends JPanel {
	private JButton quitButton;
	public JButton answerButton,startButton;
	private JPanel backgroundPanel;
	private ButtonClickListener ButtonClickListener = new ButtonClickListener();
	private ImageIcon button=new ImageIcon(Title.class.getResource("/resources/button/button.png"));
	private ImageIcon button_click=new ImageIcon(Title.class.getResource("/resources/button/button-c.png"));

	public Title() {
		setLayout(null);
		
		//title name
		JLabel titleName = new JLabel();
		titleName.setIcon(new ImageIcon(Title.class.getResource("/resources/image/title.png")));
		titleName.setFont(new Font("Cooper Black", Font.PLAIN, 60));
		titleName.setForeground(new Color(240, 240, 240));
        titleName.setBounds(100, 150, 500, 200);
        titleName.setBackground(new Color(255,0,0,0));
        add(titleName);
		
        //start, see answer, quit button
		startButton = new JButton("Game Start",button);
		startButton.setPressedIcon(button_click);
		startButton.setForeground(new Color(255, 255, 255));
        startButton.setBounds(700, 200, 150, 50);
        startButton.setFont(new Font("Cooper Black", Font.PLAIN, 19));
        startButton.setBorderPainted(false);
        startButton.setContentAreaFilled(false);
        startButton.setHorizontalTextPosition(SwingConstants.CENTER);
        add(startButton);

        answerButton = new JButton("See Answer",button);
        answerButton.setPressedIcon(button_click);
        answerButton.setForeground(new Color(255, 255, 255));
        answerButton.setFont(new Font("Cooper Black", Font.PLAIN, 19));
        answerButton.setBounds(700, 300, 150, 50);
        answerButton.setBorderPainted(false);
        answerButton.setContentAreaFilled(false);
        answerButton.setHorizontalTextPosition(SwingConstants.CENTER);
        add(answerButton);

        quitButton = new JButton("Quit",button);
        quitButton.setPressedIcon(button_click);
        quitButton.setForeground(new Color(255, 255, 255));
        quitButton.addActionListener(ButtonClickListener);
        //quitButton.setOpaque(false);
        quitButton.setFont(new Font("Cooper Black", Font.PLAIN, 19));
        quitButton.setBounds(700, 400, 150, 50);
        quitButton.setBorderPainted(false);
        //quitButton.setRolloverEnabled(false);
		quitButton.setContentAreaFilled(false);
        quitButton.setHorizontalTextPosition(SwingConstants.CENTER);
        add(quitButton);
		
		//background panel
		backgroundPanel = new JPanel();
        backgroundPanel.setBounds(0, 0, 1024, 822);
        add(backgroundPanel);
        backgroundPanel.setLayout(null);
        
        JLabel background = new JLabel("image");
        background.setBounds(0, 0, 1058, 822);
        background.setIcon(new ImageIcon(Main.class.getResource("/resources/image/background.jpg")));
        backgroundPanel.add(background);
	}
	
	class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
           String s = e.getActionCommand();
           //end the program
           if (s.equals("Quit")) {
        	   System.exit(0);
           }
           //game start and see answer button's clickListener is in Main class
        }
	}
}
