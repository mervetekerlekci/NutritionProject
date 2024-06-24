package nutritionProject;

import java.sql.SQLException;
import java.util.List;

public interface INutritionDao {
    void addVideoNutrition(NutritionModel nutrition) throws SQLException;
    void addBlogNutrition(NutritionModel nutrition) throws SQLException;
    void addMedicalNutrition(NutritionModel nutrition) throws SQLException;
    NutritionModel getNutrition(int id);
    List<NutritionModel> getAllNutrition();
    void updateNutrition(NutritionModel nutrition);
    void deleteNutrition(int id);
}
