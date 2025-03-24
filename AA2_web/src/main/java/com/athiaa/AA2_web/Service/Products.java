package com.athiaa.AA2_web.Service;

import com.athiaa.AA2_web.Model.Product;
import com.athiaa.AA2_web.Repo.Repo_products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class Products {
    @Autowired
    Repo_products repo;
//    List<Product> l = new ArrayList<>(Arrays.asList(
//      new Product(1,"Doggy",1011),
//      new Product(2,"Doll",901)
//    ));
    public List<Product> toProduct(){
        return repo.findAll();
    }

    public Product getProById(int id) {
//        for(Product p : l){
//            if(p.getId() == id){
//                return p;
//            }
//        }
//        return new Product(-1,"No Such Product Available",-1);
        return repo.findById(id).orElse(null);
    }

    public String addProduct(Product p) {
//        System.out.println(p);
//        l.add(p);
        repo.save(p);
        return "Successfully Added!...";
    }
    public Product addProduct(Product p, MultipartFile imageFile) throws IOException {
        p.setImageName(imageFile.getOriginalFilename());
        p.setImageType(imageFile.getContentType());
        p.getImageData(imageFile.getBytes());
        repo.save(p);
        return p;
    }

//    public String updateProduct(Product pq) {
////        for(Product p : l){
////            if(p.getId() == pq.getId()){
////                p.setName(pq.getName());
////                p.setPrice(pq.getPrice());
////                return "Updated Successfully...";
////            }
////        }
//        repo.save(pq);
//        return "Updated Successfully...";
//    }

    public String deleteProById(int id) {
//        for(Product p : l){
//            if(p.getId() == id){
//                l.remove(p);
//                return "Deleted Successfully..";
//            }
//        }
        repo.deleteById(id);
        return "Deleted Successfully..";
    }

    public Product updateProductImage(Product existingProduct, MultipartFile imageFile) {
        try {
            // Convert the MultipartFile to byte array
            byte[] imageData = imageFile.getBytes();

            // Set the new image data in the existing product
            existingProduct.setImageData(imageData);

            return existingProduct; // Return the product with updated image
        } catch (IOException e) {
            throw new RuntimeException("Error processing the image file: " + e.getMessage());
        }
    }
    public Product updateProduct(Product product) {
        return repo.save(product);
    }



}
