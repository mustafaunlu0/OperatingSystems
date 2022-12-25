package project;
public class Dispatcher{
	FCFS fcfs;
	MFQ mfq;
	public static boolean isStart=true;
	public Dispatcher(FCFS fcfs, MFQ mfq) {
		this.fcfs = fcfs;
		this.mfq = mfq;
	}
	//Baþlayacak kuyruðu bul
	public Proses find() {
		if(!fcfs.getReadyQueuePrio0().isEmpty()) {
			Proses item=fcfs.readyQueuePrio0.element();
			return item;
		}else if(!mfq.getReadyQueuePrio1().isEmpty()) {
			Proses item=mfq.readyQueuePrio1.element();
			return item;
		}else if(!mfq.getReadyQueuePrio2().isEmpty()) {
			Proses item=mfq.readyQueuePrio2.element();
			return item;
		}else{
			Proses item=mfq.readyQueuePrio3.element();
			return item;
		}
	}
	//Sistemin çalýþmasý
	public void run() {
		while((!fcfs.realTimeQueue.isEmpty())||(!fcfs.getReadyQueuePrio0().isEmpty())||(!mfq.userJobQueue.isEmpty())||!(mfq.getReadyQueuePrio1().isEmpty())||!(mfq.getReadyQueuePrio2().isEmpty())||!(mfq.getReadyQueuePrio3().isEmpty())) 
		{
			fcfs.isReady();
			mfq.isReady();
			if(isStart&&(!(fcfs.readyQueuePrio0.isEmpty())||!(mfq.getReadyQueuePrio1().isEmpty())||!(mfq.getReadyQueuePrio2().isEmpty())||!(mfq.getReadyQueuePrio3().isEmpty()))) {
				Proses temp=find();
				temp.setDurum("baþladý");
				System.out.println(temp);
			}
			Time.timeUp();
			if(!fcfs.getReadyQueuePrio0().isEmpty()) {
				Proses item=fcfs.readyQueuePrio0.element();
				fcfs.go(item);
			}
			else if(!mfq.getReadyQueuePrio1().isEmpty()) 
			{
				Proses item=mfq.readyQueuePrio1.element();
				mfq.goPrio1(item);
			}
			else if(!mfq.getReadyQueuePrio2().isEmpty()) 
			{
				Proses item=mfq.readyQueuePrio2.element();
				mfq.goPrio2(item);
			}else if(!mfq.getReadyQueuePrio3().isEmpty()) 
			{
				Proses item=mfq.readyQueuePrio3.element();
				mfq.goPrio3(item);
			}
			mfq.overTimePrio1();
			mfq.overTimePrio2();
			mfq.overTimePrio3();

		}
	}
}
