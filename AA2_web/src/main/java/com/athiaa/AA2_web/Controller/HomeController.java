package com.athiaa.AA2_web.Controller;


import com.athiaa.AA2_web.Model.Product;
import com.athiaa.AA2_web.Service.Products;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;

//Handle Client request
@RestController
@CrossOrigin
public class HomeController {

    @Autowired
    private Products service;

    @RequestMapping("/")
    public String home(){
        return "Welcome to the HomePage -AA";
    }
    @RequestMapping("/aboutUs")
    public String about(){
        return "About Us: Athithyan AA - 65281";
    }

    //HTTP...req
    @GetMapping("/products")
    public List<Product> getProduct(){
        return service.toProduct();
    }

    @GetMapping("/products/{Id}")
    public Product getProductById(@PathVariable int Id){
        return service.getProById(Id);
    }

  //  @PostMapping("/products")
  //  public String addProduct(@RequestBody Product p){
   //     return service.addProduct(p);
//    }
//    @PutMapping("/products")
//    public String updateProduct(@RequestBody Product p){
//        return service.updateProduct(p);
//    }
    @DeleteMapping("/products/{Id}")
    public String deleteProduct(@PathVariable int Id){
        return service.deleteProById(Id);
    }

    @PostMapping("/products")
    public ResponseEntity<?> addProduct(@RequestPart("p") Product p, @RequestPart("imageFile") MultipartFile imageFile) {
        try {
            // Save the product with image data
            Product savedProduct = service.addProduct(p, imageFile);

            String base64Image = Base64.getEncoder().encodeToString(savedProduct.getImageData());

            // Return the saved product with the Base64 image data
            savedProduct.setImageData(base64Image.getBytes()); // Set the Base64 string in the product object

            return ResponseEntity.status(201).body(savedProduct);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("{\"error\": \"Error uploading product: " + e.getMessage() + "\"}");
        }
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<?> updateProduct(
            @PathVariable("id") int id,
            @RequestPart("p") Product updatedProduct,
            @RequestPart(value = "imageFile", required = false) MultipartFile imageFile) {
        try {
            // Ensure the product exists
            Product existingProduct = service.getProById(id);
            if (existingProduct == null) {
                return ResponseEntity.status(404).body("{\"error\": \"Product not found.\"}");
            }

            // Update the product details
            existingProduct.setName(updatedProduct.getName());
            existingProduct.setPrice(updatedProduct.getPrice());

            // Update the image if a new one is provided
            if (imageFile != null && !imageFile.isEmpty()) {
                existingProduct = service.updateProductImage(existingProduct, imageFile);
            }

            // Save the updated product
            Product savedProduct = service.updateProduct(existingProduct);

            // Convert image data to Base64 if image data exists
            if (savedProduct.getImageData() != null) {
                String base64Image = Base64.getEncoder().encodeToString(savedProduct.getImageData());
                savedProduct.setImageData(base64Image.getBytes());
            }
            return ResponseEntity.ok(savedProduct);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("{\"error\": \"Error updating product: " + e.getMessage() + "\"}");
        }
    }

}
