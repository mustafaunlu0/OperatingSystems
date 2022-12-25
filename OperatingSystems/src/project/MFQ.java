package project;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class MFQ {
	Queue<Proses> userJobQueue=new LinkedBlockingQueue<>();
	Queue<Proses> readyQueuePrio1=new LinkedBlockingQueue<>();
	Queue<Proses> readyQueuePrio2=new LinkedBlockingQueue<>();
	Queue<Proses> readyQueuePrio3=new LinkedBlockingQueue<>();

	public MFQ(Queue<Proses> userJobQueue) {
		this.userJobQueue = userJobQueue;
	}
	//Vakti gelen kuyruklar� haz�r yap�p ait oldu�u kuyru�a atma
	public void isReady() {
		for(Proses item:userJobQueue) {
			if(item.getVarisZamani()==Time.time) {
				item.setDurum("haz�r");
				if(item.getOncelik()==1) {
					Proses temp=userJobQueue.remove();
					readyQueuePrio1.add(temp);
				} else if(item.getOncelik()==2) {
					Proses temp=userJobQueue.remove();
					readyQueuePrio2.add(temp);
				} else {
					Proses temp=userJobQueue.remove();
					readyQueuePrio3.add(temp);
				}
			}
		}
	}
	
	//�nceli�i 1 olanlar�n zaman a��m�na u�ray�p u�ramad���n� kontrol etme
	public void overTimePrio1() {
		for(Proses item1:readyQueuePrio1) {
			if(item1.getDurum()=="ask�da") {
				item1.zamanAsimi=item1.zamanAsimi-1;
				if(item1.zamanAsimi==0) {
					item1.setDurum("zaman a��m�");
					System.out.println(item1);
					readyQueuePrio1.remove(item1);
				}
			}

		}
	}
	//�nceli�i 2 olanlar�n zaman a��m�na u�ray�p u�ramad���n� kontrol etme
	public void overTimePrio2() {
		for(Proses item2:readyQueuePrio2) {
			if(item2.getDurum()=="ask�da") {
				item2.zamanAsimi=item2.zamanAsimi-1;
				if(item2.zamanAsimi==0) {
					item2.setDurum("zaman a��m�");
					System.out.println(item2);
					readyQueuePrio2.remove(item2);
				}
			}

		}
	}
	//�nceli�i 3 olanlar�n zaman a��m�na u�ray�p u�ramad���n� kontrol etme
	public void overTimePrio3() {
		for(Proses item3:readyQueuePrio3) {
			if(item3.getDurum()=="ask�da") {
				item3.zamanAsimi=item3.zamanAsimi-1;
				if(item3.zamanAsimi==0) {
					item3.setDurum("zaman a��m�");
					System.out.println(item3);
					readyQueuePrio3.remove(item3);
				}
			}

		}
	}
	//�nceli�i 1 olan kuyru�un �al��mas�
	public void goPrio1(Proses item) 
	{
		item.setDurum("�al���yor");
		System.out.println(item);
		item.setPatlamaZamani(item.getPatlamaZamani()-1);
		item.zamanAsimi=21;
		Dispatcher.isStart=false;
		if(item.getPatlamaZamani()==0) //Bitti�inde yap�lacaklar
		{
			item.setDurum("bitti");
			readyQueuePrio1.remove(item);
			Dispatcher.isStart=true;
		}else {  //Ask�ya al�nd���nda yap�lacaklar
			item.setDurum("ask�da");
			item.setOncelik(item.getOncelik()+1);
			Proses temp=readyQueuePrio1.remove();
			readyQueuePrio2.add(temp);
			Dispatcher.isStart=true;
		}

		System.out.println(item);
	}
	//�nceli�i 2 olan kuyru�un �al��mas�
	public void goPrio2(Proses item) 
	{
		item.setDurum("�al���yor");
		System.out.println(item);
		item.setPatlamaZamani(item.getPatlamaZamani()-1);
		item.zamanAsimi=21;
		Dispatcher.isStart=false;
		if(item.getPatlamaZamani()==0) //Bitti�inde yap�lacaklar
		{
			item.setDurum("bitti");
			readyQueuePrio2.remove(item);
			Dispatcher.isStart=true;
		}else {  //Ask�ya al�nd���nda yap�lacaklar
			item.setDurum("ask�da");
			item.setOncelik(item.getOncelik()+1);
			Proses temp=readyQueuePrio2.remove();
			readyQueuePrio3.add(temp);
			Dispatcher.isStart=true;
		}
		System.out.println(item);
	}
	
	public void goPrio3(Proses item) 
	{
		item.setDurum("�al���yor");
		System.out.println(item);
		item.setPatlamaZamani(item.getPatlamaZamani()-1);
		item.zamanAsimi=21;
		Dispatcher.isStart=false;
		if(item.getPatlamaZamani()==0) //Bitti�inde yap�lacaklar
		{
			item.setDurum("bitti");
			readyQueuePrio3.remove(item);
			Dispatcher.isStart=true;
		}else {  //Ask�ya al�nd���nda yap�lacaklar
			item.setDurum("ask�da");
			Proses temp=readyQueuePrio3.remove();
			readyQueuePrio3.add(temp);
			Dispatcher.isStart=true;
		}
		System.out.println(item);
	}
	
	public Queue<Proses> getReadyQueuePrio1() {
		return readyQueuePrio1;
	}
	public void setReadyQueuePrio1(Queue<Proses> readyQueuePrio1) {
		this.readyQueuePrio1 = readyQueuePrio1;
	}
	public Queue<Proses> getReadyQueuePrio2() {
		return readyQueuePrio2;
	}
	public void setReadyQueuePrio2(Queue<Proses> readyQueuePrio2) {
		this.readyQueuePrio2 = readyQueuePrio2;
	}
	public Queue<Proses> getReadyQueuePrio3() {
		return readyQueuePrio3;
	}
	public void setReadyQueuePrio3(Queue<Proses> readyQueuePrio3) {
		this.readyQueuePrio3 = readyQueuePrio3;
	}
}