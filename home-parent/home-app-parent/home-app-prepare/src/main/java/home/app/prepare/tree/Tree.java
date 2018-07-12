package home.app.prepare.tree;

/**
 * @author liangyongjian
 * @desc 树的接口定义
 * @create 2018-05-23 11:43
 **/
public interface Tree<Key extends Comparable<Key>> {

    /**
     * 判空
     * @return
     */
    boolean isEmpty();

    /**
     * 二叉树的结点个数
     * @return
     */
    int size();

    /**
     * 返回二叉树的高度或者深度,即结点的最大层次
     * @return
     */
    int height();

    /**
     * 先根次序遍历
     */
    String preOrder();

    /**
     * 中根次序遍历
     */
    String inOrder();

    /**
     * 后根次序遍历
     */
    String postOrder();

    /**
     * 层次遍历
     */
    String levelOrder();

    /**
     * 将data 插入
     * @return
     */
    void insert(Key data);


    /**
     * 删除
     */
    void remove(Key data);

    /**
     * 查找最大值
     * @return
     */
    Key findMin();

    /**
     * 查找最小值
     * @return
     */
    Key findMax();

    /**
     * 根据值找到结点
     * @param data
     * @return
     */
    BinaryNode findNode(Key data);

    /**
     * 是否包含某个值
     * @param data
     * @return
     */
    boolean contains(Key data) throws Exception;


    /**
     * 清空
     */
    void clear();
}
