package Dto;

import javax.annotation.processing.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.Data;

@Entity
@Data
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
  private int item_id;
  private String item_name;
  private double item_price;
  private String food_type;
  private int quantity;
  @Lob
  private byte[] picture;
	

}
