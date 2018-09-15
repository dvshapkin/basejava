import java.io.File;
import java.io.IOException;

public class MainFile {

    public static void main(String[] args) throws IOException {

        File root = new File(".");
        printLevel(root, "");

    }

    static void printLevel(File root, String prefix) {
        System.out.println(prefix + root.getName());
        if (! root.isDirectory()) {
            return;
        }
        prefix += '\t';
        for (File file : root.listFiles()) {
            printLevel(file, prefix);
        }
    }


}
