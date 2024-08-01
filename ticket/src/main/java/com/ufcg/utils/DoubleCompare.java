package com.ufcg.utils;

public class DoubleCompare {
  static double eps = 1e-6;

  public static boolean equals(double a, double b) {
    return Math.abs(a - b) <= eps;
  }
}
