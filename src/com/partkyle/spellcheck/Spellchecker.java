package com.partkyle.spellcheck;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Spellchecker {
	public static void main(String[] args) {
		try {
			BufferedReader in = new BufferedReader(new FileReader(new File("/usr/share/dict/words")));
			String word;
			List<String> dict = new ArrayList<String>();
			while ((word = in.readLine()) != null) {
				if (word.length() > 0)
					dict.add(word.trim().toLowerCase());
			}
			System.out.printf("%d words loaded from the dictionary.\n", dict.size());
			in.close();

			String prompt = "> ";
			System.out.print(prompt);
			in = new BufferedReader(new InputStreamReader(System.in));
			word = "";
			Set<String> matches = new TreeSet<String>();
			while ((word = in.readLine().trim()) != null && word.length() > 0) {
				int currentDifference = Integer.MAX_VALUE;
				for (String dictWord : dict) {
					int difference = getLevenshteinDifference(word.toLowerCase(), dictWord);

					// if there is an absolute match, break early.
					if (difference == 0) {
						System.out.printf(" - %s is not misspelled.\n", dictWord);
						matches.clear();
						break;
					} else if (difference < currentDifference) {
						matches.clear();
						matches.add(dictWord);
						currentDifference = difference;
					} else if (difference == currentDifference) {
						matches.add(dictWord);
					}
				}

				// There will always be at least one match, unless the
				// L.Difference was 0
				if (matches.size() > 0) {
					System.out.printf(" -  %d close matches.\n", matches.size());

					for (String s : matches) {
						System.out.printf("  %s", s);
					}
					System.out.println();
				}
				System.out.print(prompt);
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public static int getLevenshteinDifference(String s1, String s2) {
		int[][] differences = new int[s2.length() + 1][s1.length() + 1];

		for (int i = 0; i <= s1.length(); i++) {
			differences[0][i] = i;
		}

		for (int j = 0; j <= s2.length(); j++) {
			differences[j][0] = j;
		}

		for (int j = 1; j <= s2.length(); j++) {
			for (int i = 1; i <= s1.length(); i++) {
				if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
					differences[j][i] = differences[j - 1][i - 1];
				} else {
					differences[j][i] = min(differences[j][i - 1] + 1, differences[j - 1][i] + 1,
							differences[j - 1][i - 1] + 1);
				}
			}
		}

		return differences[s2.length()][s1.length()];
	}

	public static int min(int... ints) {
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < ints.length; i++) {
			if (ints[i] < min)
				min = ints[i];
		}

		return min;
	}

}
