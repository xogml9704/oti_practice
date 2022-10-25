package project.categoryPackage;

import lombok.Data;

@Data
public class CategoryBoardDTO {
    private int book_no;
    private String book_name;
    private String book_publisher;
    private int book_price;
    private String a_max;
    private int r_avg;
    private int d_count;
}
