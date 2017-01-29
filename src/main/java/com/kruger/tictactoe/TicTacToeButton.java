package com.kruger.tictactoe;

import java.util.Optional;

import javax.swing.JButton;

public class TicTacToeButton extends JButton {

	private static final long serialVersionUID = 8038374419240038579L;

	protected TicTacToeWindow window;
	protected TicTacToe ttt;
	protected int x;
	protected int y;

	public TicTacToeButton(TicTacToeWindow window, TicTacToe ttt, int x, int y) {
		this.window = window;
		this.ttt = ttt;
		this.x = x;
		this.y = y;
		this.addActionListener(e -> {
			setText(ttt.set(x, y));
			ttt.isWin().ifPresent(window::win);
		});
	}

	public void refresh() {
		this.setText(ttt.get(x, y));
	}

	private void setText(Optional<TicTacToeElement> element) {
		this.setText(element.map(TicTacToeElement::getText).orElse(""));
	}
}
