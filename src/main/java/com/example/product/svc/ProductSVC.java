package com.example.product.svc;

import com.example.product.dao.Product;

import java.util.List;
import java.util.Optional;

public interface ProductSVC {
  //등록
  Long save(Product product);
  //조회
  Optional<Product> findById(Long pId);
  //수정
  int update(Long pId, Product product);
  //삭제
  int delete(Long pId);
  //목록
  List<Product> findAll();

  boolean isExist(Long pId);
}
