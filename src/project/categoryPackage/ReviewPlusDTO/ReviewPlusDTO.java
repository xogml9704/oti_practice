package project.categoryPackage.ReviewPlusDTO;

import lombok.Data;

@Data
public class ReviewPlusDTO {
    private String review_content;
    private int review_score;
    private String user_id;
    private int book_no;
}
