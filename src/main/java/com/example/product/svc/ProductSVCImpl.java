package com.example.product.svc;

import com.example.product.dao.Product;
import com.example.product.dao.ProductDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductSVCImpl implements ProductSVC  {

  private final ProductDAO productDAO;

  @Override
  public Long save(Product product) {
    return productDAO.save(product);
  }

  @Override
  public Optional<Product> findById(Long pId) {
    return productDAO.findById(pId);
  }

  @Override
  public int update(Long pId, Product product) {
    return productDAO.update(pId, product);
  }

  @Override
  public int delete(Long pId) {
    return productDAO.delete(pId);
  }

  @Override
  public List<Product> findAll() {
    return productDAO.findAll();
  }

  @Override
  public boolean isExist(Long pId) {
    return productDAO.isExist(pId);
  }
  }

