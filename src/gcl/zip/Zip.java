package gcl.zip;

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
 * 文件压缩类，执行文件压缩的一些具体操作
 */
public class Zip {
    public static void zipTotal(File file){

        //压缩步骤
        System.out.println("文件读取");
        byte[] bytes = FileRead.read(file);
        System.out.println("字节转字符");
        char[]chars = TreeOperate.getChars(bytes);
        System.out.println("统计个字符出现的次数，形成Map集合");
        Map<Character,Integer> map = TreeOperate.statistics(chars);
        System.out.println("创建哈夫曼树");
        Tree tree = TreeOperate.createTree(map);
        Map<Character,String> charsMap = new HashMap<>();
        System.out.println("遍历哈夫曼树");
        TreeCode.allNodeToZip(tree,"",charsMap);
        String string = TreeCode.coding(charsMap,chars);
        System.out.println("哈夫曼树叶子节点的值和它对应的哈夫曼编码");
        for (Map.Entry<Character,String> m : charsMap.entrySet()){
            System.out.println(m.getKey()+" "+m.getValue());
        }
        System.out.println("哈夫曼编码："+string);
        System.out.println("哈夫曼编码的长度"+string.length());
        System.out.println("形成哈夫曼编码");
        byte[] byteZip = TreeCode.codeZip(string);
        System.out.println("对哈夫曼编码进行按位压缩");
        System.out.println(byteZip.length+"||"+bytes.length+"   压缩比率为："+(double) byteZip.length/(double) bytes.length);
        FileZip.zip(file,map,byteZip);
        System.out.println("压缩完成");
    }
}

