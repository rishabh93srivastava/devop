package com.niit.dao;

import java.util.List;
import com.niit.model.Product;
import com.niit.model.Category;

public interface ProductDao 
{
  List<Product> getAllProducts();
  Product getProduct(int id);
  void deleteProduct(int id);
  void saveOrUpdateProduct(Product product);
  List<Category> getAllCategories();
}
