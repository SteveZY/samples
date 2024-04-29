package zyz.steve.general;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class UniqueMorseCode {
    //leetcode 804
    public static int uniqueMorseCodeWords(String[] words) {
        String[] codes = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "src/main", ".---", "-.-", ".-..",
                "--",
                "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};
        Map<Character, String> letterMorse = new HashMap<>();
        for (int i = 0; i < codes.length; i++) {
            letterMorse.put((char) (97 + i), codes[i]);
        }
        Set<String> morseCodes = new TreeSet<>();
        for (int i = 0; i < words.length; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < words[i].length(); j++) {
                int codeIdx = (int) (words[i].charAt(j)) - 97;
                sb.append(codes[codeIdx]);
            }
            morseCodes.add(sb.toString());
        }
        return morseCodes.size();
    }

}
