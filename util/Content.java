package util;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import util.AudioRecorder;
import java.awt.Dimension;

public class Content extends JPanel {
	private boolean startedRecording = false;
	private String notRecording = "Aufzeichnung Starten";
	private String isRecording = "Aufzeichnung Stoppen";
	
	public Content(AudioRecorder ar) {
		super(new BorderLayout());
		
		JButton button = new JButton("Aufzeichnung Starten");
		button.addActionListener(e -> {
			if (!startedRecording) {
				ar.recordAudio();
			} else {
				ar.stopRecording();
			}
		
		startedRecording = !startedRecording;
		button.setText(startedRecording ? isRecording : notRecording);
		});
		
		add(button, BorderLayout.PAGE_END);
	}
}