package main.pl.kuzyyn.controller.components;

public class NoCommand implements GUICommand {

	@Override
	public void execute() {
		System.out.println("Brak komendy dla klucza.");

	}

}
