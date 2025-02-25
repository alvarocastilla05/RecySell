package com.example.Recysell.util;

public record SearchCriteria(
        String key,
        String operation,
        Object value
) {
}
