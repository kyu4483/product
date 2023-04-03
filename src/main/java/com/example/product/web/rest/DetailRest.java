package com.example.product.web.rest;

import lombok.Data;

@Data
public class DetailRest {
  private Long pId;
  private String pname;
  private Long quantity;
  private Long price;
}
