package com.fundamentosplatzi.springboot.fundamentos;

import com.fundamentosplatzi.springboot.fundamentos.bean.MyBean;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyBeanWithDependency;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyBeanWithProperties;
import com.fundamentosplatzi.springboot.fundamentos.component.ComponentDependency;
import com.fundamentosplatzi.springboot.fundamentos.entity.User;
import com.fundamentosplatzi.springboot.fundamentos.pojo.UserPojo;
import com.fundamentosplatzi.springboot.fundamentos.repository.UserRepository;
import com.fundamentosplatzi.springboot.fundamentos.service.UserService;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {

    private final Log LOGGER = LogFactory.getLog(FundamentosApplication.class);
    private ComponentDependency componentDependency;
    private MyBean myBean;
    private MyBeanWithDependency myBeanWithDependency;
    private MyBeanWithProperties myBeanWithProperties;
    private UserPojo userPojo;
    private UserRepository userRepository;

    private UserService userService;

    public FundamentosApplication(
            @Qualifier("componentTwoImplement") ComponentDependency componentDependency,
            MyBean myBean, MyBeanWithDependency myBeanWithDependency, MyBeanWithProperties myBeanWithProperties, UserPojo userPojo,
            UserRepository userRepository, UserService userService) {
        this.componentDependency = componentDependency;
        this.myBean = myBean;
        this.myBeanWithDependency = myBeanWithDependency;
        this.myBeanWithProperties = myBeanWithProperties;
        this.userPojo = userPojo;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    public static void main(String[] args) {
        SpringApplication.run(FundamentosApplication.class, args);
    }

    @Override
    public void run(String... args) {
        //ejemplosAnteriores();
        saveUserInDataBase();
        GetInformationJpqlFromUser();
        saveWithErrorTransaccional();
    }

    private void GetInformationJpqlFromUser() {
//        LOGGER.info("Usuario con meotodo finByUserEmail " + userRepository.findByUserEmail("jhon@gmail.com")
//                .orElseThrow(() -> new RuntimeException("No se encontro el usuario")));
//
//        userRepository.findAndSort("user", Sort.by("id").descending())
//                .stream()
//                .forEach(user -> LOGGER.info("user con metodo sort " + user));
//
//        userRepository.findByName("Jhon")
//                .stream()
//                .forEach(user -> LOGGER.info("Usuario con query method " + user));

//       LOGGER.info("usuario finByNameAndEmail "+userRepository.findByEmailAndName("daniel@gmail.com","Daniel")
//                .orElseThrow(()->new RuntimeException("Usuario no encotrado")));

//        userRepository.findByNameLike("%user%")
//                .stream()
//                .forEach(user -> LOGGER.info("User byNameLike " + user));

//        userRepository.findByNameOrEmail("user4","user5@gmail.com")
//                .stream()
//                .forEach(user -> LOGGER.info("User byNameOrEmail " + user));

//        userRepository.findByBirthDateBetween(LocalDate.of(2021, 02, 20),
//                        LocalDate.of(2021, 05, 20))
//                .stream()
//                .forEach(user -> LOGGER.info("User findByBirthDateBetween " + user));

//        userRepository.findByNameLikeOrderByIdDesc("%user%")
//                .stream()
//                .forEach(user -> LOGGER.info("User findByNameLikeOrderByIdDesc " + user));
//        userRepository.findByNameContainingOrderByIdDesc("user")
//                .stream()
//                .forEach(user -> LOGGER.info("User findByNameContainOrderByIdDesc " + user));
//        LOGGER.info("User en getAllByBirthDateAndEmail " + userRepository.getAllByBirthDateAndEmail(LocalDate.of(2021, 02, 20), "jhon@gmail.com")
//                .orElseThrow(() -> new RuntimeException("No se encontraron en getAllByBirthDateAndEmail")));
    }

    private void saveWithErrorTransaccional() {
        User test1 = new User("TestTransactional1", "TestTransactional1@domain.com", LocalDate.now());
        User test2 = new User("TestTransactional2", "TestTransactional2@domain.com", LocalDate.now());
        User test3 = new User("TestTransactional3", "TestTransactional3@domain.com", LocalDate.now());
        User test4 = new User("TestTransactional4", "TestTransactional4@domain.com", LocalDate.now());

        List<User> users = Arrays.asList(test1, test2, test3, test4);
        try {
            this.userService.saveTransactional(users);


        } catch (Exception e) {
            LOGGER.error("Este es un error del tip transaccional");
        }
        this.userService.getAllUsers().stream().forEach(LOGGER::info);
    }

    private void saveUserInDataBase() {
        User user1 = new User("Jhon", "jhon@gmail.com", LocalDate.of(2021, 02, 20));
        User user2 = new User("Jhon", "pedro@gmail.com", LocalDate.of(2021, 05, 17));
        User user3 = new User("Daniel", "daniel@gmail.com", LocalDate.of(2021, 05, 12));
        User user4 = new User("user4", "user4@gmail.com", LocalDate.of(2021, 07, 2));
        User user5 = new User("user5", "user5@gmail.com", LocalDate.of(2021, 07, 4));
        User user6 = new User("user6", "user6@gmail.com", LocalDate.of(2021, 01, 30));
        User user7 = new User("user7", "user7@gmail.com", LocalDate.of(2021, 03, 28));
        User user8 = new User("user8", "user8@gmail.com", LocalDate.of(2021, 02, 28));
        User user9 = new User("user9", "user9@gmail.com", LocalDate.of(2021, 10, 24));
        User user10 = new User("user10", "user10@gmail.com", LocalDate.of(2021, 10, 8));
        User user11 = new User("user11", "user11@gmail.com", LocalDate.of(2021, 11, 17));
        User user12 = new User("user12", "user12@gmail.com", LocalDate.of(2021, 12, 19));
        List<User> list = Arrays.asList(user1, user2, user3, user4, user5, user6, user7, user8, user9, user10, user11, user12);
        list.stream().forEach(userRepository::save);
    }

    public void ejemplosAnteriores() {
        myBeanWithDependency.printWithDependency();
        componentDependency.saludar();
        myBean.print();
        System.out.println(myBeanWithProperties.function());
        System.out.println(userPojo.getEmail());
        try {
            int value = 10 / 0;
            LOGGER.info("Mi valor " + value);
        } catch (Exception e) {
            LOGGER.error("Un error al dividir by cero " + e.getStackTrace());
        }
    }
}
