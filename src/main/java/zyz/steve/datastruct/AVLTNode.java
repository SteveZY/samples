package zyz.steve.datastruct;
//https://ivanzz1001.github.io/records/post/data-structure/2018/06/14/ds-avl_tree
public class AVLTNode {
    public AVLTNode left,right;
    int bf;//-1, 0 or 1 balance factor
    public int data;
    public int height;
    /*|bf| = |hl - hr| <= 1 */

    /*Rotations:
    *  Left of left Node    - imbalance - needs LL rotation
    *  L node R insertion        imbalance  - two step rotaion- LR
    *  right of right Node  - imbalance       RR rotation
    *  Right node , L insertion          - two step rotaton - RL
    * */
    public AVLTNode(int x) {
        this.data = x;
    }

    public int getHeight() {
        return height;
    }
}
