import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Image {

	public int width;
	public int height;
	public byte[] data;
	
	public Image(int width, int height) {
	
		this.width = width;
		this.height = height;
		this.data = new byte[width * height * 3];
	}

	public void set(int x, int y, int val) {
		int index = y * width * 3 + x * 3;
		data[index] = (byte)((val & 0x00FF0000) >> 16);
		data[index + 1] = (byte)((val & 0x0000FF00) >> 8);
		data[index + 2] = (byte)((val & 0x000000FF) >> 0);
	}

	public void write(String filename) {
		try {
			BufferedWriter fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename)));
			fileWriter.write("P3");
			fileWriter.newLine();
			fileWriter.write(width + " " + height);
			fileWriter.newLine();
			fileWriter.write("256");
			fileWriter.newLine();
			for(int i = 0 ; i < height; i++) {
				for(int j = 0; j < width; j++) {
					int index = i * width * 3 + j * 3;
					fileWriter.write((int)data[index] + " ");
					fileWriter.write((int)data[index + 1] + " ");
					fileWriter.write((int)data[index + 2] + ((j < width - 1)?" ":""));
				}
				fileWriter.newLine();
			}
			fileWriter.flush();
			fileWriter.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
