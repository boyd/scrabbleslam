package scrabbleslam;

import java.util.Random;
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
				dict.buildAdjacencies();
		}

		public String pickRandomStartWord() {
				String candidate = new String(); // FIXME
				int size = dict.size();
				while (true) {
						int i = new Random().nextInt(size);
						for (String word : dict.words) {
								if (0 == i) {
										candidate = word;
										break;
								}
								i--;
						}
						if (dict.findAdjacentWords(candidate).size() > 3) {
								break;
						}
				}
				return candidate;
		}
}
