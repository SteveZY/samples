package zyz.steve.union;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// leetcode 721
public class AccountsMerger {

    public static List<List<String>> accountsMerge(List<List<String>> accountList) {
        int numOfAccounts = accountList.size();
        WeightedQuickUnion wuf = new WeightedQuickUnion(numOfAccounts);
        // hash map 记录 emails  ----- group number 映射
        Map<String, Integer> emailGroup = new HashMap<String, Integer>();
        for (int i = 0; i < accountList.size(); i++) {
            List<String> account = accountList.get(i);
            int accountLen = account.size();
            String accountName = account.get(0);
            for (int j = 1; j < accountLen; j++) {
                String email = account.get(j);
                if (!emailGroup.containsKey(email)) {
                    // not in the map yet, add it
                    emailGroup.put(email, i);
                } else {
                    wuf.union(emailGroup.get(email), i);
                }
            }
        }
        // group number ---> emails mapping
        // 遍历 email group  , 产生上述 mapping
        Map<Integer, List<String>> regroupedEmails = new HashMap<>();
        for (Map.Entry<String, Integer> entry : emailGroup.entrySet()) {
            String email = entry.getKey();
            Integer group = entry.getValue();
            int root = wuf.getRoot(group);
            regroupedEmails.compute(root, (k, v) -> {
                if (v == null) {
                    // that group does not exist,
                    // create , add and return
                    List<String> emails = new ArrayList<String>();
                    emails.add(email);
                    return emails;
                }
                v.add(email);
                return v;
            });
        }
        // sort the emails and add account name
        List<List<String>> merged = new ArrayList<>();
        for (Map.Entry<Integer, List<String>> entry : regroupedEmails.entrySet()) {
            List<String> emails = entry.getValue();
            Collections.sort(emails);
            // the group index comes from the index of the account in the original list
            emails.add(0, accountList.get(entry.getKey()).get(0)); // add account name
            merged.add(emails);
        }
        return merged;
    }

    public static void main(String[] args) {
        List<List<String>> accountList = new ArrayList<>();
        List<String> account1 = new ArrayList<>();
        List<String> account2 = new ArrayList<>();
        List<String> account3 = new ArrayList<>();
        List<String> account4 = new ArrayList<>();
        account1.add("John");
        account1.add("johnsmith@mail.com");
        account1.add("john_newyork@mail.com");
        account2.add("John");
        account2.add("johnsmith@mail.com");
        account2.add("john00@mail.com");
        account4.add("John");
        account4.add("johnnybravo@mail.com");
        account3.add("Mary");
        account3.add("mary@mail.com");
        accountList.add(account1);
        accountList.add(account2);
        accountList.add(account3);
        accountList.add(account4);

        System.out.println(accountsMerge(accountList));
    }
}
