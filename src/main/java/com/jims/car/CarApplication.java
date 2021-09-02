package com.jims.car;

import com.jims.car.data.entity.Car;
import com.jims.car.data.entity.Comment;
import com.jims.car.data.entity.user_auth.UserAuth;
import com.jims.car.service.dao.car.CarDAO;
import com.jims.car.service.dao.comment.CommentDAO;
import com.jims.car.service.dao.user_auth.UserAuthDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class CarApplication implements CommandLineRunner {

    @Autowired
    private CarDAO carDAO;

    @Autowired
    private CommentDAO commentDAO;

    @Autowired
    private UserAuthDAO userAuthDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(CarApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        UserAuth userAuth = new UserAuth();
        userAuth.setUsername("userTest@yopmail.com");
        userAuth.setPassword(passwordEncoder.encode("userTestPassword"));
        userAuth.setRole("ROLE_USER");
        userAuth = userAuthDAO.create(userAuth);

        List<Car> cars = new ArrayList<>();
        Car car = new Car();
        car.setModel("Sprinter 212D");
        car.setPhoto("https://www.truck1.fr/img/Bus_Minibus_MERCEDES_BENZ_Sprinter_212D-xxl-1530/1530_8847506456.jpg");
        cars.add(car);

        car = new Car();
        car.setModel("Sprinter 312D");
        car.setPhoto("https://www.truck1.fr/img/Vehicule_utilitaire_Fourgon_utilitaire_1999_Mercedes_Benz_SPRINTER_312D-xxl-8874/8874_4844332906222.jpg");
        cars.add(car);

        car = new Car();
        car.setModel("Sprinter 316CDI");
        car.setPhoto("https://www.truck1.co.ma/img/Vehicule_utilitaire_Mercedes_Benz_Sprinter_316_CDI_XXXL-ful-16991/16991_849972335427.jpg");
        cars.add(car);

        car = new Car();
        car.setModel("Sprinter 416CDI");
        car.setPhoto("https://st.mascus.com/imagetilewm/product/autocasiones/mercedes-benz-sprinter-416-me,3579787_1.jpg");
        cars.add(car);

        final UserAuth user = userAuth;
        cars.forEach(carObj -> {
            carObj = carDAO.save(carObj);
            Comment comment = new Comment();
            comment.setComment("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
            comment.setUser(user);
            comment.setCar(carObj);
            comment.setCreatedAt(LocalDateTime.now());
            commentDAO.save(comment);
        });
    }
}
