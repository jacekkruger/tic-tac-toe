package com.kruger.tictactoe;

import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class TicTacToe {

	protected TicTacToeElement lastMove = TicTacToeElement.CROSS;

	protected TicTacToeElement data[][] = new TicTacToeElement[3][3];

	public void reset() {
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				data[x][y] = null;
			}
		}
	}

	public Optional<TicTacToeElement> set(int x, int y) {
		if (x < 0 || x > 2 || y < 0 || y > 2) {
			throw new IndexOutOfBoundsException();
		}

		if (data[x][y] == null) {
			lastMove = lastMove.nextMove();
			data[x][y] = lastMove;
			return Optional.of(lastMove);
		}

		return Optional.empty();
	}

	public Optional<TicTacToeElement> get(int x, int y) {
		return Optional.ofNullable(data[x][y]);
	}

	protected Optional<TicTacToeElement> columnWins(int x) {
		TicTacToeElement first = data[x][0];
		for (int y = 1; y < 3; y++) {
			if (data[x][y] != first) {
				return Optional.empty();
			}
		}
		return Optional.ofNullable(first);
	}

	protected Optional<TicTacToeElement> rowWins(int y) {
		TicTacToeElement first = data[0][y];
		for (int x = 1; x < 3; x++) {
			if (data[x][y] != first) {
				return Optional.empty();
			}
		}
		return Optional.ofNullable(first);
	}

	protected Optional<TicTacToeElement> diagonalWins() {
		TicTacToeElement first = data[0][0];
		for (int i = 1; i < 3; i++) {
			if (data[i][i] != first) {
				return Optional.empty();
			}
		}
		return Optional.ofNullable(first);
	}

	protected Optional<TicTacToeElement> reverseDiagonalWins() {
		TicTacToeElement first = data[0][2];
		for (int i = 1; i < 3; i++) {
			if (data[i][2 - i] != first) {
				return Optional.empty();
			}
		}
		return Optional.ofNullable(first);
	}

	private Optional<TicTacToeElement> anyRowWins() {
		for (int y = 0; y < 3; y++) {
			Optional<TicTacToeElement> winner = rowWins(y);
			if (winner.isPresent()) {
				return winner;
			}
		}
		return Optional.empty();
	}

	private Optional<TicTacToeElement> anyColumnWins() {
		for (int x = 0; x < 3; x++) {
			Optional<TicTacToeElement> winner = columnWins(x);
			if (winner.isPresent()) {
				return winner;
			}
		}
		return Optional.empty();
	}

	public Optional<TicTacToeElement> isWin() {
		Stream<Supplier<Optional<TicTacToeElement>>> checks = Stream.<Supplier<Optional<TicTacToeElement>>>of(
				this::anyColumnWins, this::anyRowWins, this::diagonalWins, this::reverseDiagonalWins);
		return checks.map(Supplier::get).filter(Optional::isPresent).map(Optional::get).findFirst();
	}

}
