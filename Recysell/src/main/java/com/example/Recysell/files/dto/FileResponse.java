package com.example.Recysell.files.dto;

public record FileResponse(
        String id,
        String name,
        String uri,
        String type,
        long size
) {
}
