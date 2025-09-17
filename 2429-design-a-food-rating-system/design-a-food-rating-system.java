import java.util.*;

class FoodRatings {
    // Maps for quick lookups
    private Map<String, String> foodToCuisine;
    private Map<String, Integer> foodToRating;
    private Map<String, PriorityQueue<Food>> cuisineToFoods;

    // Constructor
    public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
        foodToCuisine = new HashMap<>();
        foodToRating = new HashMap<>();
        cuisineToFoods = new HashMap<>();

        for (int i = 0; i < foods.length; i++) {
            String food = foods[i];
            String cuisine = cuisines[i];
            int rating = ratings[i];

            foodToCuisine.put(food, cuisine);
            foodToRating.put(food, rating);

            // Comparator: higher rating first, then lexicographically smaller name
            cuisineToFoods.putIfAbsent(cuisine, new PriorityQueue<>(
                (a, b) -> a.rating == b.rating ? a.name.compareTo(b.name) : b.rating - a.rating
            ));

            cuisineToFoods.get(cuisine).add(new Food(food, rating));
        }
    }

    // Change rating of a food
    public void changeRating(String food, int newRating) {
        foodToRating.put(food, newRating);
        String cuisine = foodToCuisine.get(food);
        cuisineToFoods.get(cuisine).add(new Food(food, newRating));
    }

    // Get highest-rated food of a cuisine
    public String highestRated(String cuisine) {
        PriorityQueue<Food> pq = cuisineToFoods.get(cuisine);

        // Lazy deletion: remove outdated entries
        while (true) {
            Food top = pq.peek();
            if (top.rating == foodToRating.get(top.name)) {
                return top.name;
            }
            pq.poll(); // discard outdated entry
        }
    }

    // Helper class
    private static class Food {
        String name;
        int rating;
        Food(String n, int r) {
            name = n;
            rating = r;
        }
    }
}

/**
 * Usage Example:
 * FoodRatings obj = new FoodRatings(
 *     new String[]{"kimchi","miso","sushi","moussaka","ramen","bulgogi"},
 *     new String[]{"korean","japanese","japanese","greek","japanese","korean"},
 *     new int[]{9,12,8,15,14,7}
 * );
 * System.out.println(obj.highestRated("korean")); // kimchi
 * obj.changeRating("sushi",16);
 * System.out.println(obj.highestRated("japanese")); // sushi
 */
