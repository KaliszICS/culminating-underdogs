import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Main extends JFrame implements ActionListener {
	private JTextField[][] grid = new JTextField[6][5];
	private JButton startButton = new JButton("Start");
	private JButton guessButton = new JButton("Guess");
	private JLabel statusLabel = new JLabel("Click Start");

	private String[] words;
	private WordleGame game;
	private SortAlgorithm sorter = new MergeSort();
	private SearchAlgorithm finder = new BinarySearch();

	public Main() {
		super("Wordle");
		try {
			words = WordReader.loadWords("words.txt");
		}
		catch (IOException e ) {
			JOptionPane.showMessageDialog(this, "Error loading words.txt");
			System.exit(1);
		}

		sorter.sort(words);
		setupUI();

		setSize(380,520);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);

	}

	private void setupUI() {
		setLayout(new BorderLayout(5,5));
		JPanel gridPanel = new JPanel(new GridLayout(6,5,3,3));

		for (int r = 0; r < 6; r++) {
			for (int c = 0; c < 5; c++) {
				JTextField tf = new JTextField();
				tf.setHorizontalAlignment(JTextField.CENTER);
				tf.setFont(new Font("Arial", Font.BOLD, 20));
				tf.setEditable(false);
				grid[r][c] = tf;
				gridPanel.add(tf);
			}
		}
		add(gridPanel, BorderLayout.CENTER);

		JPanel controlPanel = new JPanel();
		guessButton.setEnabled(false);
		controlPanel.add(startButton);
		controlPanel.add(guessButton);
		controlPanel.add(statusLabel);
		add(controlPanel, BorderLayout.SOUTH);

		startButton.addActionListener(this);
		guessButton.addActionListener(this);
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == startButton) {
			startGame();
		}
		else {
			makeGuess();
		}
	}

	private void startGame() {
		game = new WordleGame(words[(int) (Math.random() * words.length)]);
		statusLabel.setText("Guess 1");
		startButton.setEnabled(false);
		guessButton.setEnabled(true);

		for (int r = 0; r < 6; r++) {
			for (int c = 0; c < 5; c++) {
				grid[r][c].setText("");
				grid[r][c].setEditable(r == 0);
				grid[r][c].setBackground(Color.WHITE);
			}
		}

	}

	private void makeGuess() {
		int row = game.getTries();
		StringBuilder sb = new StringBuilder();
		for (int c = 0; c < 5; c++) {
			sb.append(grid[row][c].getText().trim().toLowerCase());
		}
		String guess = sb.toString();

		if (guess.length() != 5) {
			JOptionPane.showMessageDialog(this, "Please enter a 5-letter word");
			return;
		}
		if (!guess.matches("[a-z]{5}")) {
			JOptionPane.showMessageDialog(this, "Use only letters (a-z)");
			return;
		}


		String[]feedback = game.feedback(guess);
		for (int i = 0; i < 5; i++) {
			JTextField tf = grid[row][i];
			tf.setEditable(false);

			if (feedback[i].equals("G")) {
				tf.setBackground(Color.GREEN);
			}
			else if (feedback[i].equals("Y")) {
				tf.setBackground(Color.YELLOW);
			}
			else {
				tf.setBackground(Color.LIGHT_GRAY);
			}
		if (game.isDone()) {
			endGame();
		}	
		else {
			nextGuessRow();	
		}	
		}
	}

	private void nextGuessRow() {
		statusLabel.setText("Guess " + (game.getTries() + 1));
		int next = game.getTries();
		for (int c = 0; c < 5; c++) {
			grid[next][c].setEditable(true);
		}
	}

	private void endGame() {
		if (game.isWin()) {
			statusLabel.setText("You won in " + game.getTries());
		}
		else {
			statusLabel.setText("Game Over, Try again Later! The word was: " + game.getSecret());
		}
		guessButton.setEnabled(false);
		startButton.setEnabled(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(Main::new);
	}



}