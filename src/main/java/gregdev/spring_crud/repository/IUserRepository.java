package gregdev.spring_crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import gregdev.spring_crud.model.UserModel;

public interface IUserRepository extends JpaRepository<UserModel, Integer> {

}
