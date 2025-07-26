class Data {
    String value;
    int timestamp;

    public Data (String value, int timestamp) {
        this.value = value;
        this.timestamp = timestamp;
    }
}
class TimeMap {
    Map<String, ArrayList<Data>> map;
    public TimeMap() {
        map = new HashMap<>();
    }
    
    public void set(String key, String value, int timestamp) {
        map.computeIfAbsent(key, k -> new ArrayList<>())
        .add(new Data(value, timestamp));
    }
    
    public String get(String key, int timestamp) {
        String result = "";
        ArrayList<Data> list = map.get(key);
        if (list == null) return result;

        int left = 0, right = list.size() - 1;
        if (list.get(left).timestamp > timestamp) return result;
        if (list.get(right).timestamp <= timestamp) return list.get(right).value;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (list.get(mid).timestamp == timestamp) {
                return list.get(mid).value;
            }
            else if (list.get(mid).timestamp < timestamp) {
                result = list.get(mid).value;
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }
        return result;
    }
}

/**
 * Your TimeMap object will be instantiated and called as such:
 * TimeMap obj = new TimeMap();
 * obj.set(key,value,timestamp);
 * String param_2 = obj.get(key,timestamp);
 */