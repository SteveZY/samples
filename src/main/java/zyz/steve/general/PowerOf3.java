package zyz.steve.general;

public class PowerOf3 {
    public static boolean isPowerOf3(int num){
        //一个数是某个底数b的整数次方的话需要其 b进制形式为 100...0|b
        //1st：转换其为对应的b 进制形式
        //2nd：转换结果为二进制，如果异常 则 其不是 b的整数次方
        //3rd: 检查 二进制 形式是不是只有一个1，如果否，则不是，否则，是b的整数次方
        String base3St = Integer.toString(num, 3);
        if (num == 0) {
            return false;
        }
        int x;
        try {
            x = Integer.parseUnsignedInt(base3St, 2);
        } catch (Exception e) {
            return false;
        }

        return (x & x - 1) == 0;
    }

}
