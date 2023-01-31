import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PigLatinTranslatorImpl implements Translator {

	// CASE 3: vowel => add way to word.

	// CASE 1: consonant & vowel => first letter at end & ay at end.

	// CASE 2: consonant & consonant => move both to end & ay at end.

	public boolean isVowel(String letter) {
		String REGEX = "[aeiou]";
		Pattern pattern = Pattern.compile(REGEX, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(letter);
		boolean isVowel = matcher.matches();
		return isVowel;
	}

	@Override
	public List<String> translateWord(String word) {
		String[] characterArray = word.split("");
		int arrayLength = characterArray.length;

		List<String> translatedWord = new ArrayList<>(Arrays.asList(characterArray));

		String firstLetter = characterArray[0];
		if (arrayLength > 1) {
			String secondLetter = characterArray[1];
			String lastCharacter = characterArray[(arrayLength - 1)];

			if (hasPunctuation(lastCharacter)) {
				if (isVowel(firstLetter)) {
					translatedWord.remove(lastCharacter);
					translateVowelWord(word);
					translatedWord.add(lastCharacter);
				} else if (!isVowel(firstLetter) && (isVowel(secondLetter))) {
					translatedWord.remove(lastCharacter);
					translateConsonantAndVowel(word);
					translatedWord.add(lastCharacter);

				} else if (!isVowel(firstLetter) && (!isVowel(secondLetter))) {
					translatedWord.remove(lastCharacter);
					translateConsonantAndConsonant(word);
					translatedWord.add(lastCharacter);
				}
			} else {
				if (isVowel(firstLetter)) {
					translateVowelWord(word);
				} else if (!isVowel(firstLetter) && (isVowel(secondLetter))) {
					translateConsonantAndVowel(word);

				} else if (!isVowel(firstLetter) && (!isVowel(secondLetter))) {
					translateConsonantAndConsonant(word);

				}
			}

		}
		else translateVowelWord(word);

		printTranslated(translatedWord);
		return translatedWord;

	}

	public String translateVowelWord(String word) {
		String translatedWord = (word + "way");
		return translatedWord;
	}

	public String translateConsonantAndVowel(String word) {
		String translatedWord = (word);
		char firstCharacter = translatedWord.charAt(0);
		translatedWord = (translatedWord.substring(firstCharacter) + "ay" + firstCharacter);
		return translatedWord;
	}

	public String translateConsonantAndConsonant(String word) {
		String translatedWord = (word);
		String consonants = translatedWord.substring(0, 1);
		translatedWord = (translatedWord.substring(0, 1) + "ay" + consonants);
		return translatedWord;
	}

	@Override
	public List<String> translateText(String[] sentence) {
		List<String> translatedSentence = new ArrayList<>();
		int arrayLength = sentence.length;

		for (int i = 0; i < arrayLength; i++) {
			String translatedWord = String.join("", translateWord(sentence[i]));
			translatedSentence.add(translatedWord);
			System.out.print(" ");
		}

		return translatedSentence;

	}

	public boolean hasPunctuation(String lastChar) {
		String REGEX = "[.!?...\\,]";
		Pattern pattern = Pattern.compile(REGEX, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(lastChar);
		boolean hasPunctuation = matcher.matches();
		return hasPunctuation;
	}

	public void printTranslated(List<String> translated) {
		for (String letter : translated) {
			System.out.print(letter);
		}
	}
}
