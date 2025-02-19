package kr.huichan.bak.main.controller.frame;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

    public MenuController(NavbarService navbarService) {
        this.navbarService = navbarService;
    }
}
