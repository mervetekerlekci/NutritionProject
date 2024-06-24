package nutritionProject;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        NutritionDaoImp obj = new NutritionDaoImp();
        boolean exit = false;

        while (!exit) {
            System.out.println("\nNutrition Management System");
            System.out.println("1. Add a video Nutrition Record");
            System.out.println("2. Display a Single Nutrition Record");
            System.out.println("3. List All Nutrition Records");
            System.out.println("4. Delete a Nutrition Record");
            System.out.println("5. Update Nutrition Record");
            System.out.println("6. Exit");
            System.out.print("Please choose an option: ");

            int choice = NutritionHelper.getUserInputAsInt(scanner);

            switch (choice) {
                case 1:

                    System.out.print("Please enter the type of the nutrition, You can write only VIDEO, BLOG_ARTICLE or MEDICAL_INFO: ");
                    NutritionModel.NutritionType typeToAdd = NutritionModel.NutritionType.valueOf(scanner.nextLine());

                    switch (typeToAdd){
                        case VIDEO:
                            Video video = new Video();
                            System.out.print("Please enter the title of the video: ");
                            String titleToAdd = NutritionHelper.getUserInputAsStringAndOnlyLetters(scanner);
                            System.out.print("Please enter the description of the vide: ");
                            String descriptionToAdd = NutritionHelper.getUserInputAsStringAndOnlyLetters(scanner);
                            System.out.print("Please enter the transcript of the video: ");
                            String transcript = NutritionHelper.getUserInputAsStringAndOnlyLetters(scanner);
                            System.out.print("Please enter the topic of the video: ");
                            String topic = NutritionHelper.getUserInputAsStringAndOnlyLetters(scanner);
                            System.out.print("Please enter the source of the video: ");
                            String source = NutritionHelper.getUserInputAsStringAndOnlyLetters(scanner);

                            NutritionModel addvideoModel = new NutritionModel();
                            addvideoModel.setTitle(titleToAdd);
                            addvideoModel.setDescription(descriptionToAdd);
                            addvideoModel.setType(typeToAdd);
                            video.setTranscript(transcript);
                            video.setSource(source);
                            video.setTopic(topic);
                            addvideoModel.setVideo(video);

                            obj.addVideoNutrition(addvideoModel);
                            break;

                        case BLOG_ARTICLE:
                            BlogArticle blogArticle = new BlogArticle();
                            System.out.print("Please enter the title of the blog: ");
                            String title = NutritionHelper.getUserInputAsStringAndOnlyLetters(scanner);
                            System.out.print("Please enter the description of the blog: ");
                            String blog_desc = NutritionHelper.getUserInputAsStringAndOnlyLetters(scanner);
                            System.out.print("Please enter the title of the blog: ");
                            String blog_title = NutritionHelper.getUserInputAsStringAndOnlyLetters(scanner);
                            System.out.print("Please enter the comment of the blog: ");
                            String blog_comment = NutritionHelper.getUserInputAsStringAndOnlyLetters(scanner);

                            NutritionModel addBlogModel = new NutritionModel();
                            addBlogModel.setTitle(title);
                            addBlogModel.setDescription(blog_desc);
                            addBlogModel.setType(typeToAdd);
                            blogArticle.setTitle(blog_title);
                            blogArticle.setComments(blog_comment);
                            addBlogModel.setBlogArticle(blogArticle);

                            obj.addBlogNutrition(addBlogModel);
                            break;

                        case MEDICAL_INFO:
                            MedicalInfo medicalInfo = new MedicalInfo();
                            System.out.print("Please enter the title of the medical: ");
                            String mtitle = NutritionHelper.getUserInputAsStringAndOnlyLetters(scanner);
                            System.out.print("Please enter the description of the medical: ");
                            String medical_desc = NutritionHelper.getUserInputAsStringAndOnlyLetters(scanner);
                            System.out.print("Please enter the medical title of the medical: ");
                            String medical_title = NutritionHelper.getUserInputAsStringAndOnlyLetters(scanner);
                            System.out.print("Please enter the text of the medical: ");
                            String medical_text = NutritionHelper.getUserInputAsStringAndOnlyLetters(scanner);
                            System.out.print("Please enter the medical type of the medical: ");
                            String medical_type = NutritionHelper.getUserInputAsStringAndOnlyLetters(scanner);

                            NutritionModel addMedicalModel = new NutritionModel();
                            addMedicalModel.setTitle(mtitle);
                            addMedicalModel.setDescription(medical_desc);
                            addMedicalModel.setType(typeToAdd);
                            medicalInfo.setText(medical_text);
                            medicalInfo.setTitle(medical_title);
                            medicalInfo.setType(medical_type);
                            addMedicalModel.setMedicalInfo(medicalInfo);

                            obj.addMedicalNutrition(addMedicalModel);
                            break;
                    }

                    break;

                case 2:

                    System.out.print("Please enter the id of the nutrition to list: ");
                    int idToDisplay = NutritionHelper.getUserInputAsInt(scanner);
                    NutritionModel nutritionToDisplay = obj.getNutrition(idToDisplay);

                    if (nutritionToDisplay != null) {
                        System.out.println("ID: " + nutritionToDisplay.getId());
                        System.out.println("Title: " + nutritionToDisplay.getTitle());
                        System.out.println("Description: " + nutritionToDisplay.getDescription());
                        System.out.println("Type: " + nutritionToDisplay.getType());

                        Video videoInfo = nutritionToDisplay.getVideo();
                        if (videoInfo != null) {
                            System.out.println("Video Transcript: " + videoInfo.getTranscript());
                            System.out.println("Video Topic: " + videoInfo.getTopic());
                            System.out.println("Video Source: " + videoInfo.getSource());
                        }

                        MedicalInfo medicalInfo = nutritionToDisplay.getMedicalInfo();
                        if (medicalInfo != null) {
                            System.out.println("Medical Title: " + medicalInfo.getTitle());
                            System.out.println("Medical Text: " + medicalInfo.getText());
                            System.out.println("Medical Type: " + medicalInfo.getType());
                        }

                        BlogArticle blogInfo = nutritionToDisplay.getBlogArticle();
                        if (blogInfo != null) {
                            System.out.println("Blog Title: " + blogInfo.getTitle());
                            System.out.println("Blog Comments: " + blogInfo.getComments());
                        }

                    } else {
                        System.out.println("Nutrition information not found for ID: " + idToDisplay);
                    }
                    break;


                case 3:

                    List<NutritionModel> nutritionList = obj.getAllNutrition();
                    if (!nutritionList.isEmpty()) {
                        for (NutritionModel nutrition : nutritionList) {
                            System.out.println("ID: " + nutrition.getId());
                            System.out.println("Title: " + nutrition.getTitle());
                            System.out.println("Description: " + nutrition.getDescription());
                            System.out.println("Type: " + nutrition.getType());

                            // Video bilgilerini yazdır
                            Video video = nutrition.getVideo();
                            if (video != null) {
                                System.out.println("Video Transcript: " + video.getTranscript());
                                System.out.println("Video Topic: " + video.getTopic());
                                System.out.println("Video Source: " + video.getSource());
                            }

                            // Medical bilgilerini yazdır
                            MedicalInfo medical = nutrition.getMedicalInfo();
                            if (medical != null) {
                                System.out.println("Medical Title: " + medical.getTitle());
                                System.out.println("Medical Text: " + medical.getText());
                                System.out.println("Medical Type: " + medical.getType());
                            }

                            // Blog bilgilerini yazdır
                            BlogArticle blog = nutrition.getBlogArticle();
                            if (blog != null) {
                                System.out.println("Blog Title: " + blog.getTitle());
                                System.out.println("Blog Comments: " + blog.getComments());
                            }

                            System.out.println("-----------------------------------");
                        }
                    } else {
                        System.out.println("No nutrition information available.");
                    }
                    break;

                case 4:

                    System.out.print("Please enter the id of the nutrition to delete: ");
                    int idToDelete = NutritionHelper.getUserInputAsInt(scanner);
                    obj.deleteNutrition(idToDelete);
                    break;

                case 5:

                    System.out.print("Enter the ID of the nutrition fact to update: ");
                    int idToUpdate = NutritionHelper.getUserInputAsInt(scanner);

                    System.out.print("Enter new title: ");
                    String newTitle = NutritionHelper.getUserInputAsStringAndOnlyLetters(scanner);

                    System.out.print("Enter new description: ");
                    String newDescription = NutritionHelper.getUserInputAsStringAndOnlyLetters(scanner);

                    System.out.print("Enter new type: ");
                    NutritionModel.NutritionType newType = NutritionModel.NutritionType.valueOf(scanner.nextLine());

                    System.out.print("Enter new video transcript: ");
                    String newVideoTranscript =  NutritionHelper.getUserInputAsStringAndOnlyLetters(scanner);

                    System.out.print("Enter new video topic: ");
                    String newVideoTopic =  NutritionHelper.getUserInputAsStringAndOnlyLetters(scanner);

                    System.out.print("Enter new video source: ");
                    String newVideoSource = NutritionHelper.getUserInputAsStringAndOnlyLetters(scanner);

                    System.out.print("Enter new medical title: ");
                    String newMedicalTitle =  NutritionHelper.getUserInputAsStringAndOnlyLetters(scanner);

                    System.out.print("Enter new medical text: ");
                    String newMedicalText =  NutritionHelper.getUserInputAsStringAndOnlyLetters(scanner);

                    System.out.print("Enter new medical type: ");
                    String newMedicalType =  NutritionHelper.getUserInputAsStringAndOnlyLetters(scanner);

                    System.out.print("Enter new blog title: ");
                    String newBlogTitle =  NutritionHelper.getUserInputAsStringAndOnlyLetters(scanner);

                    System.out.print("Enter new blog comments: ");
                    String newBlogComments =  NutritionHelper.getUserInputAsStringAndOnlyLetters(scanner);

                    NutritionModel updateModel = new NutritionModel();
                    updateModel.setId(idToUpdate);
                    updateModel.setTitle(newTitle);
                    updateModel.setDescription(newDescription);
                    updateModel.setType(newType);

                    Video video = new Video();
                    video.setTranscript(newVideoTranscript);
                    video.setTopic(newVideoTopic);
                    video.setSource(newVideoSource);
                    updateModel.setVideo(video);

                    MedicalInfo medical = new MedicalInfo();
                    medical.setTitle(newMedicalTitle);
                    medical.setText(newMedicalText);
                    medical.setType(newMedicalType);
                    updateModel.setMedicalInfo(medical);

                    BlogArticle blog = new BlogArticle();
                    blog.setTitle(newBlogTitle);
                    blog.setComments(newBlogComments);
                    updateModel.setBlogArticle(blog);

                    obj.updateNutrition(updateModel);
                    break;


                case 6:

                    exit = true;
                    System.out.println("Exiting the program.");
                    break;

                default:
                    System.out.println("Invalid option. Please enter a valid one.");
            }
        }

        scanner.close();
    }
}
