import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;

//See Answer page class 
public class Answer extends JPanel {
	public JButton backbutton;
	private JButton nextbutton,beforebutton,soundbutton;
	private JLabel nametext;
	private JPanel textPanel,animalPanel,backgroundPanel;
	private AnimalArr animalarr=new AnimalArr();
	private JLabel animal;
	private ButtonClickListener ButtonClickListener = new ButtonClickListener();
	private ImageIcon button=new ImageIcon(Title.class.getResource("/resources/button/button.png"));
	private ImageIcon button_click=new ImageIcon(Title.class.getResource("/resources/button/button-c.png"));

	public Answer() {
		setLayout(null);
		
		//next, before, sound, back Button create
		nextbutton = new JButton("Next",button);
		nextbutton.addActionListener(ButtonClickListener);
		nextbutton.setPressedIcon(button_click);
		nextbutton.setForeground(new Color(255, 255, 255));
        nextbutton.setFont(new Font("Cooper Black", Font.PLAIN, 20));
        nextbutton.setBounds(812, 700, 150, 50);
        nextbutton.setBorderPainted(false);
        nextbutton.setContentAreaFilled(false);
        nextbutton.setHorizontalTextPosition(SwingConstants.CENTER);
        add(nextbutton);
        
        beforebutton = new JButton("Before",button);
        beforebutton.addActionListener(ButtonClickListener);
		beforebutton.setPressedIcon(button_click);
		beforebutton.setForeground(new Color(255, 255, 255));
        beforebutton.setFont(new Font("Cooper Black", Font.PLAIN, 20));
        beforebutton.setBounds(62, 700, 150, 50);
        beforebutton.setBorderPainted(false);
        beforebutton.setContentAreaFilled(false);
        beforebutton.setHorizontalTextPosition(SwingConstants.CENTER);
        add(beforebutton);
        
        soundbutton = new JButton("Sound",button);
        soundbutton.addActionListener(ButtonClickListener);
        soundbutton.setPressedIcon(button_click);
		soundbutton.setForeground(new Color(255, 255, 255));
		soundbutton.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		soundbutton.setBounds(437, 700, 150, 50);
		soundbutton.setBorderPainted(false);
		soundbutton.setContentAreaFilled(false);
        soundbutton.setHorizontalTextPosition(SwingConstants.CENTER);
		add(soundbutton);
		
        backbutton = new JButton("Back",button);
        backbutton.setPressedIcon(button_click);
		backbutton.setForeground(new Color(255, 255, 255));
        backbutton.setFont(new Font("Cooper Black", Font.PLAIN, 20));
		backbutton.setBounds(62, 72, 150, 50);
		backbutton.setBorderPainted(false);
		backbutton.setContentAreaFilled(false);
        backbutton.setHorizontalTextPosition(SwingConstants.CENTER);
		add(backbutton);
		
		//animal name panel
		textPanel = new JPanel();
		textPanel.setBorder(new LineBorder(new Color(255, 255, 255), 3, true));
		textPanel.setBackground(new Color(247, 148, 29));
		textPanel.setBounds(262, 56, 500, 80);
		add(textPanel);
		textPanel.setLayout(null);
		
		nametext = new JLabel();
		nametext.setForeground(new Color(255, 255, 255));
		nametext.setHorizontalAlignment(SwingConstants.CENTER);
		showText(0);
		nametext.setFont(new Font("Cooper Black", Font.PLAIN, 28));
		nametext.setBackground(new Color(255, 128, 0));
		nametext.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		nametext.setBounds(12, 18, 464, 42);
		textPanel.add(nametext);
		
		//animal image panel
		animalPanel = new JPanel();
		animalPanel.setBounds(262, 161, 500, 500);
		animalPanel.setBorder(new LineBorder(new Color(255, 255, 255), 3, true));
		animalPanel.setBackground(new Color(255, 255, 255));
		add(animalPanel);
		animalPanel.setLayout(null);
		
		animal = new JLabel("");
		animal.setBounds(0, 0, 500, 500);
		showImage(animalarr.getAnimal(0).getImage());
		animalPanel.add(animal);
        
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
	
	//sound play method
	private void playSound(String filepath) {
	      try {
	    	  AudioInputStream ais = AudioSystem.getAudioInputStream(Answer.class.getResource(filepath));
	          Clip clip = AudioSystem.getClip();
	          clip.open(ais);
	          clip.start();
	      } catch (Exception ex) {
	    	  System.out.println("Error with playing sound.");
	          ex.printStackTrace();
	      }
	}
	
	//show image method
	private void showImage(String filepath) {
		animal.removeAll();
		animal.setIcon(new ImageIcon(Answer.class.getResource(filepath)));
	}
	
	//show animal name method 
	private void showText(int num) {
		nametext.setText("Animal's name: "+animalarr.getAnimal(num).getName());
	}
	
	//Button's clickListener
	class ButtonClickListener implements ActionListener {
		private int num;
		ButtonClickListener(){
			num=0;
		}
        public void actionPerformed(ActionEvent e) {
        	String s = e.getActionCommand();
        	//you can see next animal
            if (s.equals("Next")) {
            	if (num==50) 
            		num=0;
            	else
            		num++;
            	showText(num);
            	showImage(animalarr.getAnimal(num).getImage());
           } 
           //you can see before animal
           else if (s.equals("Before")) {
        	   if (num==0)
        		   	num=50;
        	   else 
        		   	num--;
        	   showText(num);
        	   showImage(animalarr.getAnimal(num).getImage());
           } 
           //play sound
           else if (s.equals("Sound")) {
        	   playSound(animalarr.getAnimal(num).getSound());
           }
           //back button's clickLister is in Main class
        }
	}
}
