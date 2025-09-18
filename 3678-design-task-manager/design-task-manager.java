import java.util.*;

class TaskManager {
    // taskId -> [userId, priority]
    private Map<Integer, int[]> taskMap;
    // Max-heap of [priority, taskId, userId]
    private PriorityQueue<int[]> pq;

    public TaskManager(List<List<Integer>> tasks) {
        taskMap = new HashMap<>();
        pq = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) return Integer.compare(b[0], a[0]); // higher priority first
            return Integer.compare(b[1], a[1]); // if tie, higher taskId first
        });

        for (List<Integer> t : tasks) {
            int userId = t.get(0), taskId = t.get(1), priority = t.get(2);
            taskMap.put(taskId, new int[]{userId, priority});
            pq.offer(new int[]{priority, taskId, userId});
        }
    }

    public void add(int userId, int taskId, int priority) {
        taskMap.put(taskId, new int[]{userId, priority});
        pq.offer(new int[]{priority, taskId, userId});
    }

    public void edit(int taskId, int newPriority) {
        int userId = taskMap.get(taskId)[0];
        taskMap.put(taskId, new int[]{userId, newPriority});
        pq.offer(new int[]{newPriority, taskId, userId});
    }

    public void rmv(int taskId) {
        taskMap.remove(taskId); // lazy deletion
    }

    public int execTop() {
        while (!pq.isEmpty()) {
            int[] top = pq.peek();
            int priority = top[0], taskId = top[1], userId = top[2];

            int[] cur = taskMap.get(taskId);
            // Validate that the heap entry matches the current userId AND priority
            if (cur != null && cur[0] == userId && cur[1] == priority) {
                pq.poll();
                taskMap.remove(taskId); // executed -> remove
                return userId;
            }
            pq.poll(); // stale entry -> discard
        }
        return -1;
    }
}
