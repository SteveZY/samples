package zyz.steve.sorting;

import java.util.Scanner;

public class MergeToCountInvertedPairs {

    int [] aux = new int[60];
    int res =0;
    void setRes(int v){
        this.res = 0;
    }
    int getRes(){
        return res;
    }
    void mergeToCount(int []a, int l, int r){

        if(l>=r)
            return ;
        int mid = l + (r - l) / 2;
         mergeToCount(a,l,mid);
        mergeToCount(a,mid+1,r);

         merge(a, l, r, mid);
    }
    void merge(int[]a , int l, int r, int mid){
        for (int i = l; i <=r; i++) {
            aux[i]= a[i];
        }
        int ll = l, rr = mid+1;

        for (int i = l; i <=r; i++) {
            if(ll>mid){
                a[i] = aux[rr++];
            } else if (rr>r) {
                a[i] = aux[ll++];
            } else if (aux[rr] < aux[ll]) {
                a[i]=aux[rr++];
                res+=mid-ll+1;
            }else {
                a[i] = aux[ll++];
            }

        }
    }

    public static void main(String[] args) {
        MergeToCountInvertedPairs mergeToCount = new MergeToCountInvertedPairs();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        while (n-- > 0){
            mergeToCount.setRes(0);
            int sz = sc.nextInt();
            int[] aa = new int[sz];

            for (int i = 0; i < sz; i++) {
                aa[i] = sc.nextInt();
            }
            mergeToCount.mergeToCount(aa,0,sz-1);
            System.out.printf("Optimal train swapping takes %d swaps.%n", mergeToCount.getRes());
        }

    }

}
