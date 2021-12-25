package gcl.unzip;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author：小关同学
 * @date: 2020/12/30 21:56
 */
public class UnFileZip {
    public static void unZip(String output,byte[]bytes){
        File file;
        OutputStream outputStream = null;
        try{
            file = new File(output);
            if (!file.getParentFile().exists()){
                file.getParentFile().mkdir();
            }
            outputStream = new FileOutputStream(file);
            outputStream.write(bytes);
        }catch (IOException e1){
            e1.printStackTrace();
        }finally {
            try{
                outputStream.close();
            }catch (IOException e2){
                e2.printStackTrace();
            }
        }
    }
}
