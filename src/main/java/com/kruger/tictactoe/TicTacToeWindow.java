package com.kruger.tictactoe;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.stream.Stream;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class TicTacToeWindow extends JFrame {

	private static final long serialVersionUID = 3736831624487295004L;

	protected TicTacToe ttt;

	public TicTacToeWindow(TicTacToe ttt) {
		super("Tic Tac Toe");
		this.ttt = ttt;

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new GridLayout(3, 3));
		this.setPreferredSize(new Dimension(640, 640));

		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				this.add(new TicTacToeButton(this, ttt, x, y));
			}
		}

		this.pack();
	}

	public Stream<TicTacToeButton> getAllTicTacToeButtons() {
		return Stream.of(this.getContentPane().getComponents()).filter(TicTacToeButton.class::isInstance)
				.map(TicTacToeButton.class::cast);
	}

	public void win(TicTacToeElement winner) {
		JOptionPane.showMessageDialog(this, "And the winner is… " + winner.getText());
		ttt.reset();
		refresh();
	}

	public void refresh() {
		getAllTicTacToeButtons().forEach((b) -> b.refresh());
	}

	public static void main(String[] args) {
		TicTacToe ttt = new TicTacToe();
		TicTacToeWindow window = new TicTacToeWindow(ttt);
		window.setVisible(true);
	}

}
