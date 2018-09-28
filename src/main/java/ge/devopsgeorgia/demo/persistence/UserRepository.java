package ge.devopsgeorgia.demo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ge.devopsgeorgia.demo.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {}