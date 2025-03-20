package egovframework.example.bbs.service;

import java.util.List;

import egovframework.example.bbs.model.BoardVO;

public interface BoardService {

	/**
	 * 글을 등록한다.
	 * @param vo - 등록할 정보가 담긴 BoardVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	String insertBoard(BoardVO vo) throws Exception;

	/**
	 * 글을 수정한다.
	 * @param vo - 수정할 정보가 담긴 BoardVO
	 * @return void형
	 * @exception Exception
	 */
	void updateBoard(BoardVO vo) throws Exception;

	/**
	 * 글을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 BoardVO
	 * @return void형
	 * @exception Exception
	 */
	void deleteBoard(BoardVO vo) throws Exception;

	/**
	 * 글을 조회한다.
	 * @param vo - 조회할 정보가 담긴 BoardVO
	 * @return 조회한 글
	 * @exception Exception
	 */
	BoardVO selectBoard(BoardVO vo) throws Exception;

	/**
	 * 글 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 글 목록
	 * @exception Exception
	 */
	List<?> selectBoardList(BoardVO vo) throws Exception;

	/**
	 * 글 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 글 총 갯수
	 * @exception
	 */
	int selectBoardListTotCnt(BoardVO vo);
	String selectLoginCheck(BoardVO vo);
	
	void insertReply(BoardVO vo) throws Exception;
	List<?> selectReplyList(BoardVO vo) throws Exception;
	
	void updateBoardCount(BoardVO vo) throws Exception;

/*	void insertAttach(BoardVO vo) throws Exception;
	void deleteAttach(BoardVO vo) throws Exception;	
	BoardVO selectAttach(BoardVO vo) throws Exception;*/
	
}
