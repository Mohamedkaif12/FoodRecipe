import java.util.List;

public interface RecipeDAO {
    void add(Recipe recipe);
    void update(Recipe recipe);
    List<Recipe> fetchAll();
    void deleteById(int recipeId);
}
