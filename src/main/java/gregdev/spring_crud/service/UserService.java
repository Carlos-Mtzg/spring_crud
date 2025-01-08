package gregdev.spring_crud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import gregdev.spring_crud.dto.UserDto;
import gregdev.spring_crud.model.UserModel;
import gregdev.spring_crud.repository.IUserRepository;
import jakarta.transaction.Transactional;

@Service
@Primary
@Transactional
public class UserService {
    private final IUserRepository repository;

    UserService(IUserRepository repository) {
        this.repository = repository;
    }

    public List<UserModel> getAll() {
        return this.repository.findAll(Sort.by("id").descending());
    }

    public UserModel findById(Integer id) {
        Optional<UserModel> optional = repository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    public void save(UserModel user) {
        this.repository.save(user);
    }

    public void update(UserModel user, UserDto userDto) {
        if (userDto.getName() != null) {
            user.setName(userDto.getName());
        }
        if (userDto.getLastname() != null) {
            user.setLastname(userDto.getLastname());
        }
        if (userDto.getEmail() != null) {
            user.setEmail(userDto.getEmail());
        }
        if (userDto.getPhone() != null) {
            user.setPhone(userDto.getPhone());
        }
    }

    public void delete(Integer id) {
        this.repository.deleteById(id);
    }
}
