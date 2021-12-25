package gcl.tree;

/**
 * @author：小关同学
 * @date: 2020/12/30 21:50
 * 哈夫曼树类，用于哈夫曼树的创建等操作
 */
public class Tree implements Comparable<Tree>{
    //数据
    private Character data;
    //权值
    private Integer weight;
    //左子树
    private Tree left;
    //右子树
    private Tree right;

    public Character getData() {
        return data;
    }

    public void setData(Character data) {
        this.data = data;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Tree getLeft() {
        return left;
    }

    public void setLeft(Tree left) {
        this.left = left;
    }

    public Tree getRight() {
        return right;
    }

    public void setRight(Tree right) {
        this.right = right;
    }

    //覆写compareTo方法进行从小到大的排序
    //如果参数o的权值等于该对象的权值，则返回值 0；
    //如果该对象的权值小于参数o的权值，则返回一个小于 0 的值；
    //如果该对象的权值大于参数o的权值，则返回一个大于 0 的值。
    @Override
    public int compareTo(Tree o) {
        return this.weight - o.weight;
    }
}