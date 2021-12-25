package gcl.unzip;

import gcl.FileRead;
import gcl.tree.Tree;
import gcl.tree.TreeCode;
import gcl.tree.TreeOperate;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author：小关同学
 * @date: 2020/12/30 21:55
 */
public class UnZip {
    public static void unZipTotal(File file){
        Object[] objects;
        String fileName;
        Map<Character,Integer> map;
        byte[]codeBytes;
        System.out.println("文件读取");
        objects = FileRead.unZipRead(file);
        map = (Map<Character, Integer>) objects[0];
        codeBytes = (byte[]) objects[1];
        fileName = (String) objects[2];
        System.out.println(fileName);
        System.out.println(codeBytes.length);
        System.out.println(map.size());
        System.out.println(fileName);
        System.out.println("反编码，获取哈夫曼编码");
        String codeUnZip = TreeCode.codeUnZip(codeBytes);
        System.out.println("重新创建哈夫曼树");
        Tree tree = TreeOperate.createTree(map);
        Map<String,Character> mapToUnZip = new HashMap<>();
        System.out.println("遍历哈夫曼树");
        TreeCode.allNodeToUnZip(tree,"",mapToUnZip);
        System.out.println(mapToUnZip.size());
        System.out.println("对比还原原来的字符数组");
        System.out.println(codeUnZip);
        System.out.println(codeUnZip.length());
        for (Map.Entry<String,Character> m : mapToUnZip.entrySet()){
            System.out.println(m.getValue()+" "+m.getKey());
        }
        char[] chars = TreeCode.enCode(codeUnZip,mapToUnZip);
        System.out.println("将字符数组转为字节数组重新写入文件中");
        byte[] bytes = TreeCode.getBytes(chars);

        String path = file.getAbsolutePath();
        System.out.println(path);
        int indexPath = path.lastIndexOf("\\");
        int indexFileName = fileName.lastIndexOf(".");
        StringBuffer stringPath = new StringBuffer(path);
        StringBuffer stringFileName = new StringBuffer(fileName);
        stringFileName.delete(indexFileName,stringFileName.length());
        System.out.println(stringPath.toString());
        System.out.println(stringFileName.toString());
        stringPath.replace(indexPath,stringPath.length(),"\\"+stringFileName.toString()+"\\"+fileName);
        System.out.println(stringPath.toString());
        UnFileZip.unZip(stringPath.toString(),bytes);
    }
}

