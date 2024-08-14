package zyz.steve.sorting;

//题目： https://www.acwing.com/problem/content/5842/
//solution by Scott
//https://scwang-automation.medium.com/%E5%9B%9B%E8%88%8D%E4%BA%94%E5%85%A5ii-ef62b6e451bf
public class ACWing5839 {
    int t ;
    void setT(int t){
        this.t= t;
    }
    String solution(String  ss){

        String[] dec = ss.split("\\.");
        if(dec.length != 2) return ss;

        //拆分整数 与 小数部分
        char[] cc = dec[1].toCharArray();
        char[] ii = dec[0].toCharArray();
        int end = cc.length;
        //找到第一个不小于5的 位
        int dge5 = 0;//greater or equal to 5
        while(dge5 < end && cc[dge5]<'5')dge5++;
        if (dge5 == end) {
            return ss;//没有ge 5 的 小数位
        }
        dge5--;//指向要被进位的位置
        t--;//剩余操作次数 减小


        //先处理小数位
        while(dge5>=0)
        {
            cc[dge5]++;
            if (cc[dge5] < '5') {
                break;//接受进位后小于5， 结束，不需要进一步操作了
            }
            //进位后，可以进一步 四舍五入
            if (t <= 0) {
                break;//没有 可用 的 操作了 退出
            }
            t--;
//            cc[dge5] = '0';//做一次操作，并将当前位清零
            //dge5 记录 大于 或 等于 5 的数位 index
            dge5--;
        }
        if(dge5<0){
            //说明小数部分 自 第一个>= 5 的位置一直都有进位有向整数位进位。则 +1 到整数位
            for (int i=ii.length-1;i>=0;i--){
                if(ii[i]<'9'){
                    ii[i]+=1;
                    break;
                }
                ii[i] ='0';
            }
        }

        StringBuilder sb = new StringBuilder();

        if (ii[0] == '0' && dge5<0) {
            //当最高整数位 为 0 时，说明有进位
            sb.append("1");
        }
        sb.append(ii);
        if (dge5 >= 0) {
            sb.append(".");
        }
        for (int i = 0; i <= dge5; i++) {
            sb.append(cc[i]);
        }
        return sb.toString();

    }

    public static void main(String[] args) {

        ACWing5839 sol = new ACWing5839();

//        Scanner sc = new Scanner(System.in);

        //获取测试用例数
//        int n = sc.nextInt();
//        int t = sc.nextInt();
//        String f= sc.next();
        int t=100;
        String f="0.232122128";
        sol.setT(t);
        System.out.println(sol.solution(f));
    }

}
