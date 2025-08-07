import java.util.*;
class Solution {
    public int calPoints(String[] operations) {
        ArrayList<Integer>record= new ArrayList<>();
        for(String op : operations) {
            if(op.equals("C")) {
                record.remove(record.size()-1);
            }
            else if(op.equals("D")) {
                int last = record.get(record.size()-1);
                record.add(2*last);
            }
            else if(op.equals("+")) {
                int last = record.get(record.size() - 1);
                int secondLast = record.get(record.size() - 2);
                record.add(last + secondLast);
            } else {
                // It's an integer string
                record.add(Integer.parseInt(op));
            }
        }

        // Calculate the total sum
        int sum = 0;
        for (int score : record) {
            sum += score;
        }

        return sum;
    }
}