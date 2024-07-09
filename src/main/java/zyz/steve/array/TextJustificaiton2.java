package zyz.steve.array;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class TextJustificaiton2 {
    public static List<String> fullJustify(String[] words, int maxWidth) {

        List<String> ans = new ArrayList<>();
        List<String> line = new ArrayList<>();

        int wlen = 0;
        int slen=0;
        for(String w: words){
            if(line.size() == 0){
                wlen = w.length();
                line.add(w);
            }else{
                slen++;
                wlen += w.length();
                if(wlen+slen>maxWidth){
                    //got a new line
                    ans.add(getLine(line, maxWidth, wlen-w.length()));
                    //reset
                    line.clear();
                     wlen=w.length();
                    slen=0;
                     line.add(w);
                }
                else{
                    line.add(w);
                }
            }
        }
        if(line.size()>0) ans.add(leftjust(line,maxWidth));

        return ans;

    }
    private static String getLine(List<String> l, int maxWidth, int wl){
        int slots = l.size()-1;
        int sps = maxWidth - wl;
        int numS = sps/slots;
        int rem = sps % slots;

        if(slots == 0) return leftjust(l, maxWidth);
        StringBuilder sb = new StringBuilder();

        sb.append(l.get(0));
        String sep = IntStream.range(0,numS).mapToObj(e->" ").reduce("",(a,b)->a+b);
        for(int i=1;i<l.size();i++ ){
            sb.append(sep);
            if(rem-->0) sb.append(" ");
            sb.append(l.get(i));

        }

        return sb.toString();

    }
    private static String leftjust(List<String> l, int maxWidth){
        StringBuilder sb = new StringBuilder();
        sb.append(String.join(" ",l));
        int len = sb.length();
        sb.append(
                IntStream.range(0,maxWidth - len).mapToObj(e->" ").reduce("",(a,b)->a+b));
        return        sb.toString();
    }
}
