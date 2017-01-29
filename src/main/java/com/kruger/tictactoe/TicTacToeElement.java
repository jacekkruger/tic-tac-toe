package com.kruger.tictactoe;

enum TicTacToeElement {
	CROSS {
		@Override
		TicTacToeElement nextMove() {
			return CIRCLE;
		}

		@Override
		String getText() {
			return "X";
		}
	},
	CIRCLE {
		@Override
		TicTacToeElement nextMove() {
			return CROSS;
		}

		@Override
		String getText() {
			return "O";
		}
	};

	abstract TicTacToeElement nextMove();

	abstract String getText();
}