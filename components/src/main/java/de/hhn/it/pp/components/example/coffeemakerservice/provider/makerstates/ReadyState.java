package de.hhn.it.pp.components.example.coffeemakerservice.provider.makerstates;

import de.hhn.it.pp.components.example.coffeemakerservice.State;
import de.hhn.it.pp.components.example.coffeemakerservice.provider.WnckCoffeeMaker;

public class ReadyState extends MakerState {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(ReadyState.class);

  public ReadyState(final WnckCoffeeMaker maker) {
    super(maker);
    state = State.READY;
  }

  @Override
  public void onSwitchOn() throws IllegalStateException {
    throw new IllegalStateException("Already switched on.");
  }

  @Override
  public void onSwitchOff() throws IllegalStateException {
    maker.setMakerState(new SwitchOffState(maker));
  }

  @Override
  public void onBrew() throws IllegalStateException {
    maker.setMakerState(new BrewingState(maker));
  }

  @Override
  public void onCleanIt() throws IllegalStateException {
    maker.setMakerState(new CleaningState(maker));
  }
}
