package zyz.steve.array;


import java.util.Comparator;
import java.util.PriorityQueue;

public class FurthestBuilding {
    public static  int futhestBlding(int[] h, int b, int l){
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());

        int[] bIdx = new int[1];
        int bLeft = b;
        int lLeft = l;
        for (int i = 0; i < h.length-1; i++) {
            int diff = h[i + 1] - h[i];
            if(diff<=0) {
                bIdx[0]=i+1;
//                continue;
            }else {
                bLeft-=diff;
                pq.offer(diff);
                if(bLeft<0){
                    bLeft += pq.poll();
                    lLeft--;
                }
                if (lLeft<0) {

                    break;
                }else {
                    bIdx[0]=i+1;
                }
            }
        }

//        getBidx(h,b,l,bIdx, 0);

        return bIdx[0];
    }

    private static void getBidx(int[] h, int b, int l, int[] bIdx, int start) {

        if(start>=h.length-1){
            bIdx[0]=h.length-1;
            return;
        }
        int nextidx = start + 1;
        if(h[nextidx]<=h[start]){
            bIdx[0] = nextidx;

            getBidx(h,b,l,bIdx,nextidx);
        }
        else if(h[nextidx] - h[start] <= b){
            int bLeft = b - h[nextidx] + h[start];
            bIdx[0] = nextidx;
            getBidx(h,bLeft,l, bIdx,nextidx);
        }else if(l> 0)
        {
            int lLeft = l - 1;
            bIdx[0]=nextidx;
            getBidx(h,b,lLeft,bIdx,nextidx);
        }

    }

}
