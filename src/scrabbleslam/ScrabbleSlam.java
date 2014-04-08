package scrabbleslam;

import java.lang.StringBuilder;
import java.util.Random;
import java.util.HashSet;
import scrabbleslam.Dictionary;

public class ScrabbleSlam {
		private Dictionary dict;

		public ScrabbleSlam(String dict_location) {
				dict = new Dictionary(new Dictionary.NewWordFilter() {
						public boolean can_add(String s) {
								return s.length() == 4;
						}
				});
				dict.load(dict_location);
		}
		public HashSet<String> adjacentWords(String word) {
				return dict.findAdjacentWords(word);
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

		public String dotGraph(String search_word) {
			StringBuilder output = new StringBuilder();
			output.append("graph Words {\n");
			output.append("rankdir=LR;\n");

			for (String adj_word: dict.findAdjacentWords(search_word)) {
				output.append("\"" + search_word +  "\" -- \"" + adj_word + "\";\n");
				for (String sub_adj_word: dict.findAdjacentWords(adj_word)) {
					output.append("\"" + adj_word + "\" -- \"" + sub_adj_word+ "\";\n");
				}
			}

			output.append("}");
			return output.toString();
		}
}
