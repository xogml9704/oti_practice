package project.categoryPackage.CategoryDTO;

import java.util.ArrayList;

import lombok.Data;
import project.categoryPackage.IntegrationDTO.AuthorDTO;
import project.categoryPackage.IntegrationDTO.HashDTO;

@Data
public class CategoryBoardDTO {
    private int book_no;
    private String book_name;
    private String book_publisher;
    private int book_price;
    private int reviews_avg;
    private ArrayList<AuthorDTO> author_name;
    private ArrayList<HashDTO> hash_id;
}
