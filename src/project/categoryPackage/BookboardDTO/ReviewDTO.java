package project.categoryPackage.BookboardDTO;

import lombok.Data;

@Data
public class ReviewDTO {
    private String user_id;
    private String review_date;
    private String review_content;
    private int review_score;
}
