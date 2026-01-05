import java.util.Scanner;

public class Main {

    // Safe integer input with retry
    private static int readIntSafely(Scanner sc, String message) {
        while (true) {
            try {
                System.out.print(message);
                String input = sc.nextLine();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter numbers only.");
            }
        }
    }

    // Safe string input (non-empty)
    private static String readStringSafely(Scanner sc, String message) {
        String input;
        do {
            System.out.print(message);
            input = sc.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Input cannot be empty. Try again.");
            }
        } while (input.isEmpty());
        return input;
    }

    // Safe string input (alphabets only)
    private static String readNameSafely(Scanner sc, String message) {
        while (true) {
            System.out.print(message);
            String input = sc.nextLine().trim();

            if (input.matches("[a-zA-Z ]+")) {
                return input;
            } else {
                System.out.println("Invalid input! Please enter alphabets only.");
            }
        }
    }

    public static void main(String[] args) {

        RecipeService service = new RecipeService();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("""
                1. Add Recipe
                2. Update Recipe
                3. View All Recipes
                4. Delete Recipe
                5. Sort by Name
                6. Sort by Recipe ID
                Any other key to Exit
                """);

            choice = readIntSafely(sc, "Enter your choice: ");

            switch (choice) {

                case 1 -> {
                    Recipe r = new Recipe();

                    r.setRecipeId(readIntSafely(sc, "Recipe ID: "));
                    r.setName(readNameSafely(sc, "Recipe Name: "));

                    System.out.print("Prep Time (minutes only): ");
                    r.setPrepTime(service.validatePrepTime(sc));

                    System.out.print("Ingredient used to cook: ");
                    r.getIngredients().add(sc.nextLine());

                    service.add(r);
                }

                case 2 -> {
                    Recipe r = new Recipe();

                    r.setRecipeId(readIntSafely(sc, "Recipe ID to update: "));
                    r.setName(readNameSafely(sc, "New Recipe Name: "));

                    System.out.print("New Prep Time (minutes only): ");
                    r.setPrepTime(service.validatePrepTime(sc));

                    r.getIngredients().clear();
                    System.out.print("New Ingredient used to cook: ");
                    r.getIngredients().add(sc.nextLine());

                    service.update(r);
                }

                case 3 -> {
                    service.fetchAll().forEach(System.out::println);
                }

                case 4 -> {
                    int id = readIntSafely(sc, "Enter Recipe ID: ");
                    service.deleteById(id);
                }

                case 5 -> {
                    System.out.println("Sorted by Name:");
                    service.sortByName().forEach(System.out::println);
                }

                case 6 -> {
                    System.out.println("Sorted by Recipe ID:");
                    service.sortByRecipeId().forEach(System.out::println);
                }

                default -> {
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                }
            }

        } while (true);
    }
}

