package zyz.steve.fib;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PascalTri
{
    //1st: save every line
    //Time complexity : O(n**2)  n = rowIndex
    //space: O(n**2)
    public List<Integer> getRow(int rowIndex){
        List<List<Integer>> res = new ArrayList<>();//save every line one by one

        for (int i = 0; i <= rowIndex; i++) {
            ArrayList<Integer> row = new ArrayList<>();
//            if(rowIndex<=1) row.add(1);
            for (int j = 0; j <= i; j++) {
                if(j==0||j==i) row.add(1); //fill 1 at beginning and end for every line
                else {
                    List<Integer> prevLine = res.get(i - 1);
                    row.add(prevLine.get(j-1)+prevLine.get(j));

                }
            }
            res.add(row);
        }
        return res.get(rowIndex);
    }

    /**
     * rolling array to get better space complexity
     * @param rowIndex
     * @return
     */
    public List<Integer> getRowRollingArray(int rowIndex){
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i <= rowIndex; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if(j==0||j==i) row.add(1);
                else {
                    row.add(res.get(j-1)+res.get(j));
                }
            }
            res = row;
        }
        return res;
    }

    /**
     *
     * calculate from the end of every line
     * space：O(1) //不考虑返回数组所占用的内存
     */
    public List<Integer> getRowWithEvenLessSpace(int rowIndex){
        List<Integer> res = new ArrayList<>();
        res.add(1);
        for (int i = 1; i <= rowIndex; i++) {
            res.add(0);//由于每一行都比上一行多一个数，先增加一个元素
            for (int j = i; j >0; j--) {
//                先计算最后一个数，一直到 第一个，第0 位置的数不需要动，总是1
                 {
                     res.set(j, res.get(j-1)+res.get(j));
//                    res.add(res.get(j-1)+res.get(j));
                }
            }
        }
        return res;

    }

    /**
     * 利用线性递推公式, 每行的系数 都为 行坐标 n>=0，同列坐标的 组合数m （0<=m<=n）；最快的办法
     * 有公式： C(m,n) =A(m,n)/m! = n!/[(n-m)!m!]
     * 则： C(m-1,n)=n!/[(n-m+1)!(m-1)!]
     * 两式相除，C(m,n)/C(m-1,n) = (n-m+1)/m
     * 则可得 C(m,n) = C(m-1,n) * (n-m+1)/m
     *
     */
    public List<Long> getRowFastest(int rowIndex){
        List<Long> res = new ArrayList<>();
        res.add(1L);
        for (int i = 1; i <= rowIndex; i++) {
            res.add(res.get(i-1)*(rowIndex-i+1)/i);
        }
        return res;
    }
    public static void main(String[] args) {
        PascalTri pascalTri = new PascalTri();
        long start = System.nanoTime();
        System.out.println(pascalTri.getRow(33));
        System.out.println("time: " + (System.nanoTime() - start));

        start = System.nanoTime();
        System.out.println(pascalTri.getRowRollingArray(33));
        System.out.println("time: " + (System.nanoTime() - start));

        start = System.nanoTime();
        System.out.println(pascalTri.getRowWithEvenLessSpace(32));
        System.out.println("time: " + (System.nanoTime() - start));

        start = System.nanoTime();
        System.out.println(pascalTri.getRowFastest(33));
        System.out.println("time: " + (System.nanoTime() - start));
//        start = System.nanoTime();
    }
}
