package com.ikea.irecover.inventorymanagement.domain.articleinventory.repository;

import com.ikea.irecover.inventorymanagement.domain.articleinventory.entity.ArticlePartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticlePartRepository extends JpaRepository<ArticlePartEntity, String> {
}
