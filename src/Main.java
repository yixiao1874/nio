import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.Selector;

public class Main {

    public static void main(String[] args) throws Exception{
        FileInputStream fis = new FileInputStream("C:\\usr\\a.txt");
        FileChannel fcin = fis.getChannel();

        FileOutputStream fos = new FileOutputStream("C:\\usr\\b.txt");
        FileChannel fcOut = fos.getChannel();

        ByteBuffer buf = ByteBuffer.allocate(10);
        while (fcin.read(buf) != -1){
            buf.flip();
            fcOut.write(buf);
            buf.clear();
        }

        fcin.close();
        fcOut.close();
    }
}
