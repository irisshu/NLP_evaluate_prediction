import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Prediction {

	@SuppressWarnings("null")
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// Get data from xml file.
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader("test.xml"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String data_line = "";
		String data_line2 = "";

		try {
			while ((data_line = in.readLine()) != null) {
				data_line2 = data_line2 + "" + data_line;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String parts[] = data_line2.split(" ");
		String gold_cate[] = new String[parts.length];
		;
		String[] cutString = null;
		int count = 0;

		for (int i = 0; i < parts.length; i++) {
			if (parts[i].equals("<Text")) {
				cutString = parts[i + 1].split("\"");// category=cutString[1]
				gold_cate[count] = cutString[1];
				System.out.println(gold_cate[count]);
				count++;
			}

			// System.out.println(parts[i]);
		}

		// process();

	}

	public static double getNc_u(int n) {

		return 0;
	}

	public static void process() {

	}

}
