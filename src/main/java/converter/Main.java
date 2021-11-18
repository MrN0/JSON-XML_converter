package converter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import converter.builder.JsonBuilder;
import converter.builder.XmlBuilder;
import converter.exception.UnsupportedFormatException;
import converter.parser.JsonParser;
import converter.parser.XmlParser;

public class Main {

	public static void main(String[] args) {
		String document;
		try {
			document = readFileAsString("test.txt");
		} catch (IOException e) {
			System.out.println("Error: Couldn't read input file!");
			return;
		}
		System.out.println(convertDocument(document));
	}

	private static String readFileAsString(String filePath) throws IOException {
		return new String(Files.readAllBytes(Paths.get(filePath)));
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
