import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Prediction {

	static String gold_cate[] = new String[10000];
	static String test_cate[] = new String[10000];
	
	public static void main(String[] args) {
		
		
		getData("gold_standard.xml");
		getData("test_outcome.xml");
		// process();

	}

	
	
	@SuppressWarnings("resource")
	public static String[] getData(String xml_name){
		// Get data from xml file.
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(xml_name));
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
		
		String[] cutString = null;
		int count = 0;

		if(xml_name.equals("gold_standard.xml")){
			for (int i = 0; i < parts.length; i++) {
				if (parts[i].equals("<Text")) {
					cutString = parts[i + 1].split("\"");// category=cutString[1]
					gold_cate[count] = cutString[1];
					System.out.println("gold : "+gold_cate[count]);
					count++;
				}
			}
			System.out.println("Finished gold_standard.xml reading... ");
		}
		else if(xml_name.equals("test_outcome.xml")){
			for (int i = 0; i < parts.length; i++) {
				if (parts[i].equals("<Text")) {
					cutString = parts[i + 1].split("\"");// category=cutString[1]
					test_cate[count] = cutString[1];
					System.out.println("test : "+test_cate[count]);
					count++;
				}
			}
			System.out.println("Finished test_outcome.xml reading... ");
		}

		
		return null;		
	}

	public static void process() {

	}

}
