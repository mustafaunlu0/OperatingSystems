package project;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

public class FCFS {
	Queue<Proses> realTimeQueue;
	Queue<Proses> readyQueuePrio0=new LinkedBlockingQueue<Proses>();

	public FCFS(Queue<Proses> realTimeQueue) {
		this.realTimeQueue = realTimeQueue;
	}
	
	// Prosesin haz�r olup olmad���n�n kontrol�
	public void isReady() {
		// prosesin geli� zaman�na g�re haz�r durumuna ge�mesi
		for(Proses item:realTimeQueue) {
			if(item.getVarisZamani()==Time.time) {
				item.setDurum("haz�r");
				Proses temp=realTimeQueue.remove();
				readyQueuePrio0.add(temp);
			}
		}
	}
	//�nceli�i 0 olan kuyru�un �al��mas�
	public void go(Proses item) {
		item.setDurum("�al���yor");
		item.setPatlamaZamani(item.getPatlamaZamani()-1);
		item.zamanAsimi=21;
		Dispatcher.isStart=false;
		if(item.getPatlamaZamani()==0) { //Bitti�inde yap�lacaklar
			item.setDurum("bitti");
			readyQueuePrio0.remove(item);
			Dispatcher.isStart=true;
		}
		item.print();
	}
	
	public Queue<Proses> getReadyQueuePrio0() {
		return readyQueuePrio0;
	}

	public void setReadyQueuePrio0(Queue<Proses> readyQueuePrio0) {
		this.readyQueuePrio0 = readyQueuePrio0;
	}
}