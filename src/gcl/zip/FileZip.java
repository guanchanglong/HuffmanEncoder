package gcl.zip;

import java.io.*;
import java.util.Map;

/**
 * @author：小关同学
 * @date: 2020/12/30 21:54
 * 文件压缩类，执行对文件的写入
 */
public class FileZip {

    public static void zip(File file, Map<Character,Integer> map, byte[]bytes){
        File fileOutput;
        String path = file.getAbsolutePath();
        String fileName = file.getName();
        //返回最后一个出现字符串的索引
        int index = path.lastIndexOf(".");
        StringBuffer stringPath = new StringBuffer(path);
        stringPath.replace(index,stringPath.length(),".zip");
        System.out.println("输出的文件名为："+stringPath.toString());
        System.out.println(map.size());
        OutputStream outputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try{
            fileOutput = new File(stringPath.toString());
            outputStream = new FileOutputStream(fileOutput);
            objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(map);
            objectOutputStream.writeObject(bytes);
            objectOutputStream.writeObject(fileName);
        }catch (IOException e1){
            e1.printStackTrace();
        }finally {
            try{
                outputStream.close();
                objectOutputStream.close();
            }catch (IOException e2){
                e2.printStackTrace();
            }
        }
    }
}