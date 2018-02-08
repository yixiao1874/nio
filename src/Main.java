import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.Selector;

public class Main {
    public void change(Car car){
        car.setColor("red");
    }

    public static void main(String[] args) throws Exception{
        Car car = new Car();
        car.setColor("green");
        System.out.println(car.getColor());
        new Main().change(car);
        System.out.println(car.getColor());
    }
}

class Car{
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    private String color;
}
