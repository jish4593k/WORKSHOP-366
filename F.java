import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.opencv_core.Scalar;
import org.bytedeco.opencv.opencv_core.CvType;
import org.bytedeco.opencv.opencv_imgproc;

import static org.bytedeco.opencv.global.opencv_core.CV_8U;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imread;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imwrite;
import static org.bytedeco.opencv.global.opencv_imgproc.cvtColor;
import static org.bytedeco.opencv.global.opencv_imgproc.GaussianBlur;
import static org.bytedeco.opencv.global.opencv_imgproc.Canny;
import static org.bytedeco.opencv.global.opencv_imgproc.dilate;
import static org.bytedeco.opencv.global.opencv_imgproc.erode;

public class ImageProcessing {
    public static void main(String[] args) {
        
        Mat img = imread("resources/images.jpg");
        Mat imgGray = new Mat();
        cvtColor(img, imgGray, opencv_imgproc.COLOR_BGR2GRAY);
        imwrite("GrayImage.jpg", imgGray);

      
        Mat imgBlur = new Mat();
        GaussianBlur(imgGray, imgBlur, new Size(7, 7), 0);
        imwrite("BlurImage.jpg", imgBlur);

       
        Mat imgCanny = new Mat();
        Canny(img, imgCanny, 100, 100);
        imwrite("CannyImage.jpg", imgCanny);

      
        Mat kernel = opencv_imgproc.getStructuringElement(opencv_imgproc.MORPH_RECT, new Size(5, 5));
        Mat imgDilation = new Mat();
        dilate(imgCanny, imgDilation, kernel, new Point(-1, -1), 1, 1, new Scalar(1), 1, opencv_core.BORDER_CONSTANT, new Scalar(0));
        imwrite("DilationImage.jpg", imgDilation);

     
        Mat imgErosion = new Mat();
        erode(imgCanny, imgErosion, kernel, new Point(-1, -1), 1, 1, new Scalar(1), 1, opencv_core.BORDER_CONSTANT, new Scalar(0));
        imwrite("ErosionImage.jpg", imgErosion);
    }
}
