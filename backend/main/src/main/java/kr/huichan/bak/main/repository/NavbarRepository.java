package kr.huichan.bak.main.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import kr.huichan.bak.main.dto.NavbarMenu;

@Repository
public interface NavbarRepository extends MongoRepository<NavbarMenu, ObjectId> {
    
}
