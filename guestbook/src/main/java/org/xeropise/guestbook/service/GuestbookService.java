package org.xeropise.guestbook.service;

import org.xeropise.guestbook.dto.GuestbookDTO;
import org.xeropise.guestbook.dto.PageRequestDTO;
import org.xeropise.guestbook.dto.PageResultDTO;
import org.xeropise.guestbook.entity.Guestbook;

public interface GuestbookService {

    Long register(GuestbookDTO dto);

    PageResultDTO<GuestbookDTO, Guestbook> getList(PageRequestDTO requestDTO);

    GuestbookDTO read(Long gno);

    void remove(Long gno);

    void modify(GuestbookDTO dto);

    default Guestbook dtoToEntity(GuestbookDTO dto) {
        Guestbook entity = Guestbook.builder()
                                    .gno(dto.getGno())
                                    .title(dto.getTitle())
                                    .content(dto.getContent())
                                    .writer(dto.getWriter())
                                    .build();
        return entity;
    }

    default GuestbookDTO entityToDto(Guestbook entity) {

        GuestbookDTO dto = GuestbookDTO.builder()
                                       .gno(entity.getGno())
                                       .title(entity.getTitle())
                                       .content(entity.getContent())
                                       .writer(entity.getWriter())
                                       .regDate(entity.getRegDate())
                                       .modDate(entity.getModDate())
                                        .build();

        return dto;
    }
}
