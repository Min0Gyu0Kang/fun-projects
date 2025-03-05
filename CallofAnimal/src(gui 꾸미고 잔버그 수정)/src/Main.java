import java.awt.EventQueue;

import javax.swing.*;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

//main frame
public class Main extends JFrame {
	//container
	private Container c=getContentPane();
	private Answer answer;
	private Title title;
	private Gamescreen gamescreen;
	private CardLayout cardlayout=new CardLayout();

	//main method
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
        	public void run() {
        		try {
        			//create frame
        			Main frame = new Main();
        			frame.setVisible(true);
                } catch (Exception e) {
                	e.printStackTrace();
                }
        	}
        });
    }

    public Main() {
    	//frame setting
    	setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1024, 822);
        setLayout(cardlayout);
        
        //gamescreen page
        gamescreen=new Gamescreen();
        gamescreen.setting.backButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		//go title page from setting page
        		showPanel("title");
			}
		});
        
        //answer page
        answer=new Answer();
        answer.backbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//go title page from answer page
				showPanel("title");
			}
		});
        
        //title page
        title=new Title();
        ButtonClickListener ButtonClickListener = new ButtonClickListener();
        title.answerButton.addActionListener(ButtonClickListener);
        title.startButton.addActionListener(ButtonClickListener);
        
        //add title, answer, gamescreen page in container
        c.add(title,"title");
        c.add(answer,"answer");
        c.add(gamescreen, "gamescreen");
    }
    
    class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
           String s = e.getActionCommand();
           //go answer page from title page
           if (s.equals("See Answer")) {
        	   showPanel("answer");
           //go setting page in gamescreen from title page
           } else if (s.equals("Game Start")) {
        	   showPanel("gamescreen");
           }
        }
     }
    
    //show page method
    private void showPanel(String panelName) {
        cardlayout.show(c, panelName);
    }
}
