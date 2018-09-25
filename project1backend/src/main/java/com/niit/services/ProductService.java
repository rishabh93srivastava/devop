package com.niit.services;

import java.util.List;
import com.niit.model.Product;
import com.niit.model.Category;

public interface ProductService 
{
  List<Product> getAllProducts();
  Product getProduct(int id);
  void deleteProduct(int id); 
  void saveProduct(Product product);
  void updateProduct(Product product);
  List<Category> getAllCategories();
}
