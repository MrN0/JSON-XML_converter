package converter;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

	public static void main(String[] args) {
		var scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		scanner.close();

		if (input.startsWith("<")) {
			Element<?> result = extractElementFromXML(input);
			System.out.println(result.getAsJson());
		} else if (input.startsWith("{")) {
			Element<?> result = extractElementFromJSON(input);
			System.out.println(result.getAsXml());
		} else {
			throw new IllegalArgumentException("Unknown format!");
		}
	}

	private static Element<?> extractElementFromXML(String input) {
		var keyPattern = Pattern.compile("<([^/].*?)>");
		var valuePattern = Pattern.compile(">([^/].*?)<");

		String key = null;
		String value = null;

		Matcher matcher = keyPattern.matcher(input);
		while (matcher.find()) {
			key = matcher.group(1).replaceAll("/", "");
		}

		matcher = valuePattern.matcher(input);
		while (matcher.find()) {
			value = matcher.group(1);
		}

		return new Element<>(key, value);
	}

	private static Element<?> extractElementFromJSON(String input) {
		String s = input
				.replace("{", "")
				.replace("}", "")
				.replace("\"", "")
				.replace(" ", "");
		
		String[] arr = s.split(":");
		return new Element<>(arr[0], arr[1]);
	}

}
