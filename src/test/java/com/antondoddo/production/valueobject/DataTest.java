package com.antondoddo.production.valueobject;

import static org.junit.Assert.assertTrue;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public final class DataTest {

  protected static Object[] data() {
    return new Object[]{
            new Object[]{true},
            new Object[]{true},
            new Object[]{true},
    };
  }

  @Test
  @Parameters(method = "data")
  public void shouldCreateMovieWithoutAlteringProperties(boolean val) {
    assertTrue(val);
  }
}