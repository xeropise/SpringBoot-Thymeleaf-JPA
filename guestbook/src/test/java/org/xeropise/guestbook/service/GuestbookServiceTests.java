package org.xeropise.guestbook.service;

import com.querydsl.core.BooleanBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.xeropise.guestbook.dto.GuestbookDTO;
import org.xeropise.guestbook.dto.PageRequestDTO;
import org.xeropise.guestbook.dto.PageResultDTO;
import org.xeropise.guestbook.entity.Guestbook;

@SpringBootTest
public class GuestbookServiceTests {

    @Autowired
    private GuestbookService service;

    @Test
    public void testRegister() {

        GuestbookDTO guestbookDTO = GuestbookDTO.builder()
                                                .title("Sample Title...")
                                                .content("Sample Content...")
                                                .writer("user0")
                                                .build();
        System.out.println(service.register(guestbookDTO));
    }

    @Test
    public void testList() {

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(1).size(10).build();

        PageResultDTO<GuestbookDTO, Guestbook> resultDTO = service.getList(pageRequestDTO);

        System.out.println("PREV: "+resultDTO.isPrev());
        System.out.println("NEXT: "+resultDTO.isNext());
        System.out.println("TOTAL: "+resultDTO.getTotalPage());

        System.out.println("------------------------------");
        for (GuestbookDTO guestbookDTO : resultDTO.getDtoList()) {
            System.out.println(guestbookDTO);
        }

        System.out.println("==============================");
        resultDTO.getPageList().forEach(System.out::println);
    }

    @Test
    public void testSearch() {

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                                                      .page(1).size(10).type("tc").keyword("한글").build();

        PageResultDTO<GuestbookDTO, Guestbook> resultDTO = service.getList(pageRequestDTO);

        System.out.println("PREV: " +resultDTO.isPrev());
        System.out.println("NEXT: " +resultDTO.isNext());
        System.out.println("TOTAL: " +resultDTO.getTotalPage());

        System.out.println("-----------------------------------------------");
        for( GuestbookDTO guestbookDTO : resultDTO.getDtoList()) {
            System.out.println(guestbookDTO);
        }

        System.out.println("================================================");
        resultDTO.getPageList().forEach(System.out::println);

    }
}
