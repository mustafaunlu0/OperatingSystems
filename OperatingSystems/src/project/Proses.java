package project;
import java.io.IOException;
import java.lang.*;

public class Proses{
	//Her Proses nesnesinin kendi Process'ini oluþturuyoruz.
	Process process;
	ProcessBuilder builder=new ProcessBuilder("cmd.exe","/c","echo");
	int id;
	int varisZamani;
	int oncelik;
	int patlamaZamani;
	int zamanAsimi=20;
	String durum="yeni";
	String renk;
	
	public Proses() {}
	
	public Proses(int id,int varisZamani, int oncelik, int patlamaZamani,String renk) throws IOException {
		process=builder.start();
		this.id=id;
		this.varisZamani = varisZamani;
		this.oncelik = oncelik;
		this.patlamaZamani = patlamaZamani;
		this.renk=renk;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public int getVarisZamani() {
		return varisZamani;
	}
	public void setVarisZamani(int varisZamani) {
		this.varisZamani = varisZamani;
	}
	public int getPatlamaZamani() {
		return patlamaZamani;
	}
	public void setPatlamaZamani(int patlamaZamani) {
		this.patlamaZamani = patlamaZamani;
	}
	public int getOncelik() {
		return oncelik;
	}
	public void setOncelik(int oncelik) {
		this.oncelik = oncelik;
	}
	public String getDurum() {
		return durum;
	}
	public void setDurum(String durum) {
		this.durum = durum;
	}
	public String getRenk() {
		return renk;
	}

	public void setRenk(String renk) {
		this.renk = renk;
	}
	
	public int getZamanAsimi() {
		return zamanAsimi;
	}

	public void setZamanAsimi(int zamanAsimi) {
		this.zamanAsimi = zamanAsimi;
	}

	@Override
	public String toString() {
		return renk+Time.time+ ".sn process " + durum + "	(id:" + id + ", öncelik:" + oncelik + ", kalan süre:" + patlamaZamani + ")";
	}
}