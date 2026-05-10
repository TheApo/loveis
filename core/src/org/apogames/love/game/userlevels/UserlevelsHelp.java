package org.apogames.love.game.userlevels;

public class UserlevelsHelp {

	private String level;
	private double time;
	private int count;
	private int all;
	private String user;
	private int fun;
	private int easy;
	private int hard;
	private int creative;
	private int id;
	private double curTime;
	
	private int start;
	
	public UserlevelsHelp() {
		
	}

	public void setUserlevelsHelp(String[] feedback, int start) {
		this.start = start;
		
		this.level = getStringFromFeedback(feedback);
		this.time = getDoubleFromFeedback(feedback);
		this.count = getIntegerFromFeedback(feedback);
		this.all = getIntegerFromFeedback(feedback);
		this.user = getStringFromFeedback(feedback);
		this.fun = getIntegerFromFeedback(feedback);
		this.easy = getIntegerFromFeedback(feedback);
		this.hard = getIntegerFromFeedback(feedback);
		this.creative = getIntegerFromFeedback(feedback);
		this.id = getIntegerFromFeedback(feedback);
		this.curTime = getDoubleFromFeedback(feedback);
	}
	
	private String getStringFromFeedback(String[] feedback) {
		if (start < feedback.length) {
			start += 1;
			return feedback[start - 1];
		}
		return null;
	}
	
	private double getDoubleFromFeedback(String[] feedback) {
		if (start < feedback.length) {
			start += 1;
			return Double.valueOf(feedback[start - 1]);
		}
		return 0.0;
	}
	
	private int getIntegerFromFeedback(String[] feedback) {
		if (start < feedback.length) {
			start += 1;
			return Integer.valueOf(feedback[start - 1]);
		}
		return 0;
	}

	public String getLevel() {
		return level;
	}

	public double getTime() {
		return time;
	}

	public int getCount() {
		return count;
	}

	public int getAll() {
		return all;
	}

	public String getUser() {
		return user;
	}

	public int getFun() {
		return fun;
	}

	public int getEasy() {
		return easy;
	}

	public int getHard() {
		return hard;
	}

	public int getCreative() {
		return creative;
	}

	public int getId() {
		return id;
	}

	public double getCurTime() {
		return curTime;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void setAll(int all) {
		this.all = all;
	}

	public void setFun(int fun) {
		this.fun = fun;
	}

	public void setEasy(int easy) {
		this.easy = easy;
	}

	public void setHard(int hard) {
		this.hard = hard;
	}

	public void setCreative(int creative) {
		this.creative = creative;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public void setTime(double time) {
		this.time = time;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setCurTime(double curTime) {
		this.curTime = curTime;
	}
}
