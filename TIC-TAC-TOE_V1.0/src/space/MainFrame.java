package space;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;



public class MainFrame extends JFrame{
	protected int index = 1;
	Panel header = new Panel();
	Panel footer = new Panel();
	Panel content = new Panel();
	Label title = new Label();
	Label stat = new Label();
	Label copy = new Label();
	CustomButton oneOne = new CustomButton();
	CustomButton oneTwo = new CustomButton();
	CustomButton oneThree = new CustomButton();
	CustomButton twoOne = new CustomButton();
	CustomButton twoTwo = new CustomButton();
	CustomButton twoThree = new CustomButton();
	CustomButton threeOne = new CustomButton();
	CustomButton threeTwo = new CustomButton();
	CustomButton threeThree = new CustomButton();
	
	class CustomButton extends JButton implements ActionListener{
		
		public CustomButton() {
			this.addActionListener(this);
			this.setBackground(Color.cyan);
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int gameStat = 0;
			JButton btn = new JButton("btn1");
			if ( index <= 9 ) {
				if ( this.getText().equals("")) {
					if ( index % 2 == 0 ) {
						this.setText("X");
						stat.setText("Game Status : Player One turn" );
					}
					else {
						this.setText("O");
						stat.setText("Game Status : Player Two turn");
					}
					index++;
					gameStat = checkWinner();
					switch(gameStat) {
					case 1:
						//stat.setText("Player One Won!!!");
						showWinner(1);
						break;
					case 2:
						//stat.setText("Player Two Won!!!");
						showWinner(2);
					break;
					default:
						if ( index == 9 ) {
							JOptionPane.showMessageDialog(this, "Draw", "Game End", 2);
							lockBoard();
						}
						break;
					}
					if ( gameStat != 0 )
						lockBoard();
				}
				else
					stat.setText("Game Status : this slot is already occupied");
			}
			else {
				stat.setText("Ended");
			}
		}
		public void lockBoard() {
			index = 1;
			oneOne.setText("");
			oneTwo.setText("");
			oneThree.setText("");
			twoOne.setText("");
			twoTwo.setText("");
			twoThree.setText("");
			threeOne.setText("");
			threeTwo.setText("");
			threeThree.setText("");
			stat.setText("Game Status : O go first");
		}
		public int checkWinner() {
			int winner = 0;
			String result = "";
			if ( oneOne.getText() == oneTwo.getText() && oneOne.getText() == oneThree.getText()) {
				result = oneOne.getText();
			}
			else if ( oneOne.getText() == twoOne.getText() && oneOne.getText() == threeOne.getText()) {
				result = oneOne.getText();
			}
			else if ( oneOne.getText() == twoTwo.getText() && oneOne.getText() == threeThree.getText()) {
				result = oneOne.getText();
			}
			else if ( oneTwo.getText() == twoTwo.getText() && oneTwo.getText() == threeTwo.getText()) {
				result = oneTwo.getText();
			}
			else if ( oneThree.getText() == twoThree.getText() && oneThree.getText() == threeThree.getText()) {
				result = oneThree.getText();
			}
			else if ( twoOne.getText() == twoTwo.getText() && twoOne.getText() == twoThree.getText()) {
				result = twoOne.getText();
			}
			else if ( threeOne.getText().equals(threeTwo.getText()) && threeOne.getText().equals(threeThree.getText())) {
				result = threeOne.getText();
			}
			else if ( threeOne.getText().equals(twoTwo.getText()) && threeOne.getText().equals(oneThree.getText())) {
				result = threeOne.getText();
			}
			if ( result == "X") {
				winner = 2;
			}
			else if ( result == "O"){
				winner = 1;
			}
			return winner;
		}
		public void showWinner(int winner) {
			JOptionPane.showMessageDialog(this, "Congratulatioon player " + winner + " won the game in " + (index-1), "Game End", 2);
		}
	}
	
	public MainFrame() {
		super();
		this.header.setLayout(new GridLayout(2,1));
		this.title.setText("This is Tic-Tac-Toe! Welcome on board!");
		this.stat.setText("Game Status :");
		this.header.add(title);
		this.header.add(stat);
		this.header.setBounds(50, 50, 300, 30);
		this.add(header,BorderLayout.NORTH);
		this.content.setLayout(new GridLayout(3,3));
		this.content.add(this.oneOne);
		this.content.add(this.oneTwo);
		this.content.add(this.oneThree);
		this.content.add(this.twoOne);
		this.content.add(this.twoTwo);
		this.content.add(this.twoThree);
		this.content.add(this.threeOne);
		this.content.add(this.threeTwo);
		this.content.add(this.threeThree);
		
		this.add(content,BorderLayout.CENTER);
		this.setTitle("Tic-Tac-Toe");
		this.copy.setText("CopyLeft Of @Jonah");
		this.add(copy,BorderLayout.PAGE_END);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setSize(500,500);
		this.setVisible(true);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MainFrame mf1 = new MainFrame();
	}


}
