package project;
import java.util.*;

public class Color {
	// Renk kodlarý
	public static final String RESET = "\u001B[0m";
	//public static final String SIYAH = "\u001B[30m";
	public static final String KIRMIZI = "\u001B[31m";
	public static final String YESIL = "\u001B[32m";
	public static final String SARI = "\u001B[33m";
	public static final String MAVI = "\u001B[34m";
	public static final String MOR = "\u001B[35m";
	public static final String CYAN = "\u001B[36m";
	// Renk ekleme
	public static void add(ArrayList<String> renkler) {
		renkler.add(Color.CYAN);
		renkler.add(Color.KIRMIZI);
		renkler.add(Color.MAVI);
		renkler.add(Color.MOR);
		renkler.add(Color.RESET);
		renkler.add(Color.SARI);
		renkler.add(Color.YESIL);
	}
}

