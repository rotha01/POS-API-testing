package co.istad.demproductapisimple.repository;

import co.istad.demproductapisimple.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Repository
public class ProductRepositoryOld {
//    private final List<Product> products = new ArrayList<>(){{
//        add(new Product(1001,"Coca","Gash Drink",1.1f,1));
//        add(new Product(1002,"Fanta","Sweet Drink",1.2f,1));
//        add(new Product(1003,"Cambodia","Water",1.3f,1));
//        add(new Product(1004,"Vital","Water",1.4f,1));
//    }};
//
//    //get all
//    public List<Product> getAllProduct(){
//        return products;
//    }
//
//    //create
//    public Product createProduct(Product product){
//        products.add(product);
//        return product;
//    }
//
//    //find by id
//    public Product findProductById(int id){
//        return products.stream().filter(product -> product.getId() == id)
//                .findFirst()
//                .orElseThrow(()->new NoSuchElementException("Product not found!"));
//    }
//
//    //delete
//    public boolean deleteProduct(Integer id){
//        return products.removeIf(product -> product.getId()==id);
//    }
//
//    //update
//    public Product updateProduct(Product updateProduct){
//            for(int i=0;i<products.size();i++){
//                var product = products.get(i);
//                if(products.get(i).getId()==updateProduct.getId()){
//                    products.set(i,updateProduct);
//                }
//            }
//        return null;
//    }


}
