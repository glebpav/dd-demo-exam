package ru.mephi.dddemoexam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mephi.dddemoexam.model.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByAgeGreaterThanEqualOrderByFirstNameAsc(Integer age);

}
