import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

//game result page
public class Result extends JPanel {
	private JPanel win,lose,draw;
	private JButton quitbutton;
	private JPanel backgroundPanel;
	private ImageIcon button=new ImageIcon(Title.class.getResource("/resources/button/button.png"));
	private ImageIcon button_click=new ImageIcon(Title.class.getResource("/resources/button/button-c.png"));

	public Result() {
		setLayout(null);
		
		//win page
		win = new JPanel();
		win.setBounds(212, 300, 600, 70);
		win.setBorder(new LineBorder(new Color(255, 255, 255), 3, true));
		win.setBackground(new Color(247, 148, 29));
		add(win);
		
		JLabel winlabel = new JLabel("You Win!");
		winlabel.setForeground(new Color(255, 255, 255));
		winlabel.setFont(new Font("Cooper Black", Font.PLAIN, 50));
		win.add(winlabel);
		
		//lose page
		lose = new JPanel();
		lose.setBounds(212, 300, 600, 70);
		lose.setBorder(new LineBorder(new Color(255, 255, 255), 3, true));
		lose.setBackground(new Color(247, 148, 29));
		add(lose);
		
		JLabel loselabel = new JLabel("You Lose..");
		loselabel.setForeground(new Color(255, 255, 255));
		loselabel.setFont(new Font("Cooper Black", Font.PLAIN, 50));
		lose.add(loselabel);
		
		//draw page
		draw = new JPanel();
		draw.setBounds(212, 300, 600, 70);
		draw.setBorder(new LineBorder(new Color(255, 255, 255), 3, true));
		draw.setBackground(new Color(247, 148, 29));
		add(draw);
		
		JLabel drawlabel = new JLabel("You Draw");
		drawlabel.setForeground(new Color(255, 255, 255));
		drawlabel.setFont(new Font("Cooper Black", Font.PLAIN, 50));
		draw.add(drawlabel);
		
		//quit button
		quitbutton = new JButton("Quit",button);
		quitbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//end the program
				System.exit(0);
			}
		});
		quitbutton.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		quitbutton.setBounds(437, 630, 150, 50);
		quitbutton.setPressedIcon(button_click);
		quitbutton.setForeground(new Color(255, 255, 255));
		quitbutton.setBorderPainted(false);
		quitbutton.setContentAreaFilled(false);
        quitbutton.setHorizontalTextPosition(SwingConstants.CENTER);
		add(quitbutton);
		
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
	
	public void setResult(int num) {
		// num is 2, then you can see lose page
		if (num==2) {
			win.setVisible(false);
			lose.setVisible(true);
		}
		// num is 3, then you can see drew page
		else if (num==3) {
			draw.setVisible(true);
			win.setVisible(false);
			lose.setVisible(false);
		}
		// else, you can see win page
	}
}
