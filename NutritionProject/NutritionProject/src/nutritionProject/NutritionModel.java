package nutritionProject;

public class NutritionModel {
    private int id;
    private String title;
    private String description;
    private NutritionType type;
    private Video video;
    private BlogArticle blogArticle;
    private MedicalInfo medicalInfo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public NutritionType getType() {
        return type;
    }

    public void setType(NutritionType type) {
        this.type = type;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public BlogArticle getBlogArticle() {
        return blogArticle;
    }

    public void setBlogArticle(BlogArticle blogArticle) {
        this.blogArticle = blogArticle;
    }

    public MedicalInfo getMedicalInfo() {
        return medicalInfo;
    }

    public void setMedicalInfo(MedicalInfo medicalInfo) {
        this.medicalInfo = medicalInfo;
    }

    enum NutritionType {
        VIDEO,
        BLOG_ARTICLE,
        MEDICAL_INFO
    }
}


