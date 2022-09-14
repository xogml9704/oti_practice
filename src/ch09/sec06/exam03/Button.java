package ch09.sec06.exam03;

public class Button {
	// Field
	private ClickListener clickListener;
	// Constructor
	// method
	public void setClickListener(ClickListener clickListener) {
		this.clickListener = clickListener;
	}
	
	public void click() {
		clickListener.onClick();
	}
	
	public static interface ClickListener {
		public void onClick();
	}
	
}
