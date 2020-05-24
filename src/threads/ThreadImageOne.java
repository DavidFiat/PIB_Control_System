package threads;

import controller.PIBController;

public class ThreadImageOne extends Thread {

	private PIBController control;
	private boolean run;

	public ThreadImageOne(PIBController controlA) {
		control = controlA;
		run = true;
	}

	@Override
	public void run() {
		boolean limite = true;

		int tamanio = 568;
		int x =0;
		while (run) {
			try {
				control.moved(x);
				for(int i=0; i<tamanio && limite;i++) {
					x++;
					sleep(100);
					if(x==tamanio) {
						limite = !limite;
					}
				}
				for(int i=tamanio; i>-1 && !limite; i--) {
					x--;
					sleep(100);
					if(x==0) {
						limite = !limite;
					}
				}

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
