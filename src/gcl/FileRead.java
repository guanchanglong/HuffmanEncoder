package gcl;

import java.io.*;
import java.util.Map;

/**
 * @author：小关同学
 * @date: 2020/12/30 21:58
 * 文件读取类
 */
public class FileRead {
    /**
     * 压缩前读取的文件信息
     * @param file 传入的文件
     * @return 返回一个字节数组
     */
    public static byte[] read(File file){
        InputStream inputStream = null;
        byte[] bytes = null;
        try{
            if (file.exists()){
                inputStream = new FileInputStream(file);
                bytes = new byte[inputStream.available()];
                inputStream.read(bytes);
            }else{
                System.out.println("文件不存在");
            }
        }catch (IOException e1){
            e1.printStackTrace();
        }finally {
            try{
                inputStream.close();
            }catch (IOException e2){
                e2.printStackTrace();
            }
        }
        return bytes;
    }

    /**
     * 解压前读取zip文件
     * @param file 文件
     * @return 返回一个Object类型的数组，里面储存着map、codeBytes、fileName这些对象的值
     */
    public static Object[] unZipRead(File file){
        InputStream inputStream = null;
        ObjectInputStream objectInputStream = null;
        Object[] returnObject = new Object[3];
        String fileName;
        try {
            if (file.exists()){
                inputStream = new FileInputStream(file);
                objectInputStream = new ObjectInputStream(inputStream);
                Map<Character, Integer> map = (Map) objectInputStream.readObject();
                byte[] codeBytes = (byte[]) objectInputStream.readObject();
                fileName = (String)objectInputStream.readObject();
                returnObject[0] = map;
                returnObject[1] = codeBytes;
                returnObject[2] = fileName;
            }
        }catch (Exception e1){
            e1.printStackTrace();
        }finally {
            try{
                inputStream.close();
                objectInputStream.close();
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }
        return returnObject;
    }
}
