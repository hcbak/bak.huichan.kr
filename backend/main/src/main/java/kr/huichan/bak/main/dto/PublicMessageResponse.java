package kr.huichan.bak.main.dto;

import java.time.LocalDateTime;

public record PublicMessageResponse(
    String name,
    String message,
    LocalDateTime time
) { }
