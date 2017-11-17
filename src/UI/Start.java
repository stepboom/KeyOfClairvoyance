package UI;

import Render.GameResource;
import Render.Resource;

public class Start implements Runnable {

	private static int time;

	@Override
	public void run() {
		while (true) {
			synchronized (GameWindow.x) {
				if (GameWindow.stop == true) {
					try {
						GameWindow.x.wait();
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
			}

			try {
				Thread.sleep(1000);
				decreaseTime(1);
				GameScreen.gameTime.setText(time + "");
				if (time <= 0) {
					GameResource.gameBg.stop();
					GameScreen.gameTime.setText("0");
					FirstPanel.check = false;
					System.out.println("TIME STOP");
					if (ModePanel.mode == 1) {
						HighScoreUtility.recordHighScore(GameScreen.getScore());
						HighScorePanel.rescore1();
					} else {
						HighScoreUtility2.recordHighScore(GameScreen.getScore());
						HighScorePanel.rescore2();
					}
					FirstPanel.maincards.next(FirstPanel.firstPanel);
					Resource.soundbg.loop();
					break;
				}
				System.out.println("time " + time);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static int getTime() {
		return time;
	}

	public static void setTime(int time) {
		Start.time = time;
	}

	public static int increaseTime(int i) {
		if (time >= 999)
			return time = 999;
		else
			return time += i;
	}

	public static int decreaseTime(int i) {
		time -= i;
		if (time <= 0)
			time = 0;
		return time;
	}

}
