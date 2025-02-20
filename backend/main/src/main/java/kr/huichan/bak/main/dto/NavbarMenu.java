package kr.huichan.bak.main.dto;

public record NavbarMenu(
    String name,
    String url,
    NavbarMenu[] sub
    ) {
}
