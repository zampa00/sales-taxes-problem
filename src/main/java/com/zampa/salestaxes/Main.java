package com.zampa.salestaxes;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        
        if (args.length == 0) {
            System.out.println("Missing argument: text file with the basket.");
            return;
        }

        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(args[0]), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        }
        catch (IOException e) {
            System.out.println("Error opening file: " + args[0]);
        }

        System.out.println("INPUT:");
        System.out.println(contentBuilder.toString());

        Basket basket = Basket.build(contentBuilder.toString());

        System.out.println("OUTPUT:");
        System.out.println(basket.toString());
    }

}
