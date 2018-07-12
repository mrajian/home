package home.app.prepare.tree;

/**
 * @author liangyongjian
 * @desc 二叉树结点
 * @create 2018-05-23 11:46
 **/
public class BinaryNode<Key extends Comparable<Key>, Value> {

    private Key key;             // 排序用的Key
    private Value value;         // 结点的值
    private BinaryNode leftNode;         // 左子树根节点
    private BinaryNode rightNode;        // 右子树根节点

    public BinaryNode(Key key, Value value) {
        this.key = key;
        this.value = value;
    }

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    public BinaryNode getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(BinaryNode leftNode) {
        this.leftNode = leftNode;
    }

    public BinaryNode getRightNode() {
        return rightNode;
    }

    public void setRightNode(BinaryNode rightNode) {
        this.rightNode = rightNode;
    }

}
