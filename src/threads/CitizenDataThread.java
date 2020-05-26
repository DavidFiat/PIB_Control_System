package threads;

import java.io.IOException;

import model.*;

public class CitizenDataThread extends Thread {

	private Country c;

	public CitizenDataThread(Country c) {
		this.c = c;
	}

	public void run() {
		while (true) {
				try {
					c.citizenData();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					sleep(10000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		}
	}

}
