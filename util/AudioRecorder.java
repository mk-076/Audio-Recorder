package util;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.TargetDataLine;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.DataLine.Info;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.AudioInputStream;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioFileFormat;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class AudioRecorder {
	private final AudioFormat format;
	private TargetDataLine line;
	private Thread writer;
	private AudioInputStream aIS;
	
	public AudioRecorder(AudioFormat format) {
		this.format = format;
	}
	
	public AudioRecorder() {
		this(new AudioFormat(41000.0f, 16, 1, true, false));
	}
	
	public void recordAudio() {
		DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
		
		try {
			line = (TargetDataLine) AudioSystem.getLine(info);
			line.open(format);
			line.start();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
			return;
		}
	
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss");
		LocalDateTime now = LocalDateTime.now();
		String timestamp = now.format(formatter);
	
		File wavFile = new File("./Recordings", "Aufnahme " + timestamp + ".wav");
		
		writer = new Thread(() -> {
			try {
				aIS = new AudioInputStream(line);
				AudioSystem.write(aIS, AudioFileFormat.Type.WAVE, wavFile);
			} catch (IOException e) {
				e.printStackTrace();
				return;
			}
		});
		
		writer.start();
	}
	
	public void stopRecording() {
		line.stop();
		line.close();
		
		try {
			aIS.close();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		
		try {
			writer.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
			return;
		}
	}
}