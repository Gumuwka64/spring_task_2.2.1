package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      Car car1 = new Car("Mark", 2);
      Car car2 = new Car("BMW", 5);
      Car car3 = new Car("Ford", 6);
      Car car4 = new Car("Jeep", 1);

      User user1 = new User("Vasya", "Vasutkin", "user1@mail.io" );
      User user2 = new User("Vera", "Verova", "user2@mail.io" );
      User user3 = new User("Vlad", "Vladof", "user3@mail.io" );
      User user4 = new User("Valentina", "Valentinova", "user4@mail.io" );

      userService.add(user1.setCar(car2).setUser(user1));
      userService.add(user2.setCar(car1).setUser(user2));
      userService.add(user3.setCar(car3).setUser(user3));
      userService.add(user4.setCar(car4).setUser(user4));



      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println(user.toString()+"\n");
      }
      System.out.println("Trying to get user by car");
      System.out.println(userService.getUserByCar("Mark", 2));

      System.out.println("Trying to get user by not exist car");
      try {
         User user = userService.getUserByCar("Mark", 22);
      }catch (NoResultException e){
         System.err.println("No user found");

      }

      context.close();
   }
}
