package de.hhn.it.pp.components.example.coffeemakerservice.junit;

import de.hhn.it.pp.components.example.coffeemakerservice.CoffeeMakerListener;
import de.hhn.it.pp.components.example.coffeemakerservice.State;

public class DummyCallback implements CoffeeMakerListener {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(DummyCallback.class);

  @Override
  public void newState(final State state) {
    logger.info("Callback called with state " + state);
  }
}
