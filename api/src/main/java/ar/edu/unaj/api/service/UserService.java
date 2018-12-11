package ar.edu.unaj.api.service;

import ar.edu.unaj.api.beans.entity.User;
import ar.edu.unaj.api.exception.UserException;
import ar.edu.unaj.api.repository.UserRepository;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j
public class UserService implements GeneralService<User, String>{

    @Autowired
    private UserRepository userRepository;

    public UserService() {

        super ();
    }

    @Override
    public void create (User user) throws UserException {

        Boolean dniExist = this.userRepository.existsByDni(user.getDni());
        Boolean emailExist = this.userRepository.existsByEmail(user.getEmail());

        log.info("Checking user existing...");

        if (!dniExist || !emailExist) {
            this.userRepository.save(user);
        } else {
            log.error(String.format("Already existing object, %s", user));
            throw new UserException(100111);
        }
    }

    @Override
    public List<User> findAll () {

        return this.userRepository.findAll();
    }

    @Override
    public User findById(String id) throws UserException {

        Boolean exist = this.userRepository.existsById(id);

        log.info("Checking student existing...");

        if (exist) {
            log.debug("Returning student..");
            return this.userRepository.getOneById(id);
        } else {
            log.error(String.format("No existing student object with id: %s", id));
            throw new UserException(100222);
        }
    }

    public User findByDni(String dni) throws UserException {

        Boolean exist = this.userRepository.existsByDni(dni);

        log.info("Checking student existing...");

        if (exist) {
            log.debug("Returning student..");
            return this.userRepository.getOneByDni(dni);
        } else {
            log.error(String.format("No existing student object with DNI: %s", dni));
            throw new UserException(100222);
        }
    }

    @Override
    public void update (User user) throws UserException {

        Boolean dniExist = this.userRepository.existsByDni(user.getDni());
        Boolean emailExist = this.userRepository.existsByEmail(user.getEmail());

        log.info("Checking user existing...");

        if (dniExist && emailExist) {
            log.debug("Updating user..");
            this.userRepository.save(user);
        } else {
            log.error(String.format("No existing object, %s", user));
            throw new UserException(100222);
        }
    }

    @Override
    public void delete(String id) throws UserException {

        User user = this.userRepository.getOneById(id);

        if (user != null) {
            log.debug("Deleting user..");
            this.userRepository.delete(this.userRepository.getOneById(id));
        } else {
            log.error(String.format("No existing object with id %s", id));
            throw new UserException(100222);
        }
    }

    public void disable (User user) throws UserException {

        Boolean exist = this.userRepository.existsById(user.getId());

        if (user != null && exist) {

            if (user.getEnabled()) {

                user.setEnabled(false);
                log.debug("Updating disabled user..");
                this.userRepository.save(user);

            } else {
                log.info("The user is already disable");
            }

        } else {

            log.error(String.format("No existing object %s", user));
            throw new UserException(100222);

        }
    }
}
