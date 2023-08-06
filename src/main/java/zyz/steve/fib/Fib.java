package zyz.steve.fib;

public class Fib {
    static long fWithMem( int n){

        long second = 1;
        long first = 1;
        long third = 0;
        for (int i = 3; i <= n; i++) {
            third = second + first;
            first = second;
            second = third;
        }
        return third;
    }
    static long fibTailRecursive(int n, long first, long second){
        if(n<=1) return first;
        return fibTailRecursive(n-1, second,first+second);
    }

}
