import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

//create or connect room page class
public class Setting extends JPanel {
	public createPanel createpanel;
	public connectPanel connectpanel;
	private JButton createRoomButton,connectRoomButton;
	public JButton backButton;
	private ButtonClickListener ButtonClickListener = new ButtonClickListener();
	private JPanel backgroundPanel;
	private ImageIcon button=new ImageIcon(Title.class.getResource("/resources/button/button.png"));
	private ImageIcon button_click=new ImageIcon(Title.class.getResource("/resources/button/button-c.png"));

	public Setting() {
		setLayout(null);
		
		//create and connect room, back button
		createRoomButton = new JButton("Create Room",button);
		createRoomButton.setPressedIcon(button_click);
		createRoomButton.setForeground(new Color(255, 255, 255));
		createRoomButton.addActionListener(ButtonClickListener);
		createRoomButton.setFont(new Font("Cooper Black", Font.PLAIN, 15));
		createRoomButton.setBounds(200, 250, 150, 50);
		createRoomButton.setBorderPainted(false);
        createRoomButton.setContentAreaFilled(false);
        createRoomButton.setHorizontalTextPosition(SwingConstants.CENTER);
		add(createRoomButton);
		
		connectRoomButton = new JButton("Connect Room",button);
		connectRoomButton.setPressedIcon(button_click);
		connectRoomButton.setForeground(new Color(255, 255, 255));
		connectRoomButton.addActionListener(ButtonClickListener);
		connectRoomButton.setFont(new Font("Cooper Black", Font.PLAIN, 15));
		connectRoomButton.setBounds(674, 250, 150, 50);
		connectRoomButton.setBorderPainted(false);
        connectRoomButton.setContentAreaFilled(false);
        connectRoomButton.setHorizontalTextPosition(SwingConstants.CENTER);
		add(connectRoomButton);
		
		backButton = new JButton("Back",button);
		backButton.setPressedIcon(button_click);
		backButton.setForeground(new Color(255, 255, 255));
		backButton.setBounds(62, 72, 150, 50);
		backButton.setFont(new Font("Cooper Black", Font.PLAIN, 19));
        backButton.setBorderPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.setHorizontalTextPosition(SwingConstants.CENTER);
		add(backButton);
		
		//create room panel
		createpanel=new createPanel();
		createpanel.setBounds(200, 400, 624, 300);
		add(createpanel);
		createpanel.setVisible(false);
		
		//connect room panel
		connectpanel = new connectPanel();
		connectpanel.setBounds(200, 400, 624, 300);
		add(connectpanel);
		connectpanel.setVisible(false);
		
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
	
	//create room panel class
	class createPanel extends JPanel{
		public JTextField portInput,nameInput;
		public JButton createButton;
		private String ipnum;
		
		createPanel(){
			setLayout(null);
			setBorder(new LineBorder(new Color(255, 255, 255), 3, true));
			setBackground(new Color(247, 148, 29));
			
			try {
				InetAddress local = InetAddress.getLocalHost();
				ipnum=local.getHostAddress();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			
			//port Num and name input textField
			JLabel ip = new JLabel("Your's Ip:              "+ipnum);
			ip.setFont(new Font("Cooper Black", Font.PLAIN, 20));
			ip.setBounds(10, 25, 330, 40);
			ip.setForeground(new Color(255, 255, 255));
			add(ip);
			
			JLabel port = new JLabel("Port Number: ");
			port.setFont(new Font("Cooper Black", Font.PLAIN, 20));
			port.setBounds(10, 100, 150, 40);
			port.setForeground(new Color(255, 255, 255));
			add(port);
			
			JLabel name = new JLabel("Your's Name:");
			name.setFont(new Font("Cooper Black", Font.PLAIN, 20));
			name.setBounds(10, 175, 150, 40);
			name.setForeground(new Color(255, 255, 255));
			add(name);
			
			portInput = new JTextField();
			portInput.setFont(new Font("Dialog", Font.PLAIN, 20));
			portInput.setBounds(174, 100, 300, 40);
			add(portInput);
			portInput.setColumns(10);
			
			nameInput = new JTextField();
			nameInput.setFont(new Font("Dialog", Font.PLAIN, 20));
			nameInput.setBounds(174, 175, 300, 40);
			add(nameInput);
			nameInput.setColumns(10);
			
			//final create button
			createButton = new JButton("Create!",button);
			createButton.setFont(new Font("Cooper Black", Font.PLAIN, 20));
			createButton.setBounds(237, 240, 150, 50);
			createButton.setPressedIcon(button_click);
			createButton.setForeground(new Color(255, 255, 255));
			createButton.setBorderPainted(false);
			createButton.setContentAreaFilled(false);
	        createButton.setHorizontalTextPosition(SwingConstants.CENTER);
			add(createButton);			
		}
	}
	
	//connect room panel class
	class connectPanel extends JPanel{
		public JTextField ipInput_1,portInput_1,nameInput_1;
		public JButton connectButton;
		
		connectPanel(){
			setLayout(null);
			setBorder(new LineBorder(new Color(255, 255, 255), 3, true));
			setBackground(new Color(247, 148, 29));
			
			//port Num and name input textField
			JLabel ip_1 = new JLabel("Server's Ip: ");
			ip_1.setFont(new Font("Cooper Black", Font.PLAIN, 20));
			ip_1.setBounds(10, 25, 150, 40);
			ip_1.setForeground(new Color(255, 255, 255));
			add(ip_1);
			
			JLabel port_1 = new JLabel("Port Number: ");
			port_1.setFont(new Font("Cooper Black", Font.PLAIN, 20));
			port_1.setBounds(10, 100, 150, 40);
			port_1.setForeground(new Color(255, 255, 255));
			add(port_1);
			
			JLabel name_1 = new JLabel("Your's Name:");
			name_1.setFont(new Font("Cooper Black", Font.PLAIN, 20));
			name_1.setBounds(10, 175, 150, 40);
			name_1.setForeground(new Color(255, 255, 255));
			add(name_1);
			
			ipInput_1 = new JTextField();
			ipInput_1.setFont(new Font("Dialog", Font.PLAIN, 20));
			ipInput_1.setColumns(10);
			ipInput_1.setBounds(174, 25, 300, 40);
			add(ipInput_1);
			
			portInput_1 = new JTextField();
			portInput_1.setFont(new Font("Dialog", Font.PLAIN, 20));
			portInput_1.setColumns(10);
			portInput_1.setBounds(174, 100, 300, 40);
			add(portInput_1);
			
			nameInput_1 = new JTextField();
			nameInput_1.setFont(new Font("Dialog", Font.PLAIN, 20));
			nameInput_1.setColumns(10);
			nameInput_1.setBounds(174, 175, 300, 40);
			add(nameInput_1);
			
			//final connect button
			connectButton = new JButton("Access!",button);
			connectButton.setFont(new Font("Cooper Black", Font.PLAIN, 20));
			connectButton.setBounds(237, 240, 150, 50);
	        connectButton.setPressedIcon(button_click);
			connectButton.setForeground(new Color(255, 255, 255));
			connectButton.setBorderPainted(false);
			connectButton.setContentAreaFilled(false);
	        connectButton.setHorizontalTextPosition(SwingConstants.CENTER);
			add(connectButton);
		}
	}
	
	class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
           String s = e.getActionCommand();
           //set visible create panel
           if (s.equals("Create Room")) {
        	   createpanel.setVisible(true);
        	   connectpanel.setVisible(false);
           //set visible connect panel
           } else if (s.equals("Connect Room")) {
        	   connectpanel.setVisible(true);
        	   createpanel.setVisible(false);
           } 
           //back button's clickListener is in Main
           //final create and connect button's clickListener is in Gamescreen
        }
	}
}
