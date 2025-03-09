package kr.huichan.bak.main.document;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import kr.huichan.bak.main.dto.NavbarMenu;

public record Navbar(
    @Id
    ObjectId id,
    NavbarMenu menu
    ) { }
