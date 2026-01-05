import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Recipe implements Serializable, Comparable<Recipe> {

    private int recipeId;
    private String name;
    private List<String> ingredients = new ArrayList<>();
    private int prepTime;

    public Recipe() {}

    // Getters & Setters
    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public int getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(int prepTime) {
        this.prepTime = prepTime;
    }

    // Comparable → sort by recipeId
    @Override
    public int compareTo(Recipe r) {
        return Integer.compare(this.recipeId, r.recipeId);
    }

    @Override
    public String toString() {
        return "Recipe ID: " + recipeId +
                ", Name: " + name +
                ", Ingredients: " + ingredients +
                ", Prep Time: " + prepTime + " mins";
    }
}
