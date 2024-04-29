package zyz.steve;

public class Utils {

    public static int[] fillItem(String str) {
        int[] item = new int[2];

        str.chars().forEach(c -> {
            if (c == '0') {
                item[0]++;
            } else {
                item[1]++;
            }
        });
        return item;
    }

    public static int[][] fillItems(String[] strs) {
        int[][] items = new int[strs.length][];
        for (int i = 0; i < strs.length; i++) {
            items[i] = fillItem(strs[i]);
        }
        return items;
    }

}
