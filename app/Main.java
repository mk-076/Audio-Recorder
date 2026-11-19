package app;

import util.*;
import java.awt.BorderLayout;

public class Main {
	public static void main(String[] args) {
		Window window = new Window();
		window.add(new Content(new AudioRecorder()), BorderLayout.CENTER);
		window.setVisible(true);
	}
}