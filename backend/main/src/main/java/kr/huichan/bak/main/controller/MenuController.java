package kr.huichan.bak.main.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.huichan.bak.main.dto.NavbarMenu;
import kr.huichan.bak.main.service.NavbarService;

@RestController
@RequestMapping("/api/v1/menu")
@Tag(name = "Menu", description = "메뉴")
public class MenuController {

    private final NavbarService navbarService;
    
    @GetMapping()
    @Operation(summary = "메뉴 요청")
    public ResponseEntity<NavbarMenu> getMenu () {

        return ResponseEntity.ok(navbarService.getNavbarMenu());
    }

    @PostMapping()
    @Operation(summary = "메뉴 수정")
    public ResponseEntity<Void> updateMenu(@RequestBody NavbarMenu navbarMenuRequest) {

        navbarService.updateNavbarMenu(navbarMenuRequest);

        return ResponseEntity.status(HttpStatus.RESET_CONTENT).build();
    }

    @DeleteMapping()
    @Operation(summary = "메뉴 초기화")
    public ResponseEntity<Void> initialMenu() {

        navbarService.initialNavbar();

        return ResponseEntity.status(HttpStatus.RESET_CONTENT).build();
    }

    // 생성자 주입
    public MenuController(NavbarService navbarService) {
        this.navbarService = navbarService;
    }
}
