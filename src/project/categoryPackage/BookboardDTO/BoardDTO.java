package project.categoryPackage.BookboardDTO;

import java.util.ArrayList;

import lombok.Data;

@Data
public class BoardDTO {
    private String book_name;
    private String book_detail;
    private String book_publisher;
    private int book_price;
    private String book_store;
    private int book_page;
    private String book_lang;
    private ArrayList<WriterDTO> author;
    private ArrayList<ReviewDTO> review;
}
