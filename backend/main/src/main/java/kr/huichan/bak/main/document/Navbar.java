package kr.huichan.bak.main.document;

import org.springframework.data.annotation.Id;

import kr.huichan.bak.main.dto.NavbarMenu;

public record Navbar(
    @Id
    String id,
    NavbarMenu menu
    ) {
}
