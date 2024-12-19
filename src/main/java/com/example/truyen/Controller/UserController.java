package com.example.truyen.Controller;

import com.example.truyen.DTO.ComicFormDto;
import com.example.truyen.Entity.*;
import com.example.truyen.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {
    @Autowired
    private ComicService comicService;

    @Autowired
    private ChapterService chapterService;

    @Autowired
    private ChapterPageService chapterPageService;

    @Autowired
    private ComicCategoryService comicCategoryService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private ComicStatusService comicStatusService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/user/userProfile")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_CONTRIBUTOR', 'ROLE_USER')")
    @ResponseBody
    public String userProfile() {
        return "Welcome to User Profile";
    }

    @GetMapping("/contributor/contributorProfile")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_CONTRIBUTOR')")
    @ResponseBody
    public String contributorProfile() {
        return "Welcome to Contributor Profile";
    }

    @GetMapping("/contributor/upload-comic")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_CONTRIBUTOR')")
    public String showUploadComicForm(Model model) {
        model.addAttribute("comicForm", new ComicFormDto());
        model.addAttribute("categories", categoryService.getAllCategory());
        model.addAttribute("countries", countryService.getAllCountry());
        model.addAttribute("comicStatus", comicStatusService.getAllComicStatus());
        return "admin/upload-comic";
    }

    @PostMapping("/contributor/upload-comic")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_CONTRIBUTOR')")
    public String addNewComic(@ModelAttribute("comicForm") ComicFormDto comicForm) {
        comicService.addComic(comicForm.getComic());
        comicCategoryService.addComicCategories(comicForm.getSelectedComicCategories(), comicForm.getComic());
        return "redirect:/contributor/contributorProfile";
    }

    @GetMapping("/admin/adminProfile")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String adminProfile() {
        return "admin/index";
    }

    @GetMapping("/contributor/comic")
    public String listComics(Model model) {
        List<Comic> comics = comicService.getAllComics();
        model.addAttribute("comics", comics);
        return "admin/list-comic";
    }

    @GetMapping("/contributor/comic/edit/{comicId}")
    public String editChapters(@PathVariable Long comicId, Model model) {
        Comic comic = comicService.getComicById(comicId);
        List<Chapter> chapters = chapterService.getChaptersByComicId(comicId);
        model.addAttribute("comic", comic);
        model.addAttribute("chapters", chapters);
        return "admin/edit-chapters";
    }

    @PostMapping("/contributor/comic/delete/{comicId}/chapter-id-{id}")
    public String deleteChapter(@PathVariable Long id) {
        chapterService.deleteChapterById(id);
        return "redirect:/contributor/comic/edit/{comicId}";
    }

    @GetMapping("/contributor/comic/add/{comicId}")
    public String addChapter(@PathVariable Long comicId, Model model) {
        Comic comic = comicService.getComicById(comicId);
        model.addAttribute("comic", comic);
        return "admin/add-chapter";
    }

    @PostMapping("/contributor/comic/add/save")
    public String saveChapter(
            @RequestParam Long comicId,
            @RequestParam Integer chapterNumber,
            @RequestParam String title,
            @RequestParam String imageLinks) {
        // Tìm truyện bằng comicId
        Comic comic = comicService.getComicById(comicId);

        // Tạo chương mới
        Chapter newChapter = new Chapter();
        newChapter.setComic(comic);
        newChapter.setChapterNumber(chapterNumber);
        newChapter.setTitle(title);
        chapterService.saveChapter(newChapter);

        // Tách danh sách URL từ textarea
        String[] urls = imageLinks.split("\\r?\\n");

        // Lưu từng URL vào ChapterPage
        int pageNumber = 1;
        for (String url : urls) {
            if (!url.trim().isEmpty()) { // Bỏ qua dòng trống
                ChapterPage chapterPage = new ChapterPage();
                chapterPage.setChapter(newChapter);
                chapterPage.setPageNumber(pageNumber++);
                chapterPage.setLink(url.trim());
                chapterPageService.saveChapterPage(chapterPage);
            }
        }

        return "redirect:/contributor/comic/edit/" + comicId; // Quay lại trang chỉnh sửa chương
    }
}
