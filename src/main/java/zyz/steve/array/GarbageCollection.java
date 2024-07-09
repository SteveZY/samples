package zyz.steve.array;

import java.util.*;

//https://leetcode.com/problems/minimum-amount-of-time-to-collect-garbage/description/
//#2391
public class GarbageCollection {
    private static int time(Map<Integer, Integer> m, int max, int[] travel){
        int t =0;
        if(m.size()>0){
            for(int i=0;i<=max;i++){
                t+= m.getOrDefault(i,0);
                if(i<max)
                    t+=travel[i];
            }
        }
        return t;
    }
    public static int garbageCollection(String[] garbage, int[] travel) {
        Map<Integer, Integer> gm = new HashMap<>();
        Map<Integer, Integer> pm = new HashMap<>();
        Map<Integer, Integer> mm = new HashMap<>();
        int mmax = 0, pmax=0,gmax=0;

        for(int i=0; i< garbage.length;i++){
            String g = garbage[i];
            for(int j =0;j<g.length();j++){
                char c = g.charAt(j);
                if(c== 'M'){
                    mm.put(i,mm.getOrDefault(i,0)+1);
                    mmax = Math.max(mmax, i);
                }else if(c=='P'){
                    pm.put(i,pm.getOrDefault(i,0)+1);
                    pmax = Math.max(pmax, i);

                }else{
                    gm.put(i,gm.getOrDefault(i,0)+1);
                    gmax = Math.max(gmax, i);

                }
            }

        }
        int tt =0;
        tt+=time(mm,mmax, travel);
        tt+=time(pm,pmax, travel);
        tt+=time(gm,gmax, travel);


        return tt;
    }
    public static int gcop(String []gs, int [] travel){
        int gt = 0, pt = 0, mt = 0; //每种垃圾 的总数，
        int mIdx = 0, gIdx = 0, pIdx = 0;//需要到达 最大 位置
        for (int i = 0; i < gs.length; i++) {
            String g = gs[i];
            for (int j = 0; j < g.length(); j++) {
                char c = g.charAt(j);
                switch (c) {
                    case 'M':
                        mt++;
                        mIdx = i;
                        break;
                    case 'P':
                        pt++;
                        pIdx = i;
                        break;
                    case 'G':
                        gt++;
                        gIdx = i;
                        break;
                }
            }

        }
//        int [] tv = new int [travel.length+1];

        for (int i = 1; i < travel.length; i++) {
//            tv[i] = travel[i]+tv[i-1];
            travel[i] += travel[i - 1];
        }
        int tt = 0;
        tt += gt == 0 ? 0 :  gt + (gIdx==0?0:travel[gIdx -1]);//没有此种垃圾直接返回 0
        tt += pt == 0 ? 0 : pt + (pIdx==0?0:travel[pIdx -1]);
        tt += mt == 0 ? 0 : mt + (mIdx==0?0:travel[mIdx -1]);
        return tt;
    }

    public static void main(String[] args) {
        String [] garbage = {"G","P","GP","GG"};
        int []travel = {2,4,3};
        System.out.println(GarbageCollection.garbageCollection(garbage,travel));
        System.out.println(GarbageCollection.gcop(garbage,travel));
        System.out.println(GarbageCollection.gcop(new String []{"G","M"},new int[]{1}));

        System.out.println(Integer.bitCount(3));
    }
}
