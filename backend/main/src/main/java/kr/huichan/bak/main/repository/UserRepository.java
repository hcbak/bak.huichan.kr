package kr.huichan.bak.main.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import kr.huichan.bak.main.document.User;

@Repository
public interface UserRepository extends MongoRepository<User, ObjectId>{

    User findByUsername(String username);

}
