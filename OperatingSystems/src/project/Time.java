package project;

public class Time {
	static int time=0;
	//Zaman� artt�r�r
	public static void timeUp() {
		try {
			Thread.sleep(1000);
			time++;
		} catch (Exception e) {}
	}
}
