import java.io.BufferedReader;
import java.io.File; 
import java.io.FileNotFoundException; 
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class IterationOne {

	public static void main(String[] args) throws IOException {
		new FileOutputStream("instruction_outputs.txt").close();
		Path path = Paths.get("instruction_outputs.txt");
		appendToFile(path, "v2.0 raw" + "\n");
		try {
			FileReader fr = new FileReader("instruction_inputs.txt");
			BufferedReader br = new BufferedReader(fr);
			ArrayList<String> fileList = new ArrayList<String>();
			String str;
			while ((str = br.readLine()) != null) {
				fileList.add(str);
			}
			br.close();

			String[] newArray = new String[fileList.size()];
			newArray = fileList.toArray(newArray);
			toBinary(newArray);
		} catch (FileNotFoundException e) {
			System.out.println("There is no file.");
			e.printStackTrace();
		}
	}

	public static void toBinary(String[] numbers) throws IOException {
		String b;
		String opTyp;
		String[] last = new String[numbers.length];

		// System.out.println(numbers[2]);
		for (int j = 0; j < numbers.length; j++) {
			// System.out.println(numbers[j]);
			opTyp = numbers[j].replaceAll("[,]", "");
			String[] opType = opTyp.split("\\s");
			// System.out.println(opType[0]);
			b = numbers[j].replaceAll("[^-0-9]", " ").trim();
			String[] splitStr = b.split("\\s+");
			// System.out.println(splitStr[]);

			for (int k = 0; k < splitStr.length; k++) {
				int number = Integer.parseInt(splitStr[k]);
				if (number < 0) {
					last[k] = Integer.toBinaryString(number).substring(20);
				} else {
					last[k] = Integer.toBinaryString(number);
				}
				// System.out.println(last[k]);
			}
			instructions(last, opType[0]);
		}
	}

	public static void instructions(String[] binaryValue, String opType) throws IOException {
		int count = 0;
		String newString;
		String err = "????";

		// System.out.println(binaryValue);
		if (opType.toUpperCase().equals("AND")) {
			count++;
			 System.out.println(opType);
			for (int k = 0; k < count; k++) {
				newString = "0000" + String.format("%3s", binaryValue[k]).replace(' ', '0')
						+ String.format("%3s", binaryValue[k + 1]).replace(' ', '0') + "000"  
						+ String.format("%3s", binaryValue[k + 2]).replace(' ', '0');
			    System.out.println(newString);
				toHexadecimal(newString);

			}

		}

		else if (opType.toUpperCase().equals("ADD")) {
			count++;
			 System.out.println(opType);
			for (int k = 0; k < count; k++) {
				newString = "0001" + String.format("%3s", binaryValue[k]).replace(' ', '0')
						+ String.format("%3s", binaryValue[k + 1]).replace(' ', '0') + "000"
						+ String.format("%3s", binaryValue[k + 2]).replace(' ', '0');
				 System.out.println(newString);
				toHexadecimal(newString);
			}

		} else if (opType.toUpperCase().equals("LD")) {
			count++;
			 System.out.println(opType);
			for (int k = 0; k < count; k++) {
				newString = "0100" + String.format("%3s", binaryValue[k]).replace(' ', '0')
						+ String.format("%9s", binaryValue[k + 1]).replace(' ', '0');
				 System.out.println(newString);
				toHexadecimal(newString);
			}

		}

		else if (opType.toUpperCase().equals("ADDI")) {
			count++;
			 System.out.println(opType);
			for (int k = 0; k < count; k++) {
				newString = "0011" + String.format("%3s", binaryValue[k]).replace(' ', '0')
						+ String.format("%3s", binaryValue[k + 1]).replace(' ', '0') 
						+ String.format("%6s", binaryValue[k + 2]).replace(' ', '0');
				 System.out.println(newString);
				toHexadecimal(newString);
			}

		}

		else if (opType.toUpperCase().equals("ANDI")) {
			count++;
			 System.out.println(opType);
			for (int k = 0; k < count; k++) {
				newString = "0010" + String.format("%3s", binaryValue[k]).replace(' ', '0')
						+ String.format("%3s", binaryValue[k + 1]).replace(' ', '0') 
						+ String.format("%6s", binaryValue[k + 2]).replace(' ', '0');
				 System.out.println(newString);
				toHexadecimal(newString);
			}

		}

		else if (opType.toUpperCase().equals("ST")) {
			count++;
			 System.out.println(opType);
			for (int k = 0; k < count; k++) {
				newString = "0101" + String.format("%3s", binaryValue[k]).replace(' ', '0')
						+ String.format("%9s", binaryValue[k + 1]).replace(' ', '0');
				System.out.println(newString);
				toHexadecimal(newString);
			}

		}

		else if (opType.toUpperCase().equals("CMP")) {
			count++;
			 System.out.println(opType);
			for (int k = 0; k < count; k++) {
				newString = "0110" + "000" + String.format("%3s", binaryValue[k]).replace(' ', '0')
						+ "000"  +String.format("%3s", binaryValue[k + 1]).replace(' ', '0');
				 System.out.println(newString);
				toHexadecimal(newString);
			}

		}

		else if (opType.toUpperCase().equals("JMP")) {
			count++;
			System.out.println(opType);
			for (int k = 0; k < count; k++) {
				newString = "0111" + "00" + String.format("%10s", binaryValue[k]).replace(' ', '0');
			   System.out.println(newString);
				toHexadecimal(newString);

			}

		}

		else if (opType.toUpperCase().equals("JE")) {
			count++;
			 System.out.println(opType);
			for (int k = 0; k < count; k++) {
				newString = "1000" + "00" + String.format("%10s", binaryValue[k]).replace(' ', '0');
				System.out.println(newString);
				toHexadecimal(newString);
			}

		}

		else if (opType.toUpperCase().equals("JA")) {
			count++;
			 System.out.println(opType);
			for (int k = 0; k < count; k++) {
				newString = "1001" + "00" + String.format("%10s", binaryValue[k]).replace(' ', '0');
				System.out.println(newString);
				toHexadecimal(newString);
			}

		}

		else if (opType.toUpperCase().equals("JB")) {
			count++;
			 System.out.println(opType);
			for (int k = 0; k < count; k++) {
				newString = "1010" + "00" + String.format("%10s", binaryValue[k]).replace(' ', '0');
				System.out.println(newString);
				toHexadecimal(newString);
			}

		}

		else if (opType.toUpperCase().equals("JBE")) {
			count++;
			 System.out.println(opType);
			for (int k = 0; k < count; k++) {
				newString = "1011" + "00" + String.format("%10s", binaryValue[k]).replace(' ', '0');
				System.out.println(newString);
				toHexadecimal(newString);
			}

		}

		else if (opType.toUpperCase().equals("JBA")) {
			count++;
			 System.out.println(opType);
			for (int k = 0; k < count; k++) {
				newString = "1100" + "00" + String.format("%10s", binaryValue[k]).replace(' ', '0');
				System.out.println(newString);
				toHexadecimal(newString);
			}

		}

		else

			toHexadecimal("????");

	}

	public static void toHexadecimal(String binary) throws IOException {

		Path path = Paths.get("instruction_outputs.txt");

		if (binary.equals("????")) {
			appendToFile(path, "????" + "\n");
			System.out.println("????");
		} else {
			if(binary.startsWith("0000")) {
				int decimal = Integer.parseInt(binary, 2);
				String hexadecimalStr = Integer.toString(decimal, 16);
				hexadecimalStr = "0" + hexadecimalStr;
				appendToFile(path, hexadecimalStr.toUpperCase() + "\n");
			}
			else {
			int decimal = Integer.parseInt(binary, 2);
			String hexadecimalStr = Integer.toString(decimal, 16);
			System.out.println(hexadecimalStr.toUpperCase());
			appendToFile(path, hexadecimalStr.toUpperCase() + "\n");
			}
		}

	}

	private static void appendToFile(Path path, String content) throws IOException {

		Files.write(path, content.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE,
				StandardOpenOption.APPEND);

	}
}
