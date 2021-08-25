package converter;

import java.util.Scanner;

import converter.builder.JsonBuilder;
import converter.builder.XmlBuilder;
import converter.exception.UnsupportedFormatException;
import converter.parser.JsonParser;
import converter.parser.XmlParser;

public class Main {

	public static void main(String[] args) {
		String document = getDocumentFromInput();
		System.out.println(convertDocument(document));
	}

	private static String getDocumentFromInput() {
		try (var scanner = new Scanner(System.in)) {
			return scanner.nextLine();
		}
	}

	private static String convertDocument(String document) {
		Converter converter;

		char key = document.charAt(0);
		switch (key) {
		case '<':
			converter = new Converter(new XmlParser(), new JsonBuilder());
			break;
		case '{':
			converter = new Converter(new JsonParser(), new XmlBuilder());
			break;
		default:
			throw new UnsupportedFormatException("ERROR: Unknown document format!");
		}

		return converter.convert(document);
	}
}
