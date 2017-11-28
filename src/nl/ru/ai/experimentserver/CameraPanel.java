package nl.ru.ai.experimentserver;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import javax.swing.JPanel;
import org.opencv.core.Mat;
/**
 * Class that represents a camera panel
 * @author Tessa Beinema
 *
 */
public class CameraPanel extends JPanel {
	
	//serialVersionUID is needed, otherwise there are errors.
	private static final long serialVersionUID = 1L;
	private BufferedImage image;
	private byte[] imagePixels;
	
	public CameraPanel () {
		super();	
	}
	
	/**
	 * Method that transforms a Mat into a BufferedImage
	 * @param mat
	 */
	public void MatToBufferedImage (Mat mat)
	{
		int width = mat.width();
		int height = mat.height();
		int channels = mat.channels();
		
		byte[] matPixels = new byte[width * height * channels];
	
		mat.get(0, 0, matPixels);
		
		this.image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
		this.imagePixels = ((DataBufferByte) this.image.getRaster().getDataBuffer()).getData();
		System.arraycopy(matPixels, 0, this.imagePixels, 0, matPixels.length);	
	}
	
	public byte[] getImageByteArray () {
		return this.imagePixels;
	}
	
	/**
	 * The paint component
	 */
	public void paintComponent (Graphics g) {
		super.paintComponent(g);
		
		if (this.image == null) {
			return;
		}
		g.drawImage(this.image, 0, 0, this.image.getWidth(), this.image.getHeight(), null);
	}
	
	public BufferedImage getBufferedImage() {
		return this.image;
	}
}
