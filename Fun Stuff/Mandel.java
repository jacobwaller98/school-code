import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Mandel {

	int MAX_X = 1000;
	int MAX_Y = 1000;

	public static void main(String[] args) {
		new Mandel(-1.768537402791761947240883400847201056595206205552518056225326890392143996579015217439664376119973093588582949354023172297978192092823570966258499146719548846957213673181409910618147135950028450590566715,
		0.0005400495035209695647102872054426089298508762991118705637984149391509006042298931229502461586535581831240578702073505834331466206165329303974121146956914081239475447299603844106171592790364118096889349999999,
		.000000000001,.000000000001,"Image.png");
	}

	public Mandel(double x, double y, double width, double height, String name) {
		BufferedImage img = new BufferedImage(MAX_X, MAX_Y, BufferedImage.TYPE_INT_ARGB);
		for(int j = 0; j < MAX_Y; j++) {
				if(j%500==0) {
					//System.out.println((j/(double)MAX_Y)*100 + "% done...");
					System.out.format("%.2f %% done...\n",(j/(double)MAX_Y)*100);
				}
				for(int i = 0; i < MAX_X;i++) {
				img.setRGB(i, j, getColor(i,j,x,y,width,height));
			}
		}
		System.out.println("Image generated...");
		System.out.println("Saving image...");
		File f = new File(name);
		try {
			ImageIO.write(img, "png", f);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Done...");
	}

	int getColor(int x, int y, double cx, double cy, double width, double height) {
		double real = map(x,0,MAX_X,cx-(width/2.0),cx+(width/2.0));
		double imag = map(y,0,MAX_Y,cy-(height/2.0),cy+(height/2.0));
		ComplexNumber point = new ComplexNumber(real, imag);
		return getColorOfLocation(point);
	}

	int getColorOfLocation(ComplexNumber n) {
		ComplexNumber c = new ComplexNumber(n.real, n.img);
		int number = -1;
		for(int i=0; i<10000; i++) {
			n = runFunction(n,c);
			if(n.getMagSquared() > 4) {
				number = i;
				break;
			}
		}

		if(number == -1) {
			return getColorInt(0, 0, 0, 255);
		}
		return Color.HSBtoRGB((float) map(number,0,10000,0,1), 255, 255);
	}

	ComplexNumber runFunction(ComplexNumber z, ComplexNumber c) {
		ComplexNumber f = z.mult(z);
		f = f.add(c);
		return f;
	}

	double map(double x, double in_min, double in_max, double out_min, double out_max) {
		return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
	}

	int getColorInt(int r, int g, int b, int alpha) {
		return (alpha << 24) | (r << 16) | (g << 8) | b;
	}
}

class ComplexNumber {

	double real;
	double img;

	public ComplexNumber(double real, double img) {
		this.real = real;
		this.img = img;
	}

	public ComplexNumber add(ComplexNumber n) {
		return new ComplexNumber(real + n.real, img + n.img);
	}

	public ComplexNumber sub(ComplexNumber n) {
		return new ComplexNumber(real - n.real, img - n.img);
	}

	public ComplexNumber mult(ComplexNumber n) {
		double newreal = real * n.real - img * n.img;
        double newimag = real * n.img + img * n.real;
        return new ComplexNumber(newreal, newimag);
	}

	public double getMag() {
		return Math.sqrt(real*real + img*img);
	}

	public double getMagSquared() {
		return real*real + img*img;
	}
}
