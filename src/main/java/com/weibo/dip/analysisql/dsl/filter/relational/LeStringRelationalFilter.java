package com.weibo.dip.analysisql.dsl.filter.relational;

import com.weibo.dip.analysisql.dsl.filter.Filter;

/** @author yurun */
public class LeStringRelationalFilter extends StringRelationalFilter {
  public LeStringRelationalFilter(String name, String value) {
    super(Filter.LE, name, value);
  }
}
