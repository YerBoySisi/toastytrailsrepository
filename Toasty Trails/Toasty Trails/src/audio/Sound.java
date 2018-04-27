package audio;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {
	
	private Clip clip;
	private AudioInputStream stream;
	private String file;
	
	public Sound(String file) {
		
		this.file = file;
		
	}
	
	public void play() {
		
		try {
			stream = AudioSystem.getAudioInputStream(new File(file));
		} catch (UnsupportedAudioFileException | IOException e) {
			e.printStackTrace();
		}
		
		try {
			clip = AudioSystem.getClip();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
		
		try {
			clip.open(stream);
		} catch (LineUnavailableException | IOException e) {
			e.printStackTrace();
		}
		
		clip.start();
		
	}
	
}
