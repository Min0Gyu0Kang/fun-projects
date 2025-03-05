import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.CardLayout;
import java.awt.Font;

//Gamescreen page(setting, game,result page) class
public class Gamescreen extends JPanel {
	public Setting setting;
	private Game game;
	private Result result;
	private Server server;
	private Client client1, client2;
	private CardLayout cardlayout;
	private AnimalArr animalarr = new AnimalArr();
	private Timer time;
	private boolean client2Connected = false;


	public Gamescreen() {
		// set layout
		cardlayout = new CardLayout();
		setLayout(cardlayout);

		// create setting page
		setting = new Setting();
		setting.createpanel.createButton.addActionListener(new ActionListener() {
			// click final create button, then you can create server with the your's input
			// port number
			// and connect this server as client1 with your's input name
			private int portNum;
			private String name;

			public void actionPerformed(ActionEvent e) {
				try {
					portNum = Integer.parseInt(setting.createpanel.portInput.getText());
					name = setting.createpanel.nameInput.getText();
					checkName(name);
					// create server thread and run
					server = new Server(portNum);
					// create client1 thread and run
					client1 = new Client("localhost", portNum, name);
					server.start();
					client1.start();
				} catch (NumberFormatException ex) {
					// if invalid port format, the you can see popup menu
					JOptionPane error = new JOptionPane();
					JOptionPane.showMessageDialog(null, "Invalid port number input format");
				} catch (NameFormatException ex) {
					// if invalid name format, then you can see popup menu
					JOptionPane error = new JOptionPane();
					JOptionPane.showMessageDialog(null,
							"<html>Invalid name input format<br>(minimum 1 character,maximum 10 characters)");
				}
			}
		});
		setting.connectpanel.connectButton.addActionListener(new ActionListener() {
			// click final connect button, then you can connect server with the your's input
			// port number and name
			private int portNum;
			private String name;
			private String serverIp;

			public void actionPerformed(ActionEvent e) {
				try {
					portNum = Integer.parseInt(setting.connectpanel.portInput_1.getText());
					name = setting.connectpanel.nameInput_1.getText();
					serverIp = setting.connectpanel.ipInput_1.getText();
					checkIp(serverIp);
					checkName(name);
					portNum = Integer.parseInt(setting.connectpanel.portInput_1.getText());
					client2 = new Client(serverIp, portNum, name);
					client2.start();
				} catch (IpFormatException ex) {
					JOptionPane error = new JOptionPane();
					JOptionPane.showMessageDialog(null, "Invalid ip input format");
				} catch (NumberFormatException ex) {
					// if invalid port format, the you can see popup menu
					JOptionPane error = new JOptionPane();
					JOptionPane.showMessageDialog(null, "Invalid port number input format");
				} catch (NameFormatException ex) {
					// if invalid name format, then you can see popup menu
					JOptionPane error = new JOptionPane();
					JOptionPane.showMessageDialog(null,
							"<html>Invalid name input format<br>(minimum 1 character,maximum 10 characters)");
				}
			}
		});

		// create game page
		game = new Game();

		// create result page
		result = new Result();

		// add setting, game, result page in gamescreen page
		add(setting, "setting");
		add(game, "game");
		add(result, "result");

		// first show setting page
		showPanel("setting");
	}

	// server runnable class
	class Server implements Runnable {
		private static final int NUM_CLIENTS = 2;
		private BufferedReader[] clientReaders;
		private AtomicBoolean pEntered = new AtomicBoolean(false);
		private ServerSocket serverSocket;
		private Thread t;
		private Thread messageRelayThreads[] = new Thread[2];
		private int serverport;
		// score info
		private int[] score = { 0, 0 };
		// round info
		private int round = 1;
		private RAndom random = new RAndom();
		// constructor
		Server(int serverPort) {
			serverport = serverPort;
		}

		// client1,2's input and output control method
		private void relayMessages(BufferedReader clientReader, PrintWriter[] clientWriters, int senderIndex) {
			try {
				String message;
				while ((message = clientReader.readLine()) != null && !message.equals("quit")) {
					int receiverIndex = (senderIndex + 1) % NUM_CLIENTS;
					// send draw flag to client1,2 if round is 51 or higher
					if (round >= 51) {
						clientWriters[0].println("gameover 3");
						clientWriters[1].println("gameover 3");
						break;
					}
					// time out
					if (message.equals("####sTop####")) {
						// round up
						round += 1;
						// send answer to client1,2
						clientWriters[receiverIndex].println(
								"Time out! The answer was " + animalarr.getAnimal(random.getNum(round - 2)).getName());
						clientWriters[senderIndex].println(
								"Time out! The answer was " + animalarr.getAnimal(random.getNum(round - 2)).getName());
						// send sound flag
						clientWriters[receiverIndex].println("index " + random.getNum(round - 2));
						clientWriters[senderIndex].println("index " + random.getNum(round - 2));
						clientWriters[receiverIndex]
								.println(" " + animalarr.getAnimal(random.getNum(round - 2)).getName() + "'s sound is "
										+ animalarr.getAnimal(random.getNum(round - 2)).getCry());
						clientWriters[senderIndex].println(" " + animalarr.getAnimal(random.getNum(round - 2)).getName()
								+ "'s sound is " + animalarr.getAnimal(random.getNum(round - 2)).getCry());
						// go to next round button set visible
						game.startbutton.setVisible(true);
					}
					// else
					else {
						if (client2Connected) {
							// server send client's message to client1,client2
							clientWriters[senderIndex].println("[Client" + (senderIndex + 1) + "]: " + message);
							clientWriters[receiverIndex].println("[Client" + (senderIndex + 1) + "]: " + message);
						}
						// when the answer is correct
						if (message.equals(animalarr.getAnimal(random.getNum(round - 1)).getName())) {
							// answer flag on, round+1, and correct client score +1
							pEntered.set(true);
							score[senderIndex] += 1;
							round += 1;
						}
						// if client1 win, then send win flag to client1, and send lose flag to client2
						if (score[0] == 5) {
							clientWriters[0].println("gameover 1");
							clientWriters[1].println("gameover 2");
							break;
						}
						// if client2 win, then send win flag to client2, and send lose flag to client1
						else if (score[1] == 5) {
							clientWriters[0].println("gameover 2");
							clientWriters[1].println("gameover 1");
							break;
						}

						// if answer flag on
						if (pEntered.get()) {
							// server send who corrected to client1,2
							clientWriters[receiverIndex].println("Correct! The answer was "
									+ animalarr.getAnimal(random.getNum(round - 2)).getName());
							clientWriters[senderIndex].println("Correct! The answer was "
									+ animalarr.getAnimal(random.getNum(round - 2)).getName());
							// send sound flag
							clientWriters[receiverIndex].println("index " + random.getNum(round - 2));
							clientWriters[senderIndex].println("index " + random.getNum(round - 2));
							clientWriters[receiverIndex]
									.println(" " + animalarr.getAnimal(random.getNum(round - 2)).getName()
											+ "'s sound is " + animalarr.getAnimal(random.getNum(round - 2)).getCry());
							clientWriters[senderIndex]
									.println(" " + animalarr.getAnimal(random.getNum(round - 2)).getName()
											+ "'s sound is " + animalarr.getAnimal(random.getNum(round - 2)).getCry());
							// go to next round button set visible
							game.startbutton.setVisible(true);
							// and answer flag off
							pEntered.set(false);
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// run method
		public void run() {
			Socket[] clientSockets = new Socket[NUM_CLIENTS];
			PrintWriter[] clientWriters = new PrintWriter[NUM_CLIENTS];
			try {
				serverSocket = new ServerSocket(serverport);
				System.out.println("Server started. Waiting for clients to connect...");

				// accept client1,2 and connect send stream
				for (int i = 0; i < NUM_CLIENTS; i++) {
					clientSockets[i] = serverSocket.accept();
					clientWriters[i] = new PrintWriter(clientSockets[i].getOutputStream(), true);
					clientWriters[i].println("Client" + (i + 1) + " connected.");
				}
				clientWriters[0].println("Client2 connected.");
				client2Connected = true;// client2 connected is true
				// game start button set visible
				game.startbutton.setVisible(true);
				game.startbutton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							Thread.sleep(2000);
						} catch (Exception ex) {
							ex.printStackTrace();
						}
						// send round start
						clientWriters[0].println("Round" + round + " start!");
						clientWriters[1].println("Round" + round + " start!");
						System.out.println(random.getNum(round - 1));
						// send round, score info and animal number
						clientWriters[0].println(
								"r " + round + " s " + score[0] + " " + score[1] + " n " + random.getNum(round - 1));
						clientWriters[1].println(
								"r " + round + " s " + score[1] + " " + score[0] + " n " + random.getNum(round - 1));
						// start button set invisible and change start button to next button
						game.startbutton.setVisible(false);
						game.startbutton.setText("Next");
					}
				});

				// connect input stream and input thread start
				clientReaders = new BufferedReader[NUM_CLIENTS];
				for (int i = 0; i < NUM_CLIENTS; i++) {
					clientReaders[i] = new BufferedReader(new InputStreamReader(clientSockets[i].getInputStream()));
					final int clientIndex = i;
					messageRelayThreads[i] = new Thread(
							() -> relayMessages(clientReaders[clientIndex], clientWriters, clientIndex));
					messageRelayThreads[i].start();
				}

				messageRelayThreads[0].join();
				messageRelayThreads[1].join();
				clientWriters[0].close();
				clientWriters[1].close();
				clientReaders[0].close();
				clientReaders[1].close();
				serverSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		// server thread start method
		public void start() {
			if (t == null) {
				t = new Thread(this);
				t.start();
			}
		}
	}

	// client runnable class
	class Client implements Runnable {
		private Thread t;
		private int serverport;
		private String name;
		private String serverIp;
		public PrintWriter writer;

		// set user name and server port to enter
		Client(String serverip, int serverPort, String name) {
			serverIp = serverip;
			serverport = serverPort;
			this.name = name;
		}

		// run method
		public void run() {
			BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
			try {
				// create socket and input output stream
				Socket socket = new Socket(serverIp, serverport);
				writer = new PrintWriter(socket.getOutputStream(), true);
				BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

				// if connect accpet, then show game page
				showPanel("game");

				// server's message receive thread
				Thread messageReceiverThread = new Thread(() -> {
					try {
						String message;
						while ((message = reader.readLine()) != null) {
							// if server's message is info
							if (message.charAt(0) == 'r') {
								String[] arr = message.split(" ");
								try {
									// round up
									game.rounder.setText("Round: " + Integer.parseInt(arr[1]));
									// display score
									game.scoer.setText(
											"Score: " + Integer.parseInt(arr[3]) + "/" + Integer.parseInt(arr[4]));
									// display animal image
									game.image.removeAll();
									game.image.setIcon(new ImageIcon(Gamescreen.class
											.getResource(animalarr.getAnimal(Integer.parseInt(arr[6])).getImage())));
									// start timer
									time = new Timer(31);
									game.time.add(time);
									time.start();
								} catch (Exception ex) {
									ex.printStackTrace();
								}
							}
							// if sound flag on
							else if (message.charAt(0) == 'i') {
								try {
									String[] arr = message.split(" ");
									time.stop();
									// play sound
									playSound(animalarr.getAnimal(Integer.parseInt(arr[1])).getSound());
								} catch (Exception ex) {
									ex.printStackTrace();
								}
							}
							// if win or lose or draw flag on
							else if (message.split(" ")[0].equals("gameover")) {
								try {
									result.setResult(Integer.parseInt(message.split(" ")[1]));
								} catch (Exception ex) {
									ex.printStackTrace();
								}
								// show result page
								showPanel("result");
								reader.close();
								writer.close();
								socket.close();
								break;
							}
							// else
							else {
								// show server's message in log panel
								game.logText.append(message + "\n");
								game.logText.setCaretPosition(game.logText.getDocument().getLength());
							}
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				});

				messageReceiverThread.start();
				/*
				 * //client's input submit button
				 * game.submitbutton.addActionListener(new ActionListener() {
				 * private String input;
				 * public void actionPerformed(ActionEvent e) {
				 * if ((input=game.textField.getText())!=null) {
				 * writer.println(input);
				 * game.textField.setText("");
				 * }
				 * }
				 * });
				 */
			} catch (IOException e) {
				e.printStackTrace();
			}

			game.submitbutton.addActionListener(new ActionListener() {
				private String input;

				public void actionPerformed(ActionEvent e) {
					if (client2Connected) {
						if ((input = game.textField.getText()) != null) {
							writer.println(input);
							game.textField.setText("");
						}
					} else {
						game.textField.setText("");
					}
				}
			});
		}

		// start client thread method
		public void start() {
			if (t == null) {
				t = new Thread(this);
				try {
					t.sleep(1000);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				t.start();
			}
		}

		// sound play method
		private void playSound(String filepath) {
			try {
				AudioInputStream ais = AudioSystem.getAudioInputStream(Client.class.getResource(filepath));
				Clip clip = AudioSystem.getClip();
				clip.open(ais);
				clip.start();
			} catch (Exception ex) {
				System.out.println("Error with playing sound.");
				ex.printStackTrace();
			}
		}
	}

	// timer runnable label class
	class Timer extends JLabel implements Runnable {
		private Thread t;
		// time out code
		private static final String CODE = "####sTop####";
		// running flag
		private final AtomicBoolean run = new AtomicBoolean(false);
		private int second;

		// constructor setting timer time
		public Timer(int second) {
			setOpaque(true);
			setBounds(95, 5, 60, 60);
			setText(second + "");
			setFont(new Font("Dialog", Font.PLAIN, 30));
			setHorizontalAlignment(JLabel.CENTER);

			this.second = second;
		}

		// run method
		@Override
		public void run() {
			// running flag on
			run.set(true);
			while (run.get()) {
				try {
					// 1 second flows
					Thread.sleep(1000);
					if (second > 0) {
						second -= 1;
						setText(second + "");
					}
					// if time out
					else {
						try {
							// send time out code to server
							client1.writer.println(CODE);
						} catch (Exception ex) {
							ex.printStackTrace();
						}
						break;
					}
				} catch (InterruptedException e) {
					// and running flag off then time thread stop
					Thread.currentThread().interrupt();
				}
			}
		}

		// start timer thread
		public void start() {
			t = new Thread(this);
			t.start();
		}

		// stop method
		public void stop() {
			// running flag off
			run.set(false);
		}
	}

	// name format exception
	class NameFormatException extends Exception {
		public NameFormatException() {
		}
	}

	//
	class IpFormatException extends Exception {
		public IpFormatException() {
		}
	}

	// throw name format exception method
	private void checkName(String name) throws NameFormatException {
		if (name.equals("") || name.length() > 10) {
			throw new NameFormatException();
		}
	}

	// throw name format exception method
	private void checkIp(String ip) throws IpFormatException {
		try {
			String[] arr = ip.split("[.]");
			for (int i = 0; i < 4; ++i) {
				Integer.parseInt(arr[i]);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new IpFormatException();
		} catch (NumberFormatException e) {
			throw new IpFormatException();
		}
	}

	// show page method
	private void showPanel(String panelName) {
		cardlayout.show(this, panelName);
	}
}
