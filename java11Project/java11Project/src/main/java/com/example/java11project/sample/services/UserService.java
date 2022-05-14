package com.example.java11project.sample.services;


import com.example.java11project.sample.exceptions.UsernameAlreadyExistsException;
import com.example.java11project.sample.users.User;
import javafx.collections.ObservableList;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;

import static com.example.java11project.sample.services.FileSistemService.getPathToFile;

public class UserService {

    private static ObjectRepository<User> userRepository;

    public static void initDatabase() {

        Nitrite database = Nitrite.builder()
                .filePath("MusicLessonsApp.db")
                .openOrCreate("test", "test");

        userRepository = database.getRepository(User.class);
    }

    public static void addUser(String username, String password, String role) throws UsernameAlreadyExistsException {
        checkUserDoesNotAlreadyExist(username);
        userRepository.insert(new User(username, encodePassword(username, password), role, "", "", "", "", "", -1));
    }

    public static List<User> getAllUsers() {

        return userRepository.find().toList();
    }

    public static void getprofesors(ObservableList<String> names) {
        try {
            for (User user : userRepository.find()) {
                if (Objects.equals("profesor", user.getRole())) {
                    names.add(user.getUsername());

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int checkStatus(String nume) {
        for (User user : userRepository.find()) {
            if (Objects.equals(user.getUsername(), nume)) {
                return user.getStatus();
            }
        }
        return -2;
    }


    public static void addProgramare(String nume, String programare) {
        for (User user : userRepository.find())
            if (Objects.equals(user.getUsername(), nume)) {
                user.setProgrmare(programare);
                user.setStatus(0);
                userRepository.update(user);

            }
    }


    public static int checkUser(String nume) {
        for (User user : userRepository.find())
            if (Objects.equals(user.getUsername(), nume)) {
                return 1;

            }
        return 0;
    }

    public static void setUserStatus(String nume, int s) {
        for (User user : userRepository.find())
            if (Objects.equals(user.getUsername(), nume)) {
                user.setStatus(s);
                userRepository.update(user);

            }
    }

    public static void deleteProgramare(String nume) {
        for (User user : userRepository.find())
            if (Objects.equals(user.getUsername(), nume)) {
                user.setProgrmare("");
                userRepository.update(user);

            }
    }


    public static void getPrices(ObservableList<String> prices) {
        try {
            for (User user : userRepository.find()) {
                if (Objects.equals("profesor", user.getRole())) {
                    prices.add(user.getPrice() + " ron");

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static String returnsDescription(String name) {
        for (User user : userRepository.find()) {
            if (Objects.equals(user.getUsername(), name)) {
                return user.getDescription();
            }
        }
        return null;
    }

   /* public static String returnsReview(String name){
        for(User user: userRepository.find()){
            if(Objects.equals(user.getUsername(),name)){
                return user.getReview();
            }
        }
        return "no reviews yet";
    }

    */

    public static String returnsProgramare(String name) {
        for (User user : userRepository.find()) {
            if (Objects.equals(user.getUsername(), name) && user.getProgramare().length() != 0) {
                return user.getProgramare();
            }
        }
        return "no requests yet";
    }

    public static String returnsReviews(String name) {
        for (User user : userRepository.find()) {
            if (Objects.equals(user.getUsername(), name) && user.getReview().length() != 0) {
                return user.getReview();
            }
        }
        return "no reviews yet";
    }


    public static String returnsPrice(String name) {
        for (User user : userRepository.find()) {
            if (Objects.equals(user.getUsername(), name)) {
                return user.getPrice();
            }
        }
        return null;
    }

    public static String returnsMountain(String name) {
        for (User user : userRepository.find()) {
            if (Objects.equals(user.getUsername(), name)) {
                return user.getInstrument();
            }
        }
        return null;
    }


    public static int validateLogin(String username, String password) {
        for (User user : userRepository.find()) {
            if (Objects.equals(username, user.getUsername())) {
                String pass = encodePassword(username, password);

                if (Objects.equals(user.getPassword(), pass)) {
                    if (Objects.equals(user.getRole(), "Profesor")) {
                        return 1;

                    }
                    if (Objects.equals(user.getRole(), "Client")) {
                        return 2;
                    }
                }
            }
        }
        return 0;

    }

    private static void checkUserDoesNotAlreadyExist(String username) throws UsernameAlreadyExistsException {
        for (User user : userRepository.find()) {
            if (Objects.equals(username, user.getUsername()))
                throw new UsernameAlreadyExistsException(username);
        }
    }

    public static int modifyUserInfo(String name, String password, String p, String d, String i) {
        for (User user : userRepository.find()) {
            if (Objects.equals(name, user.getUsername())) {
                String pass = encodePassword(name, password);
                if (Objects.equals(pass, user.getPassword())) {
                    user.setUsername(name);
                    user.setPassword(pass);
                    user.setPrice(p);
                    user.setInstrument(i);
                    user.setDescription(d);
                    userRepository.update(user);
                    return 1;

                }

            }
        }
        return 0;

    }

    public static void modifyUserReview(String name, String review) {
        for (User user : userRepository.find()) {
            if (Objects.equals(name, user.getUsername())) {

                user.setReview(user.getReview() + " " + review + "\n");
                userRepository.update(user);


            }
        }

    }


    /*private*/
    static String encodePassword(String salt, String password) {
        MessageDigest md = getMessageDigest();
        md.update(salt.getBytes(StandardCharsets.UTF_8));

        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));

        // This is the way a password should be encoded when checking the credentials
        return new String(hashedPassword, StandardCharsets.UTF_8)
                .replace("\"", ""); //to be able to save in JSON format
    }

    private static MessageDigest getMessageDigest() {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-512 does not exist!");
        }
        return md;
    }


}
