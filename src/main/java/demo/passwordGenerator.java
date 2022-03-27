package demo;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class passwordGenerator {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPass = "Alcatel9!";
        String encodedPass =  encoder.encode(rawPass);
        System.out.println(encodedPass);
    }
}
