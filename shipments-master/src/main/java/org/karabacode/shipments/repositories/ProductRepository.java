package org.karabacode.shipments.repositories;


import org.karabacode.shipments.entities.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {

}