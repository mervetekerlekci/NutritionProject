package nutritionProject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NutritionDaoImp implements INutritionDao {
    Video video = new Video();
    BlogArticle blog = new BlogArticle();
    MedicalInfo medical = new MedicalInfo();

    @Override
    public void addVideoNutrition(NutritionModel nutrition) throws SQLException {

        String sql = "INSERT INTO nutrition_with_types (title, description, type, video_transcript, video_topic, video_source) VALUES (?, ?, ?, ?, ?, ?)";

        Connection connection = DbConnection.getInstance().getConnection();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            // NutritionModel'den alınan bilgiler
            statement.setString(1, nutrition.getTitle());
            statement.setString(2, nutrition.getDescription());
            statement.setString(3, nutrition.getType().toString()); // type alanının doğru bir şekilde yönetildiğini varsayıyorum

            // Video nesnesinden alınan bilgiler
            statement.setString(4, nutrition.getVideo().getTranscript());
            statement.setString(5, nutrition.getVideo().getTopic());
            statement.setString(6, nutrition.getVideo().getSource());

            // INSERT sorgusu çalıştırıldı
            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Nutrition information with video successfully added.");
            } else {
                System.out.println("An error occurred. Nutrition information could not be added.");
            }
        }
    }

    @Override
    public void addBlogNutrition(NutritionModel nutrition) throws SQLException {
        String sql = "INSERT INTO nutrition_with_types (title, description, type, blog_title, blog_comments) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DbConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, nutrition.getTitle());
            statement.setString(2, nutrition.getDescription());
            statement.setString(3, nutrition.getType().toString()); // typeToAdd değişkeninizin değeri
            statement.setString(4, nutrition.getBlogArticle().getTitle());
            statement.setString(5, nutrition.getBlogArticle().getComments());

            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Nutrition and blog article information successfully added.");
            } else {
                System.out.println("An error occurred. Information could not be added.");
            }
        }
    }

    @Override
    public void addMedicalNutrition(NutritionModel nutrition) throws SQLException {
        String sql = "INSERT INTO nutrition_with_types (title, description, type, medical_title, medical_text, medical_type) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DbConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, nutrition.getTitle());
            statement.setString(2, nutrition.getDescription());
            statement.setString(3, nutrition.getType().toString()); // typeToAdd değişkeninizin değeri
            statement.setString(4, nutrition.getMedicalInfo().getTitle());
            statement.setString(5, nutrition.getMedicalInfo().getText());
            statement.setString(6, nutrition.getMedicalInfo().getType());

            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Nutrition and medical information successfully added.");
            } else {
                System.out.println("An error occurred. Information could not be added.");
            }
        }
    }


    @Override
    public NutritionModel getNutrition(int id) {

        NutritionModel nutrition = null;
        String sql = "SELECT * FROM nutrition_with_types WHERE id = ?";

        try (Connection conn = DbConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                nutrition = new NutritionModel();
                nutrition.setId(rs.getInt("id"));
                nutrition.setTitle(rs.getString("title"));
                nutrition.setDescription(rs.getString("description"));
                nutrition.setType(NutritionModel.NutritionType.valueOf(rs.getString("type")));

                video.setTranscript(rs.getString("video_transcript"));
                video.setTopic(rs.getString("video_topic"));
                video.setSource(rs.getString("video_source"));
                medical.setTitle(rs.getString("medical_title"));
                medical.setText(rs.getString("medical_text"));
                medical.setType(rs.getString("medical_type"));
                blog.setTitle(rs.getString("blog_title"));
                blog.setComments(rs.getString("blog_comments"));

                nutrition.setVideo(video);
                nutrition.setBlogArticle(blog);
                nutrition.setMedicalInfo(medical);

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return nutrition;
    }

    @Override
    public List<NutritionModel> getAllNutrition() {
        List<NutritionModel> nutritionList = new ArrayList<>();
        String sql = "SELECT * FROM nutrition_with_types";

        try (Connection conn = DbConnection.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                NutritionModel nutrition = new NutritionModel();

                nutrition.setId(rs.getInt("id"));
                nutrition.setTitle(rs.getString("title"));
                nutrition.setDescription(rs.getString("description"));
                nutrition.setType(NutritionModel.NutritionType.valueOf(rs.getString("type")));

                video.setTranscript(rs.getString("video_transcript"));
                video.setTopic(rs.getString("video_topic"));
                video.setSource(rs.getString("video_source"));

                medical.setTitle(rs.getString("medical_title"));
                medical.setText(rs.getString("medical_text"));
                medical.setType(rs.getString("medical_type"));

                blog.setTitle(rs.getString("blog_title"));
                blog.setComments(rs.getString("blog_comments"));

                nutrition.setVideo(video);
                nutrition.setMedicalInfo(medical);
                nutrition.setBlogArticle(blog);

                nutritionList.add(nutrition);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return nutritionList;
    }

    @Override
    public void updateNutrition(NutritionModel nutrition) {
        // SQL sorgusunu video, medical ve blog bilgilerini de güncelleyecek şekilde genişletildi
        String sql = "UPDATE nutrition_with_types SET title = ?, description = ?, type = ?, video_transcript = ?, video_topic = ?, video_source = ?, medical_title = ?, medical_text = ?, medical_type = ?, blog_title = ?, blog_comments = ? WHERE id = ?";

        try (Connection conn = DbConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nutrition.getTitle());
            pstmt.setString(2, nutrition.getDescription());
            pstmt.setString(3, nutrition.getType().toString());
            pstmt.setString(4, nutrition.getVideo().getTranscript());
            pstmt.setString(5, nutrition.getVideo().getTopic());
            pstmt.setString(6, nutrition.getVideo().getSource());
            pstmt.setString(7, nutrition.getMedicalInfo().getTitle());
            pstmt.setString(8, nutrition.getMedicalInfo().getText());
            pstmt.setString(9, nutrition.getMedicalInfo().getType());
            pstmt.setString(10, nutrition.getBlogArticle().getTitle());
            pstmt.setString(11, nutrition.getBlogArticle().getComments());
            pstmt.setInt(12, nutrition.getId());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Nutrition fact updated successfully.");
            } else {
                System.out.println("An error occurred. No nutrition fact was updated.");
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }


    public void deleteNutrition(int id) {
        String sql = "DELETE FROM nutrition_with_types WHERE id = ?";

        try (Connection conn = DbConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Nutrition fact deleted successfully.");
            } else {
                System.out.println("An error occurred. No nutrition fact was deleted, possibly because it does not exist.");
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}
