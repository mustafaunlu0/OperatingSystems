package project;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.lang.*;

public class Main {
	public static void main(String[] args) throws IOException {
		Queue<Proses> realTimeQueue=new LinkedBlockingQueue<>();
		Queue<Proses> userJobQueue=new LinkedBlockingQueue<>();
		ArrayList<String> renkler = new ArrayList();
		Color.add(renkler);
		Random rnd=new Random();
		int row=0;
		//dosya okur
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(args[0]))) {
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				row++;
			}
		}catch(IOException e) {}
		
		Proses[] proses=new Proses[row];
		int ProsesNumber=0;
		
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(args[0]))) {
			String line = null;
			String specialCharacter = ", ";
			
			while ((line = bufferedReader.readLine()) != null) {
				String[] values = line.split(specialCharacter);
				int[] value=new int[3];
				for (int i = 0; i < values.length; i++) {
					value[i] = Integer.valueOf(values[i]);
				}
				proses[ProsesNumber]=new Proses(ProsesNumber,value[0],value[1],value[2],renkler.get(rnd.nextInt(renkler.size()-1)));
				ProsesNumber++;
			}
		}catch(IOException e) {}
		
		// Proseslerin varýþ zamanýna göre sýralanmasý 
		for(int i = 0; i < row-1; i++)
        {
            for(int j = i+1; j < row; j++)
            {
                if(proses[i].getVarisZamani() > proses[j].getVarisZamani()) {
                    Proses gecici = proses[i];
                    proses[i] = proses[j];
                    proses[j] = gecici;
                }
            }
        }
		// Proseslerin önceliklerine göre uygun kuyruða yerleþtirilmesi 
		for(int k=0;k<row;k++) {
			if(proses[k].getOncelik()==0) {				
				realTimeQueue.add(proses[k]);
			}else {
				userJobQueue.add(proses[k]);
			}
		}
		
		FCFS fcfs=new FCFS(realTimeQueue);
		MFQ mfq=new MFQ(userJobQueue);
		Dispatcher dispatcher=new Dispatcher(fcfs, mfq);
		dispatcher.run();
	}
}