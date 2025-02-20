package kr.huichan.bak.main.dto;

import org.springframework.data.annotation.Id;

public record NavbarMenuDocument(
    @Id
    String id,
    NavbarMenu menu
    ) {
}
