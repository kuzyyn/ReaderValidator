package main.pl.kuzyyn.view;

public class Style {
	private volatile static Style style;
	private Style(){};
	
	public static Style getInstance(){
		if (style == null){
			synchronized (Style.class){
				if (style == null) {
					style = new Style();
				}
			}
		}
		return style;
	}
	
	
	
	
}
