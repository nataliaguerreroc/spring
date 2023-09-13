package project.music.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import project.music.spring.model.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    List<User> findByName(String name);

    Optional<User> findById(UUID id);

    void deleteById(UUID id);
}

