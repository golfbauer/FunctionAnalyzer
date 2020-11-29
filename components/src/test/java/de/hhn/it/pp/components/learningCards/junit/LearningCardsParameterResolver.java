package de.hhn.it.pp.components.learningCards.junit;

import de.hhn.it.pp.components.learningCards.Card;
import de.hhn.it.pp.components.learningCards.Cardset;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LearningCardsParameterResolver implements ParameterResolver {
    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        Parameter parameter = parameterContext.getParameter();

        return Objects.equals(parameter.getParameterizedType().getTypeName(),
                "java.util.Map<java.lang.String, de.hhn.it.pp.components.learningCards.Cardset>");
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        Map<String, Cardset> cardsets = new HashMap<>();
        Cardset cardset1 = new Cardset("Empty Cardset");

        Cardset cardset2 = new Cardset("Populations");
        Card card1 = new Card("Berlin", "Whats the population of Berlin?", "The population of Berlin is 3,562,000");
        Card card2 = new Card("Heilbronn", "Whats the population of Heilbronn?", "The population of Heilbronn is 120,000");
        cardset2.addCardtoSet(card1);
        cardset1.addCardtoSet(card2);

        Cardset cardset3 = new Cardset("Capitals");
        Card card3 = new Card("Germany", "Whats the Capital of Germany?", "Berlin is the capital of Germany");
        Card card4 = new Card("France", "Whats the Capital of France?", "Paris is the capital of France");
        Card card5 = new Card("United Kingdom", "Whats the Capital of United Kingdom?", "London is the capital of United Kingdom");
        Card card6 = new Card("China", "Whats the Capital of China?", "Beijing is the capital of China");
        Card card7 = new Card("Russia", "Whats the Capital of Russia?", "Moscow is the capital of Russia");
        cardset3.addCardtoSet(card3);
        cardset3.addCardtoSet(card4);
        cardset3.addCardtoSet(card5);
        cardset3.addCardtoSet(card6);
        cardset3.addCardtoSet(card7);

        cardsets.put(cardset1.getTitle(), cardset1);
        cardsets.put(cardset2.getTitle(), cardset2);
        cardsets.put(cardset3.getTitle(), cardset3);
        return cardsets;
    }
}
