package util;

import javax.swing.JFrame;

public class Window extends JFrame {
	public Window() {
		super("Audio Aufzeichner");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 400);
	}
}