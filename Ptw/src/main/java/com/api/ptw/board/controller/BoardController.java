package com.api.ptw.board.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.ptw.board.service.BoardService;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/apis")
public class BoardController {
	
	@Resource
	private BoardService boardService;
	
	@SuppressWarnings("unchecked")
	@GetMapping(path = "/boardlist")
	public Object getBoardList(HttpSession session) {
		Map<String, Object> userInfo = new HashMap<String, Object>();
		
		try {
			userInfo = (Map<String,Object>)session.getAttribute("session");
			List<Map<String, Object>> boardList = boardService.getBoardList(userInfo);
			System.out.println(boardList);
			return boardList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping(path = "/create/board")
	public Object createBoard(HttpSession session,
							@RequestParam Map<String, Object> paramMap) {
		
		Map<String, Object> userInfo = new HashMap<String, Object>();
		Map<String, Object> resObj = new HashMap<String, Object>();
		try {
			
			userInfo = (Map<String,Object>)session.getAttribute("session");
			
			Map<String, Object> boardInput = new HashMap<>();
			boardInput.put("member_no", userInfo.get("member_no"));
			boardInput.put("title", paramMap.get("title"));
			
			boardService.createBoard(boardInput);
			
			// 최근 보드 넘버 **************cards_list 초기데이터
			Map<String, Object> lastBoardNo = boardService.getLastBoard(boardInput);
			System.out.println(lastBoardNo);
			
			List<String> initialTitle = new ArrayList<>();
			
			initialTitle.add("Do");
			initialTitle.add("Done");
			initialTitle.add("Hold");
			
			List<Map<String, Object>> initialData = new ArrayList<>();
			
			for (String title: initialTitle) {
				Map<String, Object> data = new HashMap<>();
				
				data.put("board_no", lastBoardNo.get("last_board_no"));
				data.put("title", title);
				initialData.add(data);
			}
			
			System.out.println(initialData);
			
			boardService.initialCardsList(initialData);
			
			// **********card 초기데이터
			// **********GET cardListNo
			Map<String, Object> cardParamMap = new HashMap<String, Object>();
			cardParamMap.put("board_no", lastBoardNo.get("last_board_no"));
			cardParamMap.put("title", "Do");
			
			Map<String, Object> cardListNo = boardService.getCardListNo(cardParamMap);
			
			// **********Insert card
			
			List<String> initialContents = new ArrayList<>();
			
			initialContents.add("Do 1");
			initialContents.add("Do 2");
			initialContents.add("Do 3");
			
			List<Map<String, Object>> initialCardData = new ArrayList<>();
			
			for (String content: initialContents) {
				Map<String, Object> data = new HashMap<>();
				
				data.put("contents", content);
				data.put("card_list_no", cardListNo.get("cards_list_no"));
				data.put("board_no", lastBoardNo.get("last_board_no"));
				
				initialCardData.add(data);
			}
			
			boardService.initialCard(initialCardData);
			
			resObj.put("code", HttpStatus.OK.value());
			
			return resObj;
		} catch (Exception e) {
			e.printStackTrace();
			
			resObj.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
			
			return resObj;
		}
	
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping(path = "/cardlist/{board_no}")
	public Object getCardList(HttpSession session,
							@PathVariable("board_no") String board_no) {
		
		Map<String, Object> userInfo = new HashMap<String, Object>();
		
		try {
			userInfo = (Map<String,Object>)session.getAttribute("session");
					
			List<Map<String, Object>> cardList = boardService.getCardList(board_no);
			
			return cardList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping(path = "/card/{board_no}")
	public Object getCard(HttpSession session,
							@PathVariable("board_no") String board_no) {
		
		Map<String, Object> userInfo = new HashMap<String, Object>();
		
		try {
			userInfo = (Map<String,Object>)session.getAttribute("session");
					
			List<Map<String, Object>> card = boardService.getCard(board_no);
			
			return card;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	
	}
	
	@PutMapping(path = "/card/change")
	public @ResponseBody Object doCardChange(@RequestBody Map<String, Object> paramMap) {
		
		Map<String, Object> resObj = new HashMap<String, Object>();
		
		try {
			
			boardService.updateCard(paramMap);
			
			
			return resObj;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// 추후삭제
	@SuppressWarnings("unchecked")
	@GetMapping(path = "/board/data/{board_no}")
	public Object getBoardData(HttpSession session,
							@PathVariable("board_no") int board_no) {
		
		Map<String, Object> userInfo = new HashMap<String, Object>();
		
		try {
			userInfo = (Map<String,Object>)session.getAttribute("session");
			
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("board_no", board_no);
			
			return boardService.getCount(paramMap);
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	
	}
	
	// 추후삭제
	@PutMapping(path = "/put/count")
	public @ResponseBody Object doUpdateCount(@RequestBody Map<String, Object> paramMap) {
		
		Map<String, Object> resObj = new HashMap<String, Object>();
		
		try {
			
			System.out.println(paramMap);
			
			boardService.setCount(paramMap);
			
			resObj.put("msg", "업데이트 완료");
			
			return resObj;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	
}
