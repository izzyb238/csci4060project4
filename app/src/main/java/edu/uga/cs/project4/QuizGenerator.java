package edu.uga.cs.project4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class QuizGenerator {

    private static final int QUIZ_SIZE = 6;
    private static final int OPTION_COUNT = 3;

    private QuizGenerator() {}


    public static Quiz generateQuiz(List<Country> allCountries) {
        ArrayList<Country> countries = new ArrayList<>(allCountries);
        Collections.shuffle(countries);

        ArrayList<Question> questions = new ArrayList<>();
        for (int i = 0; i < Math.min(QUIZ_SIZE, countries.size()); i++) {
            Country country = countries.get(i);
            questions.add(new Question(country, buildOptions(country, allCountries)));
        }
        return new Quiz(questions);
    }

    private static ArrayList<String> buildOptions(Country correctCountry, List<Country> allCountries) {
        HashSet<String> options = new HashSet<>();
        options.add(correctCountry.getCapitalCity());

        while (options.size() < OPTION_COUNT) {
            Country randomCountry = allCountries.get(
                    ThreadLocalRandom.current().nextInt(allCountries.size())
            );
            options.add(randomCountry.getCapitalCity());
        }

        ArrayList<String> list = new ArrayList<>(options);
        Collections.shuffle(list);
        return list;
    }

}
