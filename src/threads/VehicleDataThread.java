package threads;

import java.io.IOException;

import model.*;

public class VehicleDataThread extends Thread {

	private Transport t;

	public VehicleDataThread(Transport t) {
		this.t = t;
	}

	public void run() {
		while (true) {
			try {
				t.vehicleData();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				sleep(20000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}
