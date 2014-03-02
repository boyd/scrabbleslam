package scrabbleslam;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

public class Dictionary {
		public interface NewWordFilter {
				boolean can_add(String s);
		}

		public Set<String> words;
		private NewWordFilter filter;

		public Dictionary(NewWordFilter new_word_filter) {
				words = new HashSet<String>();
				filter = new_word_filter;
		}

		public int size() {
				return words.size();
		}

		public List<String> find_adjacent_words(String search_word) {
				List<String> adjacent_words = new ArrayList<String>();
				for (String word: this.words) {
						if (word.length() != search_word.length())
								continue;

						int distance = 0;
						for (int i = 0; i< word.length(); i++) {
								if (word.charAt(i)  != search_word.charAt(i))
										distance++;
						}

						if (distance == 1) {
								adjacent_words.add(word);
						}
				}
				return adjacent_words;
		}

		public void add(String new_word) {
				if (filter.can_add(new_word)) {
						words.add(new_word);
				}
		}

		public void load(String filename) {
				File file = new File(filename);
				BufferedReader reader = null;

				try {
						reader = new BufferedReader(new FileReader(file));
						String text = null;

						while ((text = reader.readLine()) != null)
								add(text);

				} catch (FileNotFoundException e) {
						e.printStackTrace();
				} catch (IOException e) {
						e.printStackTrace();
				} finally {
						try {
								if (reader != null) {
										reader.close();
								}
						} catch (IOException e) {
						}
				}
		}
}
