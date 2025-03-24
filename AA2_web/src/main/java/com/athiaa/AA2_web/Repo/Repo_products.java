package com.athiaa.AA2_web.Repo;

import com.athiaa.AA2_web.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Repo_products extends JpaRepository<Product, Integer> {
}
