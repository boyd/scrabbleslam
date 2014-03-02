package scrabbleslam;

import java.io.*;
import java.util.*;
import scrabbleslam.Dictionary;

public class ScrabbleSlam {
		private Dictionary dict;

		public ScrabbleSlam() {
				dict = new Dictionary(new Dictionary.NewWordFilter() {
						public boolean can_add(String s) {
								return s.length() == 4;
						}
				});
				dict.load("../dictionaries/TWL06.txt");
		}

		public void print_dictionary() {
				for (String word : dict.words) {
						System.out.print( word );
						System.out.println( dict.find_adjacent_words( word ) );
				}
		}
}
