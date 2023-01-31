import java.util.Scanner;

public class PigLatinApp {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter what you'd like to translate: ");
		String input = scanner.nextLine();
		String[] inputArray = input.split(" ");

		PigLatinTranslatorImpl translator = new PigLatinTranslatorImpl();

		
		if (inputArray.length==1) {
			translator.translateWord(input);

		}
		else {
			translator.translateText(inputArray);
		}
		
		
		scanner.close();
	}
}
