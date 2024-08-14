package zyz.steve.backtrace;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

//question from Karate
public class FindWordInGrid {

    static  List<int[]> ans = new ArrayList<>();
    static boolean inB(char[][]g, int x, int y){
        int h = g.length;
        int l = g[0].length;
        return x>=0 && y>=0 && x<h&&y<l;
    }

    static void dfs(char[][]g, int x, int y, String word, int wIdx, ArrayDeque<int[]> q){
        if(!inB(g,x,y)) return;
        if(ans.size() == word.length()) return;
        if(wIdx > word.length()-1)
            return;

        if(g[x][y] == word.charAt( wIdx)){
            q.offer(new int[] {x, y});
            if(wIdx != word.length()-1) {
//                {
                    //continue
                    //go right
                    dfs(g,x,y+1,word,wIdx+1,q);
                    //down
                    dfs(g,x+1,y,word,wIdx+1,q);
                    q.removeLast();
//                }
            }else {
//                found one;
                ans.addAll(q);
            }

        }
    }
    static List<int[]> findWord( char[][]g,String word){
        ArrayDeque<int[]> q = new ArrayDeque<>();
//        List<int[]> ans = new ArrayList<>();
        //g,x,y,
        for (int i = 0; i < g.length; i++) {
            for (int j = 0; j < g[0].length; j++) {
                if(ans.isEmpty()){
                    dfs(g,i,j,word,0,q);
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        char[][]g={
                {'a','b','c'},
                {'e','b','f'},
                {'p','d','f'},

        };
        for(int[] a: findWord(g,"pdf")){
            System.out.println("----");
            for(int e: a){
                System.out.println(e);
            }
        }
    }

}
