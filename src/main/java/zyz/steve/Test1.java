package zyz.steve;

import java.util.LinkedHashMap;
import java.util.Scanner;

public class Test1 {

    static public int main(String[] args) {
        Scanner in = new Scanner(System.in);
        int [] letter = new int[26];
        String s;
        do {
             s = in.nextLine();
            for (int i = 0; i < s.length(); i++) {
                letter[s.charAt(i)%26]++;
            }
            int numOfDistChar = 0;
            for (int i = 0; i < 26; i++) {

                if(letter[i] != 0) numOfDistChar++;
            }
            if((numOfDistChar & 0x01) != 0){
                System.out.println("IGNORE HIM!");
            }else {
                System.out.println("CHAT WITH HER!");
            }
        }while (!s.equalsIgnoreCase("exit"));
        return 0;
    }

}
