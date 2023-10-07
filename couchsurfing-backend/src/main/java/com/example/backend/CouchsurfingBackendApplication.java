package com.example.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CouchsurfingBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(CouchsurfingBackendApplication.class, args);
    }

   /* @Bean
    CommandLineRunner runner(UserRepository userRepository, TripRepository tripRepository) {
        return args -> {
            User user = new User("Test login", "test@gmail.com",
                    "password123213-ascouib", LocalDateTime.now(), false,
                    List.of(Authority.ROLE_ADMIN, Authority.ROLE_USER), 9.8, null, null, List.of(), List.of());

            User user1 = new User("Test login3", "test3232@gmail.com",
                    "password123213-ascouidsb", LocalDateTime.now(), false,
                    List.of(Authority.ROLE_ADMIN), 9.8, null, null, List.of(), List.of());

            User u1 = userRepository.insert(user);
            User u2 = userRepository.insert(user1);

            Review review = new Review(ObjectId.get(), u2, true,
                    ServiceType.SURFER, 10.0, "Cool", LocalDateTime.now());

            Trip trip = new Trip(u1, LocalDateTime.now(), LocalDateTime.now(),
                    1, "Kharkiv, Ukraine", "Message");

            tripRepository.insert(trip);

            u1.setTrips(List.of(trip));
            u1.setReviews(List.of(review));

            userRepository.save(u1);


            List<User> user = userRepository.findAll();

            user.getFirst().getReviews().forEach(System.out::println);

            List<Trip> trips = tripRepository.findAllByLocation("Kharkiv, Ukraine",
                    PageRequest.of(0, 1)).getContent();

            trips.forEach(System.out::println);

        };
    }*/
}
