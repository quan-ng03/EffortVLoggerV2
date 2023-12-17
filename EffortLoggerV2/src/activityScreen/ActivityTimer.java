package activityScreen;

import java.util.Timer;
import java.util.TimerTask;

public class ActivityTimer {
	private static int secondsElapsed;
	private static Timer timer;
	
	public ActivityTimer() {
		secondsElapsed = 0;
	}
	
	public int getSecondsElapsed() {
		return secondsElapsed;
	}
	
	public void resetTimer() {
		secondsElapsed = 0;
	}
	
	public void startSession() {
            timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    secondsElapsed++;
                }
            }, 0, 1000); // Update timer every second
        }
	public void stopSession() {
            timer.cancel();
            timer.purge();
    }
}
