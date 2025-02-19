package kr.huichan.bak.main.service;

import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import kr.huichan.bak.main.dto.NavbarMenu;
import kr.huichan.bak.main.dto.NavbarMenuDocument;
import kr.huichan.bak.main.repository.NavbarRepository;

@Service
public class NavbarService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final NavbarRepository navbarRepository;

    // 네비게이션바 요청
    public NavbarMenu getNavbarMenu() {
        
        NavbarMenu navbarMenu = null;

        try {
            navbarMenu = navbarRepository.findAll().stream().findFirst().get().menu();
        } catch (NoSuchElementException e) {
            log.info("Collection is nof found.");
            navbarMenu = initialNavbar();
        }

        return navbarMenu;
    }

    // 네비게이션바 초기화
    public NavbarMenu initialNavbar() {
        log.info("Inserting initial data.");

        navbarRepository.insert(new NavbarMenuDocument(
            null, 
            new NavbarMenu("root", null, null)));

        return navbarRepository.findAll().stream().findFirst().get().menu();
    }

    // 네비게이션바 수정
    public void updateNavbarMenu(NavbarMenu navbarMenuRequest) {
        log.info("Updating Navigation Bar.");

        NavbarMenuDocument navbarMenu = navbarRepository.findAll().stream().findFirst().get();

        navbarMenu = new NavbarMenuDocument(
            navbarMenu.id(),
            navbarMenuRequest);

        navbarRepository.save(navbarMenu);
    }

    public NavbarService(NavbarRepository navbarRepository) {
        this.navbarRepository = navbarRepository;
    }
}
