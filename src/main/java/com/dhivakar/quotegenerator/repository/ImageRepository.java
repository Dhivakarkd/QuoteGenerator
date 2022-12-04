package com.dhivakar.quotegenerator.repository;

import com.dhivakar.quotegenerator.model.ImageDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ImageRepository extends JpaRepository<ImageDO, Integer> {


}
