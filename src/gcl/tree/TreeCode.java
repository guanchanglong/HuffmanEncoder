package gcl.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author：小关同学
 * @date: 2020/12/30 21:57
 * 对哈夫曼编码的操作类
 */
public class TreeCode {

    /**
     * 压缩时遍历哈夫曼树，创建压缩时的哈夫曼编码
     * @param tree 传入的哈夫曼树
     * @param str 编码
     * @param charsMap 储存各个字符编码的集合
     */
    public static void allNodeToZip(Tree tree, String str, Map<Character,String> charsMap){
        if (tree.getLeft()==null){
            System.out.println(tree.getData());
            charsMap.put(tree.getData(),str);
        }else{
            System.out.println(tree.getData());
            allNodeToZip(tree.getLeft(),str+"0",charsMap);
            System.out.println(tree.getData());
            allNodeToZip(tree.getRight(),str+"1",charsMap);
        }
    }


    public static void allNodeToUnZip(Tree tree,String str, Map<String,Character> charsMap){
        if (tree.getLeft()==null){
            System.out.println(tree.getData());
            charsMap.put(str,tree.getData());
        }else{
            System.out.println(tree.getData());
            allNodeToUnZip(tree.getLeft(),str+"0",charsMap);
            System.out.println(tree.getData());
            allNodeToUnZip(tree.getRight(),str+"1",charsMap);
        }
    }

    //进行哈夫曼编码
    public static String coding(Map<Character,String> codeMaps,char[]chars){
        StringBuffer stringBuffer = new StringBuffer();
        String code = "";
        for (int i=0;i<chars.length;i++){
            stringBuffer.append(codeMaps.get(chars[i]));
            if (i%100000==0){
                code+=stringBuffer.toString();
                stringBuffer.setLength(0);
            }
        }
        code+=stringBuffer.toString();
        return code;
    }

    public static byte[] codeZip(String code){
        int len;
        if (code.length()%8==0){
            len = code.length()/8;
        }else{
            len = code.length()/8+1;
        }
        byte[]bytes = new byte[len];

        int index = 0;
        for (int i = 0;i < code.length();i += 8){
            String strByte;
            if (i+8>code.length()){
                strByte = code.substring(i);
            }else{
                strByte = code.substring(i,i+8);
            }
            //Integer.parseInt(strByte, 2)方法的作用是输出二进制strByte数变成十进制后的数
            //如：1010变为十进制后为10
            bytes[index] = (byte) Integer.parseInt(strByte,2);
            index++;
        }
        return bytes;
    }

    public static String codeUnZip(byte[]bytes){
        StringBuffer stringBuffer = new StringBuffer();
        String code = "";
        for (int i=0;i<bytes.length;i++){
            byte b = bytes[i];
            //如果i不为最后一位时，flag永远为false
            boolean flag = i == bytes.length-1;
            stringBuffer.append(byteToBitString(!flag,b));
            if (i%100000==0){
                code+=stringBuffer.toString();
                stringBuffer.setLength(0);
            }
        }
        code+=stringBuffer.toString();
        return code;
    }

    /**
     * 将一个byte转成一个二进制字符串
     * @param flag
     * @param b
     * @return
     */
    public static String byteToBitString(boolean flag, byte b) {

        int temp = b;
        //到了字节数组的最后一位
        if (flag) {
            temp = b | 256;
        }
        String str = Integer.toBinaryString(temp);
        return flag ? str.substring(str.length() - 8) : str;
    }

    public static char[] enCode(String code, Map<String,Character> map){
        List<Character> list = new ArrayList<>();
        for(int i = 0; i < code.length(); ) {
            int count = 1;
            boolean flag = true;
            Character b = null;
            String key = null;
            while(flag) {
                if (i+count<=code.length()){
                    key = code.substring(i, i + count);
                }else{
                    //如果到了结尾而且key还找不到匹配的值，就对key进行补零的操作
                    key="0"+key;
                    System.out.println(key);
                }
                b = map.get(key);
                if (b == null) {
                    ++count;
                } else {
                    flag = false;
                }
            }
            list.add(b);
            i+=count;
        }
        char[]chars = new char[list.size()];
        for (int i=0;i<chars.length;i++){
            chars[i] = list.get(i);
        }
        return chars;
    }

    public static byte[] getBytes(char[] chars) {
        byte[]bytes = new byte[chars.length];
        for (int i=0;i<chars.length;i++){
            bytes[i] = (byte) chars[i];
        }
        return bytes;
    }
}
