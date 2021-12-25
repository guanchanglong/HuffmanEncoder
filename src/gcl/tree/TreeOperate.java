package gcl.tree;

import java.util.*;

/**
 * @author：小关同学
 * @date: 2020/12/30 21:57
 */
public class TreeOperate {

    public static Tree createTree(Map<Character,Integer> map){
        List<Tree> list = new ArrayList<>();
        for (Map.Entry<Character,Integer> m : map.entrySet()){
            Tree tree = new Tree();
            tree.setData(m.getKey());
            tree.setWeight(m.getValue());
            tree.setLeft(null);
            tree.setRight(null);
            list.add(tree);
        }
        while(true){
            if (list.size()>1){
                Collections.sort(list);
                Tree[]trees = new Tree[2];
                Tree root = new Tree();
                //得到权值最小的两个节点
                trees[0] = list.get(0);
                trees[1] = list.get(1);
                //设置这两个节点的根节点，默认左边比右边小
                root.setLeft(trees[0]);
                root.setRight(trees[1]);
                root.setData(null);
                root.setWeight(trees[0].getWeight()+trees[1].getWeight());
                list.add(root);
                //将原来的两个最小节点删除
                list.remove(trees[0]);
                list.remove(trees[1]);
            }else{
                break;
            }
        }
        return list.get(0);
    }

    public static Map<Character,Integer> statistics(char[]chars){
        Map<Character,Integer> map = new HashMap<>();
        for (char c : chars){
            if (map.containsKey(c)){
                map.put(c,map.get(c)+1);
            }else{
                map.put(c,1);
            }
        }
        return map;
    }

    public static char[] getChars(byte[] bytes) {
        char[]chars = new char[bytes.length];
        for (int i = 0;i<bytes.length;i++){
            chars[i] = (char) bytes[i];
        }
        return chars;
    }
}