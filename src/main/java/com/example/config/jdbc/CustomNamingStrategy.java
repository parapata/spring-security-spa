package com.example.config.jdbc;

import org.springframework.data.jdbc.mapping.model.DefaultNamingStrategy;
import org.springframework.data.jdbc.mapping.model.JdbcPersistentProperty;

import com.example.util.StringUtil;

public class CustomNamingStrategy extends DefaultNamingStrategy {

    @Override
    public String getColumnName(JdbcPersistentProperty property) {
        String propertyName = property.getName();
        return StringUtil.snakelize(propertyName).toLowerCase();
    }

    @Override
    public String getTableName(Class<?> type) {
        String tableName = super.getTableName(type);
        return StringUtil.snakelize(tableName).toLowerCase();
    }
}
