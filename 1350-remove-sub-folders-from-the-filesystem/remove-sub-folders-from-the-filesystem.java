class Solution {
    public List<String> removeSubfolders(String[] folder) {
        Arrays.sort(folder); // Sort the folders lexicographically
        List<String> result = new ArrayList<>();
        
        String prev = ""; // To keep track of the last added top-level folder
        
        for (String f : folder) {
            // Check if f is NOT a subfolder of prev
            if (prev.isEmpty() || !f.startsWith(prev + "/")) {
                result.add(f);
                prev = f; // Update prev to the newly added folder
            }
        }
        
        return result;
        
    }
}