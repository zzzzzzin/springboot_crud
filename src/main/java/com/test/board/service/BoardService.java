package com.test.board.service;

import com.test.board.dto.BoardDTO;
import com.test.board.entity.BoardEntity;
import com.test.board.repository.BoardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//DTO -> Entity (Entity Class)
//DTO <- Entity (DTO Class)

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public void save(BoardDTO boardDTO) {

        BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDTO);
        boardRepository.save(boardEntity);

    }

    public List<BoardDTO> findAll() {
        List<BoardEntity> boardEntities = boardRepository.findAll();
        List<BoardDTO> boardDTOList = new ArrayList<>();
        for (BoardEntity boardEntity : boardEntities) {
            boardDTOList.add(BoardDTO.toBoardDTO(boardEntity));
        }
        return boardDTOList;
    }

    @Transactional //jpa에서 제공하지 않는 메서드를 사용 > 작성 필수
    public void updateHits(Long id) {
        boardRepository.updateHits(id);
    }

    public BoardDTO findById(Long id) {
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(id);
        if (optionalBoardEntity.isPresent()) {
            BoardEntity boardEntity = optionalBoardEntity.get();
            BoardDTO boardDTO = BoardDTO.toBoardDTO(boardEntity);
            return boardDTO;
        } else {
            return null;
        }

    }
}
