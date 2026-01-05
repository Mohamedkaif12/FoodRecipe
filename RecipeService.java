import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

public class RecipeService implements RecipeDAO {

    private File data;
    private List<Recipe> recipes;

    public RecipeService() {
        data = new File("recipeinfo.doc");
        recipes = new ArrayList<>();
        if (!data.exists()) {
            writeToFile();
        }
    }

    private void writeToFile() {
        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream(data))) {
            oos.writeObject(recipes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readFromFile() {
        try {
            if (data.length() == 0) {
                recipes = new ArrayList<>();
            } else {
                ObjectInputStream ois =
                        new ObjectInputStream(new FileInputStream(data));
                recipes = (List<Recipe>) ois.readObject();
                ois.close();
            }
        } catch (Exception e) {
            recipes = new ArrayList<>();
        }
    }

    public int validatePrepTime(Scanner sc) {
    while (true) {
        try {
            String input = sc.nextLine().trim();

            if (!input.matches("\\d+")) {
                throw new Exception("Prep time must be numbers only!");
            }

            int time = Integer.parseInt(input);

            if (time <= 0) {
                throw new Exception("Prep time must be greater than 0!");
            }

            return time;

        } catch (Exception e) {
            System.out.print(e.getMessage() + " Try again: ");
        }
    }
}


    @Override
    public void add(Recipe recipe) {
        readFromFile();
        recipes.add(recipe);
        writeToFile();
        System.out.println("Recipe added successfully");
    }

   @Override
public void update(Recipe recipe) {
    readFromFile();

    for (int i = 0; i < recipes.size(); i++) {
        if (recipes.get(i).getRecipeId() == recipe.getRecipeId()) {
            recipes.set(i, recipe); // ✅ replace
            writeToFile();
            System.out.println("Recipe updated successfully");
            return;
        }
    }

    System.out.println("Recipe not found");
}


    @Override
    public List<Recipe> fetchAll() {
        readFromFile();
        return recipes;
    }

    @Override
    public void deleteById(int recipeId) {
        readFromFile();
        Iterator<Recipe> it = recipes.iterator();
        while (it.hasNext()) {
            if (it.next().getRecipeId() == recipeId) {
                it.remove();
                writeToFile();
                System.out.println("Recipe deleted successfully");
                return;
            }
        }
        System.out.println("Recipe not found");
    }

    // Comparator → sort by name
    public List<Recipe> sortByName() {
    readFromFile();
    recipes.sort(Comparator.comparing(Recipe::getName));
    writeToFile();
    return recipes;
}


    // Comparable → sort by recipeId
    public List<Recipe> sortByRecipeId() {
    readFromFile();
    Collections.sort(recipes); // Comparable
    writeToFile();
    return recipes;
}

}
