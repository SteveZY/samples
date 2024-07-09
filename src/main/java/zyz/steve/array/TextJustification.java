package zyz.steve.array;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TextJustification {
    static List<String> reflowAndJustify(String [] lines , int width) {

        List<String> words = new ArrayList<>();
        String[] www = new String[]{};
        for(String line: lines) {
            String[] ws = line.split("\\s+");
            www = Stream.concat(Arrays.stream(www),Arrays.stream(ws)).toArray(String[]::new);
//            Arrays
//            Arrays.asList(line.split("\\s+"));
        }
        return  fullJustify(www,width);
    }

    static List<String>  reflowOp(String[] lines, int width){
        List<String> ans = new ArrayList<>();
        List<String> words = new ArrayList<>();
        for(String line:lines){
            words.addAll(Arrays.asList(line.split("\\s+")));
        }
        List<String> line = new ArrayList<>();
        int lineLen = 0;
        int numSp = 0;
        for (int i = 0; i < words.size(); i++) {
            String curWord = words.get(i);
            if(line.size()==0){
                line.add(curWord);
                lineLen=curWord.length();
            }else {
                lineLen +=curWord.length();
                numSp++;
                if(lineLen+numSp>width){
                    lineGenerator(width, ans, line, lineLen - curWord.length());
                    //reset
                    numSp=0;line.clear();
                    line.add(curWord);lineLen=curWord.length();
                }else {
                    line.add(curWord);

                }
            }
        }
        if(line.size()>0){
            lineGenerator(width,ans,line,lineLen);
        }

        return ans;
    }

    private static void lineGenerator(int width, List<String> ans, List<String> line, int lineLen) {
        StringBuilder sb = new StringBuilder();
        //got one line
        int slots = line.size() - 1;
        if(slots == 0){
            ans.add(line.get(0));
        }else {
            int remains = width - lineLen ;
            int numSpPerSlot = remains / slots;
            char [] sparr = new char[numSpPerSlot];
            Arrays.fill(sparr, '-');
            new String(sparr);
            String spacer = IntStream.range(0, numSpPerSlot).mapToObj(e -> "-").reduce("", (a, b) -> a + b);
            int leftoverSp = remains % slots;
            for (int j = 0; j < line.size() - 1; j++) {
                sb.append(line.get(j)).append(spacer);
                if (leftoverSp > 0) {
                    sb.append("-");
                }
                leftoverSp--;
            }
            sb.append(line.get(line.size() - 1));
            ans.add(sb.toString());
        }
    }

    //code 4
    public static List<String> fullJustify(String[] words, int maxWidth){
        List<String> ans = new ArrayList<>();
        int i = 0;
        while (i < words.length) {
            int sepLen =0;
            int j = i;
            int lengthOfWords =0;
            ArrayDeque<String> curLine = new ArrayDeque<>();
            for (; j < words.length; j++) {
                curLine.add(words[j]);
                lengthOfWords +=words[j].length();
                sepLen += 1;//一个空格

                if (sepLen+lengthOfWords>maxWidth) {
                    break;
                }
            }
            if(sepLen+lengthOfWords-1 > maxWidth && j>i)
            //over length
            //go one word back
            {
                lengthOfWords -= words[j].length() ;
                curLine.removeLast();
                j--;
            }
            if(j>=words.length)j--;
            //            Stream.of(1,2,3,4).map(e->"xx").;
            int numWords = j - i + 1;
            StringBuilder sb = new StringBuilder();
            if(numWords == 1 || j >= words.length - 1) {
                //                sb.append(curLine.poll()); // No justify
                leftJustify(sb, curLine, maxWidth);
                ans.add(sb.toString());
                i = j + 1;
                continue;
            }
            int numEdges = numWords-1;
            int leftPlaces = maxWidth - lengthOfWords;
            int numSepsForEachEdge = leftPlaces / numEdges;
            String sep = IntStream.range(0, numSepsForEachEdge).mapToObj(e -> "-").reduce("",(t, e) -> t + e);
            int leftOver = leftPlaces%numEdges;
            while (leftOver>0){
                sb.append(curLine.poll()).append(sep).append('-');
                leftOver -- ;
            }

            int size=curLine.size();
            for (int k =0; k < size-1; k++) {
                sb.append(curLine.poll()).append(sep);
            }
            sb.append(curLine.poll());
            ans.add(sb.toString());
            i=j+1;
        }
        return ans;
    }
    public static void leftJustify(StringBuilder sb, Queue<String> curLine, int maxWidth){
        String leftPart = String.join(" ", curLine);
        char[] spaces = new char[maxWidth - leftPart.length()];
        Arrays.fill(spaces,' ');
        sb.append(leftPart).append(new String(spaces));
    }


    //code 3
    public static List<String> wordWrap(String[] words, int max){
        List<String> ans = new ArrayList<>();
        List<String> line  = new ArrayList<>();
        int startPos =0;
        int wLen = 0, sLen=0;

        for(String word:words){
            if(line.size()==0) {
                //the first word of the line
                line.add(word);
                wLen = word.length();
            }else {
                sLen++;//先加空格，因为任何一个单词 除了第一个，其前面必须有一个空格，
                wLen +=word.length();
                if (wLen + sLen > max) {
                    //got one line
                    ans.add(String.join("-",line))
                    ;
                    //开始新的一行 并reset lineLength
                    line.clear();
                    line.add(word);

                    wLen = word.length();
                    sLen =0;
                } else {
                    line.add(word);
                }
            }

        }

//        while (startPos<words.length){
//            String w = words[startPos];
//            wLen+=w.length();
//            sLen++;
//            if(sLen+wLen>= max){
//                //One new line
//                if(sLen-1+wLen <= max){
//                    //got one line
//                    line.add(w);
//                    ans.add(String.join("-",line));
//                }else {
//                    //去除一个空格还是太大，只能
//                    //remove the last word
//                    startPos--;
//                    ans.add(String.join("-",line));
//                }
//                //start next line
//                line.clear();
//                wLen=0;sLen=0;
//            }else {
//                line.add(words[startPos]);
//            }
//            startPos++;
//
//        }
        if(line.size()>0)ans.add(String.join("-",line));
        return ans;
    }

    public static String addThousandSep(String a){
        StringBuilder sb = new StringBuilder();
        String[] seps = a.split("\\.");
        int size = seps[0].length();
        for (int i = size-1; i >=0; i--) {
            sb.append(seps[0].charAt(i));
            if((size-i) %3==0) sb.append(",");
        }
        return sb.reverse().append(".").append(seps[1]).toString();

    }

}
