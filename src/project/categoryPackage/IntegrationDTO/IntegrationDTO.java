package project.categoryPackage.IntegrationDTO;

import java.util.ArrayList;

import lombok.Data;

@Data
public class IntegrationDTO {
    private int book_no;
    private String book_name;
    private String book_publisher;
    private int book_price;
    private int reviews_avg;
    private ArrayList<AuthorDTO> author_name;
    private ArrayList<HashDTO> hash_id;
}
