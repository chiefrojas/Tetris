// timer class, doesnt extend the javax.swing.Timer because I wasnt even sure
// what form of output it had (nano, milli, seconds, hours?)

public class Timer{
  private float _difficulty = (float) 1; // change to change the difficulty of the game (.01, .1, 1, 10)
	private float _time, _elapsed;
	private long _timeElapsed;
	private int _count;
	private boolean _timerStopped;
	
	public Timer(float ticks) {
		setSpeed(ticks);
		tocs();
	}

	
	public void setSpeed(float ticks) {
		_time = ((_difficulty * 1000)/ ticks);
	}

	public void ticks() {
	    
		long current = time();
		float conv = (float)(current - _timeElapsed) + _elapsed;
		if(!_timerStopped) {
			_count += (int)(conv / _time);
			_elapsed = conv % _time;
		}
		_timeElapsed = current;
	}
	
	public void tocs() {
		_count = 0;
		_elapsed = (int)0;
		_timeElapsed = time();
		_timerStopped = false;
	}
	
	public void stopTimer(boolean paused) {
		_timerStopped = paused;
	}	
	
	public boolean timerStopped() {
		return _timerStopped;
	}
	
	public long time(){
	    return System.nanoTime() / 1000000;
	   }
	
	public boolean whileRunning() {
		if(_count > 0) {
			_count--;
			return true;
		}
		return false;
	}
}
