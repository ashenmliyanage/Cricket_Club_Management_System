package lk.ijse.util;

import javafx.scene.control.Alert;

import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

//import static com.sun.tools.doclint.Entity.image;

public class Validation {

    // Constructor and other methods...

    public static boolean MemberValid(String fullName,String bod,int age, String email,String address,InputStream image) {
        return
                isValidName(fullName) &&
                isValidBirthdate(bod) &&
                isValidAge(age) &&
                isValidEmail(email) &&
                isValidAddress(address) &&
                image != null; // Ensure image is not null
    }

    public static boolean MemberValid(String date,String DomainName,int Count) {
        String count = String.valueOf(Count);
        return
                isValidName(DomainName) &&
                        isValidBirthdate(date) &&
                        isValidCount(count);
    }

    private static boolean isValidAddress(String address) {
        return address != null && address.matches("[A-Z][a-zA-Z]*$");
    }

    private static boolean isValidName(String field) {
        String regex = "^[a-zA-Z]+(\\s[a-zA-Z]+)?(-[a-zA-Z]+)?$";

        // Check if the input matches the regex pattern
        return Pattern.matches(regex, field);
    }

    private static boolean isValidBirthdate(String bod) {
        // Define the regex pattern for a date in the format "YYYY-MM-DD"
        String regex = "^(\\d{4})-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$";

        // Check if the input matches the regex pattern
        return Pattern.matches(regex, bod);
    }

    private static boolean isValidAge(int age) {
        return age >= 0 && age <= 50;
    }

    public static boolean isValidEmail(String email) {
        return email != null && email.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
    }

    private static boolean isValidCount(String count) {
        return count != null && count.matches("\\d+");
    }

    public static boolean UserValid(String name, String address, String mail,int Age,InputStream inputStream) {
        return isValidName(name) &&
                isValidAddress(address) &&
                isValidEmail(mail) &&
                isValidAge(Age) &&
                inputStream != null;
    }
}
