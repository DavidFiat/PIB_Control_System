package threads;

import java.io.IOException;

import controller.PIBController;
import model.Country;

public class SaveDataThread extends Thread {

	private PIBController c;

	public SaveDataThread(PIBController c) {
		this.c = c;
	}

	public void run() {
		while (true) {
			try {
				c.saveData();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				sleep(300000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}
