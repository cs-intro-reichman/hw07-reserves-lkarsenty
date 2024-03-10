
public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	public static String tail(String str) {
		String tail = str.substring(1,str.length());
		return tail; 
	}

	public static int levenshtein(String word1, String word2) {
		
		word1 = word1.toLowerCase();
		word2 = word2.toLowerCase();
		
		if (word1.equals("")){
			return word2.length();
		}

		if (word2.equals("")){
			return word1.length();
		}

		if (word1.length() == 1 && word2.length() == 1){
			if (word1.equals(word2)) {
				return 0;
			} else {
				return 1;
			}
		}

		if (word1.charAt(0) == word2.charAt(0)) {
			return levenshtein(tail(word1), tail(word2));
		} else {
			int min = Math.min(Math.min(levenshtein(tail(word1), word2),levenshtein(word1, tail(word2))), levenshtein(tail(word1), tail(word2)));
			return 1 + min;
		}

	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];

		In in = new In(fileName);

		for (int i = 0; i < 3000; i ++) {
			dictionary[i] = in.readString();
		}

		return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {		
		
		for (int i = 0; i <= threshold; i++) {
			for (int t = 0; t < 3000; t++) {
				if (levenshtein(dictionary[t], word) == i) {
					return dictionary[t];
				}
			
			}
		}
		return word;
	}

}
