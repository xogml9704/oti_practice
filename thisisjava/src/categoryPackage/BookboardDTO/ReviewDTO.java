package project.categoryPackage.BookboardDTO;

import java.util.Date;

import lombok.Data;

@Data
public class ReviewDTO {
    private String user_id;
    private Date review_date;
    private String review_content;
    private int review_score;
}
