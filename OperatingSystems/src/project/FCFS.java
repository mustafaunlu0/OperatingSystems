package project;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

public class FCFS {
	Queue<Proses> realTimeQueue;
	Queue<Proses> readyQueuePrio0=new LinkedBlockingQueue<Proses>();

	public FCFS(Queue<Proses> realTimeQueue) {
		this.realTimeQueue = realTimeQueue;
	}
	
	// Prosesin hazýr olup olmadýðýnýn kontrolü
	public void isReady() {
		// prosesin geliþ zamanýna göre hazýr durumuna geçmesi
		for(Proses item:realTimeQueue) {
			if(item.getVarisZamani()==Time.time) {
				item.setDurum("hazýr");
				Proses temp=realTimeQueue.remove();
				readyQueuePrio0.add(temp);
			}
		}
	}
	//Önceliði 0 olan kuyruðun çalýþmasý
	public void go(Proses item) {
		item.setDurum("çalýþýyor");
		item.setPatlamaZamani(item.getPatlamaZamani()-1);
		item.zamanAsimi=21;
		Dispatcher.isStart=false;
		if(item.getPatlamaZamani()==0) { //Bittiðinde yapýlacaklar
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