import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Prediction {

	static String gold_cate[] = new String[10000];
	static String test_cate[] = new String[10000];

	static double tp_book = 0;
	static double fp_book = 0;
	static double tn_book = 0;
	static double fn_book = 0;

	static double tp_dvd = 0;
	static double fp_dvd = 0;
	static double tn_dvd = 0;
	static double fn_dvd = 0;

	static double tp_health = 0;
	static double fp_health = 0;
	static double tn_health = 0;
	static double fn_health = 0;

	static double tp_music = 0;
	static double fp_music = 0;
	static double tn_music = 0;
	static double fn_music = 0;

	static double tp_toys_games = 0;
	static double fp_toys_games = 0;
	static double tn_toys_games = 0;
	static double fn_toys_games = 0;
	
	static Double precision[] = new Double[5]; //oder : book, dvd, health, music, toys_games
	static Double recall[] = new Double[5];
	static String category[] = {"book", "dvd", "health", "music", "toys_games"};

	public static void main(String[] args) {

		System.out.println("Start gold_standard.xml reading... ");
		getData("gold_standard.xml");
		System.out.println("Start test_outcome.xml reading... ");
		getData("test_outcome.xml");
		Process();
		cal_precision();
		cal_recall();
		
		System.out.println("book-> tp_book,fp_book,tn_book,fn_book : "
				+ tp_book + "," + fp_book + "," + tn_book + "," + fn_book);
		
		System.out.println("dvd-> tp_dvd,fp_dvd,tn_dvd,fn_dvd : "
				+ tp_dvd + "," + fp_dvd + "," + tn_dvd + "," + fn_dvd);
		
		System.out.println("health-> tp_health,fp_health,tn_health,fn_health : "
				+ tp_health + "," + fp_health + "," + tn_health + "," + fn_health);
		
		System.out.println("music-> tp_music,fp_music,tn_music,fn_music : "
				+ tp_music + "," + fp_music + "," + tn_music + "," + fn_music);
		
		System.out.println("toys_games-> tp_toys_games,fp_toys_games,tn_toys_games,fn_toys_games : "
				+ tp_toys_games + "," + fp_toys_games + "," + tn_toys_games + "," + fn_toys_games);
		print_result();
		
	}
	
	private static void print_result() {
		
		for(int i=0; i<5 ;i++){
			
			System.out.println(category[i]);
			System.out.println("\t p : "+ precision[i]);
			System.out.println("\t r : "+ recall[i]);
		}
	}
	
	

	private static void cal_precision() {
		
		precision[0]= tp_book/(tp_book+fp_book);
		precision[1]= (double) (tp_dvd/(tp_dvd+fp_dvd));
		precision[2]= (double) (tp_health/(tp_health + fp_health));
		precision[3]= (double) (tp_music/(tp_music+fp_music));
		precision[4]= (double) (tp_toys_games/(tp_toys_games+fp_toys_games));
		
	}
	
	private static void cal_recall() {
		
		recall[0]= (double) (tp_book/(tp_book+fn_book));
		recall[1]= (double) (tp_dvd/(tp_dvd+fn_dvd));
		recall[2]= (double) (tp_health/(tp_health+fn_health));
		recall[3]= (double) (tp_music/(tp_music+fn_music));
		recall[4]= (double) (tp_toys_games/(tp_toys_games+fn_toys_games));
	}
	
	
	
	
	private static void Process() {

		for (int i = 0; i < gold_cate.length; i++) {

			if (gold_cate[i] == null) {

			} else if (gold_cate[i].equals("book")) {
				if (test_cate[i].equals(gold_cate[i])) {
					tp_book++;

					tn_dvd++;
					tn_health++;
					tn_music++;
					tn_toys_games++;

				} else {
					fn_book++;
					if (test_cate[i].equals("dvd")) {
						fp_dvd++;
						
						tn_health++;
						tn_music++;
						tn_toys_games++;
					} else if (test_cate[i].equals("health")) {
						fp_health++;
						
						tn_dvd++;
						tn_music++;
						tn_toys_games++;
					} else if (test_cate[i].equals("music")) {
						fp_music++;
						
						tn_dvd++;
						tn_health++;
						tn_toys_games++;
					} else if (test_cate[i].equals("toys_games")) {
						fp_toys_games++;
						
						tn_dvd++;
						tn_health++;
						tn_music++;
						
					}
				}
			}

			else if (gold_cate[i].equals("dvd")) {
				if (test_cate[i].equals(gold_cate[i])) {
					tp_dvd++;

					tn_book++;
					tn_health++;
					tn_music++;
					tn_toys_games++;

				} else {
					fn_dvd++;
					if (test_cate[i].equals("book")) {
						fp_book++;						
						
						tn_health++;
						tn_music++;
						tn_toys_games++;
					} else if (test_cate[i].equals("health")) {
						fp_health++;
						
						tn_book++;
						tn_music++;
						tn_toys_games++;
					} else if (test_cate[i].equals("music")) {
						fp_music++;
						
						tn_book++;
						tn_health++;
						tn_toys_games++;
					} else if (test_cate[i].equals("toys_games")) {
						fp_toys_games++;
						
						tn_book++;
						tn_health++;
						tn_music++;
					}
				}
			}

			else if (gold_cate[i].equals("health")) {
				if (test_cate[i].equals(gold_cate[i])) {
					tp_health++;

					tn_book++;
					tn_dvd++;
					tn_music++;
					tn_toys_games++;

				} else {
					fn_health++;
					if (test_cate[i].equals("book")) {
						fp_book++;
						
						tn_dvd++;
						tn_music++;
						tn_toys_games++;
					} else if (test_cate[i].equals("dvd")) {
						fp_dvd++;
						
						tn_book++;
						tn_music++;
						tn_toys_games++;
					} else if (test_cate[i].equals("music")) {
						fp_music++;
						
						tn_book++;
						tn_dvd++;
						tn_toys_games++;
					} else if (test_cate[i].equals("toys_games")) {
						fp_toys_games++;
						
						tn_book++;
						tn_dvd++;
						tn_music++;
					}
				}
			}

			else if (gold_cate[i].equals("music")) {
				if (test_cate[i].equals(gold_cate[i])) {
					tp_music++;

					tn_book++;
					tn_dvd++;
					tn_health++;
					tn_toys_games++;

				} else {
					fn_music++;
					if (test_cate[i].equals("book")) {
						fp_book++;
						
						tn_dvd++;
						tn_health++;
						tn_toys_games++;
					} else if (test_cate[i].equals("dvd")) {
						fp_dvd++;
						
						tn_book++;
						tn_health++;
						tn_toys_games++;
					} else if (test_cate[i].equals("health")) {
						fp_health++;
						
						tn_book++;
						tn_dvd++;
						tn_toys_games++;
					} else if (test_cate[i].equals("toys_games")) {
						fp_toys_games++;
						
						tn_book++;
						tn_dvd++;
						tn_health++;
					}
				}
			}

			else if (gold_cate[i].equals("toys_games")) {
				if (test_cate[i].equals(gold_cate[i])) {
					tp_toys_games++;

					tn_book++;
					tn_dvd++;
					tn_health++;
					tn_music++;

				} else {
					fn_toys_games++;
					if (test_cate[i].equals("book")) {
						fp_book++;
						
						tn_dvd++;
						tn_health++;
						tn_music++;
					} else if (test_cate[i].equals("dvd")) {
						fp_dvd++;
						
						tn_book++;
						tn_health++;
						tn_music++;
					} else if (test_cate[i].equals("health")) {
						fp_health++;
						
						tn_book++;
						tn_dvd++;
						tn_music++;
					} else if (test_cate[i].equals("music")) {
						fp_music++;
						
						tn_book++;
						tn_dvd++;
						tn_health++;
					}
				}
			}

		}

	}

	public static void getData(String xml_name) {
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

		if (xml_name.equals("gold_standard.xml")) {
			
			for (int i = 0; i < parts.length; i++) {
				if (parts[i].equals("<Text")) {
					cutString = parts[i + 1].split("\"");// category=cutString[1]
					gold_cate[count] = cutString[1];
//					System.out.println("gold : " + gold_cate[count]);
					count++;
				}
			}
			System.out.println("Finish gold_standard.xml reading... ");
		} else if (xml_name.equals("test_outcome.xml")) {

			for (int i = 0; i < parts.length; i++) {
				if (parts[i].equals("<Text")) {
					cutString = parts[i + 1].split("\"");// category=cutString[1]
					test_cate[count] = cutString[1];
//					System.out.println("test : " + test_cate[count]);
					count++;
				}
			}
			System.out.println("Finish test_outcome.xml reading... ");
		}

	}

}
