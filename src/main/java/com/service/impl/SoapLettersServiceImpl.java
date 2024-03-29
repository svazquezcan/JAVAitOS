package com.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dto.SoapLetterDTO;
import com.entity.Game;
import com.entity.Word;
import com.enums.Orientation;
import com.repository.GameRepository;
import com.repository.WordRepository;
import com.service.SoapLettersService;

@Service
public class SoapLettersServiceImpl implements SoapLettersService {

	@Autowired
	private WordRepository wordRepository;
	@Autowired
	private GameRepository gameRepository;

	private static final int N_WORDS = 5;
	
	private static final Random RANDOM = new Random();

	private static final String LETTERS = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
	
	/**
	 * Comrpueba la palabra y el juego, si ha terminado y si la palabra es válida.
	 */
	@Override
	public SoapLetterDTO checkSoapLetter(SoapLetterDTO soapLetterDTO) {
		
		List<String> words=wordRepository.findAll().stream().map(w->w.getWord()).collect(Collectors.toList());
		Game game=gameRepository.findById(soapLetterDTO.getIdGame()).get();
		int unCompleted=game.getUnCompletedWords();
		
		if(words.contains(soapLetterDTO.getWord())){
			unCompleted--;
			game.setUnCompletedWords(unCompleted);
			soapLetterDTO.setWordValidated(true);
		}
		
		if(game.getUnCompletedWords()==0) {
			soapLetterDTO.setGameFinished(true);
		}
		
		gameRepository.save(game);

		return soapLetterDTO;
	}

	/**
	 * Genera la sopa de letras de forma aleatoria
	 */
	@Override
	public SoapLetterDTO generateSoapLetter(String username) {
		//Se obtienen las palabras de la base de datos y d estás se escogen N_WORDS
		List<Word> words=wordRepository.findAll();
		Collections.shuffle(words);
		words=words.subList(0, N_WORDS);
		List<String> wordList=words.stream().map(w->w.getWord()).collect(Collectors.toList());
		String[][] soapLetterTable = new String[10][10];
		List<Position> positions= new ArrayList<>();
		Position position;
		Game game=new Game();
		SoapLetterDTO soapLetterDTO= new SoapLetterDTO();
		
		//Se registra el juego con este usuario y se alamcena en la base de datos
		game.setUsername(username);
		game.setUnCompletedWords(N_WORDS);
		game=gameRepository.save(game);
		
		//Se generan las palabras en posiciones aleatorias
		for (Word word : words) {
			position=generateRandomPosition(word,soapLetterTable,positions);
			positions.add(position);
			fillWordInTable(word.getWord(), soapLetterTable, position);
		}
		fillTableWithRandomLetters(soapLetterTable);
		
		soapLetterDTO.setTable(soapLetterTable);
		soapLetterDTO.setIdGame(game.getIdGame());
		soapLetterDTO.setWords(wordList);
		
		return soapLetterDTO;

	}
	
	/**
	 * Se genera una posición aleatoria en el tablero, que sea válida
	 * @param word
	 * @param soapLetterTable
	 * @param positions
	 * @return
	 */
	private Position generateRandomPosition(Word word,String[][] soapLetterTable, List<Position> positions) {
		int column = 0, row = 0;
		Orientation orientation = null;
		Position position;

		do {
			column = RANDOM.nextInt(10);
			row = RANDOM.nextInt(10);
			orientation =Orientation.orientations.get(RANDOM.nextInt(4));
			position=new Position(row, column, word.getWord().length(),orientation);
			 
		} while (positions.contains(position) || !checkPositionIfAvailable(word.getWord(),column,row,orientation,soapLetterTable));
		return position;
	}
	/**
	 * Comprueba que la posición sea válida
	 * @param word
	 * @param column
	 * @param row
	 * @param orientation
	 * @param table
	 * @return
	 */
	private boolean checkPositionIfAvailable(String word,int column, int row, Orientation orientation, String[][] table) {
		
		if(table[row][column]==null && orientation==Orientation.RIGHT && (table[0].length-(column+1))>=word.length()) {
			for(int i=0;i<word.length();i++) {
				if(table[row][column+i]!=null) return false;
			}
			return true;
		}else if(table[row][column]==null && orientation==Orientation.LEFT && (column+1)>=word.length()) {
			for(int i=0;i<word.length();i++) {
				if(table[row][column-i]!=null) return false;
			}
			return true;
		}else if(table[row][column]==null  && orientation==Orientation.BOTTOM && (table.length-(row+1))>=word.length()) {
			for(int i=0;i<word.length();i++) {
				if(table[row+i][column]!=null) return false;
			}
			return true;
		}else if(table[row][column]==null  && orientation==Orientation.TOP && (row+1)>=word.length()) {
			for(int i=0;i<word.length();i++) {
				if(table[row-i][column]!=null) return false;
			}
			return true;
		}
		return false;
		
	}
	
	/**
	 * Rellena el tablero con letras aleatorias
	 * @param soapLetter
	 */
	private void fillTableWithRandomLetters(String[][] soapLetter) {
		for(int i=0;i<soapLetter.length;i++) {
			for(int j=0;j<soapLetter[0].length;j++) {
				if(soapLetter[i][j]==null || soapLetter[i][j].isEmpty()) {
					soapLetter[i][j]=LETTERS.charAt(RANDOM.nextInt(LETTERS.length()))+"";
				}
			}
		}
	}
	
	/**
	 * Rellena el tablero con la palabra.
	 * @param word
	 * @param soapLetter
	 * @param position
	 */
	private void fillWordInTable(String word, String[][] soapLetter,Position position) {

		for (int i = 0; i < word.length(); i++) {
			if(position.getOrientation()==Orientation.RIGHT) {
				soapLetter[position.getRow()][position.getColumn() + i] = word.charAt(i) + "";
			} else if (position.getOrientation()==Orientation.LEFT) {
				soapLetter[position.getRow()][position.getColumn() - i] = word.charAt(i) + "";
			} else if ( position.getOrientation()==Orientation.BOTTOM) {
				soapLetter[position.getRow() + i][position.getColumn()] = word.charAt(i) + "";
			} else {
				soapLetter[position.getRow() - i][position.getColumn()] = word.charAt(i) + "";
			}
		}   
		
	}

	/**
	 * Clase auxiliar que representa una posición en el tablero.
	 * @author aimry
	 *
	 */
  class	Position {
		
		private int row;
		private int column;
		private int nLetters;
		private Orientation orientation;

		public Orientation getOrientation() {
			return orientation;
		}
		public void setOrientation(Orientation orientation) {
			this.orientation = orientation;
		}
		public Position() {
			super();
		}
		public Position(int row, int column, int nLetters) {
			super();
			this.row = row;
			this.column = column;
		}
		public Position(int row, int column, int nLetters, Orientation orientation) {
			super();
			this.row = row;
			this.column = column;
			this.nLetters=nLetters;
			this.orientation=orientation;
		}
		
		public int getRow() {
			return row;
		}
		public void setRow(int row) {
			this.row = row;
		}
		public int getColumn() {
			return column;
		}
		public void setColumn(int column) {
			this.column = column;
		}

		public int getnLetters() {
			return nLetters;
		}

		public void setnLetters(int nLetters) {
			this.nLetters = nLetters;
		}

		

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getEnclosingInstance().hashCode();
			result = prime * result + column;
			result = prime * result + nLetters;
			result = prime * result + ((orientation == null) ? 0 : orientation.hashCode());
			result = prime * result + row;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Position other = (Position) obj;
			if (!getEnclosingInstance().equals(other.getEnclosingInstance()))
				return false;
			if (column != other.column)
				return false;
			if (nLetters != other.nLetters)
				return false;
			if (orientation != other.orientation)
				return false;
			if (row != other.row)
				return false;
			return true;
		}

		private SoapLettersServiceImpl getEnclosingInstance() {
			return SoapLettersServiceImpl.this;
		}
		
	}
}
