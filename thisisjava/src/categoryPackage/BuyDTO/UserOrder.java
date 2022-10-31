package project.categoryPackage.BuyDTO;

import java.util.Date;

import lombok.Data;

@Data
public class UserOrder {
   private int order_no;
   private Date order_date;
   private String user_id;
   private String order_receivename;
   private String order_tel;
   private String order_address;
   private String order_memo;
   private char order_status;

}
